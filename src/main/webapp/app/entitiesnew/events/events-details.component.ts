import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import EventsService from './events.service';
import { type IEvents } from '@/shared/model/events.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'EventsDetails',
  setup() {
    const eventsService = inject('eventsService', () => new EventsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const events: Ref<IEvents> = ref({});

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

    return {
      alertService,
      events,

      previousState,
      t$: useI18n().t,
    };
  },
});
