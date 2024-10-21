export interface IQualityObjectivesDictionary {
  id?: number;
  objectiveslevel?: string | null;
  objectivestype?: string | null;
  objectivesname?: string | null;
  objectivescontent?: string | null;
  calculationmethod?: string | null;
  frequency?: string | null;
  evaluationcriteria?: string | null;
}

export class QualityObjectivesDictionary implements IQualityObjectivesDictionary {
  constructor(
    public id?: number,
    public objectiveslevel?: string | null,
    public objectivestype?: string | null,
    public objectivesname?: string | null,
    public objectivescontent?: string | null,
    public calculationmethod?: string | null,
    public frequency?: string | null,
    public evaluationcriteria?: string | null,
  ) {}
}
