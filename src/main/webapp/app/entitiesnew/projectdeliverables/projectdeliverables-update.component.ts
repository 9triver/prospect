import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ProjectdeliverablesService from './projectdeliverables.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import DeliverablesService from '@/entities/deliverables/deliverables.service';
import { type IDeliverables } from '@/shared/model/deliverables.model';
import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import WorkbagService from '@/entities/workbag/workbag.service';
import { type IWorkbag } from '@/shared/model/workbag.model';
import { type IProjectdeliverables, Projectdeliverables } from '@/shared/model/projectdeliverables.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProjectdeliverablesUpdate',
  setup() {
    const projectdeliverablesService = inject('projectdeliverablesService', () => new ProjectdeliverablesService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const projectdeliverables: Ref<IProjectdeliverables> = ref(new Projectdeliverables());

    const deliverablesService = inject('deliverablesService', () => new DeliverablesService());

    const deliverables: Ref<IDeliverables[]> = ref([]);

    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());

    const projectwbs: Ref<IProjectwbs[]> = ref([]);

    const workbagService = inject('workbagService', () => new WorkbagService());

    const workbags: Ref<IWorkbag[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveProjectdeliverables = async projectdeliverablesId => {
      try {
        const res = await projectdeliverablesService().find(projectdeliverablesId);
        projectdeliverables.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.projectdeliverablesId) {
      retrieveProjectdeliverables(route.params.projectdeliverablesId);
    }

    const initRelationships = () => {
      deliverablesService()
        .retrieve()
        .then(res => {
          deliverables.value = res.data;
        });
      projectwbsService()
        .retrieve()
        .then(res => {
          projectwbs.value = res.data;
        });
      workbagService()
        .retrieve()
        .then(res => {
          workbags.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      parentcode: {},
      isSubmit: {},
      status: {},
      code: {},
      belongwbsids: {},
      belongworkbagids: {},
    };
    const v$ = useVuelidate(validationRules, projectdeliverables as any);
    v$.value.$validate();

    return {
      projectdeliverablesService,
      alertService,
      projectdeliverables,
      previousState,
      isSaving,
      currentLanguage,
      deliverables,
      projectwbs,
      workbags,
      v$,
      t$,
    };
  },
  created(): void {
    this.projectdeliverables.belongwbsids = [];
    this.projectdeliverables.belongworkbagids = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.projectdeliverables.id) {
        this.projectdeliverablesService()
          .update(this.projectdeliverables)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.projectdeliverables.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.projectdeliverablesService()
          .create(this.projectdeliverables)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.projectdeliverables.created', { param: param.id }).toString());
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
