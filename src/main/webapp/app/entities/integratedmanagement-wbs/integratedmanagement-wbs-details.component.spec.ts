/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import IntegratedmanagementWbsDetails from './integratedmanagement-wbs-details.vue';
import IntegratedmanagementWbsService from './integratedmanagement-wbs.service';
import AlertService from '@/shared/alert/alert.service';

type IntegratedmanagementWbsDetailsComponentType = InstanceType<typeof IntegratedmanagementWbsDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const integratedmanagementWbsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('IntegratedmanagementWbs Management Detail Component', () => {
    let integratedmanagementWbsServiceStub: SinonStubbedInstance<IntegratedmanagementWbsService>;
    let mountOptions: MountingOptions<IntegratedmanagementWbsDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      integratedmanagementWbsServiceStub = sinon.createStubInstance<IntegratedmanagementWbsService>(IntegratedmanagementWbsService);

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
          integratedmanagementWbsService: () => integratedmanagementWbsServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        integratedmanagementWbsServiceStub.find.resolves(integratedmanagementWbsSample);
        route = {
          params: {
            integratedmanagementWbsId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(IntegratedmanagementWbsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.integratedmanagementWbs).toMatchObject(integratedmanagementWbsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        integratedmanagementWbsServiceStub.find.resolves(integratedmanagementWbsSample);
        const wrapper = shallowMount(IntegratedmanagementWbsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
