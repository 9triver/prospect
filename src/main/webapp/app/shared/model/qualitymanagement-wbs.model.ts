import { type IQualityobjectives } from '@/shared/model/qualityobjectives.model';
import { type IQualityreturns } from '@/shared/model/qualityreturns.model';
import { type IUnQualityAudit } from '@/shared/model/un-quality-audit.model';

export interface IQualitymanagementWbs {
  id?: string;
  wbsspare1?: string | null;
  wbsspare2?: string | null;
  wbsspare3?: string | null;
  wbsspare4?: string | null;
  wbsspare5?: string | null;
  qualityobjectives?: IQualityobjectives | null;
  qualityreturns?: IQualityreturns | null;
  unQualityAudit?: IUnQualityAudit | null;
}

export class QualitymanagementWbs implements IQualitymanagementWbs {
  constructor(
    public id?: string,
    public wbsspare1?: string | null,
    public wbsspare2?: string | null,
    public wbsspare3?: string | null,
    public wbsspare4?: string | null,
    public wbsspare5?: string | null,
    public qualityobjectives?: IQualityobjectives | null,
    public qualityreturns?: IQualityreturns | null,
    public unQualityAudit?: IUnQualityAudit | null,
  ) {}
}
