import { defineComponent, provide } from 'vue';

import OfficersService from './officers/officers.service';
import DepartmentService from './department/department.service';
import ProjectpbsService from './projectpbs/projectpbs.service';
import ProjectwbsService from './projectwbs/projectwbs.service';
import RoleService from './role/role.service';
import PermissionService from './permission/permission.service';
import DocumentService from './document/document.service';
import ProgressPlanService from './progress-plan/progress-plan.service';
import PlanReturnsService from './plan-returns/plan-returns.service';
import FundsEstimationService from './funds-estimation/funds-estimation.service';
import ContractService from './contract/contract.service';
import ContractCostBudgetService from './contract-cost-budget/contract-cost-budget.service';
import CostControlSystemService from './cost-control-system/cost-control-system.service';
import QualityObjectivesService from './quality-objectives/quality-objectives.service';
import QualityReturnsService from './quality-returns/quality-returns.service';
import UnQualityAuditService from './un-quality-audit/un-quality-audit.service';
import OutsourcingContractualService from './outsourcing-contractual/outsourcing-contractual.service';
import OutsourcingPurchasePlanService from './outsourcing-purchase-plan/outsourcing-purchase-plan.service';
import OutsourcingPurchaseExecuteService from './outsourcing-purchase-execute/outsourcing-purchase-execute.service';
import ProjectremitService from './projectremit/projectremit.service';
import TechnicalService from './technical/technical.service';
import TechnicalConditionService from './technical-condition/technical-condition.service';
import ProjectRiskService from './project-risk/project-risk.service';
import RiskReportService from './risk-report/risk-report.service';
import UserService from '@/entities/user/user.service';
// jhipster-needle-add-entity-service-to-entities-component-import - JHipster will import entities services here

import useMenuTabStore from '@/store/model/menuTabs'
import {storeToRefs} from 'pinia'
export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Entities',
  setup() {
    provide('userService', () => new UserService());
    provide('officersService', () => new OfficersService());
    provide('departmentService', () => new DepartmentService());
    provide('projectpbsService', () => new ProjectpbsService());
    provide('projectwbsService', () => new ProjectwbsService());
    provide('roleService', () => new RoleService());
    provide('permissionService', () => new PermissionService());
    provide('documentService', () => new DocumentService());
    provide('progressPlanService', () => new ProgressPlanService());
    provide('planReturnsService', () => new PlanReturnsService());
    provide('fundsEstimationService', () => new FundsEstimationService());
    provide('contractService', () => new ContractService());
    provide('contractCostBudgetService', () => new ContractCostBudgetService());
    provide('costControlSystemService', () => new CostControlSystemService());
    provide('qualityObjectivesService', () => new QualityObjectivesService());
    provide('qualityReturnsService', () => new QualityReturnsService());
    provide('unQualityAuditService', () => new UnQualityAuditService());
    provide('outsourcingContractualService', () => new OutsourcingContractualService());
    provide('outsourcingPurchasePlanService', () => new OutsourcingPurchasePlanService());
    provide('outsourcingPurchaseExecuteService', () => new OutsourcingPurchaseExecuteService());
    provide('projectremitService', () => new ProjectremitService());
    provide('technicalService', () => new TechnicalService());
    provide('technicalConditionService', () => new TechnicalConditionService());
    provide('projectRiskService', () => new ProjectRiskService());
    provide('riskReportService', () => new RiskReportService());
    // jhipster-needle-add-entity-service-to-entities-component - JHipster will import entities services here
  },
  computed:{
    openMenus(){
      const menuTabStore = useMenuTabStore()
      const {menuTab} = storeToRefs(menuTabStore)
      // 要实现只有当前已经打开的页签会被缓存，又因为打开keep-alive是通过打开的组件名来判断是否进行缓存
      // 我们这里拿到的已打开的页签只是路由的名字，并不能对应其组件的名字，
      // 例如，xxx实体 
      // 查询界面的路由名叫：xxx，组件名叫：xxx；
      // 编辑卡界面的路由名：xxxEdit，组件名叫：xxxUpdate
      // 新增界面的路由名叫：xxxCreate，组件名叫：xxxUpdate
      // 查看界面的路由名叫：xxxView，组件名叫：xxxDetails
      // 我们的页签里面保存的正是路由的名字，所以要根据路由的名字转化为组件的名字
      const parseMenuName = (name:string)=>{
        if(name.endsWith("Edit")){  
          return name.split("Edit")[0]+"Update"
        }
        if(name.endsWith("Create")){  
          return name.split("Create")[0]+"Update"
        }
        if(name.endsWith("View")){  
          return name.split("View")[0]+"Details"
        }
        return name
      }
      
      return menuTab.value.openMenus.map(menu=>parseMenuName(menu.name))
    }
  }
});
