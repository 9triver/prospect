import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import KeyNodeInspectionService from './key-node-inspection.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IKeyNodeInspection, KeyNodeInspection } from '@/shared/model/key-node-inspection.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'KeyNodeInspectionUpdate',
  setup() {
    const keyNodeInspectionService = inject('keyNodeInspectionService', () => new KeyNodeInspectionService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const keyNodeInspection: Ref<IKeyNodeInspection> = ref(new KeyNodeInspection());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveKeyNodeInspection = async keyNodeInspectionId => {
      try {
        const res = await keyNodeInspectionService().find(keyNodeInspectionId);
        keyNodeInspection.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.keyNodeInspectionId) {
      retrieveKeyNodeInspection(route.params.keyNodeInspectionId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      name: {},
      workbagid: {},
      workbagname: {},
      belongwbsid: {},
      projectlevel: {},
      iskey: {},
      isimplementationplan: {},
      isqualityplan: {},
      istechniqueplan: {},
      implementationplanstatus: {},
      isimplementationplanmaterial: {},
      technologyplanstatus: {},
      istechnologymaterial: {},
      firstcheckstatus: {},
      isfirstcheckmaterial: {},
      productioncheckstatus: {},
      isproductioncheckmaterial: {},
      status: {},
    };
    const v$ = useVuelidate(validationRules, keyNodeInspection as any);
    v$.value.$validate();

    return {
      keyNodeInspectionService,
      alertService,
      keyNodeInspection,
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
      if (this.keyNodeInspection.id) {
        this.keyNodeInspectionService()
          .update(this.keyNodeInspection)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.keyNodeInspection.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.keyNodeInspectionService()
          .create(this.keyNodeInspection)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.keyNodeInspection.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
