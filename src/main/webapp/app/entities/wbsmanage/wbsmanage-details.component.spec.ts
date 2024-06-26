/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import WbsmanageDetails from './wbsmanage-details.vue';
import WbsmanageService from './wbsmanage.service';
import AlertService from '@/shared/alert/alert.service';

type WbsmanageDetailsComponentType = InstanceType<typeof WbsmanageDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const wbsmanageSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Wbsmanage Management Detail Component', () => {
    let wbsmanageServiceStub: SinonStubbedInstance<WbsmanageService>;
    let mountOptions: MountingOptions<WbsmanageDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      wbsmanageServiceStub = sinon.createStubInstance<WbsmanageService>(WbsmanageService);

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
          wbsmanageService: () => wbsmanageServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        wbsmanageServiceStub.find.resolves(wbsmanageSample);
        route = {
          params: {
            wbsmanageId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(WbsmanageDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.wbsmanage).toMatchObject(wbsmanageSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        wbsmanageServiceStub.find.resolves(wbsmanageSample);
        const wrapper = shallowMount(WbsmanageDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
