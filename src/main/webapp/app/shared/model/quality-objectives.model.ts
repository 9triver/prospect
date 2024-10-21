import { type IHrManagement } from '@/shared/model/hr-management.model';
import { type IQualityReturns } from '@/shared/model/quality-returns.model';
import { type IQualityPlan } from '@/shared/model/quality-plan.model';

export interface IQualityObjectives {
  id?: number;
  name?: string | null;
  objectiveslevel?: string | null;
  objectives?: string | null;
  objectivesvalue?: string | null;
  calculationmethod?: string | null;
  frequency?: string | null;
  takeaction?: string | null;
  needresource?: string | null;
  status?: string | null;
  responsibleperson?: IHrManagement | null;
  qualityReturns?: IQualityReturns[] | null;
  qualityPlan?: IQualityPlan | null;
}

export class QualityObjectives implements IQualityObjectives {
  constructor(
    public id?: number,
    public name?: string | null,
    public objectiveslevel?: string | null,
    public objectives?: string | null,
    public objectivesvalue?: string | null,
    public calculationmethod?: string | null,
    public frequency?: string | null,
    public takeaction?: string | null,
    public needresource?: string | null,
    public status?: string | null,
    public responsibleperson?: IHrManagement | null,
    public qualityReturns?: IQualityReturns[] | null,
    public qualityPlan?: IQualityPlan | null,
  ) {}
}
