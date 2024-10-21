/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import CustomerSatisfactionService from './customer-satisfaction.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { CustomerSatisfaction } from '@/shared/model/customer-satisfaction.model';

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
  describe('CustomerSatisfaction Service', () => {
    let service: CustomerSatisfactionService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new CustomerSatisfactionService();
      currentDate = new Date();
      elemDefault = new CustomerSatisfaction(123, 0, 'AAAAAAA', 0, 'AAAAAAA', 0, currentDate, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            surveytime: dayjs(currentDate).format(DATE_FORMAT),
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

      it('should create a CustomerSatisfaction', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            surveytime: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            surveytime: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a CustomerSatisfaction', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a CustomerSatisfaction', async () => {
        const returnedFromService = Object.assign(
          {
            year: 1,
            satisfactionitem: 'BBBBBB',
            score: 1,
            opinion: 'BBBBBB',
            totalscore: 1,
            surveytime: dayjs(currentDate).format(DATE_FORMAT),
            customer: 'BBBBBB',
            plonenumber: 'BBBBBB',
            filename: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            surveytime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a CustomerSatisfaction', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a CustomerSatisfaction', async () => {
        const patchObject = Object.assign(
          {
            satisfactionitem: 'BBBBBB',
            opinion: 'BBBBBB',
            customer: 'BBBBBB',
            plonenumber: 'BBBBBB',
          },
          new CustomerSatisfaction(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            surveytime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a CustomerSatisfaction', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of CustomerSatisfaction', async () => {
        const returnedFromService = Object.assign(
          {
            year: 1,
            satisfactionitem: 'BBBBBB',
            score: 1,
            opinion: 'BBBBBB',
            totalscore: 1,
            surveytime: dayjs(currentDate).format(DATE_FORMAT),
            customer: 'BBBBBB',
            plonenumber: 'BBBBBB',
            filename: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            surveytime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of CustomerSatisfaction', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a CustomerSatisfaction', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a CustomerSatisfaction', async () => {
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
