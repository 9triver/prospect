/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import OutsourcingmPurchasePlanService from './outsourcingm-purchase-plan.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { OutsourcingmPurchasePlan } from '@/shared/model/outsourcingm-purchase-plan.model';

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
  describe('OutsourcingmPurchasePlan Service', () => {
    let service: OutsourcingmPurchasePlanService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new OutsourcingmPurchasePlanService();
      currentDate = new Date();
      elemDefault = new OutsourcingmPurchasePlan(123, 0, 'AAAAAAA', 0, 0, currentDate, currentDate, 0, 0, 'SECRET', 'Not_Audited');
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

      it('should create a OutsourcingmPurchasePlan', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
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

      it('should not create a OutsourcingmPurchasePlan', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a OutsourcingmPurchasePlan', async () => {
        const returnedFromService = Object.assign(
          {
            outsourcingplanid: 1,
            matarialname: 'BBBBBB',
            purchasingmethod: 1,
            budgit: 1,
            needtime: dayjs(currentDate).format(DATE_FORMAT),
            planusetime: dayjs(currentDate).format(DATE_FORMAT),
            supplierid: 1,
            price: 1,
            secretlevel: 'BBBBBB',
            auditStatus: 'BBBBBB',
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

      it('should not update a OutsourcingmPurchasePlan', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a OutsourcingmPurchasePlan', async () => {
        const patchObject = Object.assign(
          {
            outsourcingplanid: 1,
            matarialname: 'BBBBBB',
            needtime: dayjs(currentDate).format(DATE_FORMAT),
            planusetime: dayjs(currentDate).format(DATE_FORMAT),
            price: 1,
            secretlevel: 'BBBBBB',
          },
          new OutsourcingmPurchasePlan(),
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

      it('should not partial update a OutsourcingmPurchasePlan', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of OutsourcingmPurchasePlan', async () => {
        const returnedFromService = Object.assign(
          {
            outsourcingplanid: 1,
            matarialname: 'BBBBBB',
            purchasingmethod: 1,
            budgit: 1,
            needtime: dayjs(currentDate).format(DATE_FORMAT),
            planusetime: dayjs(currentDate).format(DATE_FORMAT),
            supplierid: 1,
            price: 1,
            secretlevel: 'BBBBBB',
            auditStatus: 'BBBBBB',
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

      it('should not return a list of OutsourcingmPurchasePlan', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a OutsourcingmPurchasePlan', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a OutsourcingmPurchasePlan', async () => {
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
