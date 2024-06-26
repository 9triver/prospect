import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import PbssubmanageService from './pbssubmanage.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import { type IPbssubmanage, Pbssubmanage } from '@/shared/model/pbssubmanage.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PbssubmanageUpdate',
  setup() {
    const pbssubmanageService = inject('pbssubmanageService', () => new PbssubmanageService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const pbssubmanage: Ref<IPbssubmanage> = ref(new Pbssubmanage());

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrievePbssubmanage = async pbssubmanageId => {
      try {
        const res = await pbssubmanageService().find(pbssubmanageId);
        pbssubmanage.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.pbssubmanageId) {
      retrievePbssubmanage(route.params.pbssubmanageId);
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
    const v$ = useVuelidate(validationRules, pbssubmanage as any);
    v$.value.$validate();

    return {
      pbssubmanageService,
      alertService,
      pbssubmanage,
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
      if (this.pbssubmanage.id) {
        this.pbssubmanageService()
          .update(this.pbssubmanage)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.pbssubmanage.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.pbssubmanageService()
          .create(this.pbssubmanage)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.pbssubmanage.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
