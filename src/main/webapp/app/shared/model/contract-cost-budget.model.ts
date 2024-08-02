import { type IProjectwbs } from '@/shared/model/projectwbs.model';

import { type ContractSubject } from '@/shared/model/enumerations/contract-subject.model';
export interface IContractCostBudget {
  id?: string;
  subject?: keyof typeof ContractSubject | null;
  auxiliaryitem?: string | null;
  unit?: string | null;
  number?: string | null;
  unitprice?: number | null;
  totalprice?: number | null;
  projectwbs?: IProjectwbs[] | null;
}

export class ContractCostBudget implements IContractCostBudget {
  constructor(
    public id?: string,
    public subject?: keyof typeof ContractSubject | null,
    public auxiliaryitem?: string | null,
    public unit?: string | null,
    public number?: string | null,
    public unitprice?: number | null,
    public totalprice?: number | null,
    public projectwbs?: IProjectwbs[] | null,
  ) {}
}
