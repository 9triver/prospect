import { type IAuditedbudget } from '@/shared/model/auditedbudget.model';

export interface IFundsavailability {
  id?: string;
  fundsid?: string | null;
  year?: number | null;
  budgit?: number | null;
  funding?: number | null;
  auditedbudget?: IAuditedbudget | null;
}

export class Fundsavailability implements IFundsavailability {
  constructor(
    public id?: string,
    public fundsid?: string | null,
    public year?: number | null,
    public budgit?: number | null,
    public funding?: number | null,
    public auditedbudget?: IAuditedbudget | null,
  ) {}
}
