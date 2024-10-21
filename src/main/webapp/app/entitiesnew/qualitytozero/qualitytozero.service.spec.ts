/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import QualitytozeroService from './qualitytozero.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { Qualitytozero } from '@/shared/model/qualitytozero.model';

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
  describe('Qualitytozero Service', () => {
    let service: QualitytozeroService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new QualitytozeroService();
      currentDate = new Date();
      elemDefault = new Qualitytozero(
        123,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'NOT_AUDITED',
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            problemhappentime: dayjs(currentDate).format(DATE_FORMAT),
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

      it('should create a Qualitytozero', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            problemhappentime: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            problemhappentime: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a Qualitytozero', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Qualitytozero', async () => {
        const returnedFromService = Object.assign(
          {
            workbagid: 'BBBBBB',
            belongwbsid: 'BBBBBB',
            outsourcingcontractid: 'BBBBBB',
            qualityproblemid: 'BBBBBB',
            qualityproblemname: 'BBBBBB',
            problemhappentime: dayjs(currentDate).format(DATE_FORMAT),
            problemresponsibleperson: 'BBBBBB',
            problemresponsibleunit: 'BBBBBB',
            producttype: 'BBBBBB',
            productname: 'BBBBBB',
            problemphenomenon: 'BBBBBB',
            problemtype: 'BBBBBB',
            qualitylevel: 'BBBBBB',
            zerotype: 'BBBBBB',
            problemreasonanalysis: 'BBBBBB',
            problemreasoncategory: 'BBBBBB',
            takemeasures: 'BBBBBB',
            onebyonecategory: 'BBBBBB',
            verificationeffect: 'BBBBBB',
            qualityprojectreport: 'BBBBBB',
            qualitytozeroreport: 'BBBBBB',
            reviewopinion: 'BBBBBB',
            implementationverificationtable: 'BBBBBB',
            auditStatus: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            problemhappentime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Qualitytozero', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Qualitytozero', async () => {
        const patchObject = Object.assign(
          {
            workbagid: 'BBBBBB',
            qualityproblemname: 'BBBBBB',
            problemhappentime: dayjs(currentDate).format(DATE_FORMAT),
            problemresponsibleperson: 'BBBBBB',
            qualitylevel: 'BBBBBB',
            zerotype: 'BBBBBB',
            problemreasonanalysis: 'BBBBBB',
            takemeasures: 'BBBBBB',
            onebyonecategory: 'BBBBBB',
            verificationeffect: 'BBBBBB',
            qualitytozeroreport: 'BBBBBB',
            reviewopinion: 'BBBBBB',
            implementationverificationtable: 'BBBBBB',
            auditStatus: 'BBBBBB',
          },
          new Qualitytozero(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            problemhappentime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a Qualitytozero', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Qualitytozero', async () => {
        const returnedFromService = Object.assign(
          {
            workbagid: 'BBBBBB',
            belongwbsid: 'BBBBBB',
            outsourcingcontractid: 'BBBBBB',
            qualityproblemid: 'BBBBBB',
            qualityproblemname: 'BBBBBB',
            problemhappentime: dayjs(currentDate).format(DATE_FORMAT),
            problemresponsibleperson: 'BBBBBB',
            problemresponsibleunit: 'BBBBBB',
            producttype: 'BBBBBB',
            productname: 'BBBBBB',
            problemphenomenon: 'BBBBBB',
            problemtype: 'BBBBBB',
            qualitylevel: 'BBBBBB',
            zerotype: 'BBBBBB',
            problemreasonanalysis: 'BBBBBB',
            problemreasoncategory: 'BBBBBB',
            takemeasures: 'BBBBBB',
            onebyonecategory: 'BBBBBB',
            verificationeffect: 'BBBBBB',
            qualityprojectreport: 'BBBBBB',
            qualitytozeroreport: 'BBBBBB',
            reviewopinion: 'BBBBBB',
            implementationverificationtable: 'BBBBBB',
            auditStatus: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            problemhappentime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Qualitytozero', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Qualitytozero', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Qualitytozero', async () => {
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
