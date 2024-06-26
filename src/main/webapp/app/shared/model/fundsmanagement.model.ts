import { type IFundsmanagementWbs } from '@/shared/model/fundsmanagement-wbs.model';

export interface IFundsmanagement {
  id?: string;
  name?: string | null;
  description?: string | null;
  starttime?: Date | null;
  endtime?: Date | null;
  wbs?: IFundsmanagementWbs | null;
}

export class Fundsmanagement implements IFundsmanagement {
  constructor(
    public id?: string,
    public name?: string | null,
    public description?: string | null,
    public starttime?: Date | null,
    public endtime?: Date | null,
    public wbs?: IFundsmanagementWbs | null,
  ) {}
}
