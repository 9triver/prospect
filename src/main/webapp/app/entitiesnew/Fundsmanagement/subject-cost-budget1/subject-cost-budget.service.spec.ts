/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import SubjectCostBudgetService from './subject-cost-budget.service';
import { SubjectCostBudget } from '@/shared/model/subject-cost-budget.model';

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
  describe('SubjectCostBudget Service', () => {
    let service: SubjectCostBudgetService;
    let elemDefault;

    beforeEach(() => {
      service = new SubjectCostBudgetService();
      elemDefault = new SubjectCostBudget(123, 'AAAAAAA', 0, 'AAAAAAA', 0, 0, 0, 0, 0);
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

      it('should create a SubjectCostBudget', async () => {
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

      it('should not create a SubjectCostBudget', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a SubjectCostBudget', async () => {
        const returnedFromService = Object.assign(
          {
            contractid: 'BBBBBB',
            subjectid: 1,
            subjectname: 'BBBBBB',
            budgetamount: 1,
            estimatedamount: 1,
            implementedamount: 1,
            difference: 1,
            percentage: 1,
          },
          elemDefault,
        );

        const expected = Object.assign({}, returnedFromService);
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a SubjectCostBudget', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a SubjectCostBudget', async () => {
        const patchObject = Object.assign(
          {
            contractid: 'BBBBBB',
            estimatedamount: 1,
            implementedamount: 1,
            percentage: 1,
          },
          new SubjectCostBudget(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a SubjectCostBudget', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of SubjectCostBudget', async () => {
        const returnedFromService = Object.assign(
          {
            contractid: 'BBBBBB',
            subjectid: 1,
            subjectname: 'BBBBBB',
            budgetamount: 1,
            estimatedamount: 1,
            implementedamount: 1,
            difference: 1,
            percentage: 1,
          },
          elemDefault,
        );
        const expected = Object.assign({}, returnedFromService);
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of SubjectCostBudget', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a SubjectCostBudget', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a SubjectCostBudget', async () => {
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
