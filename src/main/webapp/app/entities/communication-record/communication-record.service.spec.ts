/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import CommunicationRecordService from './communication-record.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { CommunicationRecord } from '@/shared/model/communication-record.model';

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
  describe('CommunicationRecord Service', () => {
    let service: CommunicationRecordService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new CommunicationRecordService();
      currentDate = new Date();
      elemDefault = new CommunicationRecord(
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
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            communicationtime: dayjs(currentDate).format(DATE_FORMAT),
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

      it('should create a CommunicationRecord', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            communicationtime: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            communicationtime: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a CommunicationRecord', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a CommunicationRecord', async () => {
        const returnedFromService = Object.assign(
          {
            wbsid: 'BBBBBB',
            wbsname: 'BBBBBB',
            workbagid: 'BBBBBB',
            associationmeetingname: 'BBBBBB',
            communicationtime: dayjs(currentDate).format(DATE_FORMAT),
            communicationlocation: 'BBBBBB',
            communicationcontent: 'BBBBBB',
            auditorid: 'BBBBBB',
            auditorname: 'BBBBBB',
            remarks: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            communicationtime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a CommunicationRecord', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a CommunicationRecord', async () => {
        const patchObject = Object.assign(
          {
            communicationtime: dayjs(currentDate).format(DATE_FORMAT),
            communicationcontent: 'BBBBBB',
            auditorid: 'BBBBBB',
            remarks: 'BBBBBB',
          },
          new CommunicationRecord(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            communicationtime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a CommunicationRecord', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of CommunicationRecord', async () => {
        const returnedFromService = Object.assign(
          {
            wbsid: 'BBBBBB',
            wbsname: 'BBBBBB',
            workbagid: 'BBBBBB',
            associationmeetingname: 'BBBBBB',
            communicationtime: dayjs(currentDate).format(DATE_FORMAT),
            communicationlocation: 'BBBBBB',
            communicationcontent: 'BBBBBB',
            auditorid: 'BBBBBB',
            auditorname: 'BBBBBB',
            remarks: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            communicationtime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of CommunicationRecord', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a CommunicationRecord', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a CommunicationRecord', async () => {
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
