import { type IWorkbag } from '@/shared/model/workbag.model';
import { type IMilestoneNode } from '@/shared/model/milestone-node.model';

export interface IOutsourcingContract {
  id?: number;
  contractid?: string | null;
  contractcode?: string | null;
  contractname?: string | null;
  contractqualityid?: string | null;
  contractcostid?: string | null;
  contractfinanceid?: string | null;
  projectid?: string | null;
  projectsecretlevel?: string | null;
  counterpartyunit?: string | null;
  negotiationdate?: Date | null;
  negotiationlocation?: string | null;
  negotiator?: string | null;
  budgetamount?: number | null;
  contractamount?: number | null;
  approver?: string | null;
  approvaldate?: Date | null;
  contractsecretlevel?: string | null;
  deliverycontent?: string | null;
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
  workbag?: IWorkbag | null;
  milestoneNodes?: IMilestoneNode[] | null;
}

export class OutsourcingContract implements IOutsourcingContract {
  constructor(
    public id?: number,
    public contractid?: string | null,
    public contractcode?: string | null,
    public contractname?: string | null,
    public contractqualityid?: string | null,
    public contractcostid?: string | null,
    public contractfinanceid?: string | null,
    public projectid?: string | null,
    public projectsecretlevel?: string | null,
    public counterpartyunit?: string | null,
    public negotiationdate?: Date | null,
    public negotiationlocation?: string | null,
    public negotiator?: string | null,
    public budgetamount?: number | null,
    public contractamount?: number | null,
    public approver?: string | null,
    public approvaldate?: Date | null,
    public contractsecretlevel?: string | null,
    public deliverycontent?: string | null,
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
    public workbag?: IWorkbag | null,
    public milestoneNodes?: IMilestoneNode[] | null,
  ) {}
}
