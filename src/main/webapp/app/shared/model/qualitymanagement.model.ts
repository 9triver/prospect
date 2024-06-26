import { type IQualitymanagementWbs } from '@/shared/model/qualitymanagement-wbs.model';

export interface IQualitymanagement {
  id?: string;
  name?: string | null;
  description?: string | null;
  starttime?: Date | null;
  endtime?: Date | null;
  wbs?: IQualitymanagementWbs | null;
}

export class Qualitymanagement implements IQualitymanagement {
  constructor(
    public id?: string,
    public name?: string | null,
    public description?: string | null,
    public starttime?: Date | null,
    public endtime?: Date | null,
    public wbs?: IQualitymanagementWbs | null,
  ) {}
}
