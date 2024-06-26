/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import ComprehensivecontrolService from './comprehensivecontrol.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { Comprehensivecontrol } from '@/shared/model/comprehensivecontrol.model';

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
  describe('Comprehensivecontrol Service', () => {
    let service: ComprehensivecontrolService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new ComprehensivecontrolService();
      currentDate = new Date();
      elemDefault = new Comprehensivecontrol(
        'ABC',
        'AAAAAAA',
        0,
        0,
        currentDate,
        currentDate,
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'NOTSTART',
        'Not_Audited',
        'AAAAAAA',
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

      it('should create a Comprehensivecontrol', async () => {
        const returnedFromService = Object.assign(
          {
            id: 'ABC',
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

      it('should not create a Comprehensivecontrol', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Comprehensivecontrol', async () => {
        const returnedFromService = Object.assign(
          {
            description: 'BBBBBB',
            number: 1,
            type: 1,
            starttime: dayjs(currentDate).format(DATE_FORMAT),
            endtime: dayjs(currentDate).format(DATE_FORMAT),
            actualstarttime: dayjs(currentDate).format(DATE_FORMAT),
            actualendtime: dayjs(currentDate).format(DATE_FORMAT),
            result: 'BBBBBB',
            acceptancetype: 'BBBBBB',
            status: 'BBBBBB',
            auditStatus: 'BBBBBB',
            responsiblename: 'BBBBBB',
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

      it('should not update a Comprehensivecontrol', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Comprehensivecontrol', async () => {
        const patchObject = Object.assign(
          {
            type: 1,
            actualstarttime: dayjs(currentDate).format(DATE_FORMAT),
            actualendtime: dayjs(currentDate).format(DATE_FORMAT),
            result: 'BBBBBB',
            acceptancetype: 'BBBBBB',
            status: 'BBBBBB',
            auditStatus: 'BBBBBB',
            responsiblename: 'BBBBBB',
          },
          new Comprehensivecontrol(),
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

      it('should not partial update a Comprehensivecontrol', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Comprehensivecontrol', async () => {
        const returnedFromService = Object.assign(
          {
            description: 'BBBBBB',
            number: 1,
            type: 1,
            starttime: dayjs(currentDate).format(DATE_FORMAT),
            endtime: dayjs(currentDate).format(DATE_FORMAT),
            actualstarttime: dayjs(currentDate).format(DATE_FORMAT),
            actualendtime: dayjs(currentDate).format(DATE_FORMAT),
            result: 'BBBBBB',
            acceptancetype: 'BBBBBB',
            status: 'BBBBBB',
            auditStatus: 'BBBBBB',
            responsiblename: 'BBBBBB',
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

      it('should not return a list of Comprehensivecontrol', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Comprehensivecontrol', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete('ABC').then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Comprehensivecontrol', async () => {
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
