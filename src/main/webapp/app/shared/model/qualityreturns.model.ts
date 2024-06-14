import { type ReturnsStatus } from '@/shared/model/enumerations/returns-status.model';
export interface IQualityreturns {
  id?: number;
  qualityreturnsid?: number | null;
  qualityreturnsname?: string | null;
  starttime?: Date | null;
  endtime?: Date | null;
  qualitytype?: number | null;
  returnstime?: Date | null;
  returnsstatus?: keyof typeof ReturnsStatus | null;
}

export class Qualityreturns implements IQualityreturns {
  constructor(
    public id?: number,
    public qualityreturnsid?: number | null,
    public qualityreturnsname?: string | null,
    public starttime?: Date | null,
    public endtime?: Date | null,
    public qualitytype?: number | null,
    public returnstime?: Date | null,
    public returnsstatus?: keyof typeof ReturnsStatus | null,
  ) {}
}
