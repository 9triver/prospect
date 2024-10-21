/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import ArchivesService from './archives.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { Archives } from '@/shared/model/archives.model';

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
  describe('Archives Service', () => {
    let service: ArchivesService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new ArchivesService();
      currentDate = new Date();
      elemDefault = new Archives(
        'ABC',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        0,
        'PUBLIC',
        currentDate,
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            date: dayjs(currentDate).format(DATE_FORMAT),
            confidentialityperiod: dayjs(currentDate).format(DATE_FORMAT),
            storageperiod: dayjs(currentDate).format(DATE_FORMAT),
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

      it('should create a Archives', async () => {
        const returnedFromService = Object.assign(
          {
            id: 'ABC',
            date: dayjs(currentDate).format(DATE_FORMAT),
            confidentialityperiod: dayjs(currentDate).format(DATE_FORMAT),
            storageperiod: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            date: currentDate,
            confidentialityperiod: currentDate,
            storageperiod: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a Archives', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Archives', async () => {
        const returnedFromService = Object.assign(
          {
            title: 'BBBBBB',
            content: 'BBBBBB',
            date: dayjs(currentDate).format(DATE_FORMAT),
            page: 1,
            secretlevel: 'BBBBBB',
            confidentialityperiod: dayjs(currentDate).format(DATE_FORMAT),
            confidentialnumber: 'BBBBBB',
            storageperiod: dayjs(currentDate).format(DATE_FORMAT),
            plannumber: 'BBBBBB',
            remarks: 'BBBBBB',
            receivingnumber: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            date: currentDate,
            confidentialityperiod: currentDate,
            storageperiod: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Archives', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Archives', async () => {
        const patchObject = Object.assign(
          {
            date: dayjs(currentDate).format(DATE_FORMAT),
            page: 1,
            secretlevel: 'BBBBBB',
            confidentialnumber: 'BBBBBB',
            plannumber: 'BBBBBB',
          },
          new Archives(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            date: currentDate,
            confidentialityperiod: currentDate,
            storageperiod: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a Archives', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Archives', async () => {
        const returnedFromService = Object.assign(
          {
            title: 'BBBBBB',
            content: 'BBBBBB',
            date: dayjs(currentDate).format(DATE_FORMAT),
            page: 1,
            secretlevel: 'BBBBBB',
            confidentialityperiod: dayjs(currentDate).format(DATE_FORMAT),
            confidentialnumber: 'BBBBBB',
            storageperiod: dayjs(currentDate).format(DATE_FORMAT),
            plannumber: 'BBBBBB',
            remarks: 'BBBBBB',
            receivingnumber: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            date: currentDate,
            confidentialityperiod: currentDate,
            storageperiod: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Archives', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Archives', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete('ABC').then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Archives', async () => {
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
