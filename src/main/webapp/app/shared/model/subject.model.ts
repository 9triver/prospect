export interface ISubject {
  id?: number;
  name?: string | null;
  type?: string | null;
  parentid?: string | null;
  remark?: string | null;
}

export class Subject implements ISubject {
  constructor(
    public id?: number,
    public name?: string | null,
    public type?: string | null,
    public parentid?: string | null,
    public remark?: string | null,
  ) {}
}
