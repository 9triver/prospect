import { type IOfficers } from '@/shared/model/officers.model';

export interface IFrontline {
  id?: string;
  name?: string | null;
  description?: string | null;
  officers?: IOfficers[] | null;
}

export class Frontline implements IFrontline {
  constructor(
    public id?: string,
    public name?: string | null,
    public description?: string | null,
    public officers?: IOfficers[] | null,
  ) {}
}
