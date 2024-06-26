/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import TechnicalConditionService from './technical-condition.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { TechnicalCondition } from '@/shared/model/technical-condition.model';

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
  describe('TechnicalCondition Service', () => {
    let service: TechnicalConditionService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new TechnicalConditionService();
      currentDate = new Date();
      elemDefault = new TechnicalCondition(
        'ABC',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        currentDate,
        'Not_Audited',
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            applicanttime: dayjs(currentDate).format(DATE_FORMAT),
            createtime: dayjs(currentDate).format(DATE_FORMAT),
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

      it('should create a TechnicalCondition', async () => {
        const returnedFromService = Object.assign(
          {
            id: 'ABC',
            applicanttime: dayjs(currentDate).format(DATE_FORMAT),
            createtime: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            applicanttime: currentDate,
            createtime: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a TechnicalCondition', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a TechnicalCondition', async () => {
        const returnedFromService = Object.assign(
          {
            caption: 'BBBBBB',
            projectname: 'BBBBBB',
            decumentid: 1,
            claimant: 'BBBBBB',
            applicant: 'BBBBBB',
            applicanttime: dayjs(currentDate).format(DATE_FORMAT),
            validrange: 'BBBBBB',
            createtime: dayjs(currentDate).format(DATE_FORMAT),
            auditStatus: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            applicanttime: currentDate,
            createtime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a TechnicalCondition', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a TechnicalCondition', async () => {
        const patchObject = Object.assign(
          {
            projectname: 'BBBBBB',
            decumentid: 1,
            applicant: 'BBBBBB',
            applicanttime: dayjs(currentDate).format(DATE_FORMAT),
            createtime: dayjs(currentDate).format(DATE_FORMAT),
            auditStatus: 'BBBBBB',
          },
          new TechnicalCondition(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            applicanttime: currentDate,
            createtime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a TechnicalCondition', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of TechnicalCondition', async () => {
        const returnedFromService = Object.assign(
          {
            caption: 'BBBBBB',
            projectname: 'BBBBBB',
            decumentid: 1,
            claimant: 'BBBBBB',
            applicant: 'BBBBBB',
            applicanttime: dayjs(currentDate).format(DATE_FORMAT),
            validrange: 'BBBBBB',
            createtime: dayjs(currentDate).format(DATE_FORMAT),
            auditStatus: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            applicanttime: currentDate,
            createtime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of TechnicalCondition', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a TechnicalCondition', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete('ABC').then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a TechnicalCondition', async () => {
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
