import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import OfficersService from './officers.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import DepartmentService from '@/entities/department/department.service';
import { type IDepartment } from '@/shared/model/department.model';
import RoleService from '@/entities/role/role.service';
import { type IRole } from '@/shared/model/role.model';
import { type IOfficers, Officers } from '@/shared/model/officers.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'OfficersUpdate',
  setup() {
    const officersService = inject('officersService', () => new OfficersService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const officers: Ref<IOfficers> = ref(new Officers());

    const departmentService = inject('departmentService', () => new DepartmentService());

    const departments: Ref<IDepartment[]> = ref([]);

    const roleService = inject('roleService', () => new RoleService());

    const roles: Ref<IRole[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveOfficers = async officersId => {
      try {
        const res = await officersService().find(officersId);
        officers.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.officersId) {
      retrieveOfficers(route.params.officersId);
    }

    const initRelationships = () => {
      departmentService()
        .retrieve()
        .then(res => {
          departments.value = res.data;
        });
      roleService()
        .retrieve()
        .then(res => {
          roles.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      officersid: {},
      officersname: {},
      password: {},
      email: {},
      phone: {},
      department: {},
      role: {},
    };
    const v$ = useVuelidate(validationRules, officers as any);
    v$.value.$validate();

    return {
      officersService,
      alertService,
      officers,
      previousState,
      isSaving,
      currentLanguage,
      departments,
      roles,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.officers.id) {
        this.officersService()
          .update(this.officers)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jHipster3App.officers.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.officersService()
          .create(this.officers)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jHipster3App.officers.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
