/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import DeliveryContentService from './delivery-content.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { DeliveryContent } from '@/shared/model/delivery-content.model';

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
  describe('DeliveryContent Service', () => {
    let service: DeliveryContentService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new DeliveryContentService();
      currentDate = new Date();
      elemDefault = new DeliveryContent(
        123,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            purchaseplandate: dayjs(currentDate).format(DATE_FORMAT),
            negotiationfiletime: dayjs(currentDate).format(DATE_FORMAT),
            bidopeningtime: dayjs(currentDate).format(DATE_FORMAT),
            noticeofcompletiontime: dayjs(currentDate).format(DATE_FORMAT),
            signingdate: dayjs(currentDate).format(DATE_FORMAT),
            contractenddate: dayjs(currentDate).format(DATE_FORMAT),
            actualcompletiontime: dayjs(currentDate).format(DATE_FORMAT),
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

      it('should create a DeliveryContent', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            purchaseplandate: dayjs(currentDate).format(DATE_FORMAT),
            negotiationfiletime: dayjs(currentDate).format(DATE_FORMAT),
            bidopeningtime: dayjs(currentDate).format(DATE_FORMAT),
            noticeofcompletiontime: dayjs(currentDate).format(DATE_FORMAT),
            signingdate: dayjs(currentDate).format(DATE_FORMAT),
            contractenddate: dayjs(currentDate).format(DATE_FORMAT),
            actualcompletiontime: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            purchaseplandate: currentDate,
            negotiationfiletime: currentDate,
            bidopeningtime: currentDate,
            noticeofcompletiontime: currentDate,
            signingdate: currentDate,
            contractenddate: currentDate,
            actualcompletiontime: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a DeliveryContent', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a DeliveryContent', async () => {
        const returnedFromService = Object.assign(
          {
            warrantyrequirement: 'BBBBBB',
            purchaseplanno: 'BBBBBB',
            purchaseplandate: dayjs(currentDate).format(DATE_FORMAT),
            purchaseplanamount: 1,
            purchasemethod: 'BBBBBB',
            purchasesecretlevel: 'BBBBBB',
            reviewmethod: 'BBBBBB',
            requirementdepartment: 'BBBBBB',
            requirementperson: 'BBBBBB',
            undertaker: 'BBBBBB',
            undertakingdepartment: 'BBBBBB',
            workbagid: 'BBBBBB',
            projectmanager: 'BBBBBB',
            fundsource: 'BBBBBB',
            thesisname: 'BBBBBB',
            contractauxiliaryno: 'BBBBBB',
            reasonfornosuppliers: 'BBBBBB',
            reasonforchange: 'BBBBBB',
            negotiationfiletime: dayjs(currentDate).format(DATE_FORMAT),
            bidopeningtime: dayjs(currentDate).format(DATE_FORMAT),
            judges: 'BBBBBB',
            responsevendorname: 'BBBBBB',
            finalquoteandscore: 'BBBBBB',
            noticeofcompletiontime: dayjs(currentDate).format(DATE_FORMAT),
            signingdate: dayjs(currentDate).format(DATE_FORMAT),
            contractenddate: dayjs(currentDate).format(DATE_FORMAT),
            actualcompletiontime: dayjs(currentDate).format(DATE_FORMAT),
            issubmitsecrecyagreement: 'BBBBBB',
            issubmitsecurityagreement: 'BBBBBB',
            remark: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            purchaseplandate: currentDate,
            negotiationfiletime: currentDate,
            bidopeningtime: currentDate,
            noticeofcompletiontime: currentDate,
            signingdate: currentDate,
            contractenddate: currentDate,
            actualcompletiontime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a DeliveryContent', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a DeliveryContent', async () => {
        const patchObject = Object.assign(
          {
            warrantyrequirement: 'BBBBBB',
            purchaseplanno: 'BBBBBB',
            purchaseplandate: dayjs(currentDate).format(DATE_FORMAT),
            purchaseplanamount: 1,
            purchasemethod: 'BBBBBB',
            reviewmethod: 'BBBBBB',
            requirementdepartment: 'BBBBBB',
            requirementperson: 'BBBBBB',
            projectmanager: 'BBBBBB',
            reasonfornosuppliers: 'BBBBBB',
            bidopeningtime: dayjs(currentDate).format(DATE_FORMAT),
            judges: 'BBBBBB',
            responsevendorname: 'BBBBBB',
            finalquoteandscore: 'BBBBBB',
            signingdate: dayjs(currentDate).format(DATE_FORMAT),
            actualcompletiontime: dayjs(currentDate).format(DATE_FORMAT),
            issubmitsecrecyagreement: 'BBBBBB',
            remark: 'BBBBBB',
          },
          new DeliveryContent(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            purchaseplandate: currentDate,
            negotiationfiletime: currentDate,
            bidopeningtime: currentDate,
            noticeofcompletiontime: currentDate,
            signingdate: currentDate,
            contractenddate: currentDate,
            actualcompletiontime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a DeliveryContent', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of DeliveryContent', async () => {
        const returnedFromService = Object.assign(
          {
            warrantyrequirement: 'BBBBBB',
            purchaseplanno: 'BBBBBB',
            purchaseplandate: dayjs(currentDate).format(DATE_FORMAT),
            purchaseplanamount: 1,
            purchasemethod: 'BBBBBB',
            purchasesecretlevel: 'BBBBBB',
            reviewmethod: 'BBBBBB',
            requirementdepartment: 'BBBBBB',
            requirementperson: 'BBBBBB',
            undertaker: 'BBBBBB',
            undertakingdepartment: 'BBBBBB',
            workbagid: 'BBBBBB',
            projectmanager: 'BBBBBB',
            fundsource: 'BBBBBB',
            thesisname: 'BBBBBB',
            contractauxiliaryno: 'BBBBBB',
            reasonfornosuppliers: 'BBBBBB',
            reasonforchange: 'BBBBBB',
            negotiationfiletime: dayjs(currentDate).format(DATE_FORMAT),
            bidopeningtime: dayjs(currentDate).format(DATE_FORMAT),
            judges: 'BBBBBB',
            responsevendorname: 'BBBBBB',
            finalquoteandscore: 'BBBBBB',
            noticeofcompletiontime: dayjs(currentDate).format(DATE_FORMAT),
            signingdate: dayjs(currentDate).format(DATE_FORMAT),
            contractenddate: dayjs(currentDate).format(DATE_FORMAT),
            actualcompletiontime: dayjs(currentDate).format(DATE_FORMAT),
            issubmitsecrecyagreement: 'BBBBBB',
            issubmitsecurityagreement: 'BBBBBB',
            remark: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            purchaseplandate: currentDate,
            negotiationfiletime: currentDate,
            bidopeningtime: currentDate,
            noticeofcompletiontime: currentDate,
            signingdate: currentDate,
            contractenddate: currentDate,
            actualcompletiontime: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of DeliveryContent', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a DeliveryContent', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a DeliveryContent', async () => {
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
