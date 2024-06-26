import { type IOutsourcingmanagementWbs } from '@/shared/model/outsourcingmanagement-wbs.model';

export interface IOutsourcingmanagement {
  id?: string;
  name?: string | null;
  description?: string | null;
  starttime?: Date | null;
  endtime?: Date | null;
  wbs?: IOutsourcingmanagementWbs | null;
}

export class Outsourcingmanagement implements IOutsourcingmanagement {
  constructor(
    public id?: string,
    public name?: string | null,
    public description?: string | null,
    public starttime?: Date | null,
    public endtime?: Date | null,
    public wbs?: IOutsourcingmanagementWbs | null,
  ) {}
}
