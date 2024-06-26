/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import ProgressplanreturnsService from './progressplanreturns.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { Progressplanreturns } from '@/shared/model/progressplanreturns.model';

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
  describe('Progressplanreturns Service', () => {
    let service: ProgressplanreturnsService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new ProgressplanreturnsService();
      currentDate = new Date();
      elemDefault = new Progressplanreturns('ABC', currentDate, currentDate, currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            planstarttime: dayjs(currentDate).format(DATE_FORMAT),
            planendtime: dayjs(currentDate).format(DATE_FORMAT),
            returnstime: dayjs(currentDate).format(DATE_FORMAT),
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

      it('should create a Progressplanreturns', async () => {
        const returnedFromService = Object.assign(
          {
            id: 'ABC',
            planstarttime: dayjs(currentDate).format(DATE_FORMAT),
            planendtime: dayjs(currentDate).format(DATE_FORMAT),
            returnstime: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            planstarttime: currentDate,
            planendtime: currentDate,
            returnstime: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a Progressplanreturns', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Progressplanreturns', async () => {
        const returnedFromService = Object.assign(
          {
            planstarttime: dayjs(currentDate).format(DATE_FORMAT),
            planendtime: dayjs(currentDate).format(DATE_FORMAT),
            returnstime: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            planstarttime: currentDate,
            planendtime: currentDate,
            returnstime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Progressplanreturns', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Progressplanreturns', async () => {
        const patchObject = Object.assign(
          {
            planendtime: dayjs(currentDate).format(DATE_FORMAT),
          },
          new Progressplanreturns(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            planstarttime: currentDate,
            planendtime: currentDate,
            returnstime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a Progressplanreturns', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Progressplanreturns', async () => {
        const returnedFromService = Object.assign(
          {
            planstarttime: dayjs(currentDate).format(DATE_FORMAT),
            planendtime: dayjs(currentDate).format(DATE_FORMAT),
            returnstime: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            planstarttime: currentDate,
            planendtime: currentDate,
            returnstime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Progressplanreturns', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Progressplanreturns', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete('ABC').then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Progressplanreturns', async () => {
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
