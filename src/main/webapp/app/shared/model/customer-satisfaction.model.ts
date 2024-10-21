import { type IProjectwbs } from '@/shared/model/projectwbs.model';

export interface ICustomerSatisfaction {
  id?: number;
  year?: number | null;
  satisfactionitem?: string | null;
  score?: number | null;
  opinion?: string | null;
  totalscore?: number | null;
  surveytime?: Date | null;
  customer?: string | null;
  plonenumber?: string | null;
  filename?: string | null;
  wbsid?: IProjectwbs | null;
}

export class CustomerSatisfaction implements ICustomerSatisfaction {
  constructor(
    public id?: number,
    public year?: number | null,
    public satisfactionitem?: string | null,
    public score?: number | null,
    public opinion?: string | null,
    public totalscore?: number | null,
    public surveytime?: Date | null,
    public customer?: string | null,
    public plonenumber?: string | null,
    public filename?: string | null,
    public wbsid?: IProjectwbs | null,
  ) {}
}
