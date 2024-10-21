import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import LetterService from './letter.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import WorkbagService from '@/entities/workbag/workbag.service';
import { type IWorkbag } from '@/shared/model/workbag.model';
import FrontlineService from '@/entities/frontline/frontline.service';
import { type IFrontline } from '@/shared/model/frontline.model';
import DepartmentService from '@/entities/department/department.service';
import { type IDepartment } from '@/shared/model/department.model';
import HrManagementService from '@/entities/hr-management/hr-management.service';
import { type IHrManagement } from '@/shared/model/hr-management.model';
import { type ILetter, Letter } from '@/shared/model/letter.model';
import { Secretlevel } from '@/shared/model/enumerations/secretlevel.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'LetterUpdate',
  setup() {
    const letterService = inject('letterService', () => new LetterService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const letter: Ref<ILetter> = ref(new Letter());

    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());

    const projectwbs: Ref<IProjectwbs[]> = ref([]);

    const workbagService = inject('workbagService', () => new WorkbagService());

    const workbags: Ref<IWorkbag[]> = ref([]);

    const frontlineService = inject('frontlineService', () => new FrontlineService());

    const frontlines: Ref<IFrontline[]> = ref([]);

    const departmentService = inject('departmentService', () => new DepartmentService());

    const departments: Ref<IDepartment[]> = ref([]);

    const hrManagementService = inject('hrManagementService', () => new HrManagementService());

    const hrManagements: Ref<IHrManagement[]> = ref([]);
    const secretlevelValues: Ref<string[]> = ref(Object.keys(Secretlevel));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveLetter = async letterId => {
      try {
        const res = await letterService().find(letterId);
        letter.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.letterId) {
      retrieveLetter(route.params.letterId);
    }

    const initRelationships = () => {
      projectwbsService()
        .retrieve()
        .then(res => {
          projectwbs.value = res.data;
        });
      workbagService()
        .retrieve()
        .then(res => {
          workbags.value = res.data;
        });
      frontlineService()
        .retrieve()
        .then(res => {
          frontlines.value = res.data;
        });
      departmentService()
        .retrieve()
        .then(res => {
          departments.value = res.data;
        });
      hrManagementService()
        .retrieve()
        .then(res => {
          hrManagements.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      lettername: {},
      letternumber: {},
      lettertype: {},
      secretlevel: {},
      lettercontent: {},
      letterstatus: {},
      lettertime: {},
      previousfile: {},
      datarecordstatus: {},
      wbsid: {},
      workbagid: {},
      frontlineid: {},
      receivingunit: {},
      sendingunit: {},
      lettermaker: {},
      letterreceiver: {},
      letterhandler: {},
    };
    const v$ = useVuelidate(validationRules, letter as any);
    v$.value.$validate();

    return {
      letterService,
      alertService,
      letter,
      previousState,
      secretlevelValues,
      isSaving,
      currentLanguage,
      projectwbs,
      workbags,
      frontlines,
      departments,
      hrManagements,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.letter.id) {
        this.letterService()
          .update(this.letter)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.letter.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.letterService()
          .create(this.letter)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.letter.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
