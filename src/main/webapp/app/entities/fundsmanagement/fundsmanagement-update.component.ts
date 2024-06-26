import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import FundsmanagementService from './fundsmanagement.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import FundsmanagementWbsService from '@/entities/fundsmanagement-wbs/fundsmanagement-wbs.service';
import { type IFundsmanagementWbs } from '@/shared/model/fundsmanagement-wbs.model';
import { type IFundsmanagement, Fundsmanagement } from '@/shared/model/fundsmanagement.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'FundsmanagementUpdate',
  setup() {
    const fundsmanagementService = inject('fundsmanagementService', () => new FundsmanagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const fundsmanagement: Ref<IFundsmanagement> = ref(new Fundsmanagement());

    const fundsmanagementWbsService = inject('fundsmanagementWbsService', () => new FundsmanagementWbsService());

    const fundsmanagementWbs: Ref<IFundsmanagementWbs[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveFundsmanagement = async fundsmanagementId => {
      try {
        const res = await fundsmanagementService().find(fundsmanagementId);
        fundsmanagement.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.fundsmanagementId) {
      retrieveFundsmanagement(route.params.fundsmanagementId);
    }

    const initRelationships = () => {
      fundsmanagementWbsService()
        .retrieve()
        .then(res => {
          fundsmanagementWbs.value = res.data;
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
    const v$ = useVuelidate(validationRules, fundsmanagement as any);
    v$.value.$validate();

    return {
      fundsmanagementService,
      alertService,
      fundsmanagement,
      previousState,
      isSaving,
      currentLanguage,
      fundsmanagementWbs,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.fundsmanagement.id) {
        this.fundsmanagementService()
          .update(this.fundsmanagement)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.fundsmanagement.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.fundsmanagementService()
          .create(this.fundsmanagement)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.fundsmanagement.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
