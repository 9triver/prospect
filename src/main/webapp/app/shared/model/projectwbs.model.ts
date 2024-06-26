import { type ICycleplan } from '@/shared/model/cycleplan.model';
import { type IProgressmanagement } from '@/shared/model/progressmanagement.model';
import { type IQualitymanagement } from '@/shared/model/qualitymanagement.model';
import { type IFundsmanagement } from '@/shared/model/fundsmanagement.model';
import { type ITechnicalmanagement } from '@/shared/model/technicalmanagement.model';
import { type IContractualfunds } from '@/shared/model/contractualfunds.model';
import { type IOutsourcingmanagement } from '@/shared/model/outsourcingmanagement.model';
import { type IResourcemanagement } from '@/shared/model/resourcemanagement.model';
import { type IRiskmanagement } from '@/shared/model/riskmanagement.model';
import { type ISecuritymanagement } from '@/shared/model/securitymanagement.model';
import { type IDocument } from '@/shared/model/document.model';
import { type ISafetycheck } from '@/shared/model/safetycheck.model';
import { type IDepartment } from '@/shared/model/department.model';
import { type IEvaluationCriteria } from '@/shared/model/evaluation-criteria.model';

export interface IProjectwbs {
  id?: string;
  projectwbsname?: string | null;
  wbsspare1?: string | null;
  wbsspare2?: string | null;
  wbsspare3?: string | null;
  wbsspare4?: string | null;
  wbsspare5?: string | null;
  cycleplan?: ICycleplan | null;
  progressmanagement?: IProgressmanagement | null;
  qualitymanagement?: IQualitymanagement | null;
  fundsmanagement?: IFundsmanagement | null;
  technicalmanagement?: ITechnicalmanagement | null;
  contractualfunds?: IContractualfunds | null;
  outsourcingmanagement?: IOutsourcingmanagement | null;
  resourcemanagement?: IResourcemanagement | null;
  riskmanagement?: IRiskmanagement | null;
  securitymanagement?: ISecuritymanagement | null;
  document?: IDocument | null;
  safetycheck?: ISafetycheck | null;
  department?: IDepartment | null;
  evaluationCriteria?: IEvaluationCriteria | null;
}

export class Projectwbs implements IProjectwbs {
  constructor(
    public id?: string,
    public projectwbsname?: string | null,
    public wbsspare1?: string | null,
    public wbsspare2?: string | null,
    public wbsspare3?: string | null,
    public wbsspare4?: string | null,
    public wbsspare5?: string | null,
    public cycleplan?: ICycleplan | null,
    public progressmanagement?: IProgressmanagement | null,
    public qualitymanagement?: IQualitymanagement | null,
    public fundsmanagement?: IFundsmanagement | null,
    public technicalmanagement?: ITechnicalmanagement | null,
    public contractualfunds?: IContractualfunds | null,
    public outsourcingmanagement?: IOutsourcingmanagement | null,
    public resourcemanagement?: IResourcemanagement | null,
    public riskmanagement?: IRiskmanagement | null,
    public securitymanagement?: ISecuritymanagement | null,
    public document?: IDocument | null,
    public safetycheck?: ISafetycheck | null,
    public department?: IDepartment | null,
    public evaluationCriteria?: IEvaluationCriteria | null,
  ) {}
}
