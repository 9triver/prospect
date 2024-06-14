/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ResourcemanagementDetails from './resourcemanagement-details.vue';
import ResourcemanagementService from './resourcemanagement.service';
import AlertService from '@/shared/alert/alert.service';

type ResourcemanagementDetailsComponentType = InstanceType<typeof ResourcemanagementDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const resourcemanagementSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Resourcemanagement Management Detail Component', () => {
    let resourcemanagementServiceStub: SinonStubbedInstance<ResourcemanagementService>;
    let mountOptions: MountingOptions<ResourcemanagementDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      resourcemanagementServiceStub = sinon.createStubInstance<ResourcemanagementService>(ResourcemanagementService);

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
          resourcemanagementService: () => resourcemanagementServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        resourcemanagementServiceStub.find.resolves(resourcemanagementSample);
        route = {
          params: {
            resourcemanagementId: '' + 123,
          },
        };
        const wrapper = shallowMount(ResourcemanagementDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.resourcemanagement).toMatchObject(resourcemanagementSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        resourcemanagementServiceStub.find.resolves(resourcemanagementSample);
        const wrapper = shallowMount(ResourcemanagementDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
