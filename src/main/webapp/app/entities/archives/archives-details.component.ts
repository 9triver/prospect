import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import ArchivesService from './archives.service';
import { type IArchives } from '@/shared/model/archives.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ArchivesDetails',
  setup() {
    const archivesService = inject('archivesService', () => new ArchivesService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const archives: Ref<IArchives> = ref({});

    const retrieveArchives = async archivesId => {
      try {
        const res = await archivesService().find(archivesId);
        archives.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.archivesId) {
      retrieveArchives(route.params.archivesId);
    }

    return {
      alertService,
      archives,

      previousState,
      t$: useI18n().t,
    };
  },
});
