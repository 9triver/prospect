import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import DocumentmenuService from './documentmenu.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IDocumentmenu, Documentmenu } from '@/shared/model/documentmenu.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'DocumentmenuUpdate',
  setup() {
    const documentmenuService = inject('documentmenuService', () => new DocumentmenuService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const documentmenu: Ref<IDocumentmenu> = ref(new Documentmenu());
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveDocumentmenu = async documentmenuId => {
      try {
        const res = await documentmenuService().find(documentmenuId);
        documentmenu.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.documentmenuId) {
      retrieveDocumentmenu(route.params.documentmenuId);
    }

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      menuid: {
        required: validations.required(t$('entity.validation.required').toString()),
      },
      belongtype: {},
      menuname: {},
      parentmenuid: {},
      createtime: {},
      creatorid: {},
      creatorname: {},
      type: {},
      filenum: {},
      fileurl: {},
      departmentid: {},
      departmentname: {},
      spare1: {},
      spare2: {},
      spare3: {},
    };
    const v$ = useVuelidate(validationRules, documentmenu as any);
    v$.value.$validate();

    return {
      documentmenuService,
      alertService,
      documentmenu,
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
      if (this.documentmenu.id) {
        this.documentmenuService()
          .update(this.documentmenu)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.documentmenu.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.documentmenuService()
          .create(this.documentmenu)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.documentmenu.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
