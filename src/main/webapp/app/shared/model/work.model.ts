import { type IWorkbag } from '@/shared/model/workbag.model';

import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IWork {
  id?: string;
  name?: string | null;
  secretlevel?: keyof typeof Secretlevel | null;
  description?: string | null;
  workbagid?: string | null;
  auditStatus?: keyof typeof AuditStatus | null;
  workbags?: IWorkbag[] | null;
}

export class Work implements IWork {
  constructor(
    public id?: string,
    public name?: string | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public description?: string | null,
    public workbagid?: string | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public workbags?: IWorkbag[] | null,
  ) {}
}
