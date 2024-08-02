/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import ProgressPlanService from './progress-plan.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { ProgressPlan } from '@/shared/model/progress-plan.model';

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
  describe('ProgressPlan Service', () => {
    let service: ProgressPlanService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new ProgressPlanService();
      currentDate = new Date();
      elemDefault = new ProgressPlan(
        'ABC',
        'AAAAAAA',
        'SECRET',
        0,
        'CYCLE',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        currentDate,
        currentDate,
        0,
        'Not_start',
        0,
        'IN_DEADLINE',
        'Not_Audited',
        'AAAAAAA',
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            readytime: dayjs(currentDate).format(DATE_FORMAT),
            starttime: dayjs(currentDate).format(DATE_FORMAT),
            endtime: dayjs(currentDate).format(DATE_FORMAT),
            actualstarttime: dayjs(currentDate).format(DATE_FORMAT),
            actualendtime: dayjs(currentDate).format(DATE_FORMAT),
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

      it('should create a ProgressPlan', async () => {
        const returnedFromService = Object.assign(
          {
            id: 'ABC',
            readytime: dayjs(currentDate).format(DATE_FORMAT),
            starttime: dayjs(currentDate).format(DATE_FORMAT),
            endtime: dayjs(currentDate).format(DATE_FORMAT),
            actualstarttime: dayjs(currentDate).format(DATE_FORMAT),
            actualendtime: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            readytime: currentDate,
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

      it('should not create a ProgressPlan', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a ProgressPlan', async () => {
        const returnedFromService = Object.assign(
          {
            planname: 'BBBBBB',
            secretlevel: 'BBBBBB',
            plantype: 1,
            planlevel: 'BBBBBB',
            planstage: 'BBBBBB',
            readytime: dayjs(currentDate).format(DATE_FORMAT),
            description: 'BBBBBB',
            deliverables: 'BBBBBB',
            planobjectives: 'BBBBBB',
            preplan: 'BBBBBB',
            starttime: dayjs(currentDate).format(DATE_FORMAT),
            endtime: dayjs(currentDate).format(DATE_FORMAT),
            actualstarttime: dayjs(currentDate).format(DATE_FORMAT),
            actualendtime: dayjs(currentDate).format(DATE_FORMAT),
            progress: 1,
            progresstype: 'BBBBBB',
            iskey: 1,
            status: 'BBBBBB',
            auditStatus: 'BBBBBB',
            remark: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            readytime: currentDate,
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

      it('should not update a ProgressPlan', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a ProgressPlan', async () => {
        const patchObject = Object.assign(
          {
            secretlevel: 'BBBBBB',
            plantype: 1,
            planlevel: 'BBBBBB',
            planstage: 'BBBBBB',
            readytime: dayjs(currentDate).format(DATE_FORMAT),
            description: 'BBBBBB',
            deliverables: 'BBBBBB',
            planobjectives: 'BBBBBB',
            endtime: dayjs(currentDate).format(DATE_FORMAT),
            progress: 1,
            progresstype: 'BBBBBB',
            iskey: 1,
            status: 'BBBBBB',
            remark: 'BBBBBB',
          },
          new ProgressPlan(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            readytime: currentDate,
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

      it('should not partial update a ProgressPlan', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of ProgressPlan', async () => {
        const returnedFromService = Object.assign(
          {
            planname: 'BBBBBB',
            secretlevel: 'BBBBBB',
            plantype: 1,
            planlevel: 'BBBBBB',
            planstage: 'BBBBBB',
            readytime: dayjs(currentDate).format(DATE_FORMAT),
            description: 'BBBBBB',
            deliverables: 'BBBBBB',
            planobjectives: 'BBBBBB',
            preplan: 'BBBBBB',
            starttime: dayjs(currentDate).format(DATE_FORMAT),
            endtime: dayjs(currentDate).format(DATE_FORMAT),
            actualstarttime: dayjs(currentDate).format(DATE_FORMAT),
            actualendtime: dayjs(currentDate).format(DATE_FORMAT),
            progress: 1,
            progresstype: 'BBBBBB',
            iskey: 1,
            status: 'BBBBBB',
            auditStatus: 'BBBBBB',
            remark: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            readytime: currentDate,
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

      it('should not return a list of ProgressPlan', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a ProgressPlan', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete('ABC').then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a ProgressPlan', async () => {
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
