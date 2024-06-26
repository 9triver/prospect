/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ComprehensiveledgerDetails from './comprehensiveledger-details.vue';
import ComprehensiveledgerService from './comprehensiveledger.service';
import AlertService from '@/shared/alert/alert.service';

type ComprehensiveledgerDetailsComponentType = InstanceType<typeof ComprehensiveledgerDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const comprehensiveledgerSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Comprehensiveledger Management Detail Component', () => {
    let comprehensiveledgerServiceStub: SinonStubbedInstance<ComprehensiveledgerService>;
    let mountOptions: MountingOptions<ComprehensiveledgerDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      comprehensiveledgerServiceStub = sinon.createStubInstance<ComprehensiveledgerService>(ComprehensiveledgerService);

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
          comprehensiveledgerService: () => comprehensiveledgerServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        comprehensiveledgerServiceStub.find.resolves(comprehensiveledgerSample);
        route = {
          params: {
            comprehensiveledgerId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(ComprehensiveledgerDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.comprehensiveledger).toMatchObject(comprehensiveledgerSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comprehensiveledgerServiceStub.find.resolves(comprehensiveledgerSample);
        const wrapper = shallowMount(ComprehensiveledgerDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
