import { type IAnnualSecurityPlan } from '@/shared/model/annual-security-plan.model';
import { type ISafetycheck } from '@/shared/model/safetycheck.model';

export interface ISecuritymanagementWbs {
  id?: string;
  wbsspare1?: string | null;
  wbsspare2?: string | null;
  wbsspare3?: string | null;
  wbsspare4?: string | null;
  wbsspare5?: string | null;
  annualSecurityPlan?: IAnnualSecurityPlan | null;
  safetycheck?: ISafetycheck | null;
}

export class SecuritymanagementWbs implements ISecuritymanagementWbs {
  constructor(
    public id?: string,
    public wbsspare1?: string | null,
    public wbsspare2?: string | null,
    public wbsspare3?: string | null,
    public wbsspare4?: string | null,
    public wbsspare5?: string | null,
    public annualSecurityPlan?: IAnnualSecurityPlan | null,
    public safetycheck?: ISafetycheck | null,
  ) {}
}
