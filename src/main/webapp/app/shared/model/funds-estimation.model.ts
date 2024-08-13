import { type IOfficers } from '@/shared/model/officers.model';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';

export interface IFundsEstimation {
  id?: string;
  name?: string | null;
  wbsid?: string;
  parentwbsid?: string | null;
  source?: string | null;
  unit?: string | null;
  number?: string | null;
  unitprice?: number | null;
  materialfee?: number | null;
  specialfee?: number | null;
  outsourcingfee?: number | null;
  totalbudgetprice?: number | null;
  responsibleperson?: IOfficers | null;
  auditorid?: IOfficers | null;
  projectwbs?: IProjectwbs[] | null;
}

export class FundsEstimation implements IFundsEstimation {
  constructor(
    public id?: string,
    public name?: string | null,
    public wbsid?: string,
    public parentwbsid?: string | null,
    public source?: string | null,
    public unit?: string | null,
    public number?: string | null,
    public unitprice?: number | null,
    public materialfee?: number | null,
    public specialfee?: number | null,
    public outsourcingfee?: number | null,
    public totalbudgetprice?: number | null,
    public responsibleperson?: IOfficers | null,
    public auditorid?: IOfficers | null,
    public projectwbs?: IProjectwbs[] | null,
  ) {}
}
