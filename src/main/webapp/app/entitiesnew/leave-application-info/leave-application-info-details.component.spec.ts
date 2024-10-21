/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import LeaveApplicationInfoDetails from './leave-application-info-details.vue';
import LeaveApplicationInfoService from './leave-application-info.service';
import AlertService from '@/shared/alert/alert.service';

type LeaveApplicationInfoDetailsComponentType = InstanceType<typeof LeaveApplicationInfoDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const leaveApplicationInfoSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('LeaveApplicationInfo Management Detail Component', () => {
    let leaveApplicationInfoServiceStub: SinonStubbedInstance<LeaveApplicationInfoService>;
    let mountOptions: MountingOptions<LeaveApplicationInfoDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      leaveApplicationInfoServiceStub = sinon.createStubInstance<LeaveApplicationInfoService>(LeaveApplicationInfoService);

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
          leaveApplicationInfoService: () => leaveApplicationInfoServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        leaveApplicationInfoServiceStub.find.resolves(leaveApplicationInfoSample);
        route = {
          params: {
            leaveApplicationInfoId: '' + 123,
          },
        };
        const wrapper = shallowMount(LeaveApplicationInfoDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.leaveApplicationInfo).toMatchObject(leaveApplicationInfoSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        leaveApplicationInfoServiceStub.find.resolves(leaveApplicationInfoSample);
        const wrapper = shallowMount(LeaveApplicationInfoDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
