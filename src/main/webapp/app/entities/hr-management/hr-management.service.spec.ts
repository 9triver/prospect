/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import HrManagementService from './hr-management.service';
import { HrManagement } from '@/shared/model/hr-management.model';

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
  describe('HrManagement Service', () => {
    let service: HrManagementService;
    let elemDefault;

    beforeEach(() => {
      service = new HrManagementService();
      elemDefault = new HrManagement(
        123,
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign({}, elemDefault);
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

      it('should create a HrManagement', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
          },
          elemDefault,
        );
        const expected = Object.assign({}, returnedFromService);

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a HrManagement', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a HrManagement', async () => {
        const returnedFromService = Object.assign(
          {
            officersid: 'BBBBBB',
            officersname: 'BBBBBB',
            projectid: 1,
            projectname: 'BBBBBB',
            projectrole: 'BBBBBB',
            jobgrade: 'BBBBBB',
            departmentid: 'BBBBBB',
            departmentname: 'BBBBBB',
            frontlineid: 'BBBBBB',
            frontlinename: 'BBBBBB',
            jobduty: 'BBBBBB',
            annualworktime: 1,
            annualtasktarget: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign({}, returnedFromService);
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a HrManagement', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a HrManagement', async () => {
        const patchObject = Object.assign(
          {
            officersid: 'BBBBBB',
            projectid: 1,
            projectname: 'BBBBBB',
            projectrole: 'BBBBBB',
            frontlineid: 'BBBBBB',
            jobduty: 'BBBBBB',
            annualtasktarget: 'BBBBBB',
          },
          new HrManagement(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a HrManagement', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of HrManagement', async () => {
        const returnedFromService = Object.assign(
          {
            officersid: 'BBBBBB',
            officersname: 'BBBBBB',
            projectid: 1,
            projectname: 'BBBBBB',
            projectrole: 'BBBBBB',
            jobgrade: 'BBBBBB',
            departmentid: 'BBBBBB',
            departmentname: 'BBBBBB',
            frontlineid: 'BBBBBB',
            frontlinename: 'BBBBBB',
            jobduty: 'BBBBBB',
            annualworktime: 1,
            annualtasktarget: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign({}, returnedFromService);
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of HrManagement', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a HrManagement', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a HrManagement', async () => {
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
