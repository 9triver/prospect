import { type IRiskidentification } from '@/shared/model/riskidentification.model';
import { type IRiskreport } from '@/shared/model/riskreport.model';

export interface IRiskmanagementWbs {
  id?: string;
  wbsspare1?: string | null;
  wbsspare2?: string | null;
  wbsspare3?: string | null;
  wbsspare4?: string | null;
  wbsspare5?: string | null;
  riskidentification?: IRiskidentification | null;
  riskreport?: IRiskreport | null;
}

export class RiskmanagementWbs implements IRiskmanagementWbs {
  constructor(
    public id?: string,
    public wbsspare1?: string | null,
    public wbsspare2?: string | null,
    public wbsspare3?: string | null,
    public wbsspare4?: string | null,
    public wbsspare5?: string | null,
    public riskidentification?: IRiskidentification | null,
    public riskreport?: IRiskreport | null,
  ) {}
}
