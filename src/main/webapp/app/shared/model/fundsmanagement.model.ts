import { type ITotalbudget } from '@/shared/model/totalbudget.model';
import { type IUnitbudget } from '@/shared/model/unitbudget.model';
import { type IDocument } from '@/shared/model/document.model';
import { type IOfficers } from '@/shared/model/officers.model';

import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IFundsmanagement {
  id?: number;
  fundsid?: number | null;
  createtime?: Date | null;
  creatorname?: string | null;
  secretlevel?: keyof typeof Secretlevel | null;
  year?: number | null;
  budgit?: number | null;
  dapartmentid?: string | null;
  draftapproval?: number | null;
  totalbudgetid?: number | null;
  unitbudgetid?: number | null;
  documentid?: number | null;
  maintainerid?: number | null;
  auditStatus?: keyof typeof AuditStatus | null;
  totalbudget?: ITotalbudget | null;
  unitbudget?: IUnitbudget | null;
  document?: IDocument | null;
  creatorid?: IOfficers | null;
  auditorid?: IOfficers | null;
}

export class Fundsmanagement implements IFundsmanagement {
  constructor(
    public id?: number,
    public fundsid?: number | null,
    public createtime?: Date | null,
    public creatorname?: string | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public year?: number | null,
    public budgit?: number | null,
    public dapartmentid?: string | null,
    public draftapproval?: number | null,
    public totalbudgetid?: number | null,
    public unitbudgetid?: number | null,
    public documentid?: number | null,
    public maintainerid?: number | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public totalbudget?: ITotalbudget | null,
    public unitbudget?: IUnitbudget | null,
    public document?: IDocument | null,
    public creatorid?: IOfficers | null,
    public auditorid?: IOfficers | null,
  ) {}
}
