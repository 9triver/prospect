import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';

import DepartmentService from './department.service';
import { type IDepartment } from '@/shared/model/department.model';
import { useAlertService } from '@/shared/alert/alert.service';
//人员
import OfficersService from '../officers/officers.service';
import { json } from 'stream/consumers';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Department',
  setup() {
    const { t: t$ } = useI18n();
    const departmentService = inject('departmentService', () => new DepartmentService());
    const officersService = inject('officersService', () => new OfficersService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const departments: Ref<IDepartment[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveDepartments = async () => {
      isFetching.value = true;
      try {
        const res = await departmentService().retrieve();
        departments.value = res.data;
        // alert(JSON.stringify(departments.value));
        // // 遍历项目列表，根据每个项目的 人员id 获取并赋值 人员name，WBS也赋值name
        // departments.value.forEach(async department => {
        //   // 假设 departments.officers.id 是一个逗号分隔的字符串，例如 "1,2,3"
        //   if (department.officers && department.officers.length>0) {
        //     alert('11111111111');
        //     // 提取每个负责人对象的id字段，并将其拼接成一个字符串
        //     const officersnames = department.officers.map(officer => officer.officersname).join(', ');
        //     alert(officersnames);
        //     // 将拼接好的字符串赋值给部门对象的一个字段，比如officersids
        //     department.officersname = officersnames;
        //     // let officernames = []; // 用于存储负责人名称的数组
        //     // for (const officerString of officerStrings) {
        //     //   alert(officerString);
        //     //   try {
        //     //     const officerObj = JSON.parse(officerString); // 解析JSON字符串为对象
        //     //     if (officerObj.id) {
        //     //       const officersId = officernames.push(officerObj.id) as number;
        //     //       const res = await officersService().find(officersId);
        //     //       if (res && res.officersname) {
        //     //         officernames.push(res.officersname); // 将负责人名称添加到数组中
        //     //         alert(officernames);
        //     //       }
        //     //     }
        //     //   } catch (error) {
        //     //     alert(`项目负责人获取name时异常: ${departments.id}` + JSON.stringify(error));
        //     //     console.error(`Error fetching project details for id ${departments.id}:`, error);
        //     //   }
        //     //  // 将数组中的负责人名称用逗号隔开串联成一个字符串
        //     //  departments.officersname = officernames.join(', ');
        //     //  alert(`项目负责人获取name: ${departments.officersname}`);
        //     // }
        //   }
        // });
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveDepartments();
    };

    onMounted(async () => {
      await retrieveDepartments();
    });

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IDepartment) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeDepartment = async () => {
      try {
        await departmentService().delete(removeId.value);
        const message = t$('jHipster0App.department.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveDepartments();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    return {
      departments,
      handleSyncList,
      isFetching,
      retrieveDepartments,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeDepartment,
      t$,
    };
  },
});
