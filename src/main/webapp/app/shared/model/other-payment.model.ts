import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import { type IContract } from '@/shared/model/contract.model';
import { type ISubject } from '@/shared/model/subject.model';

import { type OtherPaymenttype } from '@/shared/model/enumerations/other-paymenttype.model';
export interface IOtherPayment {
  id?: number;
  name?: string | null;
  type?: keyof typeof OtherPaymenttype | null;
  registertime?: Date | null;
  subjectid?: number | null;
  subjectname?: string | null;
  paymentamount?: number | null;
  contractcode?: string | null;
  contractname?: string | null;
  wbsid?: string | null;
  wbsname?: string | null;
  projectwbs?: IProjectwbs | null;
  contract?: IContract | null;
  subject?: ISubject | null;
}

export class OtherPayment implements IOtherPayment {
  constructor(
    public id?: number,
    public name?: string | null,
    public type?: keyof typeof OtherPaymenttype | null,
    public registertime?: Date | null,
    public subjectid?: number | null,
    public subjectname?: string | null,
    public paymentamount?: number | null,
    public contractcode?: string | null,
    public contractname?: string | null,
    public wbsid?: string | null,
    public wbsname?: string | null,
    public projectwbs?: IProjectwbs | null,
    public contract?: IContract | null,
    public subject?: ISubject | null,
  ) {}
}
