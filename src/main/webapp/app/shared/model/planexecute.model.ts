import { type IPlanreturns } from '@/shared/model/planreturns.model';
import { type IOfficers } from '@/shared/model/officers.model';

export interface IPlanexecute {
  id?: number;
  planname?: string | null;
  planstarttime?: Date | null;
  planendtime?: Date | null;
  planreturns?: IPlanreturns | null;
  responsibleid?: IOfficers | null;
}

export class Planexecute implements IPlanexecute {
  constructor(
    public id?: number,
    public planname?: string | null,
    public planstarttime?: Date | null,
    public planendtime?: Date | null,
    public planreturns?: IPlanreturns | null,
    public responsibleid?: IOfficers | null,
  ) {}
}
