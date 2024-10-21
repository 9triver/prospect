import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ArchivesService from './archives.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import HrManagementService from '@/entities/hr-management/hr-management.service';
import { type IHrManagement } from '@/shared/model/hr-management.model';
import { type IArchives, Archives } from '@/shared/model/archives.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ArchivesUpdate',
  setup() {
    const archivesService = inject('archivesService', () => new ArchivesService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const archives: Ref<IArchives> = ref(new Archives());

    const hrManagementService = inject('hrManagementService', () => new HrManagementService());

    const hrManagements: Ref<IHrManagement[]> = ref([]);
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveArchives = async archivesId => {
      try {
        const res = await archivesService().find(archivesId);
        archives.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.archivesId) {
      retrieveArchives(route.params.archivesId);
    }

    const initRelationships = () => {
      hrManagementService()
        .retrieve()
        .then(res => {
          hrManagements.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      title: {},
      content: {},
      date: {},
      page: {},
      secretlevel: {},
      confidentialityperiod: {},
      confidentialnumber: {},
      storageperiod: {},
      plannumber: {},
      remarks: {},
      receivingnumber: {},
      responsibleid: {},
    };
    const v$ = useVuelidate(validationRules, archives as any);
    v$.value.$validate();

    return {
      archivesService,
      alertService,
      archives,
      previousState,
      secretlevelValues,
      isSaving,
      currentLanguage,
      hrManagements,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.archives.id) {
        this.archivesService()
          .update(this.archives)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.archives.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.archivesService()
          .create(this.archives)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.archives.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
