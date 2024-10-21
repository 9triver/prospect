/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import DeviationPermitApplicationDetails from './deviation-permit-application-details.vue';
import DeviationPermitApplicationService from './deviation-permit-application.service';
import AlertService from '@/shared/alert/alert.service';

type DeviationPermitApplicationDetailsComponentType = InstanceType<typeof DeviationPermitApplicationDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const deviationPermitApplicationSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('DeviationPermitApplication Management Detail Component', () => {
    let deviationPermitApplicationServiceStub: SinonStubbedInstance<DeviationPermitApplicationService>;
    let mountOptions: MountingOptions<DeviationPermitApplicationDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      deviationPermitApplicationServiceStub =
        sinon.createStubInstance<DeviationPermitApplicationService>(DeviationPermitApplicationService);

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
          deviationPermitApplicationService: () => deviationPermitApplicationServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        deviationPermitApplicationServiceStub.find.resolves(deviationPermitApplicationSample);
        route = {
          params: {
            deviationPermitApplicationId: '' + 123,
          },
        };
        const wrapper = shallowMount(DeviationPermitApplicationDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.deviationPermitApplication).toMatchObject(deviationPermitApplicationSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        deviationPermitApplicationServiceStub.find.resolves(deviationPermitApplicationSample);
        const wrapper = shallowMount(DeviationPermitApplicationDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
