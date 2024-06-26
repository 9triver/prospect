import { type IProgressplan } from '@/shared/model/progressplan.model';
import { type IDocument } from '@/shared/model/document.model';

export interface IProgressplanreturns {
  id?: string;
  planstarttime?: Date | null;
  planendtime?: Date | null;
  returnstime?: Date | null;
  progressplan?: IProgressplan | null;
  document?: IDocument | null;
}

export class Progressplanreturns implements IProgressplanreturns {
  constructor(
    public id?: string,
    public planstarttime?: Date | null,
    public planendtime?: Date | null,
    public returnstime?: Date | null,
    public progressplan?: IProgressplan | null,
    public document?: IDocument | null,
  ) {}
}
