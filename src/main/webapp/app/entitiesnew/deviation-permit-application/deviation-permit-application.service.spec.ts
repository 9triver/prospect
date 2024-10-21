/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import DeviationPermitApplicationService from './deviation-permit-application.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { DeviationPermitApplication } from '@/shared/model/deviation-permit-application.model';

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
  describe('DeviationPermitApplication Service', () => {
    let service: DeviationPermitApplicationService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new DeviationPermitApplicationService();
      currentDate = new Date();
      elemDefault = new DeviationPermitApplication(
        123,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'NOT_AUDITED',
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            applicationdate: dayjs(currentDate).format(DATE_FORMAT),
            implementationdate: dayjs(currentDate).format(DATE_FORMAT),
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

      it('should create a DeviationPermitApplication', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            applicationdate: dayjs(currentDate).format(DATE_FORMAT),
            implementationdate: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            applicationdate: currentDate,
            implementationdate: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a DeviationPermitApplication', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a DeviationPermitApplication', async () => {
        const returnedFromService = Object.assign(
          {
            wbsid: 'BBBBBB',
            technicalfileid: 'BBBBBB',
            applicationunit: 'BBBBBB',
            applicant: 'BBBBBB',
            applicationdate: dayjs(currentDate).format(DATE_FORMAT),
            permitcontent: 'BBBBBB',
            permitreason: 'BBBBBB',
            projectinfluence: 'BBBBBB',
            contractinfluence: 'BBBBBB',
            permitrange: 'BBBBBB',
            implementationdate: dayjs(currentDate).format(DATE_FORMAT),
            remarks: 'BBBBBB',
            auditStatus: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            applicationdate: currentDate,
            implementationdate: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a DeviationPermitApplication', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a DeviationPermitApplication', async () => {
        const patchObject = Object.assign(
          {
            wbsid: 'BBBBBB',
            applicationunit: 'BBBBBB',
            applicant: 'BBBBBB',
            permitcontent: 'BBBBBB',
            permitreason: 'BBBBBB',
            implementationdate: dayjs(currentDate).format(DATE_FORMAT),
            auditStatus: 'BBBBBB',
          },
          new DeviationPermitApplication(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            applicationdate: currentDate,
            implementationdate: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a DeviationPermitApplication', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of DeviationPermitApplication', async () => {
        const returnedFromService = Object.assign(
          {
            wbsid: 'BBBBBB',
            technicalfileid: 'BBBBBB',
            applicationunit: 'BBBBBB',
            applicant: 'BBBBBB',
            applicationdate: dayjs(currentDate).format(DATE_FORMAT),
            permitcontent: 'BBBBBB',
            permitreason: 'BBBBBB',
            projectinfluence: 'BBBBBB',
            contractinfluence: 'BBBBBB',
            permitrange: 'BBBBBB',
            implementationdate: dayjs(currentDate).format(DATE_FORMAT),
            remarks: 'BBBBBB',
            auditStatus: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            applicationdate: currentDate,
            implementationdate: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of DeviationPermitApplication', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a DeviationPermitApplication', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a DeviationPermitApplication', async () => {
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
