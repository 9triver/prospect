/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import CommunicationFormDictionaryDetails from './communication-form-dictionary-details.vue';
import CommunicationFormDictionaryService from './communication-form-dictionary.service';
import AlertService from '@/shared/alert/alert.service';

type CommunicationFormDictionaryDetailsComponentType = InstanceType<typeof CommunicationFormDictionaryDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const communicationFormDictionarySample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('CommunicationFormDictionary Management Detail Component', () => {
    let communicationFormDictionaryServiceStub: SinonStubbedInstance<CommunicationFormDictionaryService>;
    let mountOptions: MountingOptions<CommunicationFormDictionaryDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      communicationFormDictionaryServiceStub =
        sinon.createStubInstance<CommunicationFormDictionaryService>(CommunicationFormDictionaryService);

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
          communicationFormDictionaryService: () => communicationFormDictionaryServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        communicationFormDictionaryServiceStub.find.resolves(communicationFormDictionarySample);
        route = {
          params: {
            communicationFormDictionaryId: '' + 123,
          },
        };
        const wrapper = shallowMount(CommunicationFormDictionaryDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.communicationFormDictionary).toMatchObject(communicationFormDictionarySample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        communicationFormDictionaryServiceStub.find.resolves(communicationFormDictionarySample);
        const wrapper = shallowMount(CommunicationFormDictionaryDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
