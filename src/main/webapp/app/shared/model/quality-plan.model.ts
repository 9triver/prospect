import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import { type IWorkbag } from '@/shared/model/workbag.model';

import { type QualityType } from '@/shared/model/enumerations/quality-type.model';
import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
export interface IQualityPlan {
  id?: string;
  name?: string | null;
  qualitytype?: keyof typeof QualityType | null;
  secretlevel?: keyof typeof Secretlevel | null;
  wbsid?: string | null;
  workbagid?: string | null;
  fileversion?: string | null;
  author?: string | null;
  attachment?: string | null;
  projectwbs?: IProjectwbs | null;
  workbag?: IWorkbag | null;
}

export class QualityPlan implements IQualityPlan {
  constructor(
    public id?: string,
    public name?: string | null,
    public qualitytype?: keyof typeof QualityType | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public wbsid?: string | null,
    public workbagid?: string | null,
    public fileversion?: string | null,
    public author?: string | null,
    public attachment?: string | null,
    public projectwbs?: IProjectwbs | null,
    public workbag?: IWorkbag | null,
  ) {}
}
