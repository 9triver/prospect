import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import OtherPaymentService from './other-payment.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import ContractService from '@/entities/contract/contract.service';
import { type IContract } from '@/shared/model/contract.model';
import SubjectService from '@/entities/subject/subject.service';
import { type ISubject } from '@/shared/model/subject.model';
import { type IOtherPayment, OtherPayment } from '@/shared/model/other-payment.model';
import { OtherPaymenttype } from '@/shared/model/enumerations/other-paymenttype.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OtherPaymentUpdate',
  setup() {
    const otherPaymentService = inject('otherPaymentService', () => new OtherPaymentService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const otherPayment: Ref<IOtherPayment> = ref(new OtherPayment());

    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());

    const projectwbs: Ref<IProjectwbs[]> = ref([]);

    const contractService = inject('contractService', () => new ContractService());

    const contracts: Ref<IContract[]> = ref([]);

    const subjectService = inject('subjectService', () => new SubjectService());

    const subjects: Ref<ISubject[]> = ref([]);
    const otherPaymenttypeValues: Ref<string[]> = ref(Object.keys(OtherPaymenttype));
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
        });
      contractService()
        .retrieve()
        .then(res => {
          contracts.value = res.data;
        });
      subjectService()
        .retrieve()
        .then(res => {
          subjects.value = res.data;
        });
    };

    initRelationships();

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
      isSaving,
      currentLanguage,
      projectwbs,
      contracts,
      subjects,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
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
