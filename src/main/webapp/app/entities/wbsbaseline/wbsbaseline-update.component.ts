import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import WbsbaselineService from './wbsbaseline.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ProjectchargeService from '@/entities/projectcharge/projectcharge.service';
import { type IProjectcharge } from '@/shared/model/projectcharge.model';
import { type IWbsbaseline, Wbsbaseline } from '@/shared/model/wbsbaseline.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'WbsbaselineUpdate',
  setup() {
    const wbsbaselineService = inject('wbsbaselineService', () => new WbsbaselineService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const wbsbaseline: Ref<IWbsbaseline> = ref(new Wbsbaseline());

    const projectchargeService = inject('projectchargeService', () => new ProjectchargeService());

    const projectcharges: Ref<IProjectcharge[]> = ref([]);
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveWbsbaseline = async wbsbaselineId => {
      try {
        const res = await wbsbaselineService().find(wbsbaselineId);
        wbsbaseline.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.wbsbaselineId) {
      retrieveWbsbaseline(route.params.wbsbaselineId);
    }

    const initRelationships = () => {
      projectchargeService()
        .retrieve()
        .then(res => {
          projectcharges.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      secretlevel: {},
      requestdeportment: {},
      chargetype: {},
      chargecontent: {},
      status: {},
      version: {},
      remark: {},
      projectcharge: {},
    };
    const v$ = useVuelidate(validationRules, wbsbaseline as any);
    v$.value.$validate();

    return {
      wbsbaselineService,
      alertService,
      wbsbaseline,
      previousState,
      secretlevelValues,
      isSaving,
      currentLanguage,
      projectcharges,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.wbsbaseline.id) {
        this.wbsbaselineService()
          .update(this.wbsbaseline)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.wbsbaseline.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.wbsbaselineService()
          .create(this.wbsbaseline)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.wbsbaseline.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
