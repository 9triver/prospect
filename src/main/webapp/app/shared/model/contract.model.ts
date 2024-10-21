import { type ICostControlSystem } from '@/shared/model/cost-control-system.model';

import { type ContractType } from '@/shared/model/enumerations/contract-type.model';
import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { type ContractStatus } from '@/shared/model/enumerations/contract-status.model';
export interface IContract {
  id?: number;
  contractcode?: string | null;
  contractname?: string | null;
  projectid?: string | null;
  projectname?: string | null;
  contracttype?: keyof typeof ContractType | null;
  year?: number | null;
  amount?: number | null;
  starttime?: Date | null;
  endtime?: Date | null;
  secretlevel?: keyof typeof Secretlevel | null;
  status?: keyof typeof ContractStatus | null;
  budgetamount?: number | null;
  estimatedamount?: number | null;
  implementedamount?: number | null;
  difference?: number | null;
  costControlSystems?: ICostControlSystem[] | null;
}

export class Contract implements IContract {
  constructor(
    public id?: number,
    public contractcode?: string | null,
    public contractname?: string | null,
    public projectid?: string | null,
    public projectname?: string | null,
    public contracttype?: keyof typeof ContractType | null,
    public year?: number | null,
    public amount?: number | null,
    public starttime?: Date | null,
    public endtime?: Date | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public status?: keyof typeof ContractStatus | null,
    public budgetamount?: number | null,
    public estimatedamount?: number | null,
    public implementedamount?: number | null,
    public difference?: number | null,
    public costControlSystems?: ICostControlSystem[] | null,
  ) {}
}
