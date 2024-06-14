import { type IOutsourcingmPurchasePlan } from '@/shared/model/outsourcingm-purchase-plan.model';
import { type IOfficers } from '@/shared/model/officers.model';

import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
export interface IOutsourcingmPurchaseExecute {
  id?: number;
  outsourcingexecuteid?: number | null;
  matarialname?: string | null;
  purchasingmethod?: number | null;
  budgit?: number | null;
  needtime?: Date | null;
  planusetime?: Date | null;
  supplierid?: number | null;
  price?: number | null;
  secretlevel?: keyof typeof Secretlevel | null;
  outsourcingplanid?: IOutsourcingmPurchasePlan | null;
  responsibleid?: IOfficers | null;
}

export class OutsourcingmPurchaseExecute implements IOutsourcingmPurchaseExecute {
  constructor(
    public id?: number,
    public outsourcingexecuteid?: number | null,
    public matarialname?: string | null,
    public purchasingmethod?: number | null,
    public budgit?: number | null,
    public needtime?: Date | null,
    public planusetime?: Date | null,
    public supplierid?: number | null,
    public price?: number | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public outsourcingplanid?: IOutsourcingmPurchasePlan | null,
    public responsibleid?: IOfficers | null,
  ) {}
}
