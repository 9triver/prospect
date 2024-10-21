import { type IWorkbag } from '@/shared/model/workbag.model';

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
  workbag?: IWorkbag | null;
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
    public workbag?: IWorkbag | null,
  ) {}
}
