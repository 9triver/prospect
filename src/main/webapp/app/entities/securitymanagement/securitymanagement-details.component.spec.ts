/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import SecuritymanagementDetails from './securitymanagement-details.vue';
import SecuritymanagementService from './securitymanagement.service';
import AlertService from '@/shared/alert/alert.service';

type SecuritymanagementDetailsComponentType = InstanceType<typeof SecuritymanagementDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const securitymanagementSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Securitymanagement Management Detail Component', () => {
    let securitymanagementServiceStub: SinonStubbedInstance<SecuritymanagementService>;
    let mountOptions: MountingOptions<SecuritymanagementDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      securitymanagementServiceStub = sinon.createStubInstance<SecuritymanagementService>(SecuritymanagementService);

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
          securitymanagementService: () => securitymanagementServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        securitymanagementServiceStub.find.resolves(securitymanagementSample);
        route = {
          params: {
            securitymanagementId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(SecuritymanagementDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.securitymanagement).toMatchObject(securitymanagementSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        securitymanagementServiceStub.find.resolves(securitymanagementSample);
        const wrapper = shallowMount(SecuritymanagementDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
