/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import ProjectBudgetService from './project-budget.service';
import { ProjectBudget } from '@/shared/model/project-budget.model';

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
  describe('ProjectBudget Service', () => {
    let service: ProjectBudgetService;
    let elemDefault;

    beforeEach(() => {
      service = new ProjectBudgetService();
      elemDefault = new ProjectBudget(
        123,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        0,
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

      it('should create a ProjectBudget', async () => {
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

      it('should not create a ProjectBudget', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a ProjectBudget', async () => {
        const returnedFromService = Object.assign(
          {
            wbsid: 'BBBBBB',
            wbsname: 'BBBBBB',
            parentwbsid: 'BBBBBB',
            subjectid: 1,
            subjectname: 'BBBBBB',
            contractid: 'BBBBBB',
            contractname: 'BBBBBB',
            year: 1,
            auxiliaryitem: 'BBBBBB',
            unit: 'BBBBBB',
            number: 'BBBBBB',
            unitprice: 1,
            budgetamount: 1,
            estimatedamount: 1,
            implementedamount: 1,
            difference: 1,
            remark: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign({}, returnedFromService);
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a ProjectBudget', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a ProjectBudget', async () => {
        const patchObject = Object.assign(
          {
            wbsname: 'BBBBBB',
            subjectid: 1,
            subjectname: 'BBBBBB',
            contractname: 'BBBBBB',
            year: 1,
            auxiliaryitem: 'BBBBBB',
            number: 'BBBBBB',
            unitprice: 1,
            budgetamount: 1,
            estimatedamount: 1,
            implementedamount: 1,
            difference: 1,
          },
          new ProjectBudget(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a ProjectBudget', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of ProjectBudget', async () => {
        const returnedFromService = Object.assign(
          {
            wbsid: 'BBBBBB',
            wbsname: 'BBBBBB',
            parentwbsid: 'BBBBBB',
            subjectid: 1,
            subjectname: 'BBBBBB',
            contractid: 'BBBBBB',
            contractname: 'BBBBBB',
            year: 1,
            auxiliaryitem: 'BBBBBB',
            unit: 'BBBBBB',
            number: 'BBBBBB',
            unitprice: 1,
            budgetamount: 1,
            estimatedamount: 1,
            implementedamount: 1,
            difference: 1,
            remark: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign({}, returnedFromService);
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of ProjectBudget', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a ProjectBudget', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a ProjectBudget', async () => {
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
