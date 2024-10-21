/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import WorkbagService from './workbag.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { Workbag } from '@/shared/model/workbag.model';

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
  describe('Workbag Service', () => {
    let service: WorkbagService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new WorkbagService();
      currentDate = new Date();
      elemDefault = new Workbag(
        'ABC',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'PUBLIC',
        'AAAAAAA',
        currentDate,
        currentDate,
        currentDate,
        0,
        0,
        'AAAAAAA',
        'NOT_AUDITED',
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            starttime: dayjs(currentDate).format(DATE_FORMAT),
            endtime: dayjs(currentDate).format(DATE_FORMAT),
            estimatedpurchasingtime: dayjs(currentDate).format(DATE_FORMAT),
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

      it('should create a Workbag', async () => {
        const returnedFromService = Object.assign(
          {
            id: 'ABC',
            starttime: dayjs(currentDate).format(DATE_FORMAT),
            endtime: dayjs(currentDate).format(DATE_FORMAT),
            estimatedpurchasingtime: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            starttime: currentDate,
            endtime: currentDate,
            estimatedpurchasingtime: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a Workbag', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Workbag', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            pbsid: 'BBBBBB',
            workbagtype: 1,
            supplier: 'BBBBBB',
            iskeyimportant: 1,
            keypbsname: 'BBBBBB',
            importantpbsname: 'BBBBBB',
            secretlevel: 'BBBBBB',
            description: 'BBBBBB',
            starttime: dayjs(currentDate).format(DATE_FORMAT),
            endtime: dayjs(currentDate).format(DATE_FORMAT),
            estimatedpurchasingtime: dayjs(currentDate).format(DATE_FORMAT),
            progress: 1,
            issafetywork: 1,
            remark: 'BBBBBB',
            auditStatus: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            starttime: currentDate,
            endtime: currentDate,
            estimatedpurchasingtime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Workbag', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Workbag', async () => {
        const patchObject = Object.assign(
          {
            name: 'BBBBBB',
            workbagtype: 1,
            iskeyimportant: 1,
            secretlevel: 'BBBBBB',
            description: 'BBBBBB',
            starttime: dayjs(currentDate).format(DATE_FORMAT),
            progress: 1,
          },
          new Workbag(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            starttime: currentDate,
            endtime: currentDate,
            estimatedpurchasingtime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a Workbag', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Workbag', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            pbsid: 'BBBBBB',
            workbagtype: 1,
            supplier: 'BBBBBB',
            iskeyimportant: 1,
            keypbsname: 'BBBBBB',
            importantpbsname: 'BBBBBB',
            secretlevel: 'BBBBBB',
            description: 'BBBBBB',
            starttime: dayjs(currentDate).format(DATE_FORMAT),
            endtime: dayjs(currentDate).format(DATE_FORMAT),
            estimatedpurchasingtime: dayjs(currentDate).format(DATE_FORMAT),
            progress: 1,
            issafetywork: 1,
            remark: 'BBBBBB',
            auditStatus: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            starttime: currentDate,
            endtime: currentDate,
            estimatedpurchasingtime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Workbag', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Workbag', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete('ABC').then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Workbag', async () => {
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
