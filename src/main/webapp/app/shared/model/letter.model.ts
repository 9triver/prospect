import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import { type IWorkbag } from '@/shared/model/workbag.model';
import { type IFrontline } from '@/shared/model/frontline.model';
import { type IDepartment } from '@/shared/model/department.model';
import { type IHrManagement } from '@/shared/model/hr-management.model';

import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
export interface ILetter {
  id?: number;
  lettername?: string | null;
  letternumber?: string | null;
  lettertype?: string | null;
  secretlevel?: keyof typeof Secretlevel | null;
  lettercontent?: string | null;
  letterstatus?: string | null;
  lettertime?: Date | null;
  previousfile?: string | null;
  datarecordstatus?: string | null;
  wbsid?: IProjectwbs | null;
  workbagid?: IWorkbag | null;
  frontlineid?: IFrontline | null;
  receivingunit?: IDepartment | null;
  sendingunit?: IDepartment | null;
  lettermaker?: IHrManagement | null;
  letterreceiver?: IHrManagement | null;
  letterhandler?: IHrManagement | null;
}

export class Letter implements ILetter {
  constructor(
    public id?: number,
    public lettername?: string | null,
    public letternumber?: string | null,
    public lettertype?: string | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public lettercontent?: string | null,
    public letterstatus?: string | null,
    public lettertime?: Date | null,
    public previousfile?: string | null,
    public datarecordstatus?: string | null,
    public wbsid?: IProjectwbs | null,
    public workbagid?: IWorkbag | null,
    public frontlineid?: IFrontline | null,
    public receivingunit?: IDepartment | null,
    public sendingunit?: IDepartment | null,
    public lettermaker?: IHrManagement | null,
    public letterreceiver?: IHrManagement | null,
    public letterhandler?: IHrManagement | null,
  ) {}
}
