/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import FrontlineDetails from './frontline-details.vue';
import FrontlineService from './frontline.service';
import AlertService from '@/shared/alert/alert.service';

type FrontlineDetailsComponentType = InstanceType<typeof FrontlineDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const frontlineSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Frontline Management Detail Component', () => {
    let frontlineServiceStub: SinonStubbedInstance<FrontlineService>;
    let mountOptions: MountingOptions<FrontlineDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      frontlineServiceStub = sinon.createStubInstance<FrontlineService>(FrontlineService);

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
          frontlineService: () => frontlineServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        frontlineServiceStub.find.resolves(frontlineSample);
        route = {
          params: {
            frontlineId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(FrontlineDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.frontline).toMatchObject(frontlineSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        frontlineServiceStub.find.resolves(frontlineSample);
        const wrapper = shallowMount(FrontlineDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
