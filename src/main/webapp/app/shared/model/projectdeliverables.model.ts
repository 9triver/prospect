import { type IDeliverables } from '@/shared/model/deliverables.model';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import { type IWorkbag } from '@/shared/model/workbag.model';

export interface IProjectdeliverables {
  id?: number;
  parentcode?: string | null;
  isSubmit?: boolean | null;
  status?: string | null;
  code?: IDeliverables | null;
  belongwbsids?: IProjectwbs[] | null;
  belongworkbagids?: IWorkbag[] | null;
}

export class Projectdeliverables implements IProjectdeliverables {
  constructor(
    public id?: number,
    public parentcode?: string | null,
    public isSubmit?: boolean | null,
    public status?: string | null,
    public code?: IDeliverables | null,
    public belongwbsids?: IProjectwbs[] | null,
    public belongworkbagids?: IWorkbag[] | null,
  ) {
    this.isSubmit = this.isSubmit ?? false;
  }
}
