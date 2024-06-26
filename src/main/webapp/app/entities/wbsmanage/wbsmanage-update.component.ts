import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import WbsmanageService from './wbsmanage.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import WbssubmanageService from '@/entities/wbssubmanage/wbssubmanage.service';
import { type IWbssubmanage } from '@/shared/model/wbssubmanage.model';
import PbssubmanageService from '@/entities/pbssubmanage/pbssubmanage.service';
import { type IPbssubmanage } from '@/shared/model/pbssubmanage.model';
import ProjectService from '@/entities/project/project.service';
import { type IProject } from '@/shared/model/project.model';
import OfficersService from '@/entities/officers/officers.service';
import { type IOfficers } from '@/shared/model/officers.model';
import { type IWbsmanage, Wbsmanage } from '@/shared/model/wbsmanage.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { AuditStatus } from '@/shared/model/enumerations/audit-status.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'WbsmanageUpdate',
  setup() {
    const wbsmanageService = inject('wbsmanageService', () => new WbsmanageService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const wbsmanage: Ref<IWbsmanage> = ref(new Wbsmanage());

    const wbssubmanageService = inject('wbssubmanageService', () => new WbssubmanageService());

    const wbssubmanages: Ref<IWbssubmanage[]> = ref([]);

    const pbssubmanageService = inject('pbssubmanageService', () => new PbssubmanageService());

    const pbssubmanages: Ref<IPbssubmanage[]> = ref([]);

    const projectService = inject('projectService', () => new ProjectService());

    const projects: Ref<IProject[]> = ref([]);

    const officersService = inject('officersService', () => new OfficersService());

    const officers: Ref<IOfficers[]> = ref([]);
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const auditStatusValues: Ref<string[]> = ref(Object.keys(AuditStatus));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveWbsmanage = async wbsmanageId => {
      try {
        const res = await wbsmanageService().find(wbsmanageId);
        wbsmanage.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.wbsmanageId) {
      retrieveWbsmanage(route.params.wbsmanageId);
    }

    const initRelationships = () => {
      wbssubmanageService()
        .retrieve()
        .then(res => {
          wbssubmanages.value = res.data;
        });
      pbssubmanageService()
        .retrieve()
        .then(res => {
          pbssubmanages.value = res.data;
        });
      projectService()
        .retrieve()
        .then(res => {
          projects.value = res.data;
        });
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
      wbsname: {},
      description: {},
      result: {},
      administratorname: {},
      responsiblename: {},
      department: {},
      secretlevel: {},
      auditStatus: {},
      wbssubmanage: {},
      pbssubmanage: {},
      project: {},
      administratorid: {},
      auditorid: {},
      responsibleid: {},
    };
    const v$ = useVuelidate(validationRules, wbsmanage as any);
    v$.value.$validate();

    return {
      wbsmanageService,
      alertService,
      wbsmanage,
      previousState,
      secretlevelValues,
      auditStatusValues,
      isSaving,
      currentLanguage,
      wbssubmanages,
      pbssubmanages,
      projects,
      officers,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.wbsmanage.id) {
        this.wbsmanageService()
          .update(this.wbsmanage)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster0App.wbsmanage.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.wbsmanageService()
          .create(this.wbsmanage)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster0App.wbsmanage.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
