/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import IntegratedmanagementWbsService from './integratedmanagement-wbs.service';
import { IntegratedmanagementWbs } from '@/shared/model/integratedmanagement-wbs.model';

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
  describe('IntegratedmanagementWbs Service', () => {
    let service: IntegratedmanagementWbsService;
    let elemDefault;

    beforeEach(() => {
      service = new IntegratedmanagementWbsService();
      elemDefault = new IntegratedmanagementWbs('ABC', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign({}, elemDefault);
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

      it('should create a IntegratedmanagementWbs', async () => {
        const returnedFromService = Object.assign(
          {
            id: 'ABC',
          },
          elemDefault,
        );
        const expected = Object.assign({}, returnedFromService);

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a IntegratedmanagementWbs', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a IntegratedmanagementWbs', async () => {
        const returnedFromService = Object.assign(
          {
            wbsspare1: 'BBBBBB',
            wbsspare2: 'BBBBBB',
            wbsspare3: 'BBBBBB',
            wbsspare4: 'BBBBBB',
            wbsspare5: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign({}, returnedFromService);
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a IntegratedmanagementWbs', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a IntegratedmanagementWbs', async () => {
        const patchObject = Object.assign(
          {
            wbsspare1: 'BBBBBB',
            wbsspare4: 'BBBBBB',
            wbsspare5: 'BBBBBB',
          },
          new IntegratedmanagementWbs(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a IntegratedmanagementWbs', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of IntegratedmanagementWbs', async () => {
        const returnedFromService = Object.assign(
          {
            wbsspare1: 'BBBBBB',
            wbsspare2: 'BBBBBB',
            wbsspare3: 'BBBBBB',
            wbsspare4: 'BBBBBB',
            wbsspare5: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign({}, returnedFromService);
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of IntegratedmanagementWbs', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a IntegratedmanagementWbs', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete('ABC').then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a IntegratedmanagementWbs', async () => {
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
