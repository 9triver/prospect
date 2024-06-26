import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import UnitbudgetService from './unitbudget.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import { type IUnitbudget, Unitbudget } from '@/shared/model/unitbudget.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'UnitbudgetUpdate',
  setup() {
    const unitbudgetService = inject('unitbudgetService', () => new UnitbudgetService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const unitbudget: Ref<IUnitbudget> = ref(new Unitbudget());

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveUnitbudget = async unitbudgetId => {
      try {
        const res = await unitbudgetService().find(unitbudgetId);
        unitbudget.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.unitbudgetId) {
      retrieveUnitbudget(route.params.unitbudgetId);
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
      subprojectname: {},
      unitbudgername: {},
      billingunit: {},
      number: {},
      totalbudget: {},
      maintainerbudget: {},
      outsourcingbudget: {},
      earmarkedbudget: {},
      testbudget: {},
      creatorid: {},
      auditorid: {},
    };
    const v$ = useVuelidate(validationRules, unitbudget as any);
    v$.value.$validate();

    return {
      unitbudgetService,
      alertService,
      unitbudget,
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
      if (this.unitbudget.id) {
        this.unitbudgetService()
          .update(this.unitbudget)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.unitbudget.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.unitbudgetService()
          .create(this.unitbudget)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.unitbudget.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
