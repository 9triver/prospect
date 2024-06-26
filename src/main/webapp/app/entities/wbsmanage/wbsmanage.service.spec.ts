/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import WbsmanageService from './wbsmanage.service';
import { Wbsmanage } from '@/shared/model/wbsmanage.model';

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
  describe('Wbsmanage Service', () => {
    let service: WbsmanageService;
    let elemDefault;

    beforeEach(() => {
      service = new WbsmanageService();
      elemDefault = new Wbsmanage('ABC', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'SECRET', 'Not_Audited');
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

      it('should create a Wbsmanage', async () => {
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

      it('should not create a Wbsmanage', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Wbsmanage', async () => {
        const returnedFromService = Object.assign(
          {
            wbsname: 'BBBBBB',
            description: 'BBBBBB',
            result: 'BBBBBB',
            administratorname: 'BBBBBB',
            responsiblename: 'BBBBBB',
            department: 'BBBBBB',
            secretlevel: 'BBBBBB',
            auditStatus: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign({}, returnedFromService);
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Wbsmanage', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Wbsmanage', async () => {
        const patchObject = Object.assign(
          {
            description: 'BBBBBB',
            result: 'BBBBBB',
            secretlevel: 'BBBBBB',
            auditStatus: 'BBBBBB',
          },
          new Wbsmanage(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a Wbsmanage', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Wbsmanage', async () => {
        const returnedFromService = Object.assign(
          {
            wbsname: 'BBBBBB',
            description: 'BBBBBB',
            result: 'BBBBBB',
            administratorname: 'BBBBBB',
            responsiblename: 'BBBBBB',
            department: 'BBBBBB',
            secretlevel: 'BBBBBB',
            auditStatus: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign({}, returnedFromService);
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Wbsmanage', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Wbsmanage', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete('ABC').then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Wbsmanage', async () => {
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
