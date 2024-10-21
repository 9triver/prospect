import { type IHrManagement } from '@/shared/model/hr-management.model';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';

import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
export interface IDocument {
  id?: string;
  documentname?: string | null;
  documenttype?: number | null;
  documentsize?: number | null;
  secretlevel?: keyof typeof Secretlevel | null;
  url?: string | null;
  createtime?: Date | null;
  creatorname?: string | null;
  creatorid?: IHrManagement | null;
  projectwbs?: IProjectwbs | null;
}

export class Document implements IDocument {
  constructor(
    public id?: string,
    public documentname?: string | null,
    public documenttype?: number | null,
    public documentsize?: number | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public url?: string | null,
    public createtime?: Date | null,
    public creatorname?: string | null,
    public creatorid?: IHrManagement | null,
    public projectwbs?: IProjectwbs | null,
  ) {}
}
