/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import QualityReturnsService from './quality-returns.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { QualityReturns } from '@/shared/model/quality-returns.model';

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
  describe('QualityReturns Service', () => {
    let service: QualityReturnsService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new QualityReturnsService();
      currentDate = new Date();
      elemDefault = new QualityReturns(
        'ABC',
        'AAAAAAA',
        'AAAAAAA',
        'Cycle',
        'SECRET',
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        'Not_Audited',
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            returntime: dayjs(currentDate).format(DATE_FORMAT),
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

      it('should create a QualityReturns', async () => {
        const returnedFromService = Object.assign(
          {
            id: 'ABC',
            returntime: dayjs(currentDate).format(DATE_FORMAT),
            createtime: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            returntime: currentDate,
            createtime: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a QualityReturns', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a QualityReturns', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            objectives: 'BBBBBB',
            qualitytype: 'BBBBBB',
            secretlevel: 'BBBBBB',
            target: 1,
            statisticalmethod: 'BBBBBB',
            statisticalfrequency: 'BBBBBB',
            istarget: 1,
            progress: 1,
            description: 'BBBBBB',
            problems: 'BBBBBB',
            improvementmeasures: 'BBBBBB',
            returntime: dayjs(currentDate).format(DATE_FORMAT),
            createtime: dayjs(currentDate).format(DATE_FORMAT),
            auditStatus: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            returntime: currentDate,
            createtime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a QualityReturns', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a QualityReturns', async () => {
        const patchObject = Object.assign(
          {
            name: 'BBBBBB',
            target: 1,
            statisticalmethod: 'BBBBBB',
            statisticalfrequency: 'BBBBBB',
            problems: 'BBBBBB',
            returntime: dayjs(currentDate).format(DATE_FORMAT),
            auditStatus: 'BBBBBB',
          },
          new QualityReturns(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            returntime: currentDate,
            createtime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a QualityReturns', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of QualityReturns', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            objectives: 'BBBBBB',
            qualitytype: 'BBBBBB',
            secretlevel: 'BBBBBB',
            target: 1,
            statisticalmethod: 'BBBBBB',
            statisticalfrequency: 'BBBBBB',
            istarget: 1,
            progress: 1,
            description: 'BBBBBB',
            problems: 'BBBBBB',
            improvementmeasures: 'BBBBBB',
            returntime: dayjs(currentDate).format(DATE_FORMAT),
            createtime: dayjs(currentDate).format(DATE_FORMAT),
            auditStatus: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            returntime: currentDate,
            createtime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of QualityReturns', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a QualityReturns', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete('ABC').then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a QualityReturns', async () => {
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
