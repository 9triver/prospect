import { type IWorkbag } from '@/shared/model/workbag.model';
import { type IContractPayment } from '@/shared/model/contract-payment.model';

export interface IPaymentCostList {
  id?: number;
  wbsid?: string | null;
  wbsname?: string | null;
  parentwbsid?: string | null;
  unit?: string | null;
  unitprice?: number | null;
  number?: number | null;
  subjectid?: number | null;
  subjectname?: string | null;
  invoicepaymentamount?: number | null;
  borrowingpaymentamount?: number | null;
  accountingamount?: number | null;
  workbag?: IWorkbag | null;
  contractPayments?: IContractPayment[] | null;
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
    public subjectid?: number | null,
    public subjectname?: string | null,
    public invoicepaymentamount?: number | null,
    public borrowingpaymentamount?: number | null,
    public accountingamount?: number | null,
    public workbag?: IWorkbag | null,
    public contractPayments?: IContractPayment[] | null,
  ) {}
}
