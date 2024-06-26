import { type IOutsourcingPurchasePlan } from '@/shared/model/outsourcing-purchase-plan.model';
import { type IOutsourcingPurchaseExecute } from '@/shared/model/outsourcing-purchase-execute.model';

export interface IOutsourcingmanagementWbs {
  id?: string;
  wbsspare1?: string | null;
  wbsspare2?: string | null;
  wbsspare3?: string | null;
  wbsspare4?: string | null;
  wbsspare5?: string | null;
  outsourcingPurchasePlan?: IOutsourcingPurchasePlan | null;
  outsourcingPurchaseExecute?: IOutsourcingPurchaseExecute | null;
}

export class OutsourcingmanagementWbs implements IOutsourcingmanagementWbs {
  constructor(
    public id?: string,
    public wbsspare1?: string | null,
    public wbsspare2?: string | null,
    public wbsspare3?: string | null,
    public wbsspare4?: string | null,
    public wbsspare5?: string | null,
    public outsourcingPurchasePlan?: IOutsourcingPurchasePlan | null,
    public outsourcingPurchaseExecute?: IOutsourcingPurchaseExecute | null,
  ) {}
}
