/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import WbsbaselineDetails from './wbsbaseline-details.vue';
import WbsbaselineService from './wbsbaseline.service';
import AlertService from '@/shared/alert/alert.service';

type WbsbaselineDetailsComponentType = InstanceType<typeof WbsbaselineDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const wbsbaselineSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Wbsbaseline Management Detail Component', () => {
    let wbsbaselineServiceStub: SinonStubbedInstance<WbsbaselineService>;
    let mountOptions: MountingOptions<WbsbaselineDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      wbsbaselineServiceStub = sinon.createStubInstance<WbsbaselineService>(WbsbaselineService);

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
          wbsbaselineService: () => wbsbaselineServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        wbsbaselineServiceStub.find.resolves(wbsbaselineSample);
        route = {
          params: {
            wbsbaselineId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(WbsbaselineDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.wbsbaseline).toMatchObject(wbsbaselineSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        wbsbaselineServiceStub.find.resolves(wbsbaselineSample);
        const wrapper = shallowMount(WbsbaselineDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
