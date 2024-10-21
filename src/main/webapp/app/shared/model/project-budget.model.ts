import { type IHrManagement } from '@/shared/model/hr-management.model';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';

export interface IProjectBudget {
  id?: number;
  wbsid?: string;
  wbsname?: string | null;
  parentwbsid?: string | null;
  subjectid?: number | null;
  subjectname?: string | null;
  contractid?: string;
  contractname?: string | null;
  year?: number | null;
  auxiliaryitem?: string | null;
  unit?: string | null;
  number?: string | null;
  unitprice?: number | null;
  budgetamount?: number | null;
  estimatedamount?: number | null;
  implementedamount?: number | null;
  difference?: number | null;
  remark?: string | null;
  responsibleperson?: IHrManagement | null;
  auditorid?: IHrManagement | null;
  projectwbs?: IProjectwbs[] | null;
}

export class ProjectBudget implements IProjectBudget {
  constructor(
    public id?: number,
    public wbsid?: string,
    public wbsname?: string | null,
    public parentwbsid?: string | null,
    public subjectid?: number | null,
    public subjectname?: string | null,
    public contractid?: string,
    public contractname?: string | null,
    public year?: number | null,
    public auxiliaryitem?: string | null,
    public unit?: string | null,
    public number?: string | null,
    public unitprice?: number | null,
    public budgetamount?: number | null,
    public estimatedamount?: number | null,
    public implementedamount?: number | null,
    public difference?: number | null,
    public remark?: string | null,
    public responsibleperson?: IHrManagement | null,
    public auditorid?: IHrManagement | null,
    public projectwbs?: IProjectwbs[] | null,
  ) {}
}
