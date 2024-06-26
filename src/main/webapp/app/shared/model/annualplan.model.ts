import { type IDocument } from '@/shared/model/document.model';
import { type IMonthplan } from '@/shared/model/monthplan.model';
import { type IProjectcharge } from '@/shared/model/projectcharge.model';
import { type IOfficers } from '@/shared/model/officers.model';

import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { type Annualplanstatus } from '@/shared/model/enumerations/annualplanstatus.model';
import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IAnnualplan {
  id?: string;
  annualplanname?: string | null;
  year?: Date | null;
  secretlevel?: keyof typeof Secretlevel | null;
  creatorname?: string | null;
  status?: keyof typeof Annualplanstatus | null;
  auditStatus?: keyof typeof AuditStatus | null;
  document?: IDocument | null;
  monthplan?: IMonthplan | null;
  projectcharge?: IProjectcharge | null;
  creatorid?: IOfficers | null;
  auditorid?: IOfficers | null;
}

export class Annualplan implements IAnnualplan {
  constructor(
    public id?: string,
    public annualplanname?: string | null,
    public year?: Date | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public creatorname?: string | null,
    public status?: keyof typeof Annualplanstatus | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public document?: IDocument | null,
    public monthplan?: IMonthplan | null,
    public projectcharge?: IProjectcharge | null,
    public creatorid?: IOfficers | null,
    public auditorid?: IOfficers | null,
  ) {}
}
