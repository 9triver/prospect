import { type IFundsmanagement } from '@/shared/model/fundsmanagement.model';

export interface IFundsavailability {
  id?: number;
  fundsavailabilityid?: number | null;
  fundsid?: number | null;
  year?: number | null;
  budgit?: number | null;
  funding?: number | null;
  fundsmanagement?: IFundsmanagement | null;
}

export class Fundsavailability implements IFundsavailability {
  constructor(
    public id?: number,
    public fundsavailabilityid?: number | null,
    public fundsid?: number | null,
    public year?: number | null,
    public budgit?: number | null,
    public funding?: number | null,
    public fundsmanagement?: IFundsmanagement | null,
  ) {}
}
