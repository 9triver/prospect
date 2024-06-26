/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import AnnualSecurityPlanService from './annual-security-plan.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { AnnualSecurityPlan } from '@/shared/model/annual-security-plan.model';

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
  describe('AnnualSecurityPlan Service', () => {
    let service: AnnualSecurityPlanService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new AnnualSecurityPlanService();
      currentDate = new Date();
      elemDefault = new AnnualSecurityPlan('ABC', 'AAAAAAA', currentDate, currentDate, 'AAAAAAA', 'Not_Audited', 0);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            releasetime: dayjs(currentDate).format(DATE_FORMAT),
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

      it('should create a AnnualSecurityPlan', async () => {
        const returnedFromService = Object.assign(
          {
            id: 'ABC',
            releasetime: dayjs(currentDate).format(DATE_FORMAT),
            createtime: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            releasetime: currentDate,
            createtime: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a AnnualSecurityPlan', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a AnnualSecurityPlan', async () => {
        const returnedFromService = Object.assign(
          {
            securityplanname: 'BBBBBB',
            releasetime: dayjs(currentDate).format(DATE_FORMAT),
            createtime: dayjs(currentDate).format(DATE_FORMAT),
            creatorname: 'BBBBBB',
            auditStatus: 'BBBBBB',
            version: 1,
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            releasetime: currentDate,
            createtime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a AnnualSecurityPlan', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a AnnualSecurityPlan', async () => {
        const patchObject = Object.assign(
          {
            releasetime: dayjs(currentDate).format(DATE_FORMAT),
            auditStatus: 'BBBBBB',
          },
          new AnnualSecurityPlan(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            releasetime: currentDate,
            createtime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a AnnualSecurityPlan', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of AnnualSecurityPlan', async () => {
        const returnedFromService = Object.assign(
          {
            securityplanname: 'BBBBBB',
            releasetime: dayjs(currentDate).format(DATE_FORMAT),
            createtime: dayjs(currentDate).format(DATE_FORMAT),
            creatorname: 'BBBBBB',
            auditStatus: 'BBBBBB',
            version: 1,
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            releasetime: currentDate,
            createtime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of AnnualSecurityPlan', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a AnnualSecurityPlan', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete('ABC').then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a AnnualSecurityPlan', async () => {
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
