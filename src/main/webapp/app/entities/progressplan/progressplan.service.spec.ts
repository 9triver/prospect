/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import ProgressplanService from './progressplan.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { Progressplan } from '@/shared/model/progressplan.model';

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
  describe('Progressplan Service', () => {
    let service: ProgressplanService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new ProgressplanService();
      currentDate = new Date();
      elemDefault = new Progressplan('ABC', 'AAAAAAA', 'YEAR', 'AAAAAAA', currentDate, 'AAAAAAA', 'AAAAAAA', 'Not_start', 'Not_Audited');
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

      it('should create a Progressplan', async () => {
        const returnedFromService = Object.assign(
          {
            id: 'ABC',
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

      it('should not create a Progressplan', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Progressplan', async () => {
        const returnedFromService = Object.assign(
          {
            progressname: 'BBBBBB',
            progresstype: 'BBBBBB',
            workfocus: 'BBBBBB',
            createtime: dayjs(currentDate).format(DATE_FORMAT),
            creatorname: 'BBBBBB',
            responsiblename: 'BBBBBB',
            status: 'BBBBBB',
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

      it('should not update a Progressplan', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Progressplan', async () => {
        const patchObject = Object.assign(
          {
            progressname: 'BBBBBB',
            createtime: dayjs(currentDate).format(DATE_FORMAT),
            creatorname: 'BBBBBB',
            responsiblename: 'BBBBBB',
            status: 'BBBBBB',
            auditStatus: 'BBBBBB',
          },
          new Progressplan(),
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

      it('should not partial update a Progressplan', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Progressplan', async () => {
        const returnedFromService = Object.assign(
          {
            progressname: 'BBBBBB',
            progresstype: 'BBBBBB',
            workfocus: 'BBBBBB',
            createtime: dayjs(currentDate).format(DATE_FORMAT),
            creatorname: 'BBBBBB',
            responsiblename: 'BBBBBB',
            status: 'BBBBBB',
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

      it('should not return a list of Progressplan', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Progressplan', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete('ABC').then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Progressplan', async () => {
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
