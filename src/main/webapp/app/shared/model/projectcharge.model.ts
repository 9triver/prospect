import { type IOfficers } from '@/shared/model/officers.model';

import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
export interface IProjectcharge {
  id?: number;
  projectname?: string | null;
  formid?: string | null;
  starttime?: Date | null;
  endtime?: Date | null;
  secretlevel?: keyof typeof Secretlevel | null;
  requestdeportment?: string | null;
  chargetype?: number | null;
  chargecontent?: string | null;
  creatorid?: IOfficers | null;
}

export class Projectcharge implements IProjectcharge {
  constructor(
    public id?: number,
    public projectname?: string | null,
    public formid?: string | null,
    public starttime?: Date | null,
    public endtime?: Date | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public requestdeportment?: string | null,
    public chargetype?: number | null,
    public chargecontent?: string | null,
    public creatorid?: IOfficers | null,
  ) {}
}
