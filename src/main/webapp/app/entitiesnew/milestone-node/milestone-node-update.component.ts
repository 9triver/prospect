import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import MilestoneNodeService from './milestone-node.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import OutsourcingContractService from '@/entities/outsourcing-contract/outsourcing-contract.service';
import { type IOutsourcingContract } from '@/shared/model/outsourcing-contract.model';
import { type IMilestoneNode, MilestoneNode } from '@/shared/model/milestone-node.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'MilestoneNodeUpdate',
  setup() {
    const milestoneNodeService = inject('milestoneNodeService', () => new MilestoneNodeService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const milestoneNode: Ref<IMilestoneNode> = ref(new MilestoneNode());

    const outsourcingContractService = inject('outsourcingContractService', () => new OutsourcingContractService());

    const outsourcingContracts: Ref<IOutsourcingContract[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveMilestoneNode = async milestoneNodeId => {
      try {
        const res = await milestoneNodeService().find(milestoneNodeId);
        milestoneNode.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.milestoneNodeId) {
      retrieveMilestoneNode(route.params.milestoneNodeId);
    }

    const initRelationships = () => {
      outsourcingContractService()
        .retrieve()
        .then(res => {
          outsourcingContracts.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      name: {},
      planpaymenttime: {},
      planpaymentamount: {},
      outsourcingContract: {},
    };
    const v$ = useVuelidate(validationRules, milestoneNode as any);
    v$.value.$validate();

    return {
      milestoneNodeService,
      alertService,
      milestoneNode,
      previousState,
      isSaving,
      currentLanguage,
      outsourcingContracts,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.milestoneNode.id) {
        this.milestoneNodeService()
          .update(this.milestoneNode)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.milestoneNode.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.milestoneNodeService()
          .create(this.milestoneNode)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.milestoneNode.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
