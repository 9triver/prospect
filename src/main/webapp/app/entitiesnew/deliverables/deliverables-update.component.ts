import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import DeliverablesService from './deliverables.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IDeliverables, Deliverables } from '@/shared/model/deliverables.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'DeliverablesUpdate',
  setup() {
    const deliverablesService = inject('deliverablesService', () => new DeliverablesService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const deliverables: Ref<IDeliverables> = ref(new Deliverables());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveDeliverables = async deliverablesId => {
      try {
        const res = await deliverablesService().find(deliverablesId);
        deliverables.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.deliverablesId) {
      retrieveDeliverables(route.params.deliverablesId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      code: {},
      name: {},
      parentcode: {},
      level: {},
      status: {},
      description: {},
    };
    const v$ = useVuelidate(validationRules, deliverables as any);
    v$.value.$validate();

    return {
      deliverablesService,
      alertService,
      deliverables,
      previousState,
      isSaving,
      currentLanguage,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.deliverables.id) {
        this.deliverablesService()
          .update(this.deliverables)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.deliverables.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.deliverablesService()
          .create(this.deliverables)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.deliverables.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
