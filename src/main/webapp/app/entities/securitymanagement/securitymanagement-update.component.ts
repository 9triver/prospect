import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import SecuritymanagementService from './securitymanagement.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import SecuritymanagementWbsService from '@/entities/securitymanagement-wbs/securitymanagement-wbs.service';
import { type ISecuritymanagementWbs } from '@/shared/model/securitymanagement-wbs.model';
import { type ISecuritymanagement, Securitymanagement } from '@/shared/model/securitymanagement.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SecuritymanagementUpdate',
  setup() {
    const securitymanagementService = inject('securitymanagementService', () => new SecuritymanagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const securitymanagement: Ref<ISecuritymanagement> = ref(new Securitymanagement());

    const securitymanagementWbsService = inject('securitymanagementWbsService', () => new SecuritymanagementWbsService());

    const securitymanagementWbs: Ref<ISecuritymanagementWbs[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveSecuritymanagement = async securitymanagementId => {
      try {
        const res = await securitymanagementService().find(securitymanagementId);
        securitymanagement.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.securitymanagementId) {
      retrieveSecuritymanagement(route.params.securitymanagementId);
    }

    const initRelationships = () => {
      securitymanagementWbsService()
        .retrieve()
        .then(res => {
          securitymanagementWbs.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      name: {},
      description: {},
      starttime: {},
      endtime: {},
      wbs: {},
    };
    const v$ = useVuelidate(validationRules, securitymanagement as any);
    v$.value.$validate();

    return {
      securitymanagementService,
      alertService,
      securitymanagement,
      previousState,
      isSaving,
      currentLanguage,
      securitymanagementWbs,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.securitymanagement.id) {
        this.securitymanagementService()
          .update(this.securitymanagement)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.securitymanagement.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.securitymanagementService()
          .create(this.securitymanagement)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.securitymanagement.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
