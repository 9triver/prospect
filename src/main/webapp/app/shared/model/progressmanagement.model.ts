import { type IProgressmanagementWbs } from '@/shared/model/progressmanagement-wbs.model';

export interface IProgressmanagement {
  id?: string;
  name?: string | null;
  description?: string | null;
  starttime?: Date | null;
  endtime?: Date | null;
  wbs?: IProgressmanagementWbs | null;
}

export class Progressmanagement implements IProgressmanagement {
  constructor(
    public id?: string,
    public name?: string | null,
    public description?: string | null,
    public starttime?: Date | null,
    public endtime?: Date | null,
    public wbs?: IProgressmanagementWbs | null,
  ) {}
}
