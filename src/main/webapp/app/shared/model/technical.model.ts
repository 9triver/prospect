import { type IOfficers } from '@/shared/model/officers.model';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';

export interface ITechnical {
  id?: string;
  name?: string | null;
  description?: string | null;
  starttime?: Date | null;
  endtime?: Date | null;
  creatorid?: IOfficers | null;
  auditorid?: IOfficers | null;
  projectwbs?: IProjectwbs[] | null;
}

export class Technical implements ITechnical {
  constructor(
    public id?: string,
    public name?: string | null,
    public description?: string | null,
    public starttime?: Date | null,
    public endtime?: Date | null,
    public creatorid?: IOfficers | null,
    public auditorid?: IOfficers | null,
    public projectwbs?: IProjectwbs[] | null,
  ) {}
}
