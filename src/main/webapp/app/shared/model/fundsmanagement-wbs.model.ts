import { type IAuditedbudget } from '@/shared/model/auditedbudget.model';
import { type ITotalbudget } from '@/shared/model/totalbudget.model';
import { type IUnitbudget } from '@/shared/model/unitbudget.model';
import { type IFundsavailability } from '@/shared/model/fundsavailability.model';
import { type IContractualfunds } from '@/shared/model/contractualfunds.model';

export interface IFundsmanagementWbs {
  id?: string;
  wbsspare1?: string | null;
  wbsspare2?: string | null;
  wbsspare3?: string | null;
  wbsspare4?: string | null;
  wbsspare5?: string | null;
  auditedbudget?: IAuditedbudget | null;
  totalbudget?: ITotalbudget | null;
  unitbudget?: IUnitbudget | null;
  fundsavailability?: IFundsavailability | null;
  contractualfunds?: IContractualfunds | null;
}

export class FundsmanagementWbs implements IFundsmanagementWbs {
  constructor(
    public id?: string,
    public wbsspare1?: string | null,
    public wbsspare2?: string | null,
    public wbsspare3?: string | null,
    public wbsspare4?: string | null,
    public wbsspare5?: string | null,
    public auditedbudget?: IAuditedbudget | null,
    public totalbudget?: ITotalbudget | null,
    public unitbudget?: IUnitbudget | null,
    public fundsavailability?: IFundsavailability | null,
    public contractualfunds?: IContractualfunds | null,
  ) {}
}
