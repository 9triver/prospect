import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ProgressmanagementService from './progressmanagement.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ProgressmanagementWbsService from '@/entities/progressmanagement-wbs/progressmanagement-wbs.service';
import { type IProgressmanagementWbs } from '@/shared/model/progressmanagement-wbs.model';
import { type IProgressmanagement, Progressmanagement } from '@/shared/model/progressmanagement.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProgressmanagementUpdate',
  setup() {
    const progressmanagementService = inject('progressmanagementService', () => new ProgressmanagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const progressmanagement: Ref<IProgressmanagement> = ref(new Progressmanagement());

    const progressmanagementWbsService = inject('progressmanagementWbsService', () => new ProgressmanagementWbsService());

    const progressmanagementWbs: Ref<IProgressmanagementWbs[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveProgressmanagement = async progressmanagementId => {
      try {
        const res = await progressmanagementService().find(progressmanagementId);
        progressmanagement.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.progressmanagementId) {
      retrieveProgressmanagement(route.params.progressmanagementId);
    }

    const initRelationships = () => {
      progressmanagementWbsService()
        .retrieve()
        .then(res => {
          progressmanagementWbs.value = res.data;
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
    const v$ = useVuelidate(validationRules, progressmanagement as any);
    v$.value.$validate();

    return {
      progressmanagementService,
      alertService,
      progressmanagement,
      previousState,
      isSaving,
      currentLanguage,
      progressmanagementWbs,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.progressmanagement.id) {
        this.progressmanagementService()
          .update(this.progressmanagement)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.progressmanagement.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.progressmanagementService()
          .create(this.progressmanagement)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.progressmanagement.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
