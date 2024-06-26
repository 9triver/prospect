/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import IntegratedmanagementDetails from './integratedmanagement-details.vue';
import IntegratedmanagementService from './integratedmanagement.service';
import AlertService from '@/shared/alert/alert.service';

type IntegratedmanagementDetailsComponentType = InstanceType<typeof IntegratedmanagementDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const integratedmanagementSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Integratedmanagement Management Detail Component', () => {
    let integratedmanagementServiceStub: SinonStubbedInstance<IntegratedmanagementService>;
    let mountOptions: MountingOptions<IntegratedmanagementDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      integratedmanagementServiceStub = sinon.createStubInstance<IntegratedmanagementService>(IntegratedmanagementService);

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
          integratedmanagementService: () => integratedmanagementServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        integratedmanagementServiceStub.find.resolves(integratedmanagementSample);
        route = {
          params: {
            integratedmanagementId: '' + 123,
          },
        };
        const wrapper = shallowMount(IntegratedmanagementDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.integratedmanagement).toMatchObject(integratedmanagementSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        integratedmanagementServiceStub.find.resolves(integratedmanagementSample);
        const wrapper = shallowMount(IntegratedmanagementDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
