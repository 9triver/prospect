/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import UnQualityAuditService from './un-quality-audit.service';
import { UnQualityAudit } from '@/shared/model/un-quality-audit.model';

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
  describe('UnQualityAudit Service', () => {
    let service: UnQualityAuditService;
    let elemDefault;

    beforeEach(() => {
      service = new UnQualityAuditService();
      elemDefault = new UnQualityAudit('ABC', 'AAAAAAA', 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 'AAAAAAA', 'Not_Audited');
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

      it('should create a UnQualityAudit', async () => {
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

      it('should not create a UnQualityAudit', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a UnQualityAudit', async () => {
        const returnedFromService = Object.assign(
          {
            unqualityname: 'BBBBBB',
            unqualitytype: 1,
            belongunitid: 'BBBBBB',
            belongunitname: 'BBBBBB',
            auditteam: 'BBBBBB',
            auditperson: 'BBBBBB',
            unqualitynum: 1,
            creatorname: 'BBBBBB',
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

      it('should not update a UnQualityAudit', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a UnQualityAudit', async () => {
        const patchObject = Object.assign(
          {
            unqualityname: 'BBBBBB',
            unqualitytype: 1,
            belongunitid: 'BBBBBB',
            belongunitname: 'BBBBBB',
            auditteam: 'BBBBBB',
            auditStatus: 'BBBBBB',
          },
          new UnQualityAudit(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a UnQualityAudit', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of UnQualityAudit', async () => {
        const returnedFromService = Object.assign(
          {
            unqualityname: 'BBBBBB',
            unqualitytype: 1,
            belongunitid: 'BBBBBB',
            belongunitname: 'BBBBBB',
            auditteam: 'BBBBBB',
            auditperson: 'BBBBBB',
            unqualitynum: 1,
            creatorname: 'BBBBBB',
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

      it('should not return a list of UnQualityAudit', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a UnQualityAudit', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete('ABC').then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a UnQualityAudit', async () => {
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
