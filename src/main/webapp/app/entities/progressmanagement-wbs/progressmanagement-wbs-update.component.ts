import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ProgressmanagementWbsService from './progressmanagement-wbs.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ProgressplanService from '@/entities/progressplan/progressplan.service';
import { type IProgressplan } from '@/shared/model/progressplan.model';
import ProgressplanreturnsService from '@/entities/progressplanreturns/progressplanreturns.service';
import { type IProgressplanreturns } from '@/shared/model/progressplanreturns.model';
import ProgressbaselineService from '@/entities/progressbaseline/progressbaseline.service';
import { type IProgressbaseline } from '@/shared/model/progressbaseline.model';
import { type IProgressmanagementWbs, ProgressmanagementWbs } from '@/shared/model/progressmanagement-wbs.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProgressmanagementWbsUpdate',
  setup() {
    const progressmanagementWbsService = inject('progressmanagementWbsService', () => new ProgressmanagementWbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const progressmanagementWbs: Ref<IProgressmanagementWbs> = ref(new ProgressmanagementWbs());

    const progressplanService = inject('progressplanService', () => new ProgressplanService());

    const progressplans: Ref<IProgressplan[]> = ref([]);

    const progressplanreturnsService = inject('progressplanreturnsService', () => new ProgressplanreturnsService());

    const progressplanreturns: Ref<IProgressplanreturns[]> = ref([]);

    const progressbaselineService = inject('progressbaselineService', () => new ProgressbaselineService());

    const progressbaselines: Ref<IProgressbaseline[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveProgressmanagementWbs = async progressmanagementWbsId => {
      try {
        const res = await progressmanagementWbsService().find(progressmanagementWbsId);
        progressmanagementWbs.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.progressmanagementWbsId) {
      retrieveProgressmanagementWbs(route.params.progressmanagementWbsId);
    }

    const initRelationships = () => {
      progressplanService()
        .retrieve()
        .then(res => {
          progressplans.value = res.data;
        });
      progressplanreturnsService()
        .retrieve()
        .then(res => {
          progressplanreturns.value = res.data;
        });
      progressbaselineService()
        .retrieve()
        .then(res => {
          progressbaselines.value = res.data;
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
      progressplan: {},
      progressplanreturns: {},
      progressbaseline: {},
    };
    const v$ = useVuelidate(validationRules, progressmanagementWbs as any);
    v$.value.$validate();

    return {
      progressmanagementWbsService,
      alertService,
      progressmanagementWbs,
      previousState,
      isSaving,
      currentLanguage,
      progressplans,
      progressplanreturns,
      progressbaselines,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.progressmanagementWbs.id) {
        this.progressmanagementWbsService()
          .update(this.progressmanagementWbs)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.progressmanagementWbs.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.progressmanagementWbsService()
          .create(this.progressmanagementWbs)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.progressmanagementWbs.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
