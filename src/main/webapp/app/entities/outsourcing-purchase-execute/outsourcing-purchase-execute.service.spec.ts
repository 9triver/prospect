/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import OutsourcingPurchaseExecuteService from './outsourcing-purchase-execute.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { OutsourcingPurchaseExecute } from '@/shared/model/outsourcing-purchase-execute.model';

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
  describe('OutsourcingPurchaseExecute Service', () => {
    let service: OutsourcingPurchaseExecuteService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new OutsourcingPurchaseExecuteService();
      currentDate = new Date();
      elemDefault = new OutsourcingPurchaseExecute('ABC', 'AAAAAAA', 0, 0, currentDate, currentDate, 0, 0, 'SECRET');
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            needtime: dayjs(currentDate).format(DATE_FORMAT),
            planusetime: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
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

      it('should create a OutsourcingPurchaseExecute', async () => {
        const returnedFromService = Object.assign(
          {
            id: 'ABC',
            needtime: dayjs(currentDate).format(DATE_FORMAT),
            planusetime: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            needtime: currentDate,
            planusetime: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a OutsourcingPurchaseExecute', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a OutsourcingPurchaseExecute', async () => {
        const returnedFromService = Object.assign(
          {
            matarialname: 'BBBBBB',
            purchasingmethod: 1,
            budgit: 1,
            needtime: dayjs(currentDate).format(DATE_FORMAT),
            planusetime: dayjs(currentDate).format(DATE_FORMAT),
            supplierid: 1,
            price: 1,
            secretlevel: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            needtime: currentDate,
            planusetime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a OutsourcingPurchaseExecute', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a OutsourcingPurchaseExecute', async () => {
        const patchObject = Object.assign(
          {
            purchasingmethod: 1,
            needtime: dayjs(currentDate).format(DATE_FORMAT),
            supplierid: 1,
            price: 1,
          },
          new OutsourcingPurchaseExecute(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            needtime: currentDate,
            planusetime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a OutsourcingPurchaseExecute', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of OutsourcingPurchaseExecute', async () => {
        const returnedFromService = Object.assign(
          {
            matarialname: 'BBBBBB',
            purchasingmethod: 1,
            budgit: 1,
            needtime: dayjs(currentDate).format(DATE_FORMAT),
            planusetime: dayjs(currentDate).format(DATE_FORMAT),
            supplierid: 1,
            price: 1,
            secretlevel: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            needtime: currentDate,
            planusetime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of OutsourcingPurchaseExecute', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a OutsourcingPurchaseExecute', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete('ABC').then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a OutsourcingPurchaseExecute', async () => {
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
