export interface ProgressPlanNew {
    id?: number; // 主键ID
    planname: string; // 计划名称
    wbsname?: string; // WBS名称
    wbsid?: string; // WBSID
    plantype?: string; // 计划类别
    planlevel?: number | null; // 计划层级
    time?: Date; // 条件具备时间
    planendtime?: Date; // 计划结束时间
    responsiblepersonname?: string; // 责任人名称
    responsiblepersonid?: string; // 责任人ID
    responsibledpartmentname?: string; // 责任部门名称
    responsibledpartmentid?: string; // 责任部门ID
  }