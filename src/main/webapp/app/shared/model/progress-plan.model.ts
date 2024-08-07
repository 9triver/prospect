import { type IOfficers } from '@/shared/model/officers.model';
import { type IDepartment } from '@/shared/model/department.model';
import { type IPlanReturns } from '@/shared/model/plan-returns.model';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import { type IProjectRisk } from '@/shared/model/project-risk.model';

import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { type PlanLevel } from '@/shared/model/enumerations/plan-level.model';
import { type Progressstatus } from '@/shared/model/enumerations/progressstatus.model';
import { type Planstatus } from '@/shared/model/enumerations/planstatus.model';
import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IProgressPlan {
  id?: string;
  planname?: string | null;
  secretlevel?: keyof typeof Secretlevel | null;
  plantype?: number | null;
  planlevel?: keyof typeof PlanLevel | null;
  belongplanid?: string | null;
  planstage?: string | null;
  readytime?: Date | null;
  description?: string | null;
  deliverables?: string | null;
  planobjectives?: string | null;
  preplan?: string | null;
  starttime?: Date | null;
  endtime?: Date | null;
  actualstarttime?: Date | null;
  actualendtime?: Date | null;
  progress?: number | null;
  progresstype?: keyof typeof Progressstatus | null;
  iskey?: number | null;
  status?: keyof typeof Planstatus | null;
  auditStatus?: keyof typeof AuditStatus | null;
  remark?: string | null;
  responsibleperson?: IOfficers | null;
  cooperatingperson?: IOfficers | null;
  auditorid?: IOfficers | null;
  responsibledepartment?: IDepartment | null;
  cooperatingdepartment?: IDepartment | null;
  planReturns?: IPlanReturns | null;
  projectwbs?: IProjectwbs[] | null;
  projectRisks?: IProjectRisk[] | null;
}

export class ProgressPlan implements IProgressPlan {
  constructor(
    public id?: string,
    public planname?: string | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public plantype?: number | null,
    public planlevel?: keyof typeof PlanLevel | null,
    public planstage?: string | null,
    public readytime?: Date | null,
    public description?: string | null,
    public deliverables?: string | null,
    public planobjectives?: string | null,
    public preplan?: string | null,
    public starttime?: Date | null,
    public endtime?: Date | null,
    public actualstarttime?: Date | null,
    public actualendtime?: Date | null,
    public progress?: number | null,
    public progresstype?: keyof typeof Progressstatus | null,
    public iskey?: number | null,
    public status?: keyof typeof Planstatus | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public remark?: string | null,
    public responsibleperson?: IOfficers | null,
    public cooperatingperson?: IOfficers | null,
    public auditorid?: IOfficers | null,
    public responsibledepartment?: IDepartment | null,
    public cooperatingdepartment?: IDepartment | null,
    public planReturns?: IPlanReturns | null,
    public projectwbs?: IProjectwbs[] | null,
    public projectRisks?: IProjectRisk[] | null,
  ) {}
}
