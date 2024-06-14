import { type IOfficers } from '@/shared/model/officers.model';

export interface IApprovalAgent {
  id?: number;
  agentid?: number | null;
  agentname?: string | null;
  agentstarttime?: Date | null;
  autocanceltime?: Date | null;
  agentdepartment?: string | null;
  originalapprovalname?: string | null;
  originaldepartment?: string | null;
  secrecylevel?: number | null;
  originalapprovalid?: IOfficers | null;
}

export class ApprovalAgent implements IApprovalAgent {
  constructor(
    public id?: number,
    public agentid?: number | null,
    public agentname?: string | null,
    public agentstarttime?: Date | null,
    public autocanceltime?: Date | null,
    public agentdepartment?: string | null,
    public originalapprovalname?: string | null,
    public originaldepartment?: string | null,
    public secrecylevel?: number | null,
    public originalapprovalid?: IOfficers | null,
  ) {}
}
