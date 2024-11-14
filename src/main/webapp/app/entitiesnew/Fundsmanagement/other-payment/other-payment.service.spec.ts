/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import OtherPaymentService from './other-payment.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { OtherPayment } from '@/shared/model/other-payment.model';

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
  describe('OtherPayment Service', () => {
    let service: OtherPaymentService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new OtherPaymentService();
      currentDate = new Date();
      elemDefault = new OtherPayment(
        123,
        'AAAAAAA',
        'SPORADICPURCHASE',
        currentDate,
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            registertime: dayjs(currentDate).format(DATE_FORMAT),
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

      it('should create a OtherPayment', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            registertime: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            registertime: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a OtherPayment', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a OtherPayment', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            type: 'BBBBBB',
            registertime: dayjs(currentDate).format(DATE_FORMAT),
            subjectid: 1,
            subjectname: 'BBBBBB',
            paymentamount: 1,
            contractcode: 'BBBBBB',
            contractname: 'BBBBBB',
            wbsid: 'BBBBBB',
            wbsname: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            registertime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a OtherPayment', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a OtherPayment', async () => {
        const patchObject = Object.assign(
          {
            name: 'BBBBBB',
            subjectid: 1,
            paymentamount: 1,
            contractcode: 'BBBBBB',
            contractname: 'BBBBBB',
          },
          new OtherPayment(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            registertime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a OtherPayment', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of OtherPayment', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            type: 'BBBBBB',
            registertime: dayjs(currentDate).format(DATE_FORMAT),
            subjectid: 1,
            subjectname: 'BBBBBB',
            paymentamount: 1,
            contractcode: 'BBBBBB',
            contractname: 'BBBBBB',
            wbsid: 'BBBBBB',
            wbsname: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            registertime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of OtherPayment', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a OtherPayment', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a OtherPayment', async () => {
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
