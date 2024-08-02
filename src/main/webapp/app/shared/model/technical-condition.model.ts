import { type IOfficers } from '@/shared/model/officers.model';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';

import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface ITechnicalCondition {
  id?: string;
  caption?: string | null;
  projectname?: string | null;
  decumentid?: number | null;
  claimant?: string | null;
  applicant?: string | null;
  applicanttime?: Date | null;
  validrange?: string | null;
  createtime?: Date | null;
  auditStatus?: keyof typeof AuditStatus | null;
  creatorid?: IOfficers | null;
  auditorid?: IOfficers | null;
  projectwbs?: IProjectwbs[] | null;
}

export class TechnicalCondition implements ITechnicalCondition {
  constructor(
    public id?: string,
    public caption?: string | null,
    public projectname?: string | null,
    public decumentid?: number | null,
    public claimant?: string | null,
    public applicant?: string | null,
    public applicanttime?: Date | null,
    public validrange?: string | null,
    public createtime?: Date | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public creatorid?: IOfficers | null,
    public auditorid?: IOfficers | null,
    public projectwbs?: IProjectwbs[] | null,
  ) {}
}
