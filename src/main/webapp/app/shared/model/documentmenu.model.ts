export interface IDocumentmenu {
  id?: number;
  menuid?: string;
  belongtype?: string | null;
  menuname?: string | null;
  parentmenuid?: string | null;
  createtime?: Date | null;
  creatorid?: string | null;
  creatorname?: string | null;
  type?: number | null;
  filenum?: number | null;
  fileurl?: string | null;
  departmentid?: string | null;
  departmentname?: string | null;
  spare1?: Date | null;
  spare2?: number | null;
  spare3?: string | null;
}

export class Documentmenu implements IDocumentmenu {
  constructor(
    public id?: number,
    public menuid?: string,
    public belongtype?: string | null,
    public menuname?: string | null,
    public parentmenuid?: string | null,
    public createtime?: Date | null,
    public creatorid?: string | null,
    public creatorname?: string | null,
    public type?: number | null,
    public filenum?: number | null,
    public fileurl?: string | null,
    public departmentid?: string | null,
    public departmentname?: string | null,
    public spare1?: Date | null,
    public spare2?: number | null,
    public spare3?: string | null,
  ) {}
}
