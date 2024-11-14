import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import OtherPaymentService from './other-payment.service';
import SubjectService from '../subject/subject.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IOtherPayment, OtherPayment } from '@/shared/model/other-payment.model';
import { OtherPaymenttype } from '@/shared/model/enumerations/other-paymenttype.model';
import { type ISubject } from '@/shared/model/subject.model';

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

    //查询科目字典
    const subjectService = inject('subjectService', () => new SubjectService());
    const subjects: Ref<ISubject[]> = ref([]);
    const retrieveSubjects = async () => {
      try {
        const res = await subjectService().retrieve();
        subjects.value = res.data.filter(subject => subject.type !== "1");
      } catch (err) {
        alertService.showHttpError(err.response);
      }
    };
    retrieveSubjects();
    const selectedSubjectName = ref('');  // 用于保存选中的科目名称
    const subjectId = ref('');
    const onSubjectChange = (value) => {
      selectedSubjectName.value = value;  // 更新选中的科目名称
      const selectedSubject = subjects.value.find(subject => subject.name === value);
      if (selectedSubject) {
        subjectId.value = selectedSubject.id;  // 假设项目编号字段是 `id`
      } else {
        subjectId.value = '';  // 如果未找到，清空科目编号
      }
    };

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

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      type: {},
      registertime: {},
      subjectid: {},
      subjectname: {},
      paymentamount: {},
      contractcode: {},
      contractname: {},
      wbsid: {},
      wbsname: {},
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
      v$,
      t$,
      subjects,
      onSubjectChange,
      subjectId,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      // 确保在创建时 ID 为 undefined
      if (!this.otherPayment.id) {
        this.otherPayment.id = undefined; // 或者直接不设置
      }
      this.isSaving = true;
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
