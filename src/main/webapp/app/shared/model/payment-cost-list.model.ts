import { type IContractPayment } from '@/shared/model/contract-payment.model';

export interface IPaymentCostList {
  id?: number;
  wbsid?: string | null;
  wbsname?: string | null;
  parentwbsid?: string | null;
  unit?: string | null;
  unitprice?: number | null;
  number?: number | null;
  invoicepaymentamount?: number | null;
  borrowingpaymentamount?: number | null;
  accountingamount?: number | null;
  contractPayment?: IContractPayment | null;
}

export class PaymentCostList implements IPaymentCostList {
  constructor(
    public id?: number,
    public wbsid?: string | null,
    public wbsname?: string | null,
    public parentwbsid?: string | null,
    public unit?: string | null,
    public unitprice?: number | null,
    public number?: number | null,
    public invoicepaymentamount?: number | null,
    public borrowingpaymentamount?: number | null,
    public accountingamount?: number | null,
    public contractPayment?: IContractPayment | null,
  ) {}
}
