import { type IOfficers } from '@/shared/model/officers.model';

export interface IHrManagement {
  id?: number;
  officersid?: string | null;
  officersname?: string | null;
  projectid?: number | null;
  projectname?: string | null;
  projectrole?: string | null;
  jobgrade?: string | null;
  departmentid?: string | null;
  departmentname?: string | null;
  frontlineid?: string | null;
  frontlinename?: string | null;
  jobduty?: string | null;
  annualworktime?: number | null;
  annualtasktarget?: string | null;
  officers?: IOfficers | null;
}

export class HrManagement implements IHrManagement {
  constructor(
    public id?: number,
    public officersid?: string | null,
    public officersname?: string | null,
    public projectid?: number | null,
    public projectname?: string | null,
    public projectrole?: string | null,
    public jobgrade?: string | null,
    public departmentid?: string | null,
    public departmentname?: string | null,
    public frontlineid?: string | null,
    public frontlinename?: string | null,
    public jobduty?: string | null,
    public annualworktime?: number | null,
    public annualtasktarget?: string | null,
    public officers?: IOfficers | null,
  ) {}
}
