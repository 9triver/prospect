import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import CommunicationPlanService from './communication-plan.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import { type ICommunicationPlan, CommunicationPlan } from '@/shared/model/communication-plan.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CommunicationPlanUpdate',
  setup() {
    const communicationPlanService = inject('communicationPlanService', () => new CommunicationPlanService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const communicationPlan: Ref<ICommunicationPlan> = ref(new CommunicationPlan());

    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());

    const projectwbs: Ref<IProjectwbs[]> = ref([]);
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveCommunicationPlan = async communicationPlanId => {
      try {
        const res = await communicationPlanService().find(communicationPlanId);
        communicationPlan.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.communicationPlanId) {
      retrieveCommunicationPlan(route.params.communicationPlanId);
    }

    const initRelationships = () => {
      projectwbsService()
        .retrieve()
        .then(res => {
          projectwbs.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      wbsid: {},
      communicationtopic: {},
      communicationtime: {},
      worktarget: {},
      workcontent: {},
      remarks: {},
      auditStatus: {},
      projectwbs: {},
    };
    const v$ = useVuelidate(validationRules, communicationPlan as any);
    v$.value.$validate();

    return {
      communicationPlanService,
      alertService,
      communicationPlan,
      previousState,
      auditStatusValues,
      isSaving,
      currentLanguage,
      projectwbs,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.communicationPlan.id) {
        this.communicationPlanService()
          .update(this.communicationPlan)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.communicationPlan.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.communicationPlanService()
          .create(this.communicationPlan)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.communicationPlan.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
