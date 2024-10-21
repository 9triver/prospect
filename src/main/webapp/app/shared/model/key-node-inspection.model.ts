export interface IKeyNodeInspection {
  id?: number;
  name?: string | null;
  workbagid?: string | null;
  workbagname?: string | null;
  belongwbsid?: string | null;
  projectlevel?: string | null;
  iskey?: string | null;
  isimplementationplan?: string | null;
  isqualityplan?: string | null;
  istechniqueplan?: string | null;
  implementationplanstatus?: string | null;
  isimplementationplanmaterial?: string | null;
  technologyplanstatus?: string | null;
  istechnologymaterial?: string | null;
  firstcheckstatus?: string | null;
  isfirstcheckmaterial?: string | null;
  productioncheckstatus?: string | null;
  isproductioncheckmaterial?: string | null;
  status?: string | null;
}

export class KeyNodeInspection implements IKeyNodeInspection {
  constructor(
    public id?: number,
    public name?: string | null,
    public workbagid?: string | null,
    public workbagname?: string | null,
    public belongwbsid?: string | null,
    public projectlevel?: string | null,
    public iskey?: string | null,
    public isimplementationplan?: string | null,
    public isqualityplan?: string | null,
    public istechniqueplan?: string | null,
    public implementationplanstatus?: string | null,
    public isimplementationplanmaterial?: string | null,
    public technologyplanstatus?: string | null,
    public istechnologymaterial?: string | null,
    public firstcheckstatus?: string | null,
    public isfirstcheckmaterial?: string | null,
    public productioncheckstatus?: string | null,
    public isproductioncheckmaterial?: string | null,
    public status?: string | null,
  ) {}
}
