import { type IHrManagement } from '@/shared/model/hr-management.model';
import { type IQualityObjectives } from '@/shared/model/quality-objectives.model';
import { type IQualityPlan } from '@/shared/model/quality-plan.model';

export interface IQualityReturns {
  id?: number;
  qualityplanid?: string | null;
  qualityobjectivesid?: string | null;
  name?: string | null;
  department?: string | null;
  responsibleid?: string | null;
  wbsid?: string | null;
  workbagid?: string | null;
  objectiveslevel?: string | null;
  objectives?: string | null;
  objectivesvalue?: string | null;
  calculationmethod?: string | null;
  frequency?: string | null;
  isobjectivesvalue?: boolean | null;
  percentage?: number | null;
  objectivescompletion?: string | null;
  problem?: string | null;
  takeaction?: string | null;
  continuousimprovement?: string | null;
  workevidence?: string | null;
  returntime?: Date | null;
  status?: string | null;
  responsibleperson?: IHrManagement | null;
  auditorid?: IHrManagement | null;
  qualityObjectives?: IQualityObjectives[] | null;
  qualityPlan?: IQualityPlan | null;
}

export class QualityReturns implements IQualityReturns {
  constructor(
    public id?: number,
    public qualityplanid?: string | null,
    public qualityobjectivesid?: string | null,
    public name?: string | null,
    public department?: string | null,
    public responsibleid?: string | null,
    public wbsid?: string | null,
    public workbagid?: string | null,
    public objectiveslevel?: string | null,
    public objectives?: string | null,
    public objectivesvalue?: string | null,
    public calculationmethod?: string | null,
    public frequency?: string | null,
    public isobjectivesvalue?: boolean | null,
    public percentage?: number | null,
    public objectivescompletion?: string | null,
    public problem?: string | null,
    public takeaction?: string | null,
    public continuousimprovement?: string | null,
    public workevidence?: string | null,
    public returntime?: Date | null,
    public status?: string | null,
    public responsibleperson?: IHrManagement | null,
    public auditorid?: IHrManagement | null,
    public qualityObjectives?: IQualityObjectives[] | null,
    public qualityPlan?: IQualityPlan | null,
  ) {
    this.isobjectivesvalue = this.isobjectivesvalue ?? false;
  }
}
