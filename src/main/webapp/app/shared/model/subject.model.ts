export interface ISubject {
  id?: number;
  name?: string | null;
  type?: string | null;
  status?: number | null;
  remark?: string | null;
}

export class Subject implements ISubject {
  constructor(
    public id?: number,
    public name?: string | null,
    public type?: string | null,
    public status?: number | null,
    public remark?: string | null,
  ) {}
}
