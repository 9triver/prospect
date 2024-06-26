export interface IComprehensiveledger {
  id?: string;
  fundsname?: string | null;
  wbsname?: string | null;
  unitname?: string | null;
  budgetsection?: string | null;
  purpose?: string | null;
  unit?: string | null;
  number?: number | null;
  unitprice?: number | null;
  foreignexchange?: number | null;
}

export class Comprehensiveledger implements IComprehensiveledger {
  constructor(
    public id?: string,
    public fundsname?: string | null,
    public wbsname?: string | null,
    public unitname?: string | null,
    public budgetsection?: string | null,
    public purpose?: string | null,
    public unit?: string | null,
    public number?: number | null,
    public unitprice?: number | null,
    public foreignexchange?: number | null,
  ) {}
}
