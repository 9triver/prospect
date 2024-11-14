import { defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

import SubjectService from './subject.service';
import { type ISubject } from '@/shared/model/subject.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SubjectDetails',
  setup() {
    const subjectService = inject('subjectService', () => new SubjectService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const subject: Ref<ISubject> = ref({});

    const retrieveSubject = async subjectId => {
      try {
        const res = await subjectService().find(subjectId);
        subject.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.subjectId) {
      retrieveSubject(route.params.subjectId);
    }

    return {
      alertService,
      subject,

      previousState,
      t$: useI18n().t,
    };
  },
});
