import { type IOfficers } from '@/shared/model/officers.model';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import { type IContract } from '@/shared/model/contract.model';

import { type ContractSubject } from '@/shared/model/enumerations/contract-subject.model';
export interface ICostControlSystem {
  id?: string;
  type?: number | null;
  subject?: keyof typeof ContractSubject | null;
  implementedamount?: number | null;
  approvedamount?: number | null;
  pendingimplementationamount?: number | null;
  contractpaymentamount?: number | null;
  managementregistrationnumber?: number | null;
  financialregistrationnumber?: number | null;
  contractbudgetamount?: number | null;
  contractsigningamount?: number | null;
  contractsettlementamount?: number | null;
  unforeseeableamount?: number | null;
  invoicepaymentamount?: number | null;
  loanpaymentamount?: number | null;
  accountoutstandingamount?: number | null;
  pendingpaymentamount?: number | null;
  pendinginvoiceamount?: number | null;
  responsibleperson?: IOfficers | null;
  auditorid?: IOfficers | null;
  projectwbs?: IProjectwbs[] | null;
  contracts?: IContract[] | null;
}

export class CostControlSystem implements ICostControlSystem {
  constructor(
    public id?: string,
    public type?: number | null,
    public subject?: keyof typeof ContractSubject | null,
    public implementedamount?: number | null,
    public approvedamount?: number | null,
    public pendingimplementationamount?: number | null,
    public contractpaymentamount?: number | null,
    public managementregistrationnumber?: number | null,
    public financialregistrationnumber?: number | null,
    public contractbudgetamount?: number | null,
    public contractsigningamount?: number | null,
    public contractsettlementamount?: number | null,
    public unforeseeableamount?: number | null,
    public invoicepaymentamount?: number | null,
    public loanpaymentamount?: number | null,
    public accountoutstandingamount?: number | null,
    public pendingpaymentamount?: number | null,
    public pendinginvoiceamount?: number | null,
    public responsibleperson?: IOfficers | null,
    public auditorid?: IOfficers | null,
    public projectwbs?: IProjectwbs[] | null,
    public contracts?: IContract[] | null,
  ) {}
}
