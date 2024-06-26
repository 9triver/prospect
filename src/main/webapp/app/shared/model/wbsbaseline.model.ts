import { type IProjectcharge } from '@/shared/model/projectcharge.model';

import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
export interface IWbsbaseline {
  id?: string;
  secretlevel?: keyof typeof Secretlevel | null;
  requestdeportment?: string | null;
  chargetype?: number | null;
  chargecontent?: string | null;
  status?: number | null;
  version?: number | null;
  remark?: string | null;
  projectcharge?: IProjectcharge | null;
}

export class Wbsbaseline implements IWbsbaseline {
  constructor(
    public id?: string,
    public secretlevel?: keyof typeof Secretlevel | null,
    public requestdeportment?: string | null,
    public chargetype?: number | null,
    public chargecontent?: string | null,
    public status?: number | null,
    public version?: number | null,
    public remark?: string | null,
    public projectcharge?: IProjectcharge | null,
  ) {}
}
