import { type IRiskReport } from '@/shared/model/risk-report.model';
import { type IOfficers } from '@/shared/model/officers.model';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import { type IProgressPlan } from '@/shared/model/progress-plan.model';

import { type Risklevel } from '@/shared/model/enumerations/risklevel.model';
export interface IProjectRisk {
  id?: string;
  year?: number | null;
  nodename?: string | null;
  risktype?: number | null;
  decumentid?: number | null;
  version?: number | null;
  usetime?: Date | null;
  systemlevel?: number | null;
  risklevel?: keyof typeof Risklevel | null;
  limitationtime?: string | null;
  closetype?: number | null;
  riskReport?: IRiskReport | null;
  creatorid?: IOfficers | null;
  responsibleperson?: IOfficers | null;
  auditorid?: IOfficers | null;
  projectwbs?: IProjectwbs[] | null;
  progressPlans?: IProgressPlan[] | null;
}

export class ProjectRisk implements IProjectRisk {
  constructor(
    public id?: string,
    public year?: number | null,
    public nodename?: string | null,
    public risktype?: number | null,
    public decumentid?: number | null,
    public version?: number | null,
    public usetime?: Date | null,
    public systemlevel?: number | null,
    public risklevel?: keyof typeof Risklevel | null,
    public limitationtime?: string | null,
    public closetype?: number | null,
    public riskReport?: IRiskReport | null,
    public creatorid?: IOfficers | null,
    public responsibleperson?: IOfficers | null,
    public auditorid?: IOfficers | null,
    public projectwbs?: IProjectwbs[] | null,
    public progressPlans?: IProgressPlan[] | null,
  ) {}
}
