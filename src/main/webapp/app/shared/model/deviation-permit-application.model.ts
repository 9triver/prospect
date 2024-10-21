import { type IProjectwbs } from '@/shared/model/projectwbs.model';

import { type AuditStatus } from '@/shared/model/enumerations/audit-status.model';
export interface IDeviationPermitApplication {
  id?: number;
  wbsid?: string | null;
  technicalfileid?: string | null;
  applicationunit?: string | null;
  applicant?: string | null;
  applicationdate?: Date | null;
  permitcontent?: string | null;
  permitreason?: string | null;
  projectinfluence?: string | null;
  contractinfluence?: string | null;
  permitrange?: string | null;
  implementationdate?: Date | null;
  remarks?: string | null;
  auditStatus?: keyof typeof AuditStatus | null;
  projectwbs?: IProjectwbs | null;
}

export class DeviationPermitApplication implements IDeviationPermitApplication {
  constructor(
    public id?: number,
    public wbsid?: string | null,
    public technicalfileid?: string | null,
    public applicationunit?: string | null,
    public applicant?: string | null,
    public applicationdate?: Date | null,
    public permitcontent?: string | null,
    public permitreason?: string | null,
    public projectinfluence?: string | null,
    public contractinfluence?: string | null,
    public permitrange?: string | null,
    public implementationdate?: Date | null,
    public remarks?: string | null,
    public auditStatus?: keyof typeof AuditStatus | null,
    public projectwbs?: IProjectwbs | null,
  ) {}
}
