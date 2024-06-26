import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import RiskreportService from './riskreport.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import RiskidentificationService from '@/entities/riskidentification/riskidentification.service';
import { type IRiskidentification } from '@/shared/model/riskidentification.model';
import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import { type IRiskreport, Riskreport } from '@/shared/model/riskreport.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'RiskreportUpdate',
  setup() {
    const riskreportService = inject('riskreportService', () => new RiskreportService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const riskreport: Ref<IRiskreport> = ref(new Riskreport());

    const riskidentificationService = inject('riskidentificationService', () => new RiskidentificationService());

    const riskidentifications: Ref<IRiskidentification[]> = ref([]);

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveRiskreport = async riskreportId => {
      try {
        const res = await riskreportService().find(riskreportId);
        riskreport.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.riskreportId) {
      retrieveRiskreport(route.params.riskreportId);
    }

    const initRelationships = () => {
      riskidentificationService()
        .retrieve()
        .then(res => {
          riskidentifications.value = res.data;
        });
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
      type: {},
      riskreportname: {},
      releasetime: {},
      auditStatus: {},
      riskidentification: {},
      creatorid: {},
      auditorid: {},
    };
    const v$ = useVuelidate(validationRules, riskreport as any);
    v$.value.$validate();

    return {
      riskreportService,
      alertService,
      riskreport,
      previousState,
      auditStatusValues,
      isSaving,
      currentLanguage,
      riskidentifications,
      officers,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.riskreport.id) {
        this.riskreportService()
          .update(this.riskreport)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.riskreport.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.riskreportService()
          .create(this.riskreport)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.riskreport.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
