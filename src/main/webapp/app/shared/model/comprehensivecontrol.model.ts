import { type IProgressplan } from '@/shared/model/progressplan.model';
import { type IProject } from '@/shared/model/project.model';
import { type IFundsmanagement } from '@/shared/model/fundsmanagement.model';
import { type ITotalbudget } from '@/shared/model/totalbudget.model';
import { type IUnitbudget } from '@/shared/model/unitbudget.model';
import { type IOfficers } from '@/shared/model/officers.model';
import { type IDepartment } from '@/shared/model/department.model';

import { type ProjectStatus } from '@/shared/model/enumerations/project-status.model';
import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IComprehensivecontrol {
  id?: string;
  description?: string | null;
  number?: number | null;
  type?: number | null;
  starttime?: Date | null;
  endtime?: Date | null;
  actualstarttime?: Date | null;
  actualendtime?: Date | null;
  result?: string | null;
  acceptancetype?: string | null;
  status?: keyof typeof ProjectStatus | null;
  auditStatus?: keyof typeof AuditStatus | null;
  responsiblename?: string | null;
  progress?: IProgressplan | null;
  project?: IProject | null;
  funds?: IFundsmanagement | null;
  totalbudget?: ITotalbudget | null;
  unitbudget?: IUnitbudget | null;
  responsibleid?: IOfficers | null;
  auditorid?: IOfficers | null;
  decument?: IDepartment | null;
  coordinationdepartment?: IDepartment | null;
}

export class Comprehensivecontrol implements IComprehensivecontrol {
  constructor(
    public id?: string,
    public description?: string | null,
    public number?: number | null,
    public type?: number | null,
    public starttime?: Date | null,
    public endtime?: Date | null,
    public actualstarttime?: Date | null,
    public actualendtime?: Date | null,
    public result?: string | null,
    public acceptancetype?: string | null,
    public status?: keyof typeof ProjectStatus | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public responsiblename?: string | null,
    public progress?: IProgressplan | null,
    public project?: IProject | null,
    public funds?: IFundsmanagement | null,
    public totalbudget?: ITotalbudget | null,
    public unitbudget?: IUnitbudget | null,
    public responsibleid?: IOfficers | null,
    public auditorid?: IOfficers | null,
    public decument?: IDepartment | null,
    public coordinationdepartment?: IDepartment | null,
  ) {}
}
