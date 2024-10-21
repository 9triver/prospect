export interface IRiskPossibility {
  id?: number;
  name?: string | null;
}

export class RiskPossibility implements IRiskPossibility {
  constructor(
    public id?: number,
    public name?: string | null,
  ) {}
}
