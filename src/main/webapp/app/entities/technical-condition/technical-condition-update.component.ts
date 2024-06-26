import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import TechnicalConditionService from './technical-condition.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import { type ITechnicalCondition, TechnicalCondition } from '@/shared/model/technical-condition.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'TechnicalConditionUpdate',
  setup() {
    const technicalConditionService = inject('technicalConditionService', () => new TechnicalConditionService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const technicalCondition: Ref<ITechnicalCondition> = ref(new TechnicalCondition());

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveTechnicalCondition = async technicalConditionId => {
      try {
        const res = await technicalConditionService().find(technicalConditionId);
        technicalCondition.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.technicalConditionId) {
      retrieveTechnicalCondition(route.params.technicalConditionId);
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
      caption: {},
      projectname: {},
      decumentid: {},
      claimant: {},
      applicant: {},
      applicanttime: {},
      validrange: {},
      createtime: {},
      auditStatus: {},
      creatorid: {},
      auditorid: {},
    };
    const v$ = useVuelidate(validationRules, technicalCondition as any);
    v$.value.$validate();

    return {
      technicalConditionService,
      alertService,
      technicalCondition,
      previousState,
      auditStatusValues,
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
      if (this.technicalCondition.id) {
        this.technicalConditionService()
          .update(this.technicalCondition)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.technicalCondition.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.technicalConditionService()
          .create(this.technicalCondition)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.technicalCondition.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
