/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import OutsourcingContractualService from './outsourcing-contractual.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { OutsourcingContractual } from '@/shared/model/outsourcing-contractual.model';

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
  describe('OutsourcingContractual Service', () => {
    let service: OutsourcingContractualService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new OutsourcingContractualService();
      currentDate = new Date();
      elemDefault = new OutsourcingContractual(
        'ABC',
        'AAAAAAA',
        0,
        currentDate,
        currentDate,
        0,
        'SECRET',
        0,
        0,
        0,
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            starttime: dayjs(currentDate).format(DATE_FORMAT),
            endtime: dayjs(currentDate).format(DATE_FORMAT),
            audittime: dayjs(currentDate).format(DATE_FORMAT),
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

      it('should create a OutsourcingContractual', async () => {
        const returnedFromService = Object.assign(
          {
            id: 'ABC',
            starttime: dayjs(currentDate).format(DATE_FORMAT),
            endtime: dayjs(currentDate).format(DATE_FORMAT),
            audittime: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            starttime: currentDate,
            endtime: currentDate,
            audittime: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a OutsourcingContractual', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a OutsourcingContractual', async () => {
        const returnedFromService = Object.assign(
          {
            department: 'BBBBBB',
            year: 1,
            starttime: dayjs(currentDate).format(DATE_FORMAT),
            endtime: dayjs(currentDate).format(DATE_FORMAT),
            status: 1,
            secretlevel: 'BBBBBB',
            foreigncurrency: 1,
            totalbudget: 1,
            fundsinplace: 1,
            responsibleunitname: 'BBBBBB',
            audittime: dayjs(currentDate).format(DATE_FORMAT),
            accountbank: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            starttime: currentDate,
            endtime: currentDate,
            audittime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a OutsourcingContractual', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a OutsourcingContractual', async () => {
        const patchObject = Object.assign(
          {
            department: 'BBBBBB',
            year: 1,
            starttime: dayjs(currentDate).format(DATE_FORMAT),
            secretlevel: 'BBBBBB',
            foreigncurrency: 1,
            audittime: dayjs(currentDate).format(DATE_FORMAT),
            accountbank: 'BBBBBB',
          },
          new OutsourcingContractual(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            starttime: currentDate,
            endtime: currentDate,
            audittime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a OutsourcingContractual', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of OutsourcingContractual', async () => {
        const returnedFromService = Object.assign(
          {
            department: 'BBBBBB',
            year: 1,
            starttime: dayjs(currentDate).format(DATE_FORMAT),
            endtime: dayjs(currentDate).format(DATE_FORMAT),
            status: 1,
            secretlevel: 'BBBBBB',
            foreigncurrency: 1,
            totalbudget: 1,
            fundsinplace: 1,
            responsibleunitname: 'BBBBBB',
            audittime: dayjs(currentDate).format(DATE_FORMAT),
            accountbank: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            starttime: currentDate,
            endtime: currentDate,
            audittime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of OutsourcingContractual', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a OutsourcingContractual', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete('ABC').then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a OutsourcingContractual', async () => {
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
