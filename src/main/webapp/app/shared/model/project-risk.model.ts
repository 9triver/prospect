import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import { type IWorkbag } from '@/shared/model/workbag.model';
import { type IFrontline } from '@/shared/model/frontline.model';
import { type ISystemLevel } from '@/shared/model/system-level.model';
import { type IRiskType } from '@/shared/model/risk-type.model';
import { type IRiskLevel } from '@/shared/model/risk-level.model';
import { type IRiskPossibility } from '@/shared/model/risk-possibility.model';
import { type IProgressPlan } from '@/shared/model/progress-plan.model';

export interface IProjectRisk {
  id?: number;
  year?: number | null;
  name?: string | null;
  riskcontent?: string | null;
  identificationtime?: Date | null;
  riskreason?: string | null;
  importantrange?: string | null;
  measuresandtimelimit?: string | null;
  conditions?: string | null;
  closedloopindicator?: string | null;
  wbsid?: IProjectwbs | null;
  workbag?: IWorkbag | null;
  frontlineid?: IFrontline | null;
  systemLevel?: ISystemLevel | null;
  riskType?: IRiskType | null;
  riskLevel?: IRiskLevel | null;
  riskPossibility?: IRiskPossibility | null;
  progressPlans?: IProgressPlan[] | null;
}

export class ProjectRisk implements IProjectRisk {
  constructor(
    public id?: number,
    public year?: number | null,
    public name?: string | null,
    public riskcontent?: string | null,
    public identificationtime?: Date | null,
    public riskreason?: string | null,
    public importantrange?: string | null,
    public measuresandtimelimit?: string | null,
    public conditions?: string | null,
    public closedloopindicator?: string | null,
    public wbsid?: IProjectwbs | null,
    public workbag?: IWorkbag | null,
    public frontlineid?: IFrontline | null,
    public systemLevel?: ISystemLevel | null,
    public riskType?: IRiskType | null,
    public riskLevel?: IRiskLevel | null,
    public riskPossibility?: IRiskPossibility | null,
    public progressPlans?: IProgressPlan[] | null,
  ) {}
}
