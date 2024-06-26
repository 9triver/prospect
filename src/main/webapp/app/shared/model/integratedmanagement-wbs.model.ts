import { type IPlanstrategy } from '@/shared/model/planstrategy.model';
import { type IComprehensivecontrol } from '@/shared/model/comprehensivecontrol.model';
import { type IDocument } from '@/shared/model/document.model';
import { type IComprehensiveledger } from '@/shared/model/comprehensiveledger.model';
import { type ICycleplan } from '@/shared/model/cycleplan.model';
import { type IAnnualplan } from '@/shared/model/annualplan.model';
import { type IMonthplan } from '@/shared/model/monthplan.model';
import { type IPlanreturns } from '@/shared/model/planreturns.model';
import { type IPlanmonitor } from '@/shared/model/planmonitor.model';
import { type IPlanexecute } from '@/shared/model/planexecute.model';
import { type IProjectcharge } from '@/shared/model/projectcharge.model';

export interface IIntegratedmanagementWbs {
  id?: string;
  wbsspare1?: string | null;
  wbsspare2?: string | null;
  wbsspare3?: string | null;
  wbsspare4?: string | null;
  wbsspare5?: string | null;
  planstrategy?: IPlanstrategy | null;
  comprehensivecontrol?: IComprehensivecontrol | null;
  document?: IDocument | null;
  comprehensiveledger?: IComprehensiveledger | null;
  cycleplan?: ICycleplan | null;
  annualplan?: IAnnualplan | null;
  monthplan?: IMonthplan | null;
  planreturns?: IPlanreturns | null;
  planmonitor?: IPlanmonitor | null;
  planexecute?: IPlanexecute | null;
  projectcharge?: IProjectcharge | null;
}

export class IntegratedmanagementWbs implements IIntegratedmanagementWbs {
  constructor(
    public id?: string,
    public wbsspare1?: string | null,
    public wbsspare2?: string | null,
    public wbsspare3?: string | null,
    public wbsspare4?: string | null,
    public wbsspare5?: string | null,
    public planstrategy?: IPlanstrategy | null,
    public comprehensivecontrol?: IComprehensivecontrol | null,
    public document?: IDocument | null,
    public comprehensiveledger?: IComprehensiveledger | null,
    public cycleplan?: ICycleplan | null,
    public annualplan?: IAnnualplan | null,
    public monthplan?: IMonthplan | null,
    public planreturns?: IPlanreturns | null,
    public planmonitor?: IPlanmonitor | null,
    public planexecute?: IPlanexecute | null,
    public projectcharge?: IProjectcharge | null,
  ) {}
}
