/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import RiskTypeDetails from './risk-type-details.vue';
import RiskTypeService from './risk-type.service';
import AlertService from '@/shared/alert/alert.service';

type RiskTypeDetailsComponentType = InstanceType<typeof RiskTypeDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const riskTypeSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('RiskType Management Detail Component', () => {
    let riskTypeServiceStub: SinonStubbedInstance<RiskTypeService>;
    let mountOptions: MountingOptions<RiskTypeDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      riskTypeServiceStub = sinon.createStubInstance<RiskTypeService>(RiskTypeService);

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
          riskTypeService: () => riskTypeServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        riskTypeServiceStub.find.resolves(riskTypeSample);
        route = {
          params: {
            riskTypeId: '' + 123,
          },
        };
        const wrapper = shallowMount(RiskTypeDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.riskType).toMatchObject(riskTypeSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        riskTypeServiceStub.find.resolves(riskTypeSample);
        const wrapper = shallowMount(RiskTypeDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
