import { type ISecrecysystem } from '@/shared/model/secrecysystem.model';
import { type IProjectSecrecy } from '@/shared/model/project-secrecy.model';

export interface ISecrecymanagementWbs {
  id?: string;
  wbsspare1?: string | null;
  wbsspare2?: string | null;
  wbsspare3?: string | null;
  wbsspare4?: string | null;
  wbsspare5?: string | null;
  secrecysystem?: ISecrecysystem | null;
  projectSecrecy?: IProjectSecrecy | null;
}

export class SecrecymanagementWbs implements ISecrecymanagementWbs {
  constructor(
    public id?: string,
    public wbsspare1?: string | null,
    public wbsspare2?: string | null,
    public wbsspare3?: string | null,
    public wbsspare4?: string | null,
    public wbsspare5?: string | null,
    public secrecysystem?: ISecrecysystem | null,
    public projectSecrecy?: IProjectSecrecy | null,
  ) {}
}
