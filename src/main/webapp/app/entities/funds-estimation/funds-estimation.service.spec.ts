/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import FundsEstimationService from './funds-estimation.service';
import { FundsEstimation } from '@/shared/model/funds-estimation.model';

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
  describe('FundsEstimation Service', () => {
    let service: FundsEstimationService;
    let elemDefault;

    beforeEach(() => {
      service = new FundsEstimationService();
      elemDefault = new FundsEstimation('ABC', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 0, 0, 0, 0);
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

      it('should create a FundsEstimation', async () => {
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

      it('should not create a FundsEstimation', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a FundsEstimation', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            source: 'BBBBBB',
            unit: 'BBBBBB',
            number: 'BBBBBB',
            unitprice: 1,
            materialfee: 1,
            specialfee: 1,
            outsourcingfee: 1,
            totalbudgetprice: 1,
          },
          elemDefault,
        );

        const expected = Object.assign({}, returnedFromService);
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a FundsEstimation', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a FundsEstimation', async () => {
        const patchObject = Object.assign(
          {
            number: 'BBBBBB',
            unitprice: 1,
            specialfee: 1,
            outsourcingfee: 1,
            totalbudgetprice: 1,
          },
          new FundsEstimation(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a FundsEstimation', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of FundsEstimation', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            source: 'BBBBBB',
            unit: 'BBBBBB',
            number: 'BBBBBB',
            unitprice: 1,
            materialfee: 1,
            specialfee: 1,
            outsourcingfee: 1,
            totalbudgetprice: 1,
          },
          elemDefault,
        );
        const expected = Object.assign({}, returnedFromService);
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of FundsEstimation', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a FundsEstimation', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete('ABC').then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a FundsEstimation', async () => {
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
