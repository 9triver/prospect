import { type IDocument } from '@/shared/model/document.model';
import { type IAnnualplan } from '@/shared/model/annualplan.model';
import { type IMonthplan } from '@/shared/model/monthplan.model';
import { type IProjectcharge } from '@/shared/model/projectcharge.model';
import { type IOfficers } from '@/shared/model/officers.model';

import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { type Cycleplanstatus } from '@/shared/model/enumerations/cycleplanstatus.model';
import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface ICycleplan {
  id?: number;
  cycleplanid?: number | null;
  cycleplanname?: string | null;
  secretlevel?: keyof typeof Secretlevel | null;
  starttime?: Date | null;
  endtime?: Date | null;
  actualstarttime?: Date | null;
  actualendtime?: Date | null;
  responsiblename?: string | null;
  status?: keyof typeof Cycleplanstatus | null;
  auditStatus?: keyof typeof AuditStatus | null;
  document?: IDocument | null;
  annualplan?: IAnnualplan | null;
  monthplan?: IMonthplan | null;
  projectcharge?: IProjectcharge | null;
  responsibleid?: IOfficers | null;
  auditorid?: IOfficers | null;
}

export class Cycleplan implements ICycleplan {
  constructor(
    public id?: number,
    public cycleplanid?: number | null,
    public cycleplanname?: string | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public starttime?: Date | null,
    public endtime?: Date | null,
    public actualstarttime?: Date | null,
    public actualendtime?: Date | null,
    public responsiblename?: string | null,
    public status?: keyof typeof Cycleplanstatus | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public document?: IDocument | null,
    public annualplan?: IAnnualplan | null,
    public monthplan?: IMonthplan | null,
    public projectcharge?: IProjectcharge | null,
    public responsibleid?: IOfficers | null,
    public auditorid?: IOfficers | null,
  ) {}
}
