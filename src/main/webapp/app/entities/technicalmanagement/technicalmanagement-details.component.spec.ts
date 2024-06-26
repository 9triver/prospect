/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import TechnicalmanagementDetails from './technicalmanagement-details.vue';
import TechnicalmanagementService from './technicalmanagement.service';
import AlertService from '@/shared/alert/alert.service';

type TechnicalmanagementDetailsComponentType = InstanceType<typeof TechnicalmanagementDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const technicalmanagementSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Technicalmanagement Management Detail Component', () => {
    let technicalmanagementServiceStub: SinonStubbedInstance<TechnicalmanagementService>;
    let mountOptions: MountingOptions<TechnicalmanagementDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      technicalmanagementServiceStub = sinon.createStubInstance<TechnicalmanagementService>(TechnicalmanagementService);

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
          technicalmanagementService: () => technicalmanagementServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        technicalmanagementServiceStub.find.resolves(technicalmanagementSample);
        route = {
          params: {
            technicalmanagementId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(TechnicalmanagementDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.technicalmanagement).toMatchObject(technicalmanagementSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        technicalmanagementServiceStub.find.resolves(technicalmanagementSample);
        const wrapper = shallowMount(TechnicalmanagementDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
