import { type IProjectHumanresourcesplan } from '@/shared/model/project-humanresourcesplan.model';
import { type IProjectremit } from '@/shared/model/projectremit.model';
import { type IHumanresources } from '@/shared/model/humanresources.model';

export interface IResourcemanagementWbs {
  id?: string;
  wbsspare1?: string | null;
  wbsspare2?: string | null;
  wbsspare3?: string | null;
  wbsspare4?: string | null;
  wbsspare5?: string | null;
  projectHumanresourcesplan?: IProjectHumanresourcesplan | null;
  projectremit?: IProjectremit | null;
  humanresources?: IHumanresources | null;
}

export class ResourcemanagementWbs implements IResourcemanagementWbs {
  constructor(
    public id?: string,
    public wbsspare1?: string | null,
    public wbsspare2?: string | null,
    public wbsspare3?: string | null,
    public wbsspare4?: string | null,
    public wbsspare5?: string | null,
    public projectHumanresourcesplan?: IProjectHumanresourcesplan | null,
    public projectremit?: IProjectremit | null,
    public humanresources?: IHumanresources | null,
  ) {}
}
