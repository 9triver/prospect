import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import TechnicalService from './technical.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import { type ITechnical, Technical } from '@/shared/model/technical.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'TechnicalUpdate',
  setup() {
    const technicalService = inject('technicalService', () => new TechnicalService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const technical: Ref<ITechnical> = ref(new Technical());

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);

    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());

    const projectwbs: Ref<IProjectwbs[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveTechnical = async technicalId => {
      try {
        const res = await technicalService().find(technicalId);
        technical.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.technicalId) {
      retrieveTechnical(route.params.technicalId);
    }

    const initRelationships = () => {
      officersService()
        .retrieve()
        .then(res => {
          officers.value = res.data;
        });
      projectwbsService()
        .retrieve()
        .then(res => {
          projectwbs.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      name: {},
      description: {},
      starttime: {},
      endtime: {},
      creatorid: {},
      auditorid: {},
      projectwbs: {},
    };
    const v$ = useVuelidate(validationRules, technical as any);
    v$.value.$validate();

    return {
      technicalService,
      alertService,
      technical,
      previousState,
      isSaving,
      currentLanguage,
      officers,
      projectwbs,
      v$,
      t$,
    };
  },
  created(): void {
    this.technical.projectwbs = [];
  },
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.technical.id) {
        this.technicalService()
          .update(this.technical)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.technical.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.technicalService()
          .create(this.technical)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.technical.created', { param: param.id }).toString());
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
