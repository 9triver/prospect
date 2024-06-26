import { type ISecuritymanagementWbs } from '@/shared/model/securitymanagement-wbs.model';

export interface ISecuritymanagement {
  id?: string;
  name?: string | null;
  description?: string | null;
  starttime?: Date | null;
  endtime?: Date | null;
  wbs?: ISecuritymanagementWbs | null;
}

export class Securitymanagement implements ISecuritymanagement {
  constructor(
    public id?: string,
    public name?: string | null,
    public description?: string | null,
    public starttime?: Date | null,
    public endtime?: Date | null,
    public wbs?: ISecuritymanagementWbs | null,
  ) {}
}
