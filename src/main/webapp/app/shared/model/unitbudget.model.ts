import { type IOfficers } from '@/shared/model/officers.model';

export interface IUnitbudget {
  id?: number;
  unitbudgetid?: number | null;
  subprojectname?: string | null;
  unitbudgername?: string | null;
  billingunit?: number | null;
  number?: number | null;
  totalbudget?: number | null;
  maintainerbudget?: number | null;
  outsourcingbudget?: number | null;
  earmarkedbudget?: number | null;
  testbudget?: number | null;
  creatorid?: IOfficers | null;
  auditorid?: IOfficers | null;
}

export class Unitbudget implements IUnitbudget {
  constructor(
    public id?: number,
    public unitbudgetid?: number | null,
    public subprojectname?: string | null,
    public unitbudgername?: string | null,
    public billingunit?: number | null,
    public number?: number | null,
    public totalbudget?: number | null,
    public maintainerbudget?: number | null,
    public outsourcingbudget?: number | null,
    public earmarkedbudget?: number | null,
    public testbudget?: number | null,
    public creatorid?: IOfficers | null,
    public auditorid?: IOfficers | null,
  ) {}
}
