export interface ICommunicationDictionary {
  id?: number;
  partiesname?: string | null;
  partiestype?: string | null;
  partiesduty?: string | null;
}

export class CommunicationDictionary implements ICommunicationDictionary {
  constructor(
    public id?: number,
    public partiesname?: string | null,
    public partiestype?: string | null,
    public partiesduty?: string | null,
  ) {}
}
