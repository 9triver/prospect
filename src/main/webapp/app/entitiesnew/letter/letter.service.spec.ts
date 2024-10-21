/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import LetterService from './letter.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { Letter } from '@/shared/model/letter.model';

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
  describe('Letter Service', () => {
    let service: LetterService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new LetterService();
      currentDate = new Date();
      elemDefault = new Letter(123, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'PUBLIC', 'AAAAAAA', 'AAAAAAA', currentDate, 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            lettertime: dayjs(currentDate).format(DATE_FORMAT),
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

      it('should create a Letter', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            lettertime: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            lettertime: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a Letter', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Letter', async () => {
        const returnedFromService = Object.assign(
          {
            lettername: 'BBBBBB',
            letternumber: 'BBBBBB',
            lettertype: 'BBBBBB',
            secretlevel: 'BBBBBB',
            lettercontent: 'BBBBBB',
            letterstatus: 'BBBBBB',
            lettertime: dayjs(currentDate).format(DATE_FORMAT),
            previousfile: 'BBBBBB',
            datarecordstatus: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            lettertime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Letter', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Letter', async () => {
        const patchObject = Object.assign(
          {
            letternumber: 'BBBBBB',
            lettertype: 'BBBBBB',
            lettercontent: 'BBBBBB',
            letterstatus: 'BBBBBB',
            lettertime: dayjs(currentDate).format(DATE_FORMAT),
            datarecordstatus: 'BBBBBB',
          },
          new Letter(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            lettertime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a Letter', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Letter', async () => {
        const returnedFromService = Object.assign(
          {
            lettername: 'BBBBBB',
            letternumber: 'BBBBBB',
            lettertype: 'BBBBBB',
            secretlevel: 'BBBBBB',
            lettercontent: 'BBBBBB',
            letterstatus: 'BBBBBB',
            lettertime: dayjs(currentDate).format(DATE_FORMAT),
            previousfile: 'BBBBBB',
            datarecordstatus: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            lettertime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Letter', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Letter', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Letter', async () => {
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
