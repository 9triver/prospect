import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import SubjectService from './subject.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type ISubject, Subject } from '@/shared/model/subject.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SubjectUpdate',
  setup() {
    const subjectService = inject('subjectService', () => new SubjectService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const subject: Ref<ISubject> = ref(new Subject());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

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

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      name: {},
      type: {},
      parentid: {},
      remark: {},
    };
    const v$ = useVuelidate(validationRules, subject as any);
    v$.value.$validate();

    return {
      subjectService,
      alertService,
      subject,
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
      if (this.subject.id) {
        this.subjectService()
          .update(this.subject)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.subject.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.subjectService()
          .create(this.subject)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.subject.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
