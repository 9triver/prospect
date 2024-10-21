/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import OfficersService from './officers.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { Officers } from '@/shared/model/officers.model';

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
  describe('Officers Service', () => {
    let service: OfficersService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new OfficersService();
      currentDate = new Date();
      elemDefault = new Officers('ABC', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, currentDate, 0, 'ON_JOB');
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            hiredate: dayjs(currentDate).format(DATE_FORMAT),
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

      it('should create a Officers', async () => {
        const returnedFromService = Object.assign(
          {
            id: 'ABC',
            hiredate: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            hiredate: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a Officers', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Officers', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            password: 'BBBBBB',
            email: 'BBBBBB',
            phone: 1,
            hiredate: dayjs(currentDate).format(DATE_FORMAT),
            years: 1,
            status: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            hiredate: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Officers', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Officers', async () => {
        const patchObject = Object.assign(
          {
            password: 'BBBBBB',
            email: 'BBBBBB',
            years: 1,
          },
          new Officers(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            hiredate: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a Officers', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Officers', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            password: 'BBBBBB',
            email: 'BBBBBB',
            phone: 1,
            hiredate: dayjs(currentDate).format(DATE_FORMAT),
            years: 1,
            status: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            hiredate: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Officers', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Officers', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete('ABC').then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Officers', async () => {
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
