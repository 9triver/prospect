import { type ICostControlSystem } from '@/shared/model/cost-control-system.model';

import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
import { type ContractStatus } from '@/shared/model/enumerations/contract-status.model';
export interface IContract {
  id?: string;
  contractname?: string | null;
  year?: number | null;
  starttime?: Date | null;
  endtime?: Date | null;
  contractbudgetcost?: number | null;
  secretlevel?: keyof typeof Secretlevel | null;
  status?: keyof typeof ContractStatus | null;
  costControlSystems?: ICostControlSystem[] | null;
}

export class Contract implements IContract {
  constructor(
    public id?: string,
    public contractname?: string | null,
    public year?: number | null,
    public starttime?: Date | null,
    public endtime?: Date | null,
    public contractbudgetcost?: number | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public status?: keyof typeof ContractStatus | null,
    public costControlSystems?: ICostControlSystem[] | null,
  ) {}
}
