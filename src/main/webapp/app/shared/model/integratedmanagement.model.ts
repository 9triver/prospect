import { type IIntegratedmanagementWbs } from '@/shared/model/integratedmanagement-wbs.model';

export interface IIntegratedmanagement {
  id?: number;
  name?: string | null;
  description?: string | null;
  wbs?: IIntegratedmanagementWbs | null;
}

export class Integratedmanagement implements IIntegratedmanagement {
  constructor(
    public id?: number,
    public name?: string | null,
    public description?: string | null,
    public wbs?: IIntegratedmanagementWbs | null,
  ) {}
}
