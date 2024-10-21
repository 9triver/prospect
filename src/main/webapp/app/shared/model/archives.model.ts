import { type IHrManagement } from '@/shared/model/hr-management.model';

import { type Secretlevel } from '@/shared/model/enumerations/secretlevel.model';
export interface IArchives {
  id?: string;
  title?: string | null;
  content?: string | null;
  date?: Date | null;
  page?: number | null;
  secretlevel?: keyof typeof Secretlevel | null;
  confidentialityperiod?: Date | null;
  confidentialnumber?: string | null;
  storageperiod?: Date | null;
  plannumber?: string | null;
  remarks?: string | null;
  receivingnumber?: string | null;
  responsibleid?: IHrManagement | null;
}

export class Archives implements IArchives {
  constructor(
    public id?: string,
    public title?: string | null,
    public content?: string | null,
    public date?: Date | null,
    public page?: number | null,
    public secretlevel?: keyof typeof Secretlevel | null,
    public confidentialityperiod?: Date | null,
    public confidentialnumber?: string | null,
    public storageperiod?: Date | null,
    public plannumber?: string | null,
    public remarks?: string | null,
    public receivingnumber?: string | null,
    public responsibleid?: IHrManagement | null,
  ) {}
}
