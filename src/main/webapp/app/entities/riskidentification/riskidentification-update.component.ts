import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import RiskidentificationService from './riskidentification.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import { type IRiskidentification, Riskidentification } from '@/shared/model/riskidentification.model';
import { Risklevel } from '@/shared/model/enumerations/risklevel.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RiskidentificationUpdate',
  setup() {
    const riskidentificationService = inject('riskidentificationService', () => new RiskidentificationService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const riskidentification: Ref<IRiskidentification> = ref(new Riskidentification());

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);
    const risklevelValues: Ref<string[]> = ref(Object.keys(Risklevel));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveRiskidentification = async riskidentificationId => {
      try {
        const res = await riskidentificationService().find(riskidentificationId);
        riskidentification.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.riskidentificationId) {
      retrieveRiskidentification(route.params.riskidentificationId);
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
      projectname: {},
      year: {},
      nodename: {},
      risktype: {},
      decumentid: {},
      version: {},
      usetime: {},
      systemlevel: {},
      risklevel: {},
      limitationtime: {},
      closetype: {},
      creatorid: {},
      responsibleid: {},
      auditorid: {},
    };
    const v$ = useVuelidate(validationRules, riskidentification as any);
    v$.value.$validate();

    return {
      riskidentificationService,
      alertService,
      riskidentification,
      previousState,
      risklevelValues,
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
      if (this.riskidentification.id) {
        this.riskidentificationService()
          .update(this.riskidentification)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.riskidentification.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.riskidentificationService()
          .create(this.riskidentification)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.riskidentification.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
