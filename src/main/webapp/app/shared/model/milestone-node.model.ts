import { type IOutsourcingContract } from '@/shared/model/outsourcing-contract.model';

export interface IMilestoneNode {
  id?: number;
  name?: string | null;
  planpaymenttime?: Date | null;
  planpaymentamount?: number | null;
  outsourcingContract?: IOutsourcingContract | null;
}

export class MilestoneNode implements IMilestoneNode {
  constructor(
    public id?: number,
    public name?: string | null,
    public planpaymenttime?: Date | null,
    public planpaymentamount?: number | null,
    public outsourcingContract?: IOutsourcingContract | null,
  ) {}
}
