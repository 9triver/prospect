import { type IOfficers } from '@/shared/model/officers.model';

import { type Risklevel } from '@/shared/model/enumerations/risklevel.model';
export interface IRiskmanagement {
  id?: number;
  riskid?: number | null;
  projectname?: string | null;
  year?: number | null;
  nodename?: string | null;
  risktype?: number | null;
  decumentid?: number | null;
  version?: number | null;
  usetime?: Date | null;
  systemlevel?: number | null;
  risklevel?: keyof typeof Risklevel | null;
  limitationtime?: string | null;
  closetype?: number | null;
  creatorid?: IOfficers | null;
  responsibleid?: IOfficers | null;
  auditorid?: IOfficers | null;
}

export class Riskmanagement implements IRiskmanagement {
  constructor(
    public id?: number,
    public riskid?: number | null,
    public projectname?: string | null,
    public year?: number | null,
    public nodename?: string | null,
    public risktype?: number | null,
    public decumentid?: number | null,
    public version?: number | null,
    public usetime?: Date | null,
    public systemlevel?: number | null,
    public risklevel?: keyof typeof Risklevel | null,
    public limitationtime?: string | null,
    public closetype?: number | null,
    public creatorid?: IOfficers | null,
    public responsibleid?: IOfficers | null,
    public auditorid?: IOfficers | null,
  ) {}
}
