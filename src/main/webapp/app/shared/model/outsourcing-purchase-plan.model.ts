import { type IOfficers } from '@/shared/model/officers.model';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';

import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IOutsourcingPurchasePlan {
  id?: string;
  matarialname?: string | null;
  purchasingmethod?: number | null;
  budgit?: number | null;
  needtime?: Date | null;
  planusetime?: Date | null;
  supplierid?: number | null;
  price?: number | null;
  secretlevel?: keyof typeof Secretlevel | null;
  auditStatus?: keyof typeof AuditStatus | null;
  responsibleperson?: IOfficers | null;
  auditorid?: IOfficers | null;
  projectwbs?: IProjectwbs[] | null;
}

export class OutsourcingPurchasePlan implements IOutsourcingPurchasePlan {
  constructor(
    public id?: string,
    public matarialname?: string | null,
    public purchasingmethod?: number | null,
    public budgit?: number | null,
    public needtime?: Date | null,
    public planusetime?: Date | null,
    public supplierid?: number | null,
    public price?: number | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public responsibleperson?: IOfficers | null,
    public auditorid?: IOfficers | null,
    public projectwbs?: IProjectwbs[] | null,
  ) {}
}
