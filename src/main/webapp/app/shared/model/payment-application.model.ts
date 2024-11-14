import { type IWorkbag } from '@/shared/model/workbag.model';

export interface IPaymentApplication {
  id?: number;
  workbagname?: string | null;
  outsourcingcontractid?: string | null;
  outsourcingcontractname?: string | null;
  planpaymentnode?: string | null;
  planpaymentname?: string | null;
  planpaymentamount?: number | null;
  contractpaymentid?: number | null;
  status?: string | null;
  workbag?: IWorkbag | null;
}

export class PaymentApplication implements IPaymentApplication {
  constructor(
    public id?: number,
    public workbagname?: string | null,
    public outsourcingcontractid?: string | null,
    public outsourcingcontractname?: string | null,
    public planpaymentnode?: string | null,
    public planpaymentname?: string | null,
    public planpaymentamount?: number | null,
    public contractpaymentid?: number | null,
    public status?: string | null,
    public workbag?: IWorkbag | null,
  ) {}
}
