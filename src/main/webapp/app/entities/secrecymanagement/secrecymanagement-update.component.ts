import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import SecrecymanagementService from './secrecymanagement.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import { type ISecrecymanagement, Secrecymanagement } from '@/shared/model/secrecymanagement.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SecrecymanagementUpdate',
  setup() {
    const secrecymanagementService = inject('secrecymanagementService', () => new SecrecymanagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const secrecymanagement: Ref<ISecrecymanagement> = ref(new Secrecymanagement());

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveSecrecymanagement = async secrecymanagementId => {
      try {
        const res = await secrecymanagementService().find(secrecymanagementId);
        secrecymanagement.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.secrecymanagementId) {
      retrieveSecrecymanagement(route.params.secrecymanagementId);
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
      secrecyid: {
        integer: validations.integer(t$('entity.validation.number').toString()),
      },
      publishedby: {},
      documentname: {},
      documenttype: {},
      documentsize: {},
      secretlevel: {},
      auditStatus: {},
      creatorid: {},
      auditorid: {},
    };
    const v$ = useVuelidate(validationRules, secrecymanagement as any);
    v$.value.$validate();

    return {
      secrecymanagementService,
      alertService,
      secrecymanagement,
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
      if (this.secrecymanagement.id) {
        this.secrecymanagementService()
          .update(this.secrecymanagement)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster3App.secrecymanagement.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.secrecymanagementService()
          .create(this.secrecymanagement)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster3App.secrecymanagement.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
