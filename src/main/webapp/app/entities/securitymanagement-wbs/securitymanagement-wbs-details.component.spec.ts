/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import SecuritymanagementWbsDetails from './securitymanagement-wbs-details.vue';
import SecuritymanagementWbsService from './securitymanagement-wbs.service';
import AlertService from '@/shared/alert/alert.service';

type SecuritymanagementWbsDetailsComponentType = InstanceType<typeof SecuritymanagementWbsDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const securitymanagementWbsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('SecuritymanagementWbs Management Detail Component', () => {
    let securitymanagementWbsServiceStub: SinonStubbedInstance<SecuritymanagementWbsService>;
    let mountOptions: MountingOptions<SecuritymanagementWbsDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      securitymanagementWbsServiceStub = sinon.createStubInstance<SecuritymanagementWbsService>(SecuritymanagementWbsService);

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
          securitymanagementWbsService: () => securitymanagementWbsServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        securitymanagementWbsServiceStub.find.resolves(securitymanagementWbsSample);
        route = {
          params: {
            securitymanagementWbsId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(SecuritymanagementWbsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.securitymanagementWbs).toMatchObject(securitymanagementWbsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        securitymanagementWbsServiceStub.find.resolves(securitymanagementWbsSample);
        const wrapper = shallowMount(SecuritymanagementWbsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
