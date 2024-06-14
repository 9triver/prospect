import { type ReturnsStatus } from '@/shared/model/enumerations/returns-status.model';
export interface IPlanreturns {
  id?: number;
  planreturnsid?: number | null;
  planreturnsname?: string | null;
  starttime?: Date | null;
  endtime?: Date | null;
  plantype?: number | null;
  returnstime?: Date | null;
  returnsstatus?: keyof typeof ReturnsStatus | null;
}

export class Planreturns implements IPlanreturns {
  constructor(
    public id?: number,
    public planreturnsid?: number | null,
    public planreturnsname?: string | null,
    public starttime?: Date | null,
    public endtime?: Date | null,
    public plantype?: number | null,
    public returnstime?: Date | null,
    public returnsstatus?: keyof typeof ReturnsStatus | null,
  ) {}
}
