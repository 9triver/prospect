import { type IOfficers } from '@/shared/model/officers.model';

import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
export interface IContractualfunds {
  id?: string;
  department?: string | null;
  year?: number | null;
  starttime?: Date | null;
  endtime?: Date | null;
  status?: number | null;
  secretlevel?: keyof typeof Secretlevel | null;
  foreigncurrency?: number | null;
  totalbudget?: number | null;
  fundsinplace?: number | null;
  responsibleunitname?: string | null;
  audittime?: Date | null;
  accountbank?: string | null;
  creatorid?: IOfficers | null;
  auditorid?: IOfficers | null;
}

export class Contractualfunds implements IContractualfunds {
  constructor(
    public id?: string,
    public department?: string | null,
    public year?: number | null,
    public starttime?: Date | null,
    public endtime?: Date | null,
    public status?: number | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public foreigncurrency?: number | null,
    public totalbudget?: number | null,
    public fundsinplace?: number | null,
    public responsibleunitname?: string | null,
    public audittime?: Date | null,
    public accountbank?: string | null,
    public creatorid?: IOfficers | null,
    public auditorid?: IOfficers | null,
  ) {}
}
