import { type IOutsourcingContract } from '@/shared/model/outsourcing-contract.model';

export interface IPaymentApplication {
  id?: number;
  workbagid?: string | null;
  contractcode?: string | null;
  planpaymentnode?: string | null;
  planpaymentamount?: number | null;
  outsourcingContract?: IOutsourcingContract | null;
}

export class PaymentApplication implements IPaymentApplication {
  constructor(
    public id?: number,
    public workbagid?: string | null,
    public contractcode?: string | null,
    public planpaymentnode?: string | null,
    public planpaymentamount?: number | null,
    public outsourcingContract?: IOutsourcingContract | null,
  ) {}
}
