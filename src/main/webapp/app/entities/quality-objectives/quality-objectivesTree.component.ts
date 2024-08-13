import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { Check,  Delete,  Edit,  Message,  Search,  Star,} from '@element-plus/icons-vue'

import QualityObjectivesService from './quality-objectives.service';
import { type IQualityObjectives } from '@/shared/model/quality-objectives.model';
import { useAlertService } from '@/shared/alert/alert.service';
//人员
import OfficersService from '../officers/officers.service';
//部门
import DepartmentService from '../department/department.service';
import type { FormInstance } from 'element-plus'
export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'QualityObjectives',
  setup() {
    const { t: t$ } = useI18n();
    const qualityObjectivesService = inject('qualityObjectivesService', () => new QualityObjectivesService());
    const alertService = inject('alertService', () => useAlertService(), true);
    
    const officersService = inject('officersService', () => new OfficersService());
    const departmentService = inject('departmentService', () => new DepartmentService());

    const qualityObjectives: Ref<IQualityObjectives[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {};

    const retrieveQualityObjectivess = async () => {
      isFetching.value = true;
      try {
        const res = await qualityObjectivesService().retrieve();
        // alert(JSON.stringify(res));
        if(res.data){
          qualityObjectives.value = res.data ;
          // 查询关联表补充细节
          const resData = details(qualityObjectives.value); 
          qualityObjectives.value = resData.value;
        } else {
          // 如果响应没有数据，可以做出适当的处理
          console.error('Retrieve Projectpbs: Response data is empty');
        }
      } catch (err) {
        // 捕获和处理 HTTP 请求错误
        console.error('Error retrieving Projectpbs:', err);
        alertService.showHttpError(err.response);
      } finally {
        setTimeout(()=>{
          isFetching.value = false;
        },1000)
      }
    };

    const queryFormRef = ref<FormInstance>()

    const handleSyncList = (formRef:FormInstance|undefined) => {
      // 清空字段
      formRef?.resetFields()
      retrieveQualityObjectivess();
    }

    onMounted(async () => {
      await retrieveQualityObjectivess();
    });

    const details = (flatData: IQualityObjectives[]) => {
      // 遍历项目列表，根据每个项目的 人员id 获取并赋值 人员name，WBS也赋值name
      qualityObjectives.value.forEach(async quality => {
        // alert("----查询关联表名称----")
        // 项目技术负责人——检查 quality.responsibleperson.id 是否存在且不为空
        if (quality.responsibleperson && quality.responsibleperson.id) {
          try {
            const officersId = quality.responsibleperson.id as number; // 使用as语法
            const res = await officersService().find(officersId);
            // alert(JSON.stringify(res));
            // alert(`名称是 ：${res.name}`);
            if (res && res.name) {
              quality.responsibleperson.name = res.name;
            }
          } catch (error) {
            alert(`项目技术负责人获取name时异常: ${quality.id}` + JSON.stringify(error));
            console.error(`Error fetching quality details for id ${quality.id}:`, error);
          }
        }
        // 项目审核人——检查 quality.auditorid.id 是否存在且不为空
        if (quality.auditorid && quality.auditorid.id) {
          try {
            const officersId = quality.auditorid.id as number; // 使用as语法
            const res = await officersService().find(officersId);
            // alert(`名称是 ：${res.name}`);
            if (res && res.name) {
              quality.auditorid.name = res.name;
            }
          } catch (error) {
            alert(`项目审核人获取name时异常: ${quality.id}` + JSON.stringify(error));
            console.error(`Error fetching quality details for id ${quality.id}:`, error);
          }
        }
        // alert(JSON.stringify(qualityObjectives.value));
      });
      return qualityObjectives;
    }

    //条件查询
    const form = ref({
      id: '',
      name: '',
      objectives: '',
      qualitytype: '',
      secretlevel: '',
      target: '',
      statisticalmethod:'',
      statisticalfrequency:'',
      istarget:'',
      description:'',
      progress:'',
      problems:'',
      priorty:'',
      improvementmeasures:'',
      returntime: '',
      createtime:'',
      auditStatus:''
    })   
    const onSubmit = async () => {
      isFetching.value = true;
      try {
        const res = await qualityObjectivesService().query(form.value);
        qualityObjectives.value = res.data;
        alert(JSON.stringify(qualityObjectives.value))
        // 查询关联表补充细节
        const resData = details(qualityObjectives.value);    
        qualityObjectives.value = resData.value;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const removeId: Ref<string> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IQualityObjectives) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeQualityObjectives = async () => {
      try {
        await qualityObjectivesService().delete(removeId.value);
        const message = t$('jy1App.qualityObjectives.deleted', { param: removeId.value }).toString();
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveQualityObjectivess();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };
    

    return {
      qualityObjectives,
      handleSyncList,
      isFetching,
      retrieveQualityObjectivess,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeQualityObjectives,
      t$,
      form,
      onSubmit,
      Edit,
      queryFormRef,
    };
  },
});