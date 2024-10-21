export interface IDeliverables {
  id?: number;
  code?: string | null;
  name?: string | null;
  parentcode?: string | null;
  level?: string | null;
  status?: string | null;
  description?: string | null;
}

export class Deliverables implements IDeliverables {
  constructor(
    public id?: number,
    public code?: string | null,
    public name?: string | null,
    public parentcode?: string | null,
    public level?: string | null,
    public status?: string | null,
    public description?: string | null,
  ) {}
}
