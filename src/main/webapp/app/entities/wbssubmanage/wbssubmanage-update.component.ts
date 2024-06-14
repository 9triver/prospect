import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import WbssubmanageService from './wbssubmanage.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import { type IWbssubmanage, Wbssubmanage } from '@/shared/model/wbssubmanage.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'WbssubmanageUpdate',
  setup() {
    const wbssubmanageService = inject('wbssubmanageService', () => new WbssubmanageService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const wbssubmanage: Ref<IWbssubmanage> = ref(new Wbssubmanage());

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveWbssubmanage = async wbssubmanageId => {
      try {
        const res = await wbssubmanageService().find(wbssubmanageId);
        wbssubmanage.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.wbssubmanageId) {
      retrieveWbssubmanage(route.params.wbssubmanageId);
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
      pbssubid: {},
      pbssubname: {},
      responsiblename: {},
      responsibledepartment: {},
      relevantdepartment: {},
      type: {},
      starttime: {},
      endtime: {},
      secretlevel: {},
      auditStatus: {},
      responsibleid: {},
      auditorid: {},
    };
    const v$ = useVuelidate(validationRules, wbssubmanage as any);
    v$.value.$validate();

    return {
      wbssubmanageService,
      alertService,
      wbssubmanage,
      previousState,
      secretlevelValues,
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
      if (this.wbssubmanage.id) {
        this.wbssubmanageService()
          .update(this.wbssubmanage)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster3App.wbssubmanage.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.wbssubmanageService()
          .create(this.wbssubmanage)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster3App.wbssubmanage.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
