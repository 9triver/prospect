import { type IOfficers } from '@/shared/model/officers.model';

import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
export interface IDocument {
  id?: string;
  documentname?: string | null;
  documenttype?: number | null;
  documentsize?: number | null;
  secretlevel?: keyof typeof Secretlevel | null;
  createtime?: Date | null;
  creatorname?: string | null;
  creatorid?: IOfficers | null;
}

export class Document implements IDocument {
  constructor(
    public id?: string,
    public documentname?: string | null,
    public documenttype?: number | null,
    public documentsize?: number | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public createtime?: Date | null,
    public creatorname?: string | null,
    public creatorid?: IOfficers | null,
  ) {}
}
