import { type IProjectRisk } from '@/shared/model/project-risk.model';
import { type IHrManagement } from '@/shared/model/hr-management.model';

export interface IRiskReturn {
  id?: number;
  belongriskid?: number | null;
  status?: string | null;
  closestatus?: string | null;
  evidencefile?: string | null;
  riskid?: IProjectRisk | null;
  creatorid?: IHrManagement | null;
}

export class RiskReturn implements IRiskReturn {
  constructor(
    public id?: number,
    public belongriskid?: number | null,
    public status?: string | null,
    public closestatus?: string | null,
    public evidencefile?: string | null,
    public riskid?: IProjectRisk | null,
    public creatorid?: IHrManagement | null,
  ) {}
}
