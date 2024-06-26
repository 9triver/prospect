import { type IDocument } from '@/shared/model/document.model';
import { type IPlanexecute } from '@/shared/model/planexecute.model';
import { type IProjectcharge } from '@/shared/model/projectcharge.model';
import { type IOfficers } from '@/shared/model/officers.model';

import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { type Annualplanstatus } from '@/shared/model/enumerations/annualplanstatus.model';
import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IMonthplan {
  id?: string;
  monthplanname?: string | null;
  month?: Date | null;
  secretlevel?: keyof typeof Secretlevel | null;
  creatorname?: string | null;
  status?: keyof typeof Annualplanstatus | null;
  auditStatus?: keyof typeof AuditStatus | null;
  document?: IDocument | null;
  planreturns?: IPlanexecute | null;
  projectcharge?: IProjectcharge | null;
  creatorid?: IOfficers | null;
  auditorid?: IOfficers | null;
}

export class Monthplan implements IMonthplan {
  constructor(
    public id?: string,
    public monthplanname?: string | null,
    public month?: Date | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public creatorname?: string | null,
    public status?: keyof typeof Annualplanstatus | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public document?: IDocument | null,
    public planreturns?: IPlanexecute | null,
    public projectcharge?: IProjectcharge | null,
    public creatorid?: IOfficers | null,
    public auditorid?: IOfficers | null,
  ) {}
}
