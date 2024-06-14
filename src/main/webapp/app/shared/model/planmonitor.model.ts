import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
export interface IPlanmonitor {
  id?: number;
  month?: Date | null;
  type?: string | null;
  year?: number | null;
  secretlevel?: keyof typeof Secretlevel | null;
  status?: number | null;
}

export class Planmonitor implements IPlanmonitor {
  constructor(
    public id?: number,
    public month?: Date | null,
    public type?: string | null,
    public year?: number | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public status?: number | null,
  ) {}
}
