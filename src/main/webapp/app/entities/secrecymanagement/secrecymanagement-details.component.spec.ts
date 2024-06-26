/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import SecrecymanagementDetails from './secrecymanagement-details.vue';
import SecrecymanagementService from './secrecymanagement.service';
import AlertService from '@/shared/alert/alert.service';

type SecrecymanagementDetailsComponentType = InstanceType<typeof SecrecymanagementDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const secrecymanagementSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Secrecymanagement Management Detail Component', () => {
    let secrecymanagementServiceStub: SinonStubbedInstance<SecrecymanagementService>;
    let mountOptions: MountingOptions<SecrecymanagementDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      secrecymanagementServiceStub = sinon.createStubInstance<SecrecymanagementService>(SecrecymanagementService);

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
          secrecymanagementService: () => secrecymanagementServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        secrecymanagementServiceStub.find.resolves(secrecymanagementSample);
        route = {
          params: {
            secrecymanagementId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(SecrecymanagementDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.secrecymanagement).toMatchObject(secrecymanagementSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        secrecymanagementServiceStub.find.resolves(secrecymanagementSample);
        const wrapper = shallowMount(SecrecymanagementDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
