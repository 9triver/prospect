import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import QualityobjectivesService from './qualityobjectives.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import QualityreturnsService from '@/entities/qualityreturns/qualityreturns.service';
import { type IQualityreturns } from '@/shared/model/qualityreturns.model';
import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import { type IQualityobjectives, Qualityobjectives } from '@/shared/model/qualityobjectives.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'QualityobjectivesUpdate',
  setup() {
    const qualityobjectivesService = inject('qualityobjectivesService', () => new QualityobjectivesService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const qualityobjectives: Ref<IQualityobjectives> = ref(new Qualityobjectives());

    const qualityreturnsService = inject('qualityreturnsService', () => new QualityreturnsService());

    const qualityreturns: Ref<IQualityreturns[]> = ref([]);

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveQualityobjectives = async qualityobjectivesId => {
      try {
        const res = await qualityobjectivesService().find(qualityobjectivesId);
        qualityobjectives.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.qualityobjectivesId) {
      retrieveQualityobjectives(route.params.qualityobjectivesId);
    }

    const initRelationships = () => {
      qualityreturnsService()
        .retrieve()
        .then(res => {
          qualityreturns.value = res.data;
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
      qualityobjectivesname: {},
      year: {},
      createtime: {},
      creatorname: {},
      secretlevel: {},
      auditStatus: {},
      qualityreturns: {},
      creatorid: {},
      auditorid: {},
    };
    const v$ = useVuelidate(validationRules, qualityobjectives as any);
    v$.value.$validate();

    return {
      qualityobjectivesService,
      alertService,
      qualityobjectives,
      previousState,
      secretlevelValues,
      auditStatusValues,
      isSaving,
      currentLanguage,
      qualityreturns,
      officers,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.qualityobjectives.id) {
        this.qualityobjectivesService()
          .update(this.qualityobjectives)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.qualityobjectives.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.qualityobjectivesService()
          .create(this.qualityobjectives)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.qualityobjectives.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
