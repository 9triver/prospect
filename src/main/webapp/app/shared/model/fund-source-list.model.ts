import { type IContract } from '@/shared/model/contract.model';
import { type ITransactionPayment } from '@/shared/model/transaction-payment.model';
import { type ISporadicPurchasePayment } from '@/shared/model/sporadic-purchase-payment.model';
import { type ISharePayment } from '@/shared/model/share-payment.model';
import { type IContractPayment } from '@/shared/model/contract-payment.model';

export interface IFundSourceList {
  id?: number;
  paymentid?: string | null;
  contractcode?: string | null;
  contractname?: string | null;
  wbsid?: string | null;
  wbsname?: string | null;
  amount?: number | null;
  contract?: IContract | null;
  transactionPayment?: ITransactionPayment | null;
  sporadicPurchasePayment?: ISporadicPurchasePayment | null;
  sharePayment?: ISharePayment | null;
  contractPayments?: IContractPayment[] | null;
}

export class FundSourceList implements IFundSourceList {
  constructor(
    public id?: number,
    public paymentid?: string | null,
    public contractcode?: string | null,
    public contractname?: string | null,
    public wbsid?: string | null,
    public wbsname?: string | null,
    public amount?: number | null,
    public contract?: IContract | null,
    public transactionPayment?: ITransactionPayment | null,
    public sporadicPurchasePayment?: ISporadicPurchasePayment | null,
    public sharePayment?: ISharePayment | null,
    public contractPayments?: IContractPayment[] | null,
  ) {}
}
