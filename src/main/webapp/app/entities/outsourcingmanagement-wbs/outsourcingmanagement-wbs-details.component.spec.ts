/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import OutsourcingmanagementWbsDetails from './outsourcingmanagement-wbs-details.vue';
import OutsourcingmanagementWbsService from './outsourcingmanagement-wbs.service';
import AlertService from '@/shared/alert/alert.service';

type OutsourcingmanagementWbsDetailsComponentType = InstanceType<typeof OutsourcingmanagementWbsDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const outsourcingmanagementWbsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('OutsourcingmanagementWbs Management Detail Component', () => {
    let outsourcingmanagementWbsServiceStub: SinonStubbedInstance<OutsourcingmanagementWbsService>;
    let mountOptions: MountingOptions<OutsourcingmanagementWbsDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      outsourcingmanagementWbsServiceStub = sinon.createStubInstance<OutsourcingmanagementWbsService>(OutsourcingmanagementWbsService);

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
          outsourcingmanagementWbsService: () => outsourcingmanagementWbsServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        outsourcingmanagementWbsServiceStub.find.resolves(outsourcingmanagementWbsSample);
        route = {
          params: {
            outsourcingmanagementWbsId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(OutsourcingmanagementWbsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.outsourcingmanagementWbs).toMatchObject(outsourcingmanagementWbsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        outsourcingmanagementWbsServiceStub.find.resolves(outsourcingmanagementWbsSample);
        const wrapper = shallowMount(OutsourcingmanagementWbsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
