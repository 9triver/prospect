/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import QualityobjectivesService from './qualityobjectives.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { Qualityobjectives } from '@/shared/model/qualityobjectives.model';

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
  describe('Qualityobjectives Service', () => {
    let service: QualityobjectivesService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new QualityobjectivesService();
      currentDate = new Date();
      elemDefault = new Qualityobjectives(123, 0, 'AAAAAAA', 0, currentDate, 'AAAAAAA', 'SECRET', 'Not_Audited');
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            createtime: dayjs(currentDate).format(DATE_FORMAT),
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

      it('should create a Qualityobjectives', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            createtime: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            createtime: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a Qualityobjectives', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Qualityobjectives', async () => {
        const returnedFromService = Object.assign(
          {
            qualityobjectivesid: 1,
            qualityobjectivesname: 'BBBBBB',
            year: 1,
            createtime: dayjs(currentDate).format(DATE_FORMAT),
            creatorname: 'BBBBBB',
            secretlevel: 'BBBBBB',
            auditStatus: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            createtime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Qualityobjectives', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Qualityobjectives', async () => {
        const patchObject = Object.assign(
          {
            qualityobjectivesname: 'BBBBBB',
            year: 1,
            createtime: dayjs(currentDate).format(DATE_FORMAT),
            creatorname: 'BBBBBB',
            secretlevel: 'BBBBBB',
            auditStatus: 'BBBBBB',
          },
          new Qualityobjectives(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            createtime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a Qualityobjectives', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Qualityobjectives', async () => {
        const returnedFromService = Object.assign(
          {
            qualityobjectivesid: 1,
            qualityobjectivesname: 'BBBBBB',
            year: 1,
            createtime: dayjs(currentDate).format(DATE_FORMAT),
            creatorname: 'BBBBBB',
            secretlevel: 'BBBBBB',
            auditStatus: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            createtime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Qualityobjectives', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Qualityobjectives', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Qualityobjectives', async () => {
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
