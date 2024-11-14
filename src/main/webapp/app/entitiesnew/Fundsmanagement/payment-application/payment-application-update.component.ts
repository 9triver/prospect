import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import PaymentApplicationService from './payment-application.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import WorkbagService from '@/entitiesnew/workbag/workbag.service';
import { type IWorkbag } from '@/shared/model/workbag.model';
import OutsourcingContractService from '@/entitiesnew/outsourcing-contract/outsourcing-contract.service';
import { type IOutsourcingContract, OutsourcingContract } from '@/shared/model/outsourcing-contract.model';
import MilestoneNodeService from '@/entitiesnew/milestone-node/milestone-node.service';
import { type IMilestoneNode } from '@/shared/model/milestone-node.model';
import { type IPaymentApplication, PaymentApplication } from '@/shared/model/payment-application.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PaymentApplicationUpdate',
  setup() {
    const paymentApplicationService = inject('paymentApplicationService', () => new PaymentApplicationService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const paymentApplication: Ref<IPaymentApplication> = ref(new PaymentApplication());

    //任务包
    const workbagService = inject('workbagService', () => new WorkbagService());
    const workbags: Ref<IWorkbag[]> = ref([]);
    //外协合同
    const outsourcingcontractService = inject('outsourcingcontractService', () => new OutsourcingContractService());
    const outsourcingcontracts: Ref<IOutsourcingContract[]> = ref([]);
    const outsourcingContract: Ref<IOutsourcingContract> = ref(new OutsourcingContract());
    
    //里程碑节点
    const milestoneNodeService = inject('milestoneNodeService', () => new MilestoneNodeService());
    const milestoneNodes: Ref<IMilestoneNode[]> = ref([]);

    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrievePaymentApplication = async paymentApplicationId => {
      try {
        const res = await paymentApplicationService().find(paymentApplicationId);
        paymentApplication.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.paymentApplicationId) {
      retrievePaymentApplication(route.params.paymentApplicationId);
    }

    const initRelationships = () => {
      workbagService()
        .retrieve()
        .then(res => {
          workbags.value = res.data;
        });
    };

    initRelationships();

    //查询外协合同信息，并获取里程碑节点信息
    const outsourcingcontractId = ref('');
    const outsourcingcontractName = ref('');
    const workbagName = ref('');
    const onWorkbagChange = async (value) => {
      workbagName.value = value.name; // 获取选择的外协合同名称
      // 获取选择的外协合同 ID
      outsourcingcontractId.value = value.outsourcingcontractid;
      outsourcingcontractName.value = value.outsourcingcontractname;
      const outid = Number(outsourcingcontractId.value);
      // 调用函数查询外协合同信息
      await retrieveOutsourcingContract(outid);

      if (outsourcingContract.value && outsourcingContract.value.milestoneNodes) {
        milestoneNodes.value = outsourcingContract.value.milestoneNodes;
        // alert("对应的里程碑节点是："+ JSON.stringify(milestoneNodes.value));
      }else{
        // milestoneNodes.value = [];
        alert("该任务包没有对应的里程碑节点");
      }

    };
    // 查询外协合同信息的异步方法
    const retrieveOutsourcingContract = async (outid) => {
      try {
        const res = await outsourcingcontractService().find(outid);
        outsourcingContract.value = res;
        // alert("查到了外协合同：" + JSON.stringify(outsourcingContract.value));
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };
    // 获取选择的里程碑节点
    const planPaymentNode = ref('');
    const planPaymentName = ref('');
    const onMilestoneNodeChange = (value) => {
      planPaymentNode.value = value.id;
      planPaymentName.value = value.name;
    };

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      outsourcingcontractid: {},
      planpaymentnode: {},
      planpaymentamount: {},
      workbag: {},
    };
    const v$ = useVuelidate(validationRules, paymentApplication as any);
    v$.value.$validate();

    return {
      paymentApplicationService,
      alertService,
      paymentApplication,
      previousState,
      isSaving,
      currentLanguage,
      workbags,
      v$,
      t$,
      onWorkbagChange,
      outsourcingcontractId,
      outsourcingcontractName,
      milestoneNodes,
      onMilestoneNodeChange,
      planPaymentNode,
      planPaymentName,
      workbagName,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      // 补充保存字段
      this.paymentApplication.planpaymentnode = this.planPaymentNode;
      this.paymentApplication.planpaymentname = this.planPaymentName;
      this.paymentApplication.outsourcingcontractid = this.outsourcingcontractId;
      this.paymentApplication.outsourcingcontractname = this.outsourcingcontractName;
      this.paymentApplication.workbagname = this.workbagName;
      // alert(JSON.stringify(this.paymentApplication));
      //更新
      if (this.paymentApplication.id) {
        this.paymentApplicationService()
          .update(this.paymentApplication)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.paymentApplication.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      //新增
      } else {
        this.paymentApplicationService()
          .create(this.paymentApplication)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.paymentApplication.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
