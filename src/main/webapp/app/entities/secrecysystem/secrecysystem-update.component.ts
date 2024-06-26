import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import SecrecysystemService from './secrecysystem.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import { type ISecrecysystem, Secrecysystem } from '@/shared/model/secrecysystem.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SecrecysystemUpdate',
  setup() {
    const secrecysystemService = inject('secrecysystemService', () => new SecrecysystemService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const secrecysystem: Ref<ISecrecysystem> = ref(new Secrecysystem());

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveSecrecysystem = async secrecysystemId => {
      try {
        const res = await secrecysystemService().find(secrecysystemId);
        secrecysystem.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.secrecysystemId) {
      retrieveSecrecysystem(route.params.secrecysystemId);
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
      publishedby: {},
      documentname: {},
      documenttype: {},
      documentsize: {},
      secretlevel: {},
      auditStatus: {},
      creatorid: {},
      auditorid: {},
    };
    const v$ = useVuelidate(validationRules, secrecysystem as any);
    v$.value.$validate();

    return {
      secrecysystemService,
      alertService,
      secrecysystem,
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
      if (this.secrecysystem.id) {
        this.secrecysystemService()
          .update(this.secrecysystem)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.secrecysystem.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.secrecysystemService()
          .create(this.secrecysystem)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.secrecysystem.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
