import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import EventsService from './events.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IEvents, Events } from '@/shared/model/events.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'EventsUpdate',
  setup() {
    const eventsService = inject('eventsService', () => new EventsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const events: Ref<IEvents> = ref(new Events());
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveEvents = async eventsId => {
      try {
        const res = await eventsService().find(eventsId);
        events.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.eventsId) {
      retrieveEvents(route.params.eventsId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      title: {},
      content: {},
      time: {},
      place: {},
      participants: {},
      picture: {},
      description: {},
      secretlevel: {},
      attachment: {},
    };
    const v$ = useVuelidate(validationRules, events as any);
    v$.value.$validate();

    return {
      eventsService,
      alertService,
      events,
      previousState,
      secretlevelValues,
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
      if (this.events.id) {
        this.eventsService()
          .update(this.events)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.events.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.eventsService()
          .create(this.events)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.events.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
