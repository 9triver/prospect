import { type IProjectBudget } from '@/shared/model/project-budget.model';
import { type IHrManagement } from '@/shared/model/hr-management.model';

export interface ISubjectCostBudget {
  id?: number;
  contractid?: string;
  subjectid?: number;
  subjectname?: string | null;
  budgetamount?: number | null;
  estimatedamount?: number | null;
  implementedamount?: number | null;
  difference?: number | null;
  percentage?: number | null;
  projectBudget?: IProjectBudget | null;
  responsibleperson?: IHrManagement | null;
  auditorid?: IHrManagement | null;
}

export class SubjectCostBudget implements ISubjectCostBudget {
  constructor(
    public id?: number,
    public contractid?: string,
    public subjectid?: number,
    public subjectname?: string | null,
    public budgetamount?: number | null,
    public estimatedamount?: number | null,
    public implementedamount?: number | null,
    public difference?: number | null,
    public percentage?: number | null,
    public projectBudget?: IProjectBudget | null,
    public responsibleperson?: IHrManagement | null,
    public auditorid?: IHrManagement | null,
  ) {}
}
