import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import OutsourcingPurchasePlanService from './outsourcing-purchase-plan.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ProjectService from '@/entities/project/project.service';
import { type IProject } from '@/shared/model/project.model';
import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import { type IOutsourcingPurchasePlan, OutsourcingPurchasePlan } from '@/shared/model/outsourcing-purchase-plan.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OutsourcingPurchasePlanUpdate',
  setup() {
    const outsourcingPurchasePlanService = inject('outsourcingPurchasePlanService', () => new OutsourcingPurchasePlanService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const outsourcingPurchasePlan: Ref<IOutsourcingPurchasePlan> = ref(new OutsourcingPurchasePlan());

    const projectService = inject('projectService', () => new ProjectService());

    const projects: Ref<IProject[]> = ref([]);

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveOutsourcingPurchasePlan = async outsourcingPurchasePlanId => {
      try {
        const res = await outsourcingPurchasePlanService().find(outsourcingPurchasePlanId);
        outsourcingPurchasePlan.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.outsourcingPurchasePlanId) {
      retrieveOutsourcingPurchasePlan(route.params.outsourcingPurchasePlanId);
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
      matarialname: {},
      purchasingmethod: {},
      budgit: {},
      needtime: {},
      planusetime: {},
      supplierid: {},
      price: {},
      secretlevel: {},
      auditStatus: {},
      project: {},
      responsibleid: {},
      auditorid: {},
    };
    const v$ = useVuelidate(validationRules, outsourcingPurchasePlan as any);
    v$.value.$validate();

    return {
      outsourcingPurchasePlanService,
      alertService,
      outsourcingPurchasePlan,
      previousState,
      secretlevelValues,
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
      if (this.outsourcingPurchasePlan.id) {
        this.outsourcingPurchasePlanService()
          .update(this.outsourcingPurchasePlan)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.outsourcingPurchasePlan.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.outsourcingPurchasePlanService()
          .create(this.outsourcingPurchasePlan)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.outsourcingPurchasePlan.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
