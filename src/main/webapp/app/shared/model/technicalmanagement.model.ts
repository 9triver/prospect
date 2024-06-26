import { type ITechnicalmanagementWbs } from '@/shared/model/technicalmanagement-wbs.model';

export interface ITechnicalmanagement {
  id?: string;
  name?: string | null;
  description?: string | null;
  starttime?: Date | null;
  endtime?: Date | null;
  wbs?: ITechnicalmanagementWbs | null;
}

export class Technicalmanagement implements ITechnicalmanagement {
  constructor(
    public id?: string,
    public name?: string | null,
    public description?: string | null,
    public starttime?: Date | null,
    public endtime?: Date | null,
    public wbs?: ITechnicalmanagementWbs | null,
  ) {}
}
