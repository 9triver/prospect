import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import OtherPaymentService from './other-payment.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ProjectwbsService from '@/entitiesnew/projectwbs/projectwbs.service';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import ContractService from '@/entitiesnew/Fundsmanagement/contract/contract.service';
import { type IContract } from '@/shared/model/contract.model';
import SubjectService from '@/entitiesnew/Fundsmanagement/subject/subject.service';
import { type ISubject } from '@/shared/model/subject.model';
import { type IOtherPayment, OtherPayment } from '@/shared/model/other-payment.model';
import { OtherPaymenttype } from '@/shared/model/enumerations/other-paymenttype.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OtherPaymentUpdate',
  setup() {
    const otherPaymentService = inject('otherPaymentService', () => new OtherPaymentService());
    const alertService = inject('alertService', () => useAlertService(), true);

    //获取类型
    const otherPayment: Ref<IOtherPayment> = ref(new OtherPayment());
    const otherPaymenttypeValues: Ref<string[]> = ref(Object.keys(OtherPaymenttype));
    const getPaymentTypeLabel = (type) => {
      switch (type) {
        case 'SPORADICPURCHASE':
          return '零星采购';
        case 'SHARE':
          return '分摊费用';
        case 'TRANSACTION':
          return '事务费用';
        default:
          return type; // 如果没有匹配的类型，返回原值
      }
    };

    //获取科目字典-选择type=2其他7个科目
    const subjectService = inject('subjectService', () => new SubjectService());
    const subjects: Ref<ISubject[]> = ref([]);

    //获取项目
    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());
    const projectwbs: Ref<IProjectwbs[]> = ref([]);

    //获取合同
    const contractService = inject('contractService', () => new ContractService());
    const contracts: Ref<IContract[]> = ref([]);

    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveOtherPayment = async otherPaymentId => {
      try {
        const res = await otherPaymentService().find(otherPaymentId);
        otherPayment.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.otherPaymentId) {
      retrieveOtherPayment(route.params.otherPaymentId);
    }

    const initRelationships = () => {
      projectwbsService()
        .retrieve()
        .then(res => {
          projectwbs.value = res.data;
          //只选择出重大项目
          projectwbs.value = res.data.filter(projectwbs => projectwbs.parentwbsid === "" || projectwbs.parentwbsid === null || projectwbs.parentwbsid === undefined);
        });
      contractService()
        .retrieve()
        .then(res => {
          contracts.value = res.data;
        });
      subjectService()
        .retrieve()
        .then(res => {
          subjects.value = res.data.filter(subject => subject.type === "2");
        });
    };

    initRelationships();



    // 定义一个ref来保存选中的科目编号
    // const selectedSubjectName = ref('');  // 用于保存选中的科目名称
    // const subjectId = ref('');
    // const onSubjectChange = (value) => {
    //   selectedSubjectName.value = value;  // 更新选中的科目名称
    //   const selectedSubject = subjects.value.find(subject => subject.name === value);
    //   if (selectedSubject) {
    //     subjectId.value = selectedSubject.id;  // 假设项目编号字段是 `id`
    //   } else {
    //     subjectId.value = '';  // 如果未找到，清空科目编号
    //   }
    // };
    // 定义一个ref来保存选中的合同名称
    // const contractName = ref('');
    // const onContractChange = (value) => {
    //   contractName.value = value.contractname;
    // };
    // 定义一个ref来保存选中的合同名称
    // const wbsName = ref('');
    // const onWbsChange = (value) => {
    //   wbsName.value = value.wbsname;
    // };

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      name: {},
      type: {},
      registertime: {},
      subjectid: {},
      subjectname: {},
      paymentamount: {},
      contractcode: {},
      contractname: {},
      wbsid: {},
      wbsname: {},
      projectwbs: {},
      contract: {},
      subject: {},
    };
    const v$ = useVuelidate(validationRules, otherPayment as any);
    v$.value.$validate();

    return {
      otherPaymentService,
      alertService,
      otherPayment,
      previousState,
      otherPaymenttypeValues,
      getPaymentTypeLabel,
      isSaving,
      currentLanguage,
      projectwbs,
      contracts,
      subjects,
      v$,
      t$,
      // onSubjectChange,
      // subjectId,
      // onContractChange,
      // contractName,
      // onWbsChange,
      // wbsName,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      // 补充保存字段
      this.otherPayment.contractname = this.otherPayment.contract.contractname;
      this.otherPayment.contractcode = this.otherPayment.contract.contractcode;
      this.otherPayment.subjectid = this.otherPayment.subject.id;
      this.otherPayment.subjectname = this.otherPayment.subject.name;
      this.otherPayment.wbsid = this.otherPayment.projectwbs.id;
      this.otherPayment.wbsname = this.otherPayment.projectwbs.wbsname;
      //更新
      if (this.otherPayment.id) {
        this.otherPaymentService()
          .update(this.otherPayment)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.otherPayment.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      //新增
      } else {
        this.otherPaymentService()
          .create(this.otherPayment)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.otherPayment.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
