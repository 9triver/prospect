import { type IResourcemanagementWbs } from '@/shared/model/resourcemanagement-wbs.model';

export interface IResourcemanagement {
  id?: string;
  name?: string | null;
  description?: string | null;
  starttime?: Date | null;
  endtime?: Date | null;
  wbs?: IResourcemanagementWbs | null;
}

export class Resourcemanagement implements IResourcemanagement {
  constructor(
    public id?: string,
    public name?: string | null,
    public description?: string | null,
    public starttime?: Date | null,
    public endtime?: Date | null,
    public wbs?: IResourcemanagementWbs | null,
  ) {}
}
