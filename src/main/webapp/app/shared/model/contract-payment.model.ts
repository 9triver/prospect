import { type PaymentType } from '@/shared/model/enumerations/payment-type.model';
export interface IContractPayment {
  id?: number;
  workbagid?: string | null;
  workbagname?: string | null;
  contractcode?: string | null;
  contractname?: string | null;
  planpaymentnode?: string | null;
  planpaymentamount?: number | null;
  actualpaymentamount?: number | null;
  paymenttype?: keyof typeof PaymentType | null;
  financialvoucherid?: string | null;
}

export class ContractPayment implements IContractPayment {
  constructor(
    public id?: number,
    public workbagid?: string | null,
    public workbagname?: string | null,
    public contractcode?: string | null,
    public contractname?: string | null,
    public planpaymentnode?: string | null,
    public planpaymentamount?: number | null,
    public actualpaymentamount?: number | null,
    public paymenttype?: keyof typeof PaymentType | null,
    public financialvoucherid?: string | null,
  ) {}
}
