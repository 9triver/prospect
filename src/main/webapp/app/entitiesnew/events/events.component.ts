import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import EventsService from './events.service';
import { type IEvents } from '@/shared/model/events.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Events',
  setup() {
    const { t: t$ } = useI18n();
    const eventsService = inject('eventsService', () => new EventsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const events: Ref<IEvents[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveEventss = async () => {
      isFetching.value = true;
      try {
        const res = await eventsService().retrieve();
        events.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveEventss();
    };

    onMounted(async () => {
      await retrieveEventss();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IEvents) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeEvents = async () => {
      try {
        await eventsService().delete(removeId.value);
        const message = t$('jy1App.events.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveEventss();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      events,
      handleSyncList,
      isFetching,
      retrieveEventss,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeEvents,
      t$,
    };
  },
});
