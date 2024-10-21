import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import CommunicationDictionaryService from './communication-dictionary.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type ICommunicationDictionary, CommunicationDictionary } from '@/shared/model/communication-dictionary.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CommunicationDictionaryUpdate',
  setup() {
    const communicationDictionaryService = inject('communicationDictionaryService', () => new CommunicationDictionaryService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const communicationDictionary: Ref<ICommunicationDictionary> = ref(new CommunicationDictionary());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveCommunicationDictionary = async communicationDictionaryId => {
      try {
        const res = await communicationDictionaryService().find(communicationDictionaryId);
        communicationDictionary.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.communicationDictionaryId) {
      retrieveCommunicationDictionary(route.params.communicationDictionaryId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      partiesname: {},
      partiestype: {},
      partiesduty: {},
    };
    const v$ = useVuelidate(validationRules, communicationDictionary as any);
    v$.value.$validate();

    return {
      communicationDictionaryService,
      alertService,
      communicationDictionary,
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
      if (this.communicationDictionary.id) {
        this.communicationDictionaryService()
          .update(this.communicationDictionary)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.communicationDictionary.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.communicationDictionaryService()
          .create(this.communicationDictionary)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.communicationDictionary.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
