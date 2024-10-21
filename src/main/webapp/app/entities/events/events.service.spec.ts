/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import EventsService from './events.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { Events } from '@/shared/model/events.model';

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
  describe('Events Service', () => {
    let service: EventsService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new EventsService();
      currentDate = new Date();
      elemDefault = new Events('ABC', 'AAAAAAA', 'AAAAAAA', currentDate, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'PUBLIC', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            time: dayjs(currentDate).format(DATE_FORMAT),
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

      it('should create a Events', async () => {
        const returnedFromService = Object.assign(
          {
            id: 'ABC',
            time: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            time: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a Events', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Events', async () => {
        const returnedFromService = Object.assign(
          {
            title: 'BBBBBB',
            content: 'BBBBBB',
            time: dayjs(currentDate).format(DATE_FORMAT),
            place: 'BBBBBB',
            participants: 'BBBBBB',
            picture: 'BBBBBB',
            description: 'BBBBBB',
            secretlevel: 'BBBBBB',
            attachment: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            time: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Events', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Events', async () => {
        const patchObject = Object.assign(
          {
            title: 'BBBBBB',
            time: dayjs(currentDate).format(DATE_FORMAT),
            description: 'BBBBBB',
            secretlevel: 'BBBBBB',
          },
          new Events(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            time: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a Events', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Events', async () => {
        const returnedFromService = Object.assign(
          {
            title: 'BBBBBB',
            content: 'BBBBBB',
            time: dayjs(currentDate).format(DATE_FORMAT),
            place: 'BBBBBB',
            participants: 'BBBBBB',
            picture: 'BBBBBB',
            description: 'BBBBBB',
            secretlevel: 'BBBBBB',
            attachment: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            time: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve().then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Events', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Events', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete('ABC').then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Events', async () => {
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
