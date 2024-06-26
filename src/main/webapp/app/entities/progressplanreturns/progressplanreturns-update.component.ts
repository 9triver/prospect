import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ProgressplanreturnsService from './progressplanreturns.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ProgressplanService from '@/entities/progressplan/progressplan.service';
import { type IProgressplan } from '@/shared/model/progressplan.model';
import DocumentService from '@/entities/document/document.service';
import { type IDocument } from '@/shared/model/document.model';
import { type IProgressplanreturns, Progressplanreturns } from '@/shared/model/progressplanreturns.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ProgressplanreturnsUpdate',
  setup() {
    const progressplanreturnsService = inject('progressplanreturnsService', () => new ProgressplanreturnsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const progressplanreturns: Ref<IProgressplanreturns> = ref(new Progressplanreturns());

    const progressplanService = inject('progressplanService', () => new ProgressplanService());

    const progressplans: Ref<IProgressplan[]> = ref([]);

    const documentService = inject('documentService', () => new DocumentService());

    const documents: Ref<IDocument[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveProgressplanreturns = async progressplanreturnsId => {
      try {
        const res = await progressplanreturnsService().find(progressplanreturnsId);
        progressplanreturns.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.progressplanreturnsId) {
      retrieveProgressplanreturns(route.params.progressplanreturnsId);
    }

    const initRelationships = () => {
      progressplanService()
        .retrieve()
        .then(res => {
          progressplans.value = res.data;
        });
      documentService()
        .retrieve()
        .then(res => {
          documents.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      planstarttime: {},
      planendtime: {},
      returnstime: {},
      progressplan: {},
      document: {},
    };
    const v$ = useVuelidate(validationRules, progressplanreturns as any);
    v$.value.$validate();

    return {
      progressplanreturnsService,
      alertService,
      progressplanreturns,
      previousState,
      isSaving,
      currentLanguage,
      progressplans,
      documents,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.progressplanreturns.id) {
        this.progressplanreturnsService()
          .update(this.progressplanreturns)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.progressplanreturns.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.progressplanreturnsService()
          .create(this.progressplanreturns)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.progressplanreturns.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
