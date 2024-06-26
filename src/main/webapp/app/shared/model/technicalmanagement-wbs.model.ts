import { type ITechnicalCondition } from '@/shared/model/technical-condition.model';

export interface ITechnicalmanagementWbs {
  id?: string;
  wbsspare1?: string | null;
  wbsspare2?: string | null;
  wbsspare3?: string | null;
  wbsspare4?: string | null;
  wbsspare5?: string | null;
  technicalCondition?: ITechnicalCondition | null;
}

export class TechnicalmanagementWbs implements ITechnicalmanagementWbs {
  constructor(
    public id?: string,
    public wbsspare1?: string | null,
    public wbsspare2?: string | null,
    public wbsspare3?: string | null,
    public wbsspare4?: string | null,
    public wbsspare5?: string | null,
    public technicalCondition?: ITechnicalCondition | null,
  ) {}
}
