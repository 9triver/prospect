import { type IOutsourcingPurchasePlan } from '@/shared/model/outsourcing-purchase-plan.model';
import { type IOfficers } from '@/shared/model/officers.model';

import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
export interface IOutsourcingPurchaseExecute {
  id?: string;
  matarialname?: string | null;
  purchasingmethod?: number | null;
  budgit?: number | null;
  needtime?: Date | null;
  planusetime?: Date | null;
  supplierid?: number | null;
  price?: number | null;
  secretlevel?: keyof typeof Secretlevel | null;
  outsourcingplanid?: IOutsourcingPurchasePlan | null;
  responsibleid?: IOfficers | null;
}

export class OutsourcingPurchaseExecute implements IOutsourcingPurchaseExecute {
  constructor(
    public id?: string,
    public matarialname?: string | null,
    public purchasingmethod?: number | null,
    public budgit?: number | null,
    public needtime?: Date | null,
    public planusetime?: Date | null,
    public supplierid?: number | null,
    public price?: number | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public outsourcingplanid?: IOutsourcingPurchasePlan | null,
    public responsibleid?: IOfficers | null,
  ) {}
}
