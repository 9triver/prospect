import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import SystemLevelService from './system-level.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type ISystemLevel, SystemLevel } from '@/shared/model/system-level.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SystemLevelUpdate',
  setup() {
    const systemLevelService = inject('systemLevelService', () => new SystemLevelService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const systemLevel: Ref<ISystemLevel> = ref(new SystemLevel());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveSystemLevel = async systemLevelId => {
      try {
        const res = await systemLevelService().find(systemLevelId);
        systemLevel.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.systemLevelId) {
      retrieveSystemLevel(route.params.systemLevelId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      name: {},
    };
    const v$ = useVuelidate(validationRules, systemLevel as any);
    v$.value.$validate();

    return {
      systemLevelService,
      alertService,
      systemLevel,
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
      if (this.systemLevel.id) {
        this.systemLevelService()
          .update(this.systemLevel)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.systemLevel.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.systemLevelService()
          .create(this.systemLevel)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.systemLevel.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
