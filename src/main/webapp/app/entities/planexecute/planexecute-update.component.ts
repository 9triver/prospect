import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import PlanexecuteService from './planexecute.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import PlanreturnsService from '@/entities/planreturns/planreturns.service';
import { type IPlanreturns } from '@/shared/model/planreturns.model';
import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import { type IPlanexecute, Planexecute } from '@/shared/model/planexecute.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PlanexecuteUpdate',
  setup() {
    const planexecuteService = inject('planexecuteService', () => new PlanexecuteService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const planexecute: Ref<IPlanexecute> = ref(new Planexecute());

    const planreturnsService = inject('planreturnsService', () => new PlanreturnsService());

    const planreturns: Ref<IPlanreturns[]> = ref([]);

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrievePlanexecute = async planexecuteId => {
      try {
        const res = await planexecuteService().find(planexecuteId);
        planexecute.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.planexecuteId) {
      retrievePlanexecute(route.params.planexecuteId);
    }

    const initRelationships = () => {
      planreturnsService()
        .retrieve()
        .then(res => {
          planreturns.value = res.data;
        });
      officersService()
        .retrieve()
        .then(res => {
          officers.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      planname: {},
      planstarttime: {},
      planendtime: {},
      planreturns: {},
      responsibleid: {},
    };
    const v$ = useVuelidate(validationRules, planexecute as any);
    v$.value.$validate();

    return {
      planexecuteService,
      alertService,
      planexecute,
      previousState,
      isSaving,
      currentLanguage,
      planreturns,
      officers,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.planexecute.id) {
        this.planexecuteService()
          .update(this.planexecute)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster3App.planexecute.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.planexecuteService()
          .create(this.planexecute)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster3App.planexecute.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
