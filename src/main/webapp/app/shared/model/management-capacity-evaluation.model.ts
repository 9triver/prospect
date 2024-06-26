import { type IEvaluationCriteria } from '@/shared/model/evaluation-criteria.model';
import { type IProject } from '@/shared/model/project.model';
import { type IOfficers } from '@/shared/model/officers.model';

export interface IManagementCapacityEvaluation {
  id?: string;
  year?: number | null;
  deprotment?: string | null;
  createtime?: Date | null;
  status?: number | null;
  totalmark?: number | null;
  mark?: number | null;
  ratingpersonname?: string | null;
  ratingdepartment?: string | null;
  ratingtimg?: Date | null;
  evaluationCriteria?: IEvaluationCriteria | null;
  project?: IProject | null;
  creatorid?: IOfficers | null;
  responsibleid?: IOfficers | null;
  ratingperson?: IOfficers | null;
}

export class ManagementCapacityEvaluation implements IManagementCapacityEvaluation {
  constructor(
    public id?: string,
    public year?: number | null,
    public deprotment?: string | null,
    public createtime?: Date | null,
    public status?: number | null,
    public totalmark?: number | null,
    public mark?: number | null,
    public ratingpersonname?: string | null,
    public ratingdepartment?: string | null,
    public ratingtimg?: Date | null,
    public evaluationCriteria?: IEvaluationCriteria | null,
    public project?: IProject | null,
    public creatorid?: IOfficers | null,
    public responsibleid?: IOfficers | null,
    public ratingperson?: IOfficers | null,
  ) {}
}
