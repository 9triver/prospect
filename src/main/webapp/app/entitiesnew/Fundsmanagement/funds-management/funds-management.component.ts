import { defineComponent, inject, onMounted, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { Check,  Delete,  Edit,  Message,  Search,  Star,} from '@element-plus/icons-vue';

import { useAlertService } from '@/shared/alert/alert.service';
import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import SubjectCostBudgetService from '../subject-cost-budget/subject-cost-budget.service';
import { type ISubjectCostBudget } from '@/shared/model/subject-cost-budget.model';
import ProjectBudgetService from '../project-budget/project-budget.service';
import { type IProjectBudget } from '@/shared/model/project-budget.model';
import SubjectService from '@/entitiesnew/Fundsmanagement/subject/subject.service';
import { type ISubject } from '@/shared/model/subject.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'FundsManagement',
  setup() {
    const { t: t$ } = useI18n();
    const alertService = inject('alertService', () => useAlertService(), true);
    //项目信息
    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());
    const projectwbs: Ref<IProjectwbs[]> = ref([]);
    const selectedProjectwbs = ref<IProjectwbs | null>(null);
    //科目经费信息
    const subjectCostBudgetService = inject('subjectCostBudgetService', () => new SubjectCostBudgetService());
    const subjectCostBudgets: Ref<ISubjectCostBudget[]> = ref([]);
    //系统内经费信息
    const projectBudgetService = inject('projectBudgetService', () => new ProjectBudgetService());
    const projectBudgets: Ref<IProjectBudget[]> = ref([]);
    //获取科目字典-选择type=2其他7个科目
    const subjectService = inject('subjectService', () => new SubjectService());
    const subjects: Ref<ISubject[]> = ref([]);


    const isFetching = ref(false);

    const clear = () => {};


    const retrieveFunds = async () => {
      isFetching.value = true;
      try {
        const projectwbsres = await projectwbsService().retrieve();
        projectwbs.value = projectwbsres.data;
        //只选择出重大项目
        projectwbs.value = projectwbsres.data.filter(projectwbs => projectwbs.parentwbsid === "" || projectwbs.parentwbsid === null || projectwbs.parentwbsid === undefined);

        // 如果 projectwbs 数组有值，默认选择第一个项目
        if (projectwbs.value.length > 0) {
          selectedProjectwbs.value = projectwbs.value[0];
        };

        const wbsid = selectedProjectwbs.value.id;
        //查询对应重大项目的科目经费信息
        const subjectcostbudgetres = await subjectCostBudgetService().query(wbsid);
        subjectCostBudgets.value = subjectcostbudgetres.data;
        //查询对应重大项目的系统内经费信息
        const projectbudgetres = await projectBudgetService().query(wbsid);
        projectBudgets.value = projectbudgetres.data;

      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    onMounted(async () => {
      await retrieveFunds();
    });

    // 默认显示“科目经费”Tab
    const activeTab = ref('subject');

    //条件查询
    const form = ref({
      wbsname:''
    })   
    const onSubmit = async () => {
      isFetching.value = true;
      try {
        //整数调整
        alert("sss");
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    return {
      isFetching,
      retrieveFunds,
      clear,
      t$,
      form,
      onSubmit,
      Edit,
      subjectCostBudgets,
      projectBudgets,
      projectwbs,
      selectedProjectwbs,
      subjects,
      subjectCostBudgetService,
      projectBudgetService,
      activeTab,
    };
  },
});
