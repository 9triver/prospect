import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import QualitymanagementWbsService from './qualitymanagement-wbs.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import QualityobjectivesService from '@/entities/qualityobjectives/qualityobjectives.service';
import { type IQualityobjectives } from '@/shared/model/qualityobjectives.model';
import QualityreturnsService from '@/entities/qualityreturns/qualityreturns.service';
import { type IQualityreturns } from '@/shared/model/qualityreturns.model';
import UnQualityAuditService from '@/entities/un-quality-audit/un-quality-audit.service';
import { type IUnQualityAudit } from '@/shared/model/un-quality-audit.model';
import { type IQualitymanagementWbs, QualitymanagementWbs } from '@/shared/model/qualitymanagement-wbs.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'QualitymanagementWbsUpdate',
  setup() {
    const qualitymanagementWbsService = inject('qualitymanagementWbsService', () => new QualitymanagementWbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const qualitymanagementWbs: Ref<IQualitymanagementWbs> = ref(new QualitymanagementWbs());

    const qualityobjectivesService = inject('qualityobjectivesService', () => new QualityobjectivesService());

    const qualityobjectives: Ref<IQualityobjectives[]> = ref([]);

    const qualityreturnsService = inject('qualityreturnsService', () => new QualityreturnsService());

    const qualityreturns: Ref<IQualityreturns[]> = ref([]);

    const unQualityAuditService = inject('unQualityAuditService', () => new UnQualityAuditService());

    const unQualityAudits: Ref<IUnQualityAudit[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveQualitymanagementWbs = async qualitymanagementWbsId => {
      try {
        const res = await qualitymanagementWbsService().find(qualitymanagementWbsId);
        qualitymanagementWbs.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.qualitymanagementWbsId) {
      retrieveQualitymanagementWbs(route.params.qualitymanagementWbsId);
    }

    const initRelationships = () => {
      qualityobjectivesService()
        .retrieve()
        .then(res => {
          qualityobjectives.value = res.data;
        });
      qualityreturnsService()
        .retrieve()
        .then(res => {
          qualityreturns.value = res.data;
        });
      unQualityAuditService()
        .retrieve()
        .then(res => {
          unQualityAudits.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      wbsspare1: {},
      wbsspare2: {},
      wbsspare3: {},
      wbsspare4: {},
      wbsspare5: {},
      qualityobjectives: {},
      qualityreturns: {},
      unQualityAudit: {},
    };
    const v$ = useVuelidate(validationRules, qualitymanagementWbs as any);
    v$.value.$validate();

    return {
      qualitymanagementWbsService,
      alertService,
      qualitymanagementWbs,
      previousState,
      isSaving,
      currentLanguage,
      qualityobjectives,
      qualityreturns,
      unQualityAudits,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.qualitymanagementWbs.id) {
        this.qualitymanagementWbsService()
          .update(this.qualitymanagementWbs)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.qualitymanagementWbs.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.qualitymanagementWbsService()
          .create(this.qualitymanagementWbs)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.qualitymanagementWbs.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
