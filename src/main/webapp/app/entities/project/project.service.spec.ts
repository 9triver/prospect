/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import ProjectService from './project.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { Project } from '@/shared/model/project.model';

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
  describe('Project Service', () => {
    let service: ProjectService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new ProjectService();
      currentDate = new Date();
      elemDefault = new Project('ABC', 'AAAAAAA', 'AAAAAAA', 0, 0, 'AAAAAAA', currentDate, 0, 'NOTSTART', 'Not_Audited');
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            duedate: dayjs(currentDate).format(DATE_FORMAT),
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

      it('should create a Project', async () => {
        const returnedFromService = Object.assign(
          {
            id: 'ABC',
            duedate: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            duedate: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a Project', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Project', async () => {
        const returnedFromService = Object.assign(
          {
            projectname: 'BBBBBB',
            description: 'BBBBBB',
            number: 1,
            projecttype: 1,
            responsiblename: 'BBBBBB',
            duedate: dayjs(currentDate).format(DATE_FORMAT),
            priorty: 1,
            status: 'BBBBBB',
            auditStatus: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            duedate: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Project', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Project', async () => {
        const patchObject = Object.assign(
          {
            number: 1,
            responsiblename: 'BBBBBB',
            duedate: dayjs(currentDate).format(DATE_FORMAT),
            status: 'BBBBBB',
            auditStatus: 'BBBBBB',
          },
          new Project(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            duedate: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a Project', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Project', async () => {
        const returnedFromService = Object.assign(
          {
            projectname: 'BBBBBB',
            description: 'BBBBBB',
            number: 1,
            projecttype: 1,
            responsiblename: 'BBBBBB',
            duedate: dayjs(currentDate).format(DATE_FORMAT),
            priorty: 1,
            status: 'BBBBBB',
            auditStatus: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            duedate: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Project', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Project', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete('ABC').then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Project', async () => {
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
