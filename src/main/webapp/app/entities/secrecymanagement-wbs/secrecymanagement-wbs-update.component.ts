import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import SecrecymanagementWbsService from './secrecymanagement-wbs.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import SecrecysystemService from '@/entities/secrecysystem/secrecysystem.service';
import { type ISecrecysystem } from '@/shared/model/secrecysystem.model';
import ProjectSecrecyService from '@/entities/project-secrecy/project-secrecy.service';
import { type IProjectSecrecy } from '@/shared/model/project-secrecy.model';
import { type ISecrecymanagementWbs, SecrecymanagementWbs } from '@/shared/model/secrecymanagement-wbs.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SecrecymanagementWbsUpdate',
  setup() {
    const secrecymanagementWbsService = inject('secrecymanagementWbsService', () => new SecrecymanagementWbsService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const secrecymanagementWbs: Ref<ISecrecymanagementWbs> = ref(new SecrecymanagementWbs());

    const secrecysystemService = inject('secrecysystemService', () => new SecrecysystemService());

    const secrecysystems: Ref<ISecrecysystem[]> = ref([]);

    const projectSecrecyService = inject('projectSecrecyService', () => new ProjectSecrecyService());

    const projectSecrecies: Ref<IProjectSecrecy[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveSecrecymanagementWbs = async secrecymanagementWbsId => {
      try {
        const res = await secrecymanagementWbsService().find(secrecymanagementWbsId);
        secrecymanagementWbs.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.secrecymanagementWbsId) {
      retrieveSecrecymanagementWbs(route.params.secrecymanagementWbsId);
    }

    const initRelationships = () => {
      secrecysystemService()
        .retrieve()
        .then(res => {
          secrecysystems.value = res.data;
        });
      projectSecrecyService()
        .retrieve()
        .then(res => {
          projectSecrecies.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      wbsspare1: {},
      wbsspare2: {},
      wbsspare3: {},
      wbsspare4: {},
      wbsspare5: {},
      secrecysystem: {},
      projectSecrecy: {},
    };
    const v$ = useVuelidate(validationRules, secrecymanagementWbs as any);
    v$.value.$validate();

    return {
      secrecymanagementWbsService,
      alertService,
      secrecymanagementWbs,
      previousState,
      isSaving,
      currentLanguage,
      secrecysystems,
      projectSecrecies,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.secrecymanagementWbs.id) {
        this.secrecymanagementWbsService()
          .update(this.secrecymanagementWbs)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.secrecymanagementWbs.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.secrecymanagementWbsService()
          .create(this.secrecymanagementWbs)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.secrecymanagementWbs.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
