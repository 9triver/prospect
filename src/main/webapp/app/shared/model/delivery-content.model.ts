import { type IOutsourcingContract } from '@/shared/model/outsourcing-contract.model';

export interface IDeliveryContent {
  id?: number;
  warrantyrequirement?: string | null;
  purchaseplanno?: string | null;
  purchaseplandate?: Date | null;
  purchaseplanamount?: number | null;
  purchasemethod?: string | null;
  purchasesecretlevel?: string | null;
  reviewmethod?: string | null;
  requirementdepartment?: string | null;
  requirementperson?: string | null;
  undertaker?: string | null;
  undertakingdepartment?: string | null;
  workbagid?: string | null;
  projectmanager?: string | null;
  fundsource?: string | null;
  thesisname?: string | null;
  contractauxiliaryno?: string | null;
  reasonfornosuppliers?: string | null;
  reasonforchange?: string | null;
  negotiationfiletime?: Date | null;
  bidopeningtime?: Date | null;
  judges?: string | null;
  responsevendorname?: string | null;
  finalquoteandscore?: string | null;
  noticeofcompletiontime?: Date | null;
  signingdate?: Date | null;
  contractenddate?: Date | null;
  actualcompletiontime?: Date | null;
  issubmitsecrecyagreement?: string | null;
  issubmitsecurityagreement?: string | null;
  remark?: string | null;
  outsourcingContract?: IOutsourcingContract | null;
}

export class DeliveryContent implements IDeliveryContent {
  constructor(
    public id?: number,
    public warrantyrequirement?: string | null,
    public purchaseplanno?: string | null,
    public purchaseplandate?: Date | null,
    public purchaseplanamount?: number | null,
    public purchasemethod?: string | null,
    public purchasesecretlevel?: string | null,
    public reviewmethod?: string | null,
    public requirementdepartment?: string | null,
    public requirementperson?: string | null,
    public undertaker?: string | null,
    public undertakingdepartment?: string | null,
    public workbagid?: string | null,
    public projectmanager?: string | null,
    public fundsource?: string | null,
    public thesisname?: string | null,
    public contractauxiliaryno?: string | null,
    public reasonfornosuppliers?: string | null,
    public reasonforchange?: string | null,
    public negotiationfiletime?: Date | null,
    public bidopeningtime?: Date | null,
    public judges?: string | null,
    public responsevendorname?: string | null,
    public finalquoteandscore?: string | null,
    public noticeofcompletiontime?: Date | null,
    public signingdate?: Date | null,
    public contractenddate?: Date | null,
    public actualcompletiontime?: Date | null,
    public issubmitsecrecyagreement?: string | null,
    public issubmitsecurityagreement?: string | null,
    public remark?: string | null,
    public outsourcingContract?: IOutsourcingContract | null,
  ) {}
}
