import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import OutsourcingmPurchasePlanService from './outsourcingm-purchase-plan.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ProjectService from '@/entities/project/project.service';
import { type IProject } from '@/shared/model/project.model';
import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import { type IOutsourcingmPurchasePlan, OutsourcingmPurchasePlan } from '@/shared/model/outsourcingm-purchase-plan.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OutsourcingmPurchasePlanUpdate',
  setup() {
    const outsourcingmPurchasePlanService = inject('outsourcingmPurchasePlanService', () => new OutsourcingmPurchasePlanService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const outsourcingmPurchasePlan: Ref<IOutsourcingmPurchasePlan> = ref(new OutsourcingmPurchasePlan());

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

    const retrieveOutsourcingmPurchasePlan = async outsourcingmPurchasePlanId => {
      try {
        const res = await outsourcingmPurchasePlanService().find(outsourcingmPurchasePlanId);
        outsourcingmPurchasePlan.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.outsourcingmPurchasePlanId) {
      retrieveOutsourcingmPurchasePlan(route.params.outsourcingmPurchasePlanId);
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
      outsourcingplanid: {
        integer: validations.integer(t$('entity.validation.number').toString()),
      },
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
    const v$ = useVuelidate(validationRules, outsourcingmPurchasePlan as any);
    v$.value.$validate();

    return {
      outsourcingmPurchasePlanService,
      alertService,
      outsourcingmPurchasePlan,
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
      if (this.outsourcingmPurchasePlan.id) {
        this.outsourcingmPurchasePlanService()
          .update(this.outsourcingmPurchasePlan)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster3App.outsourcingmPurchasePlan.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.outsourcingmPurchasePlanService()
          .create(this.outsourcingmPurchasePlan)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster3App.outsourcingmPurchasePlan.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
