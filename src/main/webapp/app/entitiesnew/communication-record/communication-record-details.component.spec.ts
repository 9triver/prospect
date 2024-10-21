/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import CommunicationRecordDetails from './communication-record-details.vue';
import CommunicationRecordService from './communication-record.service';
import AlertService from '@/shared/alert/alert.service';

type CommunicationRecordDetailsComponentType = InstanceType<typeof CommunicationRecordDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const communicationRecordSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('CommunicationRecord Management Detail Component', () => {
    let communicationRecordServiceStub: SinonStubbedInstance<CommunicationRecordService>;
    let mountOptions: MountingOptions<CommunicationRecordDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      communicationRecordServiceStub = sinon.createStubInstance<CommunicationRecordService>(CommunicationRecordService);

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          'font-awesome-icon': true,
          'router-link': true,
        },
        provide: {
          alertService,
          communicationRecordService: () => communicationRecordServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        communicationRecordServiceStub.find.resolves(communicationRecordSample);
        route = {
          params: {
            communicationRecordId: '' + 123,
          },
        };
        const wrapper = shallowMount(CommunicationRecordDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.communicationRecord).toMatchObject(communicationRecordSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        communicationRecordServiceStub.find.resolves(communicationRecordSample);
        const wrapper = shallowMount(CommunicationRecordDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
