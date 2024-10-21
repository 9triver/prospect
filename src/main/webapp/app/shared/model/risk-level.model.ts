export interface IRiskLevel {
  id?: number;
  name?: string | null;
}

export class RiskLevel implements IRiskLevel {
  constructor(
    public id?: number,
    public name?: string | null,
  ) {}
}
