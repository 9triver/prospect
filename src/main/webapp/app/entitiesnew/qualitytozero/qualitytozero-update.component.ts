import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import QualitytozeroService from './qualitytozero.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import WorkbagService from '@/entities/workbag/workbag.service';
import { type IWorkbag } from '@/shared/model/workbag.model';
import { type IQualitytozero, Qualitytozero } from '@/shared/model/qualitytozero.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'QualitytozeroUpdate',
  setup() {
    const qualitytozeroService = inject('qualitytozeroService', () => new QualitytozeroService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const qualitytozero: Ref<IQualitytozero> = ref(new Qualitytozero());

    const workbagService = inject('workbagService', () => new WorkbagService());

    const workbags: Ref<IWorkbag[]> = ref([]);
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveQualitytozero = async qualitytozeroId => {
      try {
        const res = await qualitytozeroService().find(qualitytozeroId);
        qualitytozero.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.qualitytozeroId) {
      retrieveQualitytozero(route.params.qualitytozeroId);
    }

    const initRelationships = () => {
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
      workbagid: {},
      belongwbsid: {},
      outsourcingcontractid: {},
      qualityproblemid: {},
      qualityproblemname: {},
      problemhappentime: {},
      problemresponsibleperson: {},
      problemresponsibleunit: {},
      producttype: {},
      productname: {},
      problemphenomenon: {},
      problemtype: {},
      qualitylevel: {},
      zerotype: {},
      problemreasonanalysis: {},
      problemreasoncategory: {},
      takemeasures: {},
      onebyonecategory: {},
      verificationeffect: {},
      qualityprojectreport: {},
      qualitytozeroreport: {},
      reviewopinion: {},
      implementationverificationtable: {},
      auditStatus: {},
      workbag: {},
    };
    const v$ = useVuelidate(validationRules, qualitytozero as any);
    v$.value.$validate();

    return {
      qualitytozeroService,
      alertService,
      qualitytozero,
      previousState,
      auditStatusValues,
      isSaving,
      currentLanguage,
      workbags,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.qualitytozero.id) {
        this.qualitytozeroService()
          .update(this.qualitytozero)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.qualitytozero.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.qualitytozeroService()
          .create(this.qualitytozero)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.qualitytozero.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
