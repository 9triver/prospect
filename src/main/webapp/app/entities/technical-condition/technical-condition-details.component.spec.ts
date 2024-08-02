/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import TechnicalConditionDetails from './technical-condition-details.vue';
import TechnicalConditionService from './technical-condition.service';
import AlertService from '@/shared/alert/alert.service';

type TechnicalConditionDetailsComponentType = InstanceType<typeof TechnicalConditionDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const technicalConditionSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('TechnicalCondition Management Detail Component', () => {
    let technicalConditionServiceStub: SinonStubbedInstance<TechnicalConditionService>;
    let mountOptions: MountingOptions<TechnicalConditionDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      technicalConditionServiceStub = sinon.createStubInstance<TechnicalConditionService>(TechnicalConditionService);

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
          technicalConditionService: () => technicalConditionServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        technicalConditionServiceStub.find.resolves(technicalConditionSample);
        route = {
          params: {
            technicalConditionId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(TechnicalConditionDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.technicalCondition).toMatchObject(technicalConditionSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        technicalConditionServiceStub.find.resolves(technicalConditionSample);
        const wrapper = shallowMount(TechnicalConditionDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
