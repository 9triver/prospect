import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import PbsbaselineService from './pbsbaseline.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ProjectchargeService from '@/entities/projectcharge/projectcharge.service';
import { type IProjectcharge } from '@/shared/model/projectcharge.model';
import { type IPbsbaseline, Pbsbaseline } from '@/shared/model/pbsbaseline.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PbsbaselineUpdate',
  setup() {
    const pbsbaselineService = inject('pbsbaselineService', () => new PbsbaselineService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const pbsbaseline: Ref<IPbsbaseline> = ref(new Pbsbaseline());

    const projectchargeService = inject('projectchargeService', () => new ProjectchargeService());

    const projectcharges: Ref<IProjectcharge[]> = ref([]);
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrievePbsbaseline = async pbsbaselineId => {
      try {
        const res = await pbsbaselineService().find(pbsbaselineId);
        pbsbaseline.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.pbsbaselineId) {
      retrievePbsbaseline(route.params.pbsbaselineId);
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
    const v$ = useVuelidate(validationRules, pbsbaseline as any);
    v$.value.$validate();

    return {
      pbsbaselineService,
      alertService,
      pbsbaseline,
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
      if (this.pbsbaseline.id) {
        this.pbsbaselineService()
          .update(this.pbsbaseline)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.pbsbaseline.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.pbsbaselineService()
          .create(this.pbsbaseline)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.pbsbaseline.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
