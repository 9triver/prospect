/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import ProjectchargeService from './projectcharge.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { Projectcharge } from '@/shared/model/projectcharge.model';

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
  describe('Projectcharge Service', () => {
    let service: ProjectchargeService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new ProjectchargeService();
      currentDate = new Date();
      elemDefault = new Projectcharge(123, 'AAAAAAA', 'AAAAAAA', currentDate, currentDate, 'SECRET', 'AAAAAAA', 0, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            starttime: dayjs(currentDate).format(DATE_FORMAT),
            endtime: dayjs(currentDate).format(DATE_FORMAT),
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

      it('should create a Projectcharge', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            starttime: dayjs(currentDate).format(DATE_FORMAT),
            endtime: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            starttime: currentDate,
            endtime: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a Projectcharge', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Projectcharge', async () => {
        const returnedFromService = Object.assign(
          {
            projectname: 'BBBBBB',
            formid: 'BBBBBB',
            starttime: dayjs(currentDate).format(DATE_FORMAT),
            endtime: dayjs(currentDate).format(DATE_FORMAT),
            secretlevel: 'BBBBBB',
            requestdeportment: 'BBBBBB',
            chargetype: 1,
            chargecontent: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            starttime: currentDate,
            endtime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Projectcharge', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Projectcharge', async () => {
        const patchObject = Object.assign(
          {
            formid: 'BBBBBB',
            starttime: dayjs(currentDate).format(DATE_FORMAT),
            endtime: dayjs(currentDate).format(DATE_FORMAT),
            chargetype: 1,
          },
          new Projectcharge(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            starttime: currentDate,
            endtime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a Projectcharge', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Projectcharge', async () => {
        const returnedFromService = Object.assign(
          {
            projectname: 'BBBBBB',
            formid: 'BBBBBB',
            starttime: dayjs(currentDate).format(DATE_FORMAT),
            endtime: dayjs(currentDate).format(DATE_FORMAT),
            secretlevel: 'BBBBBB',
            requestdeportment: 'BBBBBB',
            chargetype: 1,
            chargecontent: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            starttime: currentDate,
            endtime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Projectcharge', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Projectcharge', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Projectcharge', async () => {
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
