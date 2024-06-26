import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import OutsourcingmanagementService from './outsourcingmanagement.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import OutsourcingmanagementWbsService from '@/entities/outsourcingmanagement-wbs/outsourcingmanagement-wbs.service';
import { type IOutsourcingmanagementWbs } from '@/shared/model/outsourcingmanagement-wbs.model';
import { type IOutsourcingmanagement, Outsourcingmanagement } from '@/shared/model/outsourcingmanagement.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OutsourcingmanagementUpdate',
  setup() {
    const outsourcingmanagementService = inject('outsourcingmanagementService', () => new OutsourcingmanagementService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const outsourcingmanagement: Ref<IOutsourcingmanagement> = ref(new Outsourcingmanagement());

    const outsourcingmanagementWbsService = inject('outsourcingmanagementWbsService', () => new OutsourcingmanagementWbsService());

    const outsourcingmanagementWbs: Ref<IOutsourcingmanagementWbs[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveOutsourcingmanagement = async outsourcingmanagementId => {
      try {
        const res = await outsourcingmanagementService().find(outsourcingmanagementId);
        outsourcingmanagement.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.outsourcingmanagementId) {
      retrieveOutsourcingmanagement(route.params.outsourcingmanagementId);
    }

    const initRelationships = () => {
      outsourcingmanagementWbsService()
        .retrieve()
        .then(res => {
          outsourcingmanagementWbs.value = res.data;
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
    const v$ = useVuelidate(validationRules, outsourcingmanagement as any);
    v$.value.$validate();

    return {
      outsourcingmanagementService,
      alertService,
      outsourcingmanagement,
      previousState,
      isSaving,
      currentLanguage,
      outsourcingmanagementWbs,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.outsourcingmanagement.id) {
        this.outsourcingmanagementService()
          .update(this.outsourcingmanagement)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.outsourcingmanagement.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.outsourcingmanagementService()
          .create(this.outsourcingmanagement)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.outsourcingmanagement.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
