import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import { type IWorkbag } from '@/shared/model/workbag.model';
import { type ICommunicationDictionary } from '@/shared/model/communication-dictionary.model';
import { type ICommunicationFormDictionary } from '@/shared/model/communication-form-dictionary.model';

export interface ICommunicationRecord {
  id?: number;
  wbsid?: string | null;
  wbsname?: string | null;
  workbagid?: string | null;
  associationmeetingname?: string | null;
  communicationtime?: Date | null;
  communicationlocation?: string | null;
  communicationcontent?: string | null;
  auditorid?: string | null;
  auditorname?: string | null;
  remarks?: string | null;
  projectwbs?: IProjectwbs | null;
  workbag?: IWorkbag | null;
  communication?: ICommunicationDictionary | null;
  workCommunicationForm?: ICommunicationFormDictionary | null;
}

export class CommunicationRecord implements ICommunicationRecord {
  constructor(
    public id?: number,
    public wbsid?: string | null,
    public wbsname?: string | null,
    public workbagid?: string | null,
    public associationmeetingname?: string | null,
    public communicationtime?: Date | null,
    public communicationlocation?: string | null,
    public communicationcontent?: string | null,
    public auditorid?: string | null,
    public auditorname?: string | null,
    public remarks?: string | null,
    public projectwbs?: IProjectwbs | null,
    public workbag?: IWorkbag | null,
    public communication?: ICommunicationDictionary | null,
    public workCommunicationForm?: ICommunicationFormDictionary | null,
  ) {}
}
