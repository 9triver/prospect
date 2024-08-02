/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import CostControlSystemService from './cost-control-system.service';
import { CostControlSystem } from '@/shared/model/cost-control-system.model';

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
  describe('CostControlSystem Service', () => {
    let service: CostControlSystemService;
    let elemDefault;

    beforeEach(() => {
      service = new CostControlSystemService();
      elemDefault = new CostControlSystem('ABC', 0, 'Materialfee', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign({}, elemDefault);
        axiosStub.get.resolves({ data: returnedFromService });

        return service.find('ABC').then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        axiosStub.get.rejects(error);
        return service
          .find('ABC')
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should create a CostControlSystem', async () => {
        const returnedFromService = Object.assign(
          {
            id: 'ABC',
          },
          elemDefault,
        );
        const expected = Object.assign({}, returnedFromService);

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a CostControlSystem', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a CostControlSystem', async () => {
        const returnedFromService = Object.assign(
          {
            type: 1,
            subject: 'BBBBBB',
            implementedamount: 1,
            approvedamount: 1,
            pendingimplementationamount: 1,
            contractpaymentamount: 1,
            managementregistrationnumber: 1,
            financialregistrationnumber: 1,
            contractbudgetamount: 1,
            contractsigningamount: 1,
            contractsettlementamount: 1,
            unforeseeableamount: 1,
            invoicepaymentamount: 1,
            loanpaymentamount: 1,
            accountoutstandingamount: 1,
            pendingpaymentamount: 1,
            pendinginvoiceamount: 1,
          },
          elemDefault,
        );

        const expected = Object.assign({}, returnedFromService);
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a CostControlSystem', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a CostControlSystem', async () => {
        const patchObject = Object.assign(
          {
            subject: 'BBBBBB',
            implementedamount: 1,
            pendingimplementationamount: 1,
            contractpaymentamount: 1,
            managementregistrationnumber: 1,
            contractsigningamount: 1,
            unforeseeableamount: 1,
            pendinginvoiceamount: 1,
          },
          new CostControlSystem(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a CostControlSystem', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of CostControlSystem', async () => {
        const returnedFromService = Object.assign(
          {
            type: 1,
            subject: 'BBBBBB',
            implementedamount: 1,
            approvedamount: 1,
            pendingimplementationamount: 1,
            contractpaymentamount: 1,
            managementregistrationnumber: 1,
            financialregistrationnumber: 1,
            contractbudgetamount: 1,
            contractsigningamount: 1,
            contractsettlementamount: 1,
            unforeseeableamount: 1,
            invoicepaymentamount: 1,
            loanpaymentamount: 1,
            accountoutstandingamount: 1,
            pendingpaymentamount: 1,
            pendinginvoiceamount: 1,
          },
          elemDefault,
        );
        const expected = Object.assign({}, returnedFromService);
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of CostControlSystem', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a CostControlSystem', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete('ABC').then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a CostControlSystem', async () => {
        axiosStub.delete.rejects(error);

        return service
          .delete('ABC')
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
