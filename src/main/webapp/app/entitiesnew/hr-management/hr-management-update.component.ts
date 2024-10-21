import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import HrManagementService from './hr-management.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import { type IHrManagement, HrManagement } from '@/shared/model/hr-management.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'HrManagementUpdate',
  setup() {
    const hrManagementService = inject('hrManagementService', () => new HrManagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const hrManagement: Ref<IHrManagement> = ref(new HrManagement());

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveHrManagement = async hrManagementId => {
      try {
        const res = await hrManagementService().find(hrManagementId);
        hrManagement.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.hrManagementId) {
      retrieveHrManagement(route.params.hrManagementId);
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
      officersid: {},
      officersname: {},
      projectid: {},
      projectname: {},
      projectrole: {},
      jobgrade: {},
      departmentid: {},
      departmentname: {},
      frontlineid: {},
      frontlinename: {},
      jobduty: {},
      annualworktime: {},
      annualtasktarget: {},
      officers: {},
    };
    const v$ = useVuelidate(validationRules, hrManagement as any);
    v$.value.$validate();

    return {
      hrManagementService,
      alertService,
      hrManagement,
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
      if (this.hrManagement.id) {
        this.hrManagementService()
          .update(this.hrManagement)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.hrManagement.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.hrManagementService()
          .create(this.hrManagement)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.hrManagement.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
