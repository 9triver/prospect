/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import DeliverablesDetails from './deliverables-details.vue';
import DeliverablesService from './deliverables.service';
import AlertService from '@/shared/alert/alert.service';

type DeliverablesDetailsComponentType = InstanceType<typeof DeliverablesDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const deliverablesSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Deliverables Management Detail Component', () => {
    let deliverablesServiceStub: SinonStubbedInstance<DeliverablesService>;
    let mountOptions: MountingOptions<DeliverablesDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      deliverablesServiceStub = sinon.createStubInstance<DeliverablesService>(DeliverablesService);

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
          deliverablesService: () => deliverablesServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        deliverablesServiceStub.find.resolves(deliverablesSample);
        route = {
          params: {
            deliverablesId: '' + 123,
          },
        };
        const wrapper = shallowMount(DeliverablesDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.deliverables).toMatchObject(deliverablesSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        deliverablesServiceStub.find.resolves(deliverablesSample);
        const wrapper = shallowMount(DeliverablesDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
