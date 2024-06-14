import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import SafetycheckService from './safetycheck.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import { type ISafetycheck, Safetycheck } from '@/shared/model/safetycheck.model';
import { Risklevel } from '@/shared/model/enumerations/risklevel.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SafetycheckUpdate',
  setup() {
    const safetycheckService = inject('safetycheckService', () => new SafetycheckService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const safetycheck: Ref<ISafetycheck> = ref(new Safetycheck());

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);
    const risklevelValues: Ref<string[]> = ref(Object.keys(Risklevel));
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveSafetycheck = async safetycheckId => {
      try {
        const res = await safetycheckService().find(safetycheckId);
        safetycheck.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.safetycheckId) {
      retrieveSafetycheck(route.params.safetycheckId);
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
      safetycheckid: {},
      safetycheckname: {},
      checksource: {},
      checktime: {},
      effectivetime: {},
      operatinglocation: {},
      deprotment: {},
      phonenumber: {},
      risklevel: {},
      auditStatus: {},
      auditorid: {},
      responsibleid: {},
    };
    const v$ = useVuelidate(validationRules, safetycheck as any);
    v$.value.$validate();

    return {
      safetycheckService,
      alertService,
      safetycheck,
      previousState,
      risklevelValues,
      auditStatusValues,
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
      if (this.safetycheck.id) {
        this.safetycheckService()
          .update(this.safetycheck)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster3App.safetycheck.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.safetycheckService()
          .create(this.safetycheck)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster3App.safetycheck.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
