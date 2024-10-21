export interface ICommunicationFormDictionary {
  id?: number;
  communicationformname?: string | null;
  communicationformtype?: string | null;
  status?: string | null;
}

export class CommunicationFormDictionary implements ICommunicationFormDictionary {
  constructor(
    public id?: number,
    public communicationformname?: string | null,
    public communicationformtype?: string | null,
    public status?: string | null,
  ) {}
}
