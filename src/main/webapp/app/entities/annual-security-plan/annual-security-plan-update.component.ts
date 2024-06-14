import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import AnnualSecurityPlanService from './annual-security-plan.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ProjectService from '@/entities/project/project.service';
import { type IProject } from '@/shared/model/project.model';
import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import { type IAnnualSecurityPlan, AnnualSecurityPlan } from '@/shared/model/annual-security-plan.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'AnnualSecurityPlanUpdate',
  setup() {
    const annualSecurityPlanService = inject('annualSecurityPlanService', () => new AnnualSecurityPlanService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const annualSecurityPlan: Ref<IAnnualSecurityPlan> = ref(new AnnualSecurityPlan());

    const projectService = inject('projectService', () => new ProjectService());

    const projects: Ref<IProject[]> = ref([]);

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveAnnualSecurityPlan = async annualSecurityPlanId => {
      try {
        const res = await annualSecurityPlanService().find(annualSecurityPlanId);
        annualSecurityPlan.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.annualSecurityPlanId) {
      retrieveAnnualSecurityPlan(route.params.annualSecurityPlanId);
    }

    const initRelationships = () => {
      projectService()
        .retrieve()
        .then(res => {
          projects.value = res.data;
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
      securityplanid: {
        integer: validations.integer(t$('entity.validation.number').toString()),
      },
      securityplanname: {},
      releasetime: {},
      createtime: {},
      creatorname: {},
      auditStatus: {},
      version: {},
      project: {},
      creatorid: {},
      auditorid: {},
    };
    const v$ = useVuelidate(validationRules, annualSecurityPlan as any);
    v$.value.$validate();

    return {
      annualSecurityPlanService,
      alertService,
      annualSecurityPlan,
      previousState,
      auditStatusValues,
      isSaving,
      currentLanguage,
      projects,
      officers,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.annualSecurityPlan.id) {
        this.annualSecurityPlanService()
          .update(this.annualSecurityPlan)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster3App.annualSecurityPlan.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.annualSecurityPlanService()
          .create(this.annualSecurityPlan)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster3App.annualSecurityPlan.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
