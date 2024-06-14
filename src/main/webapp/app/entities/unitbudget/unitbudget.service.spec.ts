/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import UnitbudgetService from './unitbudget.service';
import { Unitbudget } from '@/shared/model/unitbudget.model';

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
  describe('Unitbudget Service', () => {
    let service: UnitbudgetService;
    let elemDefault;

    beforeEach(() => {
      service = new UnitbudgetService();
      elemDefault = new Unitbudget(123, 0, 'AAAAAAA', 'AAAAAAA', 0, 0, 0, 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign({}, elemDefault);
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

      it('should create a Unitbudget', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
          },
          elemDefault,
        );
        const expected = Object.assign({}, returnedFromService);

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a Unitbudget', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Unitbudget', async () => {
        const returnedFromService = Object.assign(
          {
            unitbudgetid: 1,
            subprojectname: 'BBBBBB',
            unitbudgername: 'BBBBBB',
            billingunit: 1,
            number: 1,
            totalbudget: 1,
            maintainerbudget: 1,
            outsourcingbudget: 1,
            earmarkedbudget: 1,
            testbudget: 1,
          },
          elemDefault,
        );

        const expected = Object.assign({}, returnedFromService);
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Unitbudget', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Unitbudget', async () => {
        const patchObject = Object.assign(
          {
            subprojectname: 'BBBBBB',
            unitbudgername: 'BBBBBB',
            billingunit: 1,
            number: 1,
            totalbudget: 1,
            outsourcingbudget: 1,
          },
          new Unitbudget(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a Unitbudget', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Unitbudget', async () => {
        const returnedFromService = Object.assign(
          {
            unitbudgetid: 1,
            subprojectname: 'BBBBBB',
            unitbudgername: 'BBBBBB',
            billingunit: 1,
            number: 1,
            totalbudget: 1,
            maintainerbudget: 1,
            outsourcingbudget: 1,
            earmarkedbudget: 1,
            testbudget: 1,
          },
          elemDefault,
        );
        const expected = Object.assign({}, returnedFromService);
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Unitbudget', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Unitbudget', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Unitbudget', async () => {
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
