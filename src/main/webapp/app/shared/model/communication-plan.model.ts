import { type IProjectwbs } from '@/shared/model/projectwbs.model';

import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface ICommunicationPlan {
  id?: number;
  wbsid?: string | null;
  communicationtopic?: string | null;
  communicationtime?: Date | null;
  worktarget?: string | null;
  workcontent?: string | null;
  remarks?: string | null;
  auditStatus?: keyof typeof AuditStatus | null;
  projectwbs?: IProjectwbs | null;
}

export class CommunicationPlan implements ICommunicationPlan {
  constructor(
    public id?: number,
    public wbsid?: string | null,
    public communicationtopic?: string | null,
    public communicationtime?: Date | null,
    public worktarget?: string | null,
    public workcontent?: string | null,
    public remarks?: string | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public projectwbs?: IProjectwbs | null,
  ) {}
}
