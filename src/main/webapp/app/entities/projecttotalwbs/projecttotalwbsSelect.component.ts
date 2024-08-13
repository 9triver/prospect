
import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useRouter } from 'vue-router';
import { useI18n } from 'vue-i18n';

import ProjecttotalwbsService from './projecttotalwbs.service';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import { useAlertService } from '@/shared/alert/alert.service';
import { ElTable } from 'element-plus';
// import { json } from 'stream/consumers';

export default defineComponent({
  name: 'ProjectwbsSelect',
  setup() {
    const { t: t$ } = useI18n();
    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());
    const alertService = inject('alertService', () => useAlertService(), true);
    const router = useRouter();

    const projectwbs = ref<IProjectwbs[]>([]);
    const isFetching = ref(false);

    const handleSyncList = async () => {
      isFetching.value = true;
      try {
        const response = await projectwbsService().retrieve();
        if (response.data) {
          projectwbs.value = response.data;
          await fetchAdditionalData();
        }
      } catch (error) {
        alertService.showHttpError(error.response);
      } finally {
        isFetching.value = false;
      }
    };

    const fetchAdditionalData = async () => {
      for (let i = 0; i < projectwbs.value.length; i++) {
        await fetchOfficerName(projectwbs.value[i]);
        await fetchDepartmentName(projectwbs.value[i]);
      }
    };

    const fetchOfficerName = async (projectwbsItem: IProjectwbs) => {
      if (projectwbsItem.responsibleid?.id) {
        try {
          const officerId = projectwbsItem.responsibleid.id;
          const response = await officersService().find(officerId);
          if (response.officersname) {
            projectwbsItem.responsibleid.name = response.officersname;
          }
        } catch (error) {
          console.error(`Error fetching officer details for id ${projectwbsItem.responsibleid.id}:`, error);
        }
      }
    };

    const fetchDepartmentName = async (projectwbsItem: IProjectwbs) => {
      if (projectwbsItem.department?.id) {
        try {
          const departmentId = projectwbsItem.department.id;
          const response = await departmentService().find(departmentId);
          if (response.departmentname) {
            projectwbsItem.department.name = response.departmentname;
          }
        } catch (error) {
          console.error(`Error fetching department details for id ${projectwbsItem.department.id}:`, error);
        }
      }
    };

    const toggleSelection = () => {
      // Logic to clear selected items or perform other actions
    };

    return {
      t$,
      projectwbs,
      isFetching,
      handleSyncList,
      toggleSelection,
    };
  },
});
