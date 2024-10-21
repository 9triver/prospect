import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import FrontlineService from './frontline.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import { type IFrontline, Frontline } from '@/shared/model/frontline.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'FrontlineUpdate',
  setup() {
    const frontlineService = inject('frontlineService', () => new FrontlineService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const frontline: Ref<IFrontline> = ref(new Frontline());

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveFrontline = async frontlineId => {
      try {
        const res = await frontlineService().find(frontlineId);
        frontline.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.frontlineId) {
      retrieveFrontline(route.params.frontlineId);
    }

    const initRelationships = () => {
      officersService()
        .retrieve()
        .then(res => {
          officers.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      name: {},
      description: {},
      officers: {},
    };
    const v$ = useVuelidate(validationRules, frontline as any);
    v$.value.$validate();

    return {
      frontlineService,
      alertService,
      frontline,
      previousState,
      isSaving,
      currentLanguage,
      officers,
      v$,
      t$,
    };
  },
  created(): void {
    this.frontline.officers = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.frontline.id) {
        this.frontlineService()
          .update(this.frontline)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.frontline.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.frontlineService()
          .create(this.frontline)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.frontline.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },

    getSelected(selectedVals, option, pkField = 'id'): any {
      if (selectedVals) {
        return selectedVals.find(value => option[pkField] === value[pkField]) ?? option;
      }
      return option;
    },
  },
});
