import { type IHrManagement } from '@/shared/model/hr-management.model';
import { type IProgressPlan } from '@/shared/model/progress-plan.model';

import { type PlanLevel } from '@/shared/model/enumerations/plan-level.model';
import { type Planstatus } from '@/shared/model/enumerations/planstatus.model';
import { type ReturnsStatus } from '@/shared/model/enumerations/returns-status.model';
export interface IPlanReturns {
  id?: string;
  planreturnsname?: string | null;
  plantype?: number | null;
  planlevel?: keyof typeof PlanLevel | null;
  description?: string | null;
  actualstarttime?: Date | null;
  actualendtime?: Date | null;
  deliverables?: string | null;
  progress?: number | null;
  status?: keyof typeof Planstatus | null;
  impactanalysis?: string | null;
  returnstime?: Date | null;
  rejectionreason?: string | null;
  returnsstatus?: keyof typeof ReturnsStatus | null;
  responsibleperson?: IHrManagement | null;
  auditorid?: IHrManagement | null;
  progressPlan?: IProgressPlan | null;
}

export class PlanReturns implements IPlanReturns {
  constructor(
    public id?: string,
    public planreturnsname?: string | null,
    public plantype?: number | null,
    public planlevel?: keyof typeof PlanLevel | null,
    public description?: string | null,
    public actualstarttime?: Date | null,
    public actualendtime?: Date | null,
    public deliverables?: string | null,
    public progress?: number | null,
    public status?: keyof typeof Planstatus | null,
    public impactanalysis?: string | null,
    public returnstime?: Date | null,
    public rejectionreason?: string | null,
    public returnsstatus?: keyof typeof ReturnsStatus | null,
    public responsibleperson?: IHrManagement | null,
    public auditorid?: IHrManagement | null,
    public progressPlan?: IProgressPlan | null,
  ) {}
}
