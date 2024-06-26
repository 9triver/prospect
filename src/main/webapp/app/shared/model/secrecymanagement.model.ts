import { type ISecrecymanagementWbs } from '@/shared/model/secrecymanagement-wbs.model';

export interface ISecrecymanagement {
  id?: string;
  name?: string | null;
  description?: string | null;
  starttime?: Date | null;
  endtime?: Date | null;
  wbs?: ISecrecymanagementWbs | null;
}

export class Secrecymanagement implements ISecrecymanagement {
  constructor(
    public id?: string,
    public name?: string | null,
    public description?: string | null,
    public starttime?: Date | null,
    public endtime?: Date | null,
    public wbs?: ISecrecymanagementWbs | null,
  ) {}
}
