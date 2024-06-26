/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import PbsmanageDetails from './pbsmanage-details.vue';
import PbsmanageService from './pbsmanage.service';
import AlertService from '@/shared/alert/alert.service';

type PbsmanageDetailsComponentType = InstanceType<typeof PbsmanageDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const pbsmanageSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Pbsmanage Management Detail Component', () => {
    let pbsmanageServiceStub: SinonStubbedInstance<PbsmanageService>;
    let mountOptions: MountingOptions<PbsmanageDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      pbsmanageServiceStub = sinon.createStubInstance<PbsmanageService>(PbsmanageService);

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
          pbsmanageService: () => pbsmanageServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        pbsmanageServiceStub.find.resolves(pbsmanageSample);
        route = {
          params: {
            pbsmanageId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(PbsmanageDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.pbsmanage).toMatchObject(pbsmanageSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        pbsmanageServiceStub.find.resolves(pbsmanageSample);
        const wrapper = shallowMount(PbsmanageDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
