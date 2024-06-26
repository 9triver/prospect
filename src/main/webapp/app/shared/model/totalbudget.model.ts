export interface ITotalbudget {
  id?: string;
  valuationsubjects?: string | null;
  budget?: number | null;
  percentage?: number | null;
  remarks?: string | null;
}

export class Totalbudget implements ITotalbudget {
  constructor(
    public id?: string,
    public valuationsubjects?: string | null,
    public budget?: number | null,
    public percentage?: number | null,
    public remarks?: string | null,
  ) {}
}
