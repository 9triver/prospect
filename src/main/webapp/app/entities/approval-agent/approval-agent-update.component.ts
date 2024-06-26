import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ApprovalAgentService from './approval-agent.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import { type IApprovalAgent, ApprovalAgent } from '@/shared/model/approval-agent.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ApprovalAgentUpdate',
  setup() {
    const approvalAgentService = inject('approvalAgentService', () => new ApprovalAgentService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const approvalAgent: Ref<IApprovalAgent> = ref(new ApprovalAgent());

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveApprovalAgent = async approvalAgentId => {
      try {
        const res = await approvalAgentService().find(approvalAgentId);
        approvalAgent.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.approvalAgentId) {
      retrieveApprovalAgent(route.params.approvalAgentId);
    }

    const initRelationships = () => {
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
      agentid: {},
      agentname: {},
      agentstarttime: {},
      autocanceltime: {},
      agentdepartment: {},
      originalapprovalname: {},
      originaldepartment: {},
      secrecylevel: {},
      originalapprovalid: {},
    };
    const v$ = useVuelidate(validationRules, approvalAgent as any);
    v$.value.$validate();

    return {
      approvalAgentService,
      alertService,
      approvalAgent,
      previousState,
      isSaving,
      currentLanguage,
      officers,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.approvalAgent.id) {
        this.approvalAgentService()
          .update(this.approvalAgent)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.approvalAgent.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.approvalAgentService()
          .create(this.approvalAgent)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.approvalAgent.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
