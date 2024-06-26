import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ResourcemanagementService from './resourcemanagement.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ResourcemanagementWbsService from '@/entities/resourcemanagement-wbs/resourcemanagement-wbs.service';
import { type IResourcemanagementWbs } from '@/shared/model/resourcemanagement-wbs.model';
import { type IResourcemanagement, Resourcemanagement } from '@/shared/model/resourcemanagement.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ResourcemanagementUpdate',
  setup() {
    const resourcemanagementService = inject('resourcemanagementService', () => new ResourcemanagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const resourcemanagement: Ref<IResourcemanagement> = ref(new Resourcemanagement());

    const resourcemanagementWbsService = inject('resourcemanagementWbsService', () => new ResourcemanagementWbsService());

    const resourcemanagementWbs: Ref<IResourcemanagementWbs[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveResourcemanagement = async resourcemanagementId => {
      try {
        const res = await resourcemanagementService().find(resourcemanagementId);
        resourcemanagement.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.resourcemanagementId) {
      retrieveResourcemanagement(route.params.resourcemanagementId);
    }

    const initRelationships = () => {
      resourcemanagementWbsService()
        .retrieve()
        .then(res => {
          resourcemanagementWbs.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      name: {},
      description: {},
      starttime: {},
      endtime: {},
      wbs: {},
    };
    const v$ = useVuelidate(validationRules, resourcemanagement as any);
    v$.value.$validate();

    return {
      resourcemanagementService,
      alertService,
      resourcemanagement,
      previousState,
      isSaving,
      currentLanguage,
      resourcemanagementWbs,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.resourcemanagement.id) {
        this.resourcemanagementService()
          .update(this.resourcemanagement)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.resourcemanagement.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.resourcemanagementService()
          .create(this.resourcemanagement)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.resourcemanagement.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
