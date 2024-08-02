import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import QualityReturnsService from './quality-returns.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import QualityObjectivesService from '@/entities/quality-objectives/quality-objectives.service';
import { type IQualityObjectives } from '@/shared/model/quality-objectives.model';
import { type IQualityReturns, QualityReturns } from '@/shared/model/quality-returns.model';
import { QualityType } from '@/shared/model/enumerations/quality-type.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'QualityReturnsUpdate',
  setup() {
    const qualityReturnsService = inject('qualityReturnsService', () => new QualityReturnsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const qualityReturns: Ref<IQualityReturns> = ref(new QualityReturns());

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);

    const qualityObjectivesService = inject('qualityObjectivesService', () => new QualityObjectivesService());

    const qualityObjectives: Ref<IQualityObjectives[]> = ref([]);
    const qualityTypeValues: Ref<string[]> = ref(Object.keys(QualityType));
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveQualityReturns = async qualityReturnsId => {
      try {
        const res = await qualityReturnsService().find(qualityReturnsId);
        qualityReturns.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.qualityReturnsId) {
      retrieveQualityReturns(route.params.qualityReturnsId);
    }

    const initRelationships = () => {
      officersService()
        .retrieve()
        .then(res => {
          officers.value = res.data;
        });
      qualityObjectivesService()
        .retrieve()
        .then(res => {
          qualityObjectives.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      name: {},
      objectives: {},
      qualitytype: {},
      secretlevel: {},
      target: {},
      statisticalmethod: {},
      statisticalfrequency: {},
      istarget: {},
      progress: {},
      description: {},
      problems: {},
      improvementmeasures: {},
      returntime: {},
      createtime: {},
      auditStatus: {},
      responsibleperson: {},
      auditorid: {},
      creatorid: {},
      qualityObjectives: {},
    };
    const v$ = useVuelidate(validationRules, qualityReturns as any);
    v$.value.$validate();

    return {
      qualityReturnsService,
      alertService,
      qualityReturns,
      previousState,
      qualityTypeValues,
      secretlevelValues,
      auditStatusValues,
      isSaving,
      currentLanguage,
      officers,
      qualityObjectives,
      v$,
      t$,
    };
  },
  created(): void {
    this.qualityReturns.qualityObjectives = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.qualityReturns.id) {
        this.qualityReturnsService()
          .update(this.qualityReturns)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.qualityReturns.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.qualityReturnsService()
          .create(this.qualityReturns)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.qualityReturns.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },

    getSelected(selectedVals, option, pkField = 'id'): any {
      if (selectedVals) {
        return selectedVals.find(value => option[pkField] === value[pkField]) ?? option;
      }
      return option;
    },
  },
});
