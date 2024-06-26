/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import SafetycheckService from './safetycheck.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { Safetycheck } from '@/shared/model/safetycheck.model';

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
  describe('Safetycheck Service', () => {
    let service: SafetycheckService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new SafetycheckService();
      currentDate = new Date();
      elemDefault = new Safetycheck('ABC', 'AAAAAAA', 'AAAAAAA', currentDate, currentDate, 'AAAAAAA', 'AAAAAAA', 0, 'One', 'Not_Audited');
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            checktime: dayjs(currentDate).format(DATE_FORMAT),
            effectivetime: dayjs(currentDate).format(DATE_FORMAT),
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

      it('should create a Safetycheck', async () => {
        const returnedFromService = Object.assign(
          {
            id: 'ABC',
            checktime: dayjs(currentDate).format(DATE_FORMAT),
            effectivetime: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            checktime: currentDate,
            effectivetime: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a Safetycheck', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Safetycheck', async () => {
        const returnedFromService = Object.assign(
          {
            safetycheckname: 'BBBBBB',
            checksource: 'BBBBBB',
            checktime: dayjs(currentDate).format(DATE_FORMAT),
            effectivetime: dayjs(currentDate).format(DATE_FORMAT),
            operatinglocation: 'BBBBBB',
            deprotment: 'BBBBBB',
            phonenumber: 1,
            risklevel: 'BBBBBB',
            auditStatus: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            checktime: currentDate,
            effectivetime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Safetycheck', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Safetycheck', async () => {
        const patchObject = Object.assign(
          {
            safetycheckname: 'BBBBBB',
            effectivetime: dayjs(currentDate).format(DATE_FORMAT),
            operatinglocation: 'BBBBBB',
            deprotment: 'BBBBBB',
            phonenumber: 1,
            auditStatus: 'BBBBBB',
          },
          new Safetycheck(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            checktime: currentDate,
            effectivetime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a Safetycheck', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Safetycheck', async () => {
        const returnedFromService = Object.assign(
          {
            safetycheckname: 'BBBBBB',
            checksource: 'BBBBBB',
            checktime: dayjs(currentDate).format(DATE_FORMAT),
            effectivetime: dayjs(currentDate).format(DATE_FORMAT),
            operatinglocation: 'BBBBBB',
            deprotment: 'BBBBBB',
            phonenumber: 1,
            risklevel: 'BBBBBB',
            auditStatus: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            checktime: currentDate,
            effectivetime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Safetycheck', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Safetycheck', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete('ABC').then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Safetycheck', async () => {
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
