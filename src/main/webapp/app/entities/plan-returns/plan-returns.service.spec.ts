/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import PlanReturnsService from './plan-returns.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { PlanReturns } from '@/shared/model/plan-returns.model';

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
  describe('PlanReturns Service', () => {
    let service: PlanReturnsService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new PlanReturnsService();
      currentDate = new Date();
      elemDefault = new PlanReturns(
        'ABC',
        'AAAAAAA',
        0,
        'CYCLE',
        'AAAAAAA',
        currentDate,
        currentDate,
        'AAAAAAA',
        0,
        'IN_DEADLINE',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'Not_Comlated',
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            actualstarttime: dayjs(currentDate).format(DATE_FORMAT),
            actualendtime: dayjs(currentDate).format(DATE_FORMAT),
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

      it('should create a PlanReturns', async () => {
        const returnedFromService = Object.assign(
          {
            id: 'ABC',
            actualstarttime: dayjs(currentDate).format(DATE_FORMAT),
            actualendtime: dayjs(currentDate).format(DATE_FORMAT),
            returnstime: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            actualstarttime: currentDate,
            actualendtime: currentDate,
            returnstime: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a PlanReturns', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a PlanReturns', async () => {
        const returnedFromService = Object.assign(
          {
            planreturnsname: 'BBBBBB',
            plantype: 1,
            planlevel: 'BBBBBB',
            description: 'BBBBBB',
            actualstarttime: dayjs(currentDate).format(DATE_FORMAT),
            actualendtime: dayjs(currentDate).format(DATE_FORMAT),
            deliverables: 'BBBBBB',
            progress: 1,
            status: 'BBBBBB',
            impactanalysis: 'BBBBBB',
            returnstime: dayjs(currentDate).format(DATE_FORMAT),
            rejectionreason: 'BBBBBB',
            returnsstatus: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            actualstarttime: currentDate,
            actualendtime: currentDate,
            returnstime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a PlanReturns', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a PlanReturns', async () => {
        const patchObject = Object.assign(
          {
            planreturnsname: 'BBBBBB',
            description: 'BBBBBB',
            actualstarttime: dayjs(currentDate).format(DATE_FORMAT),
            actualendtime: dayjs(currentDate).format(DATE_FORMAT),
            impactanalysis: 'BBBBBB',
            returnstime: dayjs(currentDate).format(DATE_FORMAT),
          },
          new PlanReturns(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            actualstarttime: currentDate,
            actualendtime: currentDate,
            returnstime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a PlanReturns', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of PlanReturns', async () => {
        const returnedFromService = Object.assign(
          {
            planreturnsname: 'BBBBBB',
            plantype: 1,
            planlevel: 'BBBBBB',
            description: 'BBBBBB',
            actualstarttime: dayjs(currentDate).format(DATE_FORMAT),
            actualendtime: dayjs(currentDate).format(DATE_FORMAT),
            deliverables: 'BBBBBB',
            progress: 1,
            status: 'BBBBBB',
            impactanalysis: 'BBBBBB',
            returnstime: dayjs(currentDate).format(DATE_FORMAT),
            rejectionreason: 'BBBBBB',
            returnsstatus: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            actualstarttime: currentDate,
            actualendtime: currentDate,
            returnstime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of PlanReturns', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a PlanReturns', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete('ABC').then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a PlanReturns', async () => {
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
