import { type IProgressplan } from '@/shared/model/progressplan.model';
import { type IProgressplanreturns } from '@/shared/model/progressplanreturns.model';
import { type IProgressbaseline } from '@/shared/model/progressbaseline.model';

export interface IProgressmanagementWbs {
  id?: string;
  wbsspare1?: string | null;
  wbsspare2?: string | null;
  wbsspare3?: string | null;
  wbsspare4?: string | null;
  wbsspare5?: string | null;
  progressplan?: IProgressplan | null;
  progressplanreturns?: IProgressplanreturns | null;
  progressbaseline?: IProgressbaseline | null;
}

export class ProgressmanagementWbs implements IProgressmanagementWbs {
  constructor(
    public id?: string,
    public wbsspare1?: string | null,
    public wbsspare2?: string | null,
    public wbsspare3?: string | null,
    public wbsspare4?: string | null,
    public wbsspare5?: string | null,
    public progressplan?: IProgressplan | null,
    public progressplanreturns?: IProgressplanreturns | null,
    public progressbaseline?: IProgressbaseline | null,
  ) {}
}
