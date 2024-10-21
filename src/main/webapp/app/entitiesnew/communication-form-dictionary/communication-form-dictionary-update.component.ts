import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import CommunicationFormDictionaryService from './communication-form-dictionary.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type ICommunicationFormDictionary, CommunicationFormDictionary } from '@/shared/model/communication-form-dictionary.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CommunicationFormDictionaryUpdate',
  setup() {
    const communicationFormDictionaryService = inject('communicationFormDictionaryService', () => new CommunicationFormDictionaryService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const communicationFormDictionary: Ref<ICommunicationFormDictionary> = ref(new CommunicationFormDictionary());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveCommunicationFormDictionary = async communicationFormDictionaryId => {
      try {
        const res = await communicationFormDictionaryService().find(communicationFormDictionaryId);
        communicationFormDictionary.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.communicationFormDictionaryId) {
      retrieveCommunicationFormDictionary(route.params.communicationFormDictionaryId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      communicationformname: {},
      communicationformtype: {},
      status: {},
    };
    const v$ = useVuelidate(validationRules, communicationFormDictionary as any);
    v$.value.$validate();

    return {
      communicationFormDictionaryService,
      alertService,
      communicationFormDictionary,
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
      if (this.communicationFormDictionary.id) {
        this.communicationFormDictionaryService()
          .update(this.communicationFormDictionary)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.communicationFormDictionary.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.communicationFormDictionaryService()
          .create(this.communicationFormDictionary)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.communicationFormDictionary.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
