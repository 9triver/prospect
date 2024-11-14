import type { IDepartment } from '@/shared/model/department.model';
import type { IOfficers } from '@/shared/model/officers.model';
import {type IProjectwbs} from '@/shared/model/projectwbs.model'

export interface ProgressPlanNew {
    id?: number; // 主键ID
    planname?: string; // 计划名称
    plantype?: string; // 计划类别
    planlevel?: string; // 计划层级
    time?: Date; // 条件具备时间
    planendtime?: Date; // 计划结束时间
    plancontent?: '',// 计划内容
    preplanname?: '',// 前置计划名称
    deliverable?: '',//交付物
    assistingperson?: '',// 协助人
    assistingdepartment?: '',// 协助部门
    keyplan?: false,// 是否关键计划
    risks?: ''// 风险
    projectwbs?: IProjectwbs | null; // 所属项目WBS
    responsibleperson?: IOfficers | null; // 责任人
    responsibledepartment?: IDepartment | null; // 责任部门
    coordinator?: IOfficers | null; // 配合人
    coordinatedepartment?: IDepartment | null; // 配合部门
  }