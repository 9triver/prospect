import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import QualityObjectivesService from './quality-objectives.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import QualityReturnsService from '@/entities/quality-returns/quality-returns.service';
import { type IQualityReturns } from '@/shared/model/quality-returns.model';
import { type IQualityObjectives, QualityObjectives } from '@/shared/model/quality-objectives.model';
import { QualityType } from '@/shared/model/enumerations/quality-type.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'QualityObjectivesUpdate',
  setup() {
    const qualityObjectivesService = inject('qualityObjectivesService', () => new QualityObjectivesService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const qualityObjectives: Ref<IQualityObjectives> = ref(new QualityObjectives());

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);

    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());

    const projectwbs: Ref<IProjectwbs[]> = ref([]);

    const qualityReturnsService = inject('qualityReturnsService', () => new QualityReturnsService());

    const qualityReturns: Ref<IQualityReturns[]> = ref([]);
    const qualityTypeValues: Ref<string[]> = ref(Object.keys(QualityType));
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveQualityObjectives = async qualityObjectivesId => {
      try {
        const res = await qualityObjectivesService().find(qualityObjectivesId);
        qualityObjectives.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.qualityObjectivesId) {
      retrieveQualityObjectives(route.params.qualityObjectivesId);
    }

    const initRelationships = () => {
      officersService()
        .retrieve()
        .then(res => {
          officers.value = res.data;
        });
      projectwbsService()
        .retrieve()
        .then(res => {
          projectwbs.value = res.data;
        });
      qualityReturnsService()
        .retrieve()
        .then(res => {
          qualityReturns.value = res.data;
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
      projectwbs: {},
      qualityReturns: {},
    };
    const v$ = useVuelidate(validationRules, qualityObjectives as any);
    v$.value.$validate();

    return {
      qualityObjectivesService,
      alertService,
      qualityObjectives,
      previousState,
      qualityTypeValues,
      secretlevelValues,
      auditStatusValues,
      isSaving,
      currentLanguage,
      officers,
      projectwbs,
      qualityReturns,
      v$,
      t$,
    };
  },
  created(): void {
    this.qualityObjectives.projectwbs = [];
    this.qualityObjectives.qualityReturns = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.qualityObjectives.id) {
        this.qualityObjectivesService()
          .update(this.qualityObjectives)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.qualityObjectives.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.qualityObjectivesService()
          .create(this.qualityObjectives)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.qualityObjectives.created', { param: param.id }).toString());
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
