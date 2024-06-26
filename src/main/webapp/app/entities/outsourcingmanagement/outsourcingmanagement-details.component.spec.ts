/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import OutsourcingmanagementDetails from './outsourcingmanagement-details.vue';
import OutsourcingmanagementService from './outsourcingmanagement.service';
import AlertService from '@/shared/alert/alert.service';

type OutsourcingmanagementDetailsComponentType = InstanceType<typeof OutsourcingmanagementDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const outsourcingmanagementSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Outsourcingmanagement Management Detail Component', () => {
    let outsourcingmanagementServiceStub: SinonStubbedInstance<OutsourcingmanagementService>;
    let mountOptions: MountingOptions<OutsourcingmanagementDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      outsourcingmanagementServiceStub = sinon.createStubInstance<OutsourcingmanagementService>(OutsourcingmanagementService);

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
          outsourcingmanagementService: () => outsourcingmanagementServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        outsourcingmanagementServiceStub.find.resolves(outsourcingmanagementSample);
        route = {
          params: {
            outsourcingmanagementId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(OutsourcingmanagementDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.outsourcingmanagement).toMatchObject(outsourcingmanagementSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        outsourcingmanagementServiceStub.find.resolves(outsourcingmanagementSample);
        const wrapper = shallowMount(OutsourcingmanagementDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
