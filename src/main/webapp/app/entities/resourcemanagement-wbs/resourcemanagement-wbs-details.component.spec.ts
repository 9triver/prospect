/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ResourcemanagementWbsDetails from './resourcemanagement-wbs-details.vue';
import ResourcemanagementWbsService from './resourcemanagement-wbs.service';
import AlertService from '@/shared/alert/alert.service';

type ResourcemanagementWbsDetailsComponentType = InstanceType<typeof ResourcemanagementWbsDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const resourcemanagementWbsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('ResourcemanagementWbs Management Detail Component', () => {
    let resourcemanagementWbsServiceStub: SinonStubbedInstance<ResourcemanagementWbsService>;
    let mountOptions: MountingOptions<ResourcemanagementWbsDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      resourcemanagementWbsServiceStub = sinon.createStubInstance<ResourcemanagementWbsService>(ResourcemanagementWbsService);

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
          resourcemanagementWbsService: () => resourcemanagementWbsServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        resourcemanagementWbsServiceStub.find.resolves(resourcemanagementWbsSample);
        route = {
          params: {
            resourcemanagementWbsId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(ResourcemanagementWbsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.resourcemanagementWbs).toMatchObject(resourcemanagementWbsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        resourcemanagementWbsServiceStub.find.resolves(resourcemanagementWbsSample);
        const wrapper = shallowMount(ResourcemanagementWbsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
