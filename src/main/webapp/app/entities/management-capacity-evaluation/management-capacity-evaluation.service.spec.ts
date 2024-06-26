/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import ManagementCapacityEvaluationService from './management-capacity-evaluation.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { ManagementCapacityEvaluation } from '@/shared/model/management-capacity-evaluation.model';

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
  describe('ManagementCapacityEvaluation Service', () => {
    let service: ManagementCapacityEvaluationService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new ManagementCapacityEvaluationService();
      currentDate = new Date();
      elemDefault = new ManagementCapacityEvaluation('ABC', 0, 'AAAAAAA', currentDate, 0, 0, 0, 'AAAAAAA', 'AAAAAAA', currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            createtime: dayjs(currentDate).format(DATE_FORMAT),
            ratingtimg: dayjs(currentDate).format(DATE_FORMAT),
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

      it('should create a ManagementCapacityEvaluation', async () => {
        const returnedFromService = Object.assign(
          {
            id: 'ABC',
            createtime: dayjs(currentDate).format(DATE_FORMAT),
            ratingtimg: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            createtime: currentDate,
            ratingtimg: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a ManagementCapacityEvaluation', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a ManagementCapacityEvaluation', async () => {
        const returnedFromService = Object.assign(
          {
            year: 1,
            deprotment: 'BBBBBB',
            createtime: dayjs(currentDate).format(DATE_FORMAT),
            status: 1,
            totalmark: 1,
            mark: 1,
            ratingpersonname: 'BBBBBB',
            ratingdepartment: 'BBBBBB',
            ratingtimg: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            createtime: currentDate,
            ratingtimg: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a ManagementCapacityEvaluation', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a ManagementCapacityEvaluation', async () => {
        const patchObject = Object.assign(
          {
            year: 1,
            deprotment: 'BBBBBB',
            createtime: dayjs(currentDate).format(DATE_FORMAT),
            status: 1,
            totalmark: 1,
            mark: 1,
          },
          new ManagementCapacityEvaluation(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            createtime: currentDate,
            ratingtimg: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a ManagementCapacityEvaluation', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of ManagementCapacityEvaluation', async () => {
        const returnedFromService = Object.assign(
          {
            year: 1,
            deprotment: 'BBBBBB',
            createtime: dayjs(currentDate).format(DATE_FORMAT),
            status: 1,
            totalmark: 1,
            mark: 1,
            ratingpersonname: 'BBBBBB',
            ratingdepartment: 'BBBBBB',
            ratingtimg: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            createtime: currentDate,
            ratingtimg: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of ManagementCapacityEvaluation', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a ManagementCapacityEvaluation', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete('ABC').then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a ManagementCapacityEvaluation', async () => {
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
