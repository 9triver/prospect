/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import ApprovalAgentService from './approval-agent.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { ApprovalAgent } from '@/shared/model/approval-agent.model';

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
  describe('ApprovalAgent Service', () => {
    let service: ApprovalAgentService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new ApprovalAgentService();
      currentDate = new Date();
      elemDefault = new ApprovalAgent('ABC', 0, 'AAAAAAA', currentDate, currentDate, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            agentstarttime: dayjs(currentDate).format(DATE_FORMAT),
            autocanceltime: dayjs(currentDate).format(DATE_FORMAT),
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

      it('should create a ApprovalAgent', async () => {
        const returnedFromService = Object.assign(
          {
            id: 'ABC',
            agentstarttime: dayjs(currentDate).format(DATE_FORMAT),
            autocanceltime: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            agentstarttime: currentDate,
            autocanceltime: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a ApprovalAgent', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a ApprovalAgent', async () => {
        const returnedFromService = Object.assign(
          {
            agentid: 1,
            agentname: 'BBBBBB',
            agentstarttime: dayjs(currentDate).format(DATE_FORMAT),
            autocanceltime: dayjs(currentDate).format(DATE_FORMAT),
            agentdepartment: 'BBBBBB',
            originalapprovalname: 'BBBBBB',
            originaldepartment: 'BBBBBB',
            secrecylevel: 1,
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            agentstarttime: currentDate,
            autocanceltime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a ApprovalAgent', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a ApprovalAgent', async () => {
        const patchObject = Object.assign(
          {
            originalapprovalname: 'BBBBBB',
          },
          new ApprovalAgent(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            agentstarttime: currentDate,
            autocanceltime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a ApprovalAgent', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of ApprovalAgent', async () => {
        const returnedFromService = Object.assign(
          {
            agentid: 1,
            agentname: 'BBBBBB',
            agentstarttime: dayjs(currentDate).format(DATE_FORMAT),
            autocanceltime: dayjs(currentDate).format(DATE_FORMAT),
            agentdepartment: 'BBBBBB',
            originalapprovalname: 'BBBBBB',
            originaldepartment: 'BBBBBB',
            secrecylevel: 1,
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            agentstarttime: currentDate,
            autocanceltime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of ApprovalAgent', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a ApprovalAgent', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete('ABC').then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a ApprovalAgent', async () => {
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
