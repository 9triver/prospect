import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ResourcemanagementWbsService from './resourcemanagement-wbs.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ProjectHumanresourcesplanService from '@/entities/project-humanresourcesplan/project-humanresourcesplan.service';
import { type IProjectHumanresourcesplan } from '@/shared/model/project-humanresourcesplan.model';
import ProjectremitService from '@/entities/projectremit/projectremit.service';
import { type IProjectremit } from '@/shared/model/projectremit.model';
import HumanresourcesService from '@/entities/humanresources/humanresources.service';
import { type IHumanresources } from '@/shared/model/humanresources.model';
import { type IResourcemanagementWbs, ResourcemanagementWbs } from '@/shared/model/resourcemanagement-wbs.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ResourcemanagementWbsUpdate',
  setup() {
    const resourcemanagementWbsService = inject('resourcemanagementWbsService', () => new ResourcemanagementWbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const resourcemanagementWbs: Ref<IResourcemanagementWbs> = ref(new ResourcemanagementWbs());

    const projectHumanresourcesplanService = inject('projectHumanresourcesplanService', () => new ProjectHumanresourcesplanService());

    const projectHumanresourcesplans: Ref<IProjectHumanresourcesplan[]> = ref([]);

    const projectremitService = inject('projectremitService', () => new ProjectremitService());

    const projectremits: Ref<IProjectremit[]> = ref([]);

    const humanresourcesService = inject('humanresourcesService', () => new HumanresourcesService());

    const humanresources: Ref<IHumanresources[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveResourcemanagementWbs = async resourcemanagementWbsId => {
      try {
        const res = await resourcemanagementWbsService().find(resourcemanagementWbsId);
        resourcemanagementWbs.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.resourcemanagementWbsId) {
      retrieveResourcemanagementWbs(route.params.resourcemanagementWbsId);
    }

    const initRelationships = () => {
      projectHumanresourcesplanService()
        .retrieve()
        .then(res => {
          projectHumanresourcesplans.value = res.data;
        });
      projectremitService()
        .retrieve()
        .then(res => {
          projectremits.value = res.data;
        });
      humanresourcesService()
        .retrieve()
        .then(res => {
          humanresources.value = res.data;
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
      projectHumanresourcesplan: {},
      projectremit: {},
      humanresources: {},
    };
    const v$ = useVuelidate(validationRules, resourcemanagementWbs as any);
    v$.value.$validate();

    return {
      resourcemanagementWbsService,
      alertService,
      resourcemanagementWbs,
      previousState,
      isSaving,
      currentLanguage,
      projectHumanresourcesplans,
      projectremits,
      humanresources,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.resourcemanagementWbs.id) {
        this.resourcemanagementWbsService()
          .update(this.resourcemanagementWbs)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.resourcemanagementWbs.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.resourcemanagementWbsService()
          .create(this.resourcemanagementWbs)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.resourcemanagementWbs.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
