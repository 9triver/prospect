/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import CycleplanService from './cycleplan.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { Cycleplan } from '@/shared/model/cycleplan.model';

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
  describe('Cycleplan Service', () => {
    let service: CycleplanService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new CycleplanService();
      currentDate = new Date();
      elemDefault = new Cycleplan(
        123,
        0,
        'AAAAAAA',
        'SECRET',
        currentDate,
        currentDate,
        currentDate,
        currentDate,
        'AAAAAAA',
        'IN_DEADLINE',
        'Not_Audited',
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            starttime: dayjs(currentDate).format(DATE_FORMAT),
            endtime: dayjs(currentDate).format(DATE_FORMAT),
            actualstarttime: dayjs(currentDate).format(DATE_FORMAT),
            actualendtime: dayjs(currentDate).format(DATE_FORMAT),
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

      it('should create a Cycleplan', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            starttime: dayjs(currentDate).format(DATE_FORMAT),
            endtime: dayjs(currentDate).format(DATE_FORMAT),
            actualstarttime: dayjs(currentDate).format(DATE_FORMAT),
            actualendtime: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            starttime: currentDate,
            endtime: currentDate,
            actualstarttime: currentDate,
            actualendtime: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a Cycleplan', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Cycleplan', async () => {
        const returnedFromService = Object.assign(
          {
            cycleplanid: 1,
            cycleplanname: 'BBBBBB',
            secretlevel: 'BBBBBB',
            starttime: dayjs(currentDate).format(DATE_FORMAT),
            endtime: dayjs(currentDate).format(DATE_FORMAT),
            actualstarttime: dayjs(currentDate).format(DATE_FORMAT),
            actualendtime: dayjs(currentDate).format(DATE_FORMAT),
            responsiblename: 'BBBBBB',
            status: 'BBBBBB',
            auditStatus: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            starttime: currentDate,
            endtime: currentDate,
            actualstarttime: currentDate,
            actualendtime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Cycleplan', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Cycleplan', async () => {
        const patchObject = Object.assign(
          {
            cycleplanname: 'BBBBBB',
            starttime: dayjs(currentDate).format(DATE_FORMAT),
            actualstarttime: dayjs(currentDate).format(DATE_FORMAT),
            actualendtime: dayjs(currentDate).format(DATE_FORMAT),
            auditStatus: 'BBBBBB',
          },
          new Cycleplan(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            starttime: currentDate,
            endtime: currentDate,
            actualstarttime: currentDate,
            actualendtime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a Cycleplan', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Cycleplan', async () => {
        const returnedFromService = Object.assign(
          {
            cycleplanid: 1,
            cycleplanname: 'BBBBBB',
            secretlevel: 'BBBBBB',
            starttime: dayjs(currentDate).format(DATE_FORMAT),
            endtime: dayjs(currentDate).format(DATE_FORMAT),
            actualstarttime: dayjs(currentDate).format(DATE_FORMAT),
            actualendtime: dayjs(currentDate).format(DATE_FORMAT),
            responsiblename: 'BBBBBB',
            status: 'BBBBBB',
            auditStatus: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            starttime: currentDate,
            endtime: currentDate,
            actualstarttime: currentDate,
            actualendtime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Cycleplan', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Cycleplan', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Cycleplan', async () => {
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
