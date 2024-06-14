export interface ITotalbudget {
  id?: number;
  totalbudgetid?: number | null;
  valuationsubjects?: string | null;
  budget?: number | null;
  percentage?: number | null;
  remarks?: string | null;
}

export class Totalbudget implements ITotalbudget {
  constructor(
    public id?: number,
    public totalbudgetid?: number | null,
    public valuationsubjects?: string | null,
    public budget?: number | null,
    public percentage?: number | null,
    public remarks?: string | null,
  ) {}
}
