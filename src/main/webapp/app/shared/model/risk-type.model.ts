export interface IRiskType {
  id?: number;
  name?: string | null;
}

export class RiskType implements IRiskType {
  constructor(
    public id?: number,
    public name?: string | null,
  ) {}
}
