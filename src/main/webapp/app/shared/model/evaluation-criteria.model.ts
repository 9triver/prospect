import { type IDepartment } from '@/shared/model/department.model';

export interface IEvaluationCriteria {
  id?: number;
  standardtype?: number | null;
  standardname?: string | null;
  mark?: number | null;
  department?: IDepartment | null;
}

export class EvaluationCriteria implements IEvaluationCriteria {
  constructor(
    public id?: number,
    public standardtype?: number | null,
    public standardname?: string | null,
    public mark?: number | null,
    public department?: IDepartment | null,
  ) {}
}
