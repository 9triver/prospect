import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import PbsmanageService from './pbsmanage.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import PbssubmanageService from '@/entities/pbssubmanage/pbssubmanage.service';
import { type IPbssubmanage } from '@/shared/model/pbssubmanage.model';
import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import { type IPbsmanage, Pbsmanage } from '@/shared/model/pbsmanage.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'PbsmanageUpdate',
  setup() {
    const pbsmanageService = inject('pbsmanageService', () => new PbsmanageService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const pbsmanage: Ref<IPbsmanage> = ref(new Pbsmanage());

    const pbssubmanageService = inject('pbssubmanageService', () => new PbssubmanageService());

    const pbssubmanages: Ref<IPbssubmanage[]> = ref([]);

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrievePbsmanage = async pbsmanageId => {
      try {
        const res = await pbsmanageService().find(pbsmanageId);
        pbsmanage.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.pbsmanageId) {
      retrievePbsmanage(route.params.pbsmanageId);
    }

    const initRelationships = () => {
      pbssubmanageService()
        .retrieve()
        .then(res => {
          pbssubmanages.value = res.data;
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
      pbsname: {},
      number: {},
      type: {},
      starttime: {},
      endtime: {},
      administratorid: {},
      administratorname: {},
      responsiblename: {},
      department: {},
      secretlevel: {},
      auditStatus: {},
      auditUserid: {},
      pbssubmanage: {},
      responsibleid: {},
      auditorid: {},
    };
    const v$ = useVuelidate(validationRules, pbsmanage as any);
    v$.value.$validate();

    return {
      pbsmanageService,
      alertService,
      pbsmanage,
      previousState,
      secretlevelValues,
      auditStatusValues,
      isSaving,
      currentLanguage,
      pbssubmanages,
      officers,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.pbsmanage.id) {
        this.pbsmanageService()
          .update(this.pbsmanage)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.pbsmanage.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.pbsmanageService()
          .create(this.pbsmanage)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.pbsmanage.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
