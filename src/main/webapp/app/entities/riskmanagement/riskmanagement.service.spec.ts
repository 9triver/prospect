/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import RiskmanagementService from './riskmanagement.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { Riskmanagement } from '@/shared/model/riskmanagement.model';

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
  describe('Riskmanagement Service', () => {
    let service: RiskmanagementService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new RiskmanagementService();
      currentDate = new Date();
      elemDefault = new Riskmanagement(123, 0, 'AAAAAAA', 0, 'AAAAAAA', 0, 0, 0, currentDate, 0, 'One', 'AAAAAAA', 0);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            usetime: dayjs(currentDate).format(DATE_FORMAT),
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

      it('should create a Riskmanagement', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            usetime: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            usetime: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a Riskmanagement', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Riskmanagement', async () => {
        const returnedFromService = Object.assign(
          {
            riskid: 1,
            projectname: 'BBBBBB',
            year: 1,
            nodename: 'BBBBBB',
            risktype: 1,
            decumentid: 1,
            version: 1,
            usetime: dayjs(currentDate).format(DATE_FORMAT),
            systemlevel: 1,
            risklevel: 'BBBBBB',
            limitationtime: 'BBBBBB',
            closetype: 1,
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            usetime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Riskmanagement', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Riskmanagement', async () => {
        const patchObject = Object.assign(
          {
            riskid: 1,
            risktype: 1,
            version: 1,
            systemlevel: 1,
            risklevel: 'BBBBBB',
          },
          new Riskmanagement(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            usetime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a Riskmanagement', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Riskmanagement', async () => {
        const returnedFromService = Object.assign(
          {
            riskid: 1,
            projectname: 'BBBBBB',
            year: 1,
            nodename: 'BBBBBB',
            risktype: 1,
            decumentid: 1,
            version: 1,
            usetime: dayjs(currentDate).format(DATE_FORMAT),
            systemlevel: 1,
            risklevel: 'BBBBBB',
            limitationtime: 'BBBBBB',
            closetype: 1,
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            usetime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Riskmanagement', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Riskmanagement', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Riskmanagement', async () => {
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
