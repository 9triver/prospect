/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import OutsourcingContractService from './outsourcing-contract.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { OutsourcingContract } from '@/shared/model/outsourcing-contract.model';

const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

const axiosStub = {
  get: sinon.stub(axios, 'get'),
  post: sinon.stub(axios, 'post'),
  put: sinon.stub(axios, 'put'),
  patch: sinon.stub(axios, 'patch'),
  delete: sinon.stub(axios, 'delete'),
};

describe('Service Tests', () => {
  describe('OutsourcingContract Service', () => {
    let service: OutsourcingContractService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new OutsourcingContractService();
      currentDate = new Date();
      elemDefault = new OutsourcingContract(
        123,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            negotiationdate: dayjs(currentDate).format(DATE_FORMAT),
            approvaldate: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        axiosStub.get.resolves({ data: returnedFromService });

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        axiosStub.get.rejects(error);
        return service
          .find(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should create a OutsourcingContract', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            negotiationdate: dayjs(currentDate).format(DATE_FORMAT),
            approvaldate: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            negotiationdate: currentDate,
            approvaldate: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a OutsourcingContract', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a OutsourcingContract', async () => {
        const returnedFromService = Object.assign(
          {
            contractid: 'BBBBBB',
            contractcode: 'BBBBBB',
            contractname: 'BBBBBB',
            contractqualityid: 'BBBBBB',
            contractcostid: 'BBBBBB',
            contractfinanceid: 'BBBBBB',
            projectid: 'BBBBBB',
            projectsecretlevel: 'BBBBBB',
            counterpartyunit: 'BBBBBB',
            negotiationdate: dayjs(currentDate).format(DATE_FORMAT),
            negotiationlocation: 'BBBBBB',
            negotiator: 'BBBBBB',
            budgetamount: 1,
            contractamount: 1,
            approver: 'BBBBBB',
            approvaldate: dayjs(currentDate).format(DATE_FORMAT),
            contractsecretlevel: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            negotiationdate: currentDate,
            approvaldate: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a OutsourcingContract', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a OutsourcingContract', async () => {
        const patchObject = Object.assign(
          {
            contractname: 'BBBBBB',
            contractcostid: 'BBBBBB',
            projectid: 'BBBBBB',
            projectsecretlevel: 'BBBBBB',
            counterpartyunit: 'BBBBBB',
            negotiationlocation: 'BBBBBB',
            negotiator: 'BBBBBB',
            budgetamount: 1,
            approvaldate: dayjs(currentDate).format(DATE_FORMAT),
          },
          new OutsourcingContract(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            negotiationdate: currentDate,
            approvaldate: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a OutsourcingContract', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of OutsourcingContract', async () => {
        const returnedFromService = Object.assign(
          {
            contractid: 'BBBBBB',
            contractcode: 'BBBBBB',
            contractname: 'BBBBBB',
            contractqualityid: 'BBBBBB',
            contractcostid: 'BBBBBB',
            contractfinanceid: 'BBBBBB',
            projectid: 'BBBBBB',
            projectsecretlevel: 'BBBBBB',
            counterpartyunit: 'BBBBBB',
            negotiationdate: dayjs(currentDate).format(DATE_FORMAT),
            negotiationlocation: 'BBBBBB',
            negotiator: 'BBBBBB',
            budgetamount: 1,
            contractamount: 1,
            approver: 'BBBBBB',
            approvaldate: dayjs(currentDate).format(DATE_FORMAT),
            contractsecretlevel: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            negotiationdate: currentDate,
            approvaldate: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of OutsourcingContract', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a OutsourcingContract', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a OutsourcingContract', async () => {
        axiosStub.delete.rejects(error);

        return service
          .delete(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
