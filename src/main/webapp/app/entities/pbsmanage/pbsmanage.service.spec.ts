/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import PbsmanageService from './pbsmanage.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { Pbsmanage } from '@/shared/model/pbsmanage.model';

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
  describe('Pbsmanage Service', () => {
    let service: PbsmanageService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new PbsmanageService();
      currentDate = new Date();
      elemDefault = new Pbsmanage(
        'ABC',
        'AAAAAAA',
        0,
        'AAAAAAA',
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'SECRET',
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

      it('should create a Pbsmanage', async () => {
        const returnedFromService = Object.assign(
          {
            id: 'ABC',
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

      it('should not create a Pbsmanage', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Pbsmanage', async () => {
        const returnedFromService = Object.assign(
          {
            pbsname: 'BBBBBB',
            number: 1,
            type: 'BBBBBB',
            starttime: dayjs(currentDate).format(DATE_FORMAT),
            endtime: dayjs(currentDate).format(DATE_FORMAT),
            administratorid: 'BBBBBB',
            administratorname: 'BBBBBB',
            responsiblename: 'BBBBBB',
            department: 'BBBBBB',
            secretlevel: 'BBBBBB',
            auditStatus: 'BBBBBB',
            auditUserid: 'BBBBBB',
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

      it('should not update a Pbsmanage', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Pbsmanage', async () => {
        const patchObject = Object.assign(
          {
            pbsname: 'BBBBBB',
            type: 'BBBBBB',
            starttime: dayjs(currentDate).format(DATE_FORMAT),
            auditUserid: 'BBBBBB',
          },
          new Pbsmanage(),
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

      it('should not partial update a Pbsmanage', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Pbsmanage', async () => {
        const returnedFromService = Object.assign(
          {
            pbsname: 'BBBBBB',
            number: 1,
            type: 'BBBBBB',
            starttime: dayjs(currentDate).format(DATE_FORMAT),
            endtime: dayjs(currentDate).format(DATE_FORMAT),
            administratorid: 'BBBBBB',
            administratorname: 'BBBBBB',
            responsiblename: 'BBBBBB',
            department: 'BBBBBB',
            secretlevel: 'BBBBBB',
            auditStatus: 'BBBBBB',
            auditUserid: 'BBBBBB',
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

      it('should not return a list of Pbsmanage', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Pbsmanage', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete('ABC').then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Pbsmanage', async () => {
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
