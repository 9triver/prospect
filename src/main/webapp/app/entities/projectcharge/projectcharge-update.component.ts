import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ProjectchargeService from './projectcharge.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import { type IProjectcharge, Projectcharge } from '@/shared/model/projectcharge.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProjectchargeUpdate',
  setup() {
    const projectchargeService = inject('projectchargeService', () => new ProjectchargeService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const projectcharge: Ref<IProjectcharge> = ref(new Projectcharge());

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveProjectcharge = async projectchargeId => {
      try {
        const res = await projectchargeService().find(projectchargeId);
        projectcharge.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.projectchargeId) {
      retrieveProjectcharge(route.params.projectchargeId);
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
      projectname: {},
      formid: {},
      starttime: {},
      endtime: {},
      secretlevel: {},
      requestdeportment: {},
      chargetype: {},
      chargecontent: {},
      creatorid: {},
    };
    const v$ = useVuelidate(validationRules, projectcharge as any);
    v$.value.$validate();

    return {
      projectchargeService,
      alertService,
      projectcharge,
      previousState,
      secretlevelValues,
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
      if (this.projectcharge.id) {
        this.projectchargeService()
          .update(this.projectcharge)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.projectcharge.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.projectchargeService()
          .create(this.projectcharge)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.projectcharge.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
