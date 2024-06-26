import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
export interface IProgressbaseline {
  id?: string;
  secretlevel?: keyof typeof Secretlevel | null;
  requestdeportment?: string | null;
  chargetype?: number | null;
  chargecontent?: string | null;
  status?: number | null;
  version?: number | null;
  remark?: string | null;
}

export class Progressbaseline implements IProgressbaseline {
  constructor(
    public id?: string,
    public secretlevel?: keyof typeof Secretlevel | null,
    public requestdeportment?: string | null,
    public chargetype?: number | null,
    public chargecontent?: string | null,
    public status?: number | null,
    public version?: number | null,
    public remark?: string | null,
  ) {}
}
