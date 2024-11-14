import { type IOutsourcingContract } from '@/shared/model/outsourcing-contract.model';

export interface IMilestoneNode {
  id?: number;
  outsourcingcontractid?: string | null;
  outsourcingcontractname?: string | null;
  name?: string | null;
  planpaymenttime?: Date | null;
  planpaymentamount?: number | null;
  outsourcingContracts?: IOutsourcingContract[] | null;
}

export class MilestoneNode implements IMilestoneNode {
  constructor(
    public id?: number,
    public outsourcingcontractid?: string | null,
    public outsourcingcontractname?: string | null,
    public name?: string | null,
    public planpaymenttime?: Date | null,
    public planpaymentamount?: number | null,
    public outsourcingContracts?: IOutsourcingContract[] | null,
  ) {}
}
