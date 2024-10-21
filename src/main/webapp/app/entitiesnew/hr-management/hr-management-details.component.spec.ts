/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import HrManagementDetails from './hr-management-details.vue';
import HrManagementService from './hr-management.service';
import AlertService from '@/shared/alert/alert.service';

type HrManagementDetailsComponentType = InstanceType<typeof HrManagementDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const hrManagementSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('HrManagement Management Detail Component', () => {
    let hrManagementServiceStub: SinonStubbedInstance<HrManagementService>;
    let mountOptions: MountingOptions<HrManagementDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      hrManagementServiceStub = sinon.createStubInstance<HrManagementService>(HrManagementService);

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
          hrManagementService: () => hrManagementServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        hrManagementServiceStub.find.resolves(hrManagementSample);
        route = {
          params: {
            hrManagementId: '' + 123,
          },
        };
        const wrapper = shallowMount(HrManagementDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.hrManagement).toMatchObject(hrManagementSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        hrManagementServiceStub.find.resolves(hrManagementSample);
        const wrapper = shallowMount(HrManagementDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
