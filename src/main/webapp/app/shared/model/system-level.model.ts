export interface ISystemLevel {
  id?: number;
  name?: string | null;
}

export class SystemLevel implements ISystemLevel {
  constructor(
    public id?: number,
    public name?: string | null,
  ) {}
}
