/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import OutsourcingContractualDetails from './outsourcing-contractual-details.vue';
import OutsourcingContractualService from './outsourcing-contractual.service';
import AlertService from '@/shared/alert/alert.service';

type OutsourcingContractualDetailsComponentType = InstanceType<typeof OutsourcingContractualDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const outsourcingContractualSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('OutsourcingContractual Management Detail Component', () => {
    let outsourcingContractualServiceStub: SinonStubbedInstance<OutsourcingContractualService>;
    let mountOptions: MountingOptions<OutsourcingContractualDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      outsourcingContractualServiceStub = sinon.createStubInstance<OutsourcingContractualService>(OutsourcingContractualService);

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
          outsourcingContractualService: () => outsourcingContractualServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        outsourcingContractualServiceStub.find.resolves(outsourcingContractualSample);
        route = {
          params: {
            outsourcingContractualId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(OutsourcingContractualDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.outsourcingContractual).toMatchObject(outsourcingContractualSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        outsourcingContractualServiceStub.find.resolves(outsourcingContractualSample);
        const wrapper = shallowMount(OutsourcingContractualDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
