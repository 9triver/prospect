/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import TechnicalmanagementWbsDetails from './technicalmanagement-wbs-details.vue';
import TechnicalmanagementWbsService from './technicalmanagement-wbs.service';
import AlertService from '@/shared/alert/alert.service';

type TechnicalmanagementWbsDetailsComponentType = InstanceType<typeof TechnicalmanagementWbsDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const technicalmanagementWbsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('TechnicalmanagementWbs Management Detail Component', () => {
    let technicalmanagementWbsServiceStub: SinonStubbedInstance<TechnicalmanagementWbsService>;
    let mountOptions: MountingOptions<TechnicalmanagementWbsDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      technicalmanagementWbsServiceStub = sinon.createStubInstance<TechnicalmanagementWbsService>(TechnicalmanagementWbsService);

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
          technicalmanagementWbsService: () => technicalmanagementWbsServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        technicalmanagementWbsServiceStub.find.resolves(technicalmanagementWbsSample);
        route = {
          params: {
            technicalmanagementWbsId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(TechnicalmanagementWbsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.technicalmanagementWbs).toMatchObject(technicalmanagementWbsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        technicalmanagementWbsServiceStub.find.resolves(technicalmanagementWbsSample);
        const wrapper = shallowMount(TechnicalmanagementWbsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
