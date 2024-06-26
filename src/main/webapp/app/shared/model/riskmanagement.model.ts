import { type IRiskmanagementWbs } from '@/shared/model/riskmanagement-wbs.model';

export interface IRiskmanagement {
  id?: string;
  name?: string | null;
  description?: string | null;
  starttime?: Date | null;
  endtime?: Date | null;
  wbs?: IRiskmanagementWbs | null;
}

export class Riskmanagement implements IRiskmanagement {
  constructor(
    public id?: string,
    public name?: string | null,
    public description?: string | null,
    public starttime?: Date | null,
    public endtime?: Date | null,
    public wbs?: IRiskmanagementWbs | null,
  ) {}
}
