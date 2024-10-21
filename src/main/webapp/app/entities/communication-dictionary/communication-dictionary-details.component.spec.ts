/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import CommunicationDictionaryDetails from './communication-dictionary-details.vue';
import CommunicationDictionaryService from './communication-dictionary.service';
import AlertService from '@/shared/alert/alert.service';

type CommunicationDictionaryDetailsComponentType = InstanceType<typeof CommunicationDictionaryDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const communicationDictionarySample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('CommunicationDictionary Management Detail Component', () => {
    let communicationDictionaryServiceStub: SinonStubbedInstance<CommunicationDictionaryService>;
    let mountOptions: MountingOptions<CommunicationDictionaryDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      communicationDictionaryServiceStub = sinon.createStubInstance<CommunicationDictionaryService>(CommunicationDictionaryService);

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
          communicationDictionaryService: () => communicationDictionaryServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        communicationDictionaryServiceStub.find.resolves(communicationDictionarySample);
        route = {
          params: {
            communicationDictionaryId: '' + 123,
          },
        };
        const wrapper = shallowMount(CommunicationDictionaryDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.communicationDictionary).toMatchObject(communicationDictionarySample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        communicationDictionaryServiceStub.find.resolves(communicationDictionarySample);
        const wrapper = shallowMount(CommunicationDictionaryDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
