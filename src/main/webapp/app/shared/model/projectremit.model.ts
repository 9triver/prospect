import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IProjectremit {
  id?: string;
  remit?: string | null;
  outdeportment?: string | null;
  indeportment?: string | null;
  projectname?: string | null;
  deportment?: string | null;
  projectleader?: string | null;
  secretlevel?: keyof typeof Secretlevel | null;
  auditStatus?: keyof typeof AuditStatus | null;
}

export class Projectremit implements IProjectremit {
  constructor(
    public id?: string,
    public remit?: string | null,
    public outdeportment?: string | null,
    public indeportment?: string | null,
    public projectname?: string | null,
    public deportment?: string | null,
    public projectleader?: string | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public auditStatus?: keyof typeof AuditStatus | null,
  ) {}
}
