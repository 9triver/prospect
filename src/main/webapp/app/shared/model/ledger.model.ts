import { type IOfficers } from '@/shared/model/officers.model';

export interface ILedger {
  id?: number;
  officers?: IOfficers | null;
}

export class Ledger implements ILedger {
  constructor(
    public id?: number,
    public officers?: IOfficers | null,
  ) {}
}
