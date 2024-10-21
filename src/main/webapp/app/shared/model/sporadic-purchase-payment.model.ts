import { type PaymentType } from '@/shared/model/enumerations/payment-type.model';
export interface ISporadicPurchasePayment {
  id?: number;
  planpaymentnode?: string | null;
  planpaymentamount?: number | null;
  actualpaymentamount?: number | null;
  paymenttype?: keyof typeof PaymentType | null;
  financialvoucherid?: string | null;
}

export class SporadicPurchasePayment implements ISporadicPurchasePayment {
  constructor(
    public id?: number,
    public planpaymentnode?: string | null,
    public planpaymentamount?: number | null,
    public actualpaymentamount?: number | null,
    public paymenttype?: keyof typeof PaymentType | null,
    public financialvoucherid?: string | null,
  ) {}
}
