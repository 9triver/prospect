import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
export interface IEvents {
  id?: string;
  title?: string | null;
  content?: string | null;
  time?: Date | null;
  place?: string | null;
  participants?: string | null;
  picture?: string | null;
  description?: string | null;
  secretlevel?: keyof typeof Secretlevel | null;
  attachment?: string | null;
}

export class Events implements IEvents {
  constructor(
    public id?: string,
    public title?: string | null,
    public content?: string | null,
    public time?: Date | null,
    public place?: string | null,
    public participants?: string | null,
    public picture?: string | null,
    public description?: string | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public attachment?: string | null,
  ) {}
}
