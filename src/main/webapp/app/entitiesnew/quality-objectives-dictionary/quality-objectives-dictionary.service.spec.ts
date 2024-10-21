/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import QualityObjectivesDictionaryService from './quality-objectives-dictionary.service';
import { QualityObjectivesDictionary } from '@/shared/model/quality-objectives-dictionary.model';

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
  describe('QualityObjectivesDictionary Service', () => {
    let service: QualityObjectivesDictionaryService;
    let elemDefault;

    beforeEach(() => {
      service = new QualityObjectivesDictionaryService();
      elemDefault = new QualityObjectivesDictionary(123, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
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

      it('should create a QualityObjectivesDictionary', async () => {
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

      it('should not create a QualityObjectivesDictionary', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a QualityObjectivesDictionary', async () => {
        const returnedFromService = Object.assign(
          {
            objectiveslevel: 'BBBBBB',
            objectivestype: 'BBBBBB',
            objectivesname: 'BBBBBB',
            objectivescontent: 'BBBBBB',
            calculationmethod: 'BBBBBB',
            frequency: 'BBBBBB',
            evaluationcriteria: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign({}, returnedFromService);
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a QualityObjectivesDictionary', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a QualityObjectivesDictionary', async () => {
        const patchObject = Object.assign(
          {
            objectivescontent: 'BBBBBB',
            calculationmethod: 'BBBBBB',
            frequency: 'BBBBBB',
          },
          new QualityObjectivesDictionary(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a QualityObjectivesDictionary', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of QualityObjectivesDictionary', async () => {
        const returnedFromService = Object.assign(
          {
            objectiveslevel: 'BBBBBB',
            objectivestype: 'BBBBBB',
            objectivesname: 'BBBBBB',
            objectivescontent: 'BBBBBB',
            calculationmethod: 'BBBBBB',
            frequency: 'BBBBBB',
            evaluationcriteria: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign({}, returnedFromService);
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of QualityObjectivesDictionary', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a QualityObjectivesDictionary', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a QualityObjectivesDictionary', async () => {
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
