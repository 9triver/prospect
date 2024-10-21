/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import MilestoneNodeService from './milestone-node.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { MilestoneNode } from '@/shared/model/milestone-node.model';

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
  describe('MilestoneNode Service', () => {
    let service: MilestoneNodeService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new MilestoneNodeService();
      currentDate = new Date();
      elemDefault = new MilestoneNode(123, 'AAAAAAA', currentDate, 0);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            planpaymenttime: dayjs(currentDate).format(DATE_FORMAT),
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

      it('should create a MilestoneNode', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            planpaymenttime: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            planpaymenttime: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a MilestoneNode', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a MilestoneNode', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            planpaymenttime: dayjs(currentDate).format(DATE_FORMAT),
            planpaymentamount: 1,
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            planpaymenttime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a MilestoneNode', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a MilestoneNode', async () => {
        const patchObject = Object.assign({}, new MilestoneNode());
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            planpaymenttime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a MilestoneNode', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of MilestoneNode', async () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            planpaymenttime: dayjs(currentDate).format(DATE_FORMAT),
            planpaymentamount: 1,
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            planpaymenttime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of MilestoneNode', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a MilestoneNode', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a MilestoneNode', async () => {
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
