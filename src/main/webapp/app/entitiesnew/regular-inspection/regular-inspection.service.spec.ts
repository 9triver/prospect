/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import RegularInspectionService from './regular-inspection.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { RegularInspection } from '@/shared/model/regular-inspection.model';

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
  describe('RegularInspection Service', () => {
    let service: RegularInspectionService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new RegularInspectionService();
      currentDate = new Date();
      elemDefault = new RegularInspection(
        123,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'PUBLIC',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'NOT_AUDITED',
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            checktime: dayjs(currentDate).format(DATE_FORMAT),
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

      it('should create a RegularInspection', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            checktime: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            checktime: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a RegularInspection', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a RegularInspection', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            workbagid: 'BBBBBB',
            workbagname: 'BBBBBB',
            type: 'BBBBBB',
            secretlevel: 'BBBBBB',
            standard: 'BBBBBB',
            measurementmethod: 'BBBBBB',
            checkresult: 'BBBBBB',
            checktarget: 'BBBBBB',
            checktime: dayjs(currentDate).format(DATE_FORMAT),
            checkcompletion: 'BBBBBB',
            checkstatus: 'BBBBBB',
            auditStatus: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            checktime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a RegularInspection', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a RegularInspection', async () => {
        const patchObject = Object.assign(
          {
            workbagid: 'BBBBBB',
            type: 'BBBBBB',
            standard: 'BBBBBB',
            measurementmethod: 'BBBBBB',
            checkcompletion: 'BBBBBB',
            checkstatus: 'BBBBBB',
          },
          new RegularInspection(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            checktime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a RegularInspection', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of RegularInspection', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            workbagid: 'BBBBBB',
            workbagname: 'BBBBBB',
            type: 'BBBBBB',
            secretlevel: 'BBBBBB',
            standard: 'BBBBBB',
            measurementmethod: 'BBBBBB',
            checkresult: 'BBBBBB',
            checktarget: 'BBBBBB',
            checktime: dayjs(currentDate).format(DATE_FORMAT),
            checkcompletion: 'BBBBBB',
            checkstatus: 'BBBBBB',
            auditStatus: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            checktime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of RegularInspection', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a RegularInspection', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a RegularInspection', async () => {
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
