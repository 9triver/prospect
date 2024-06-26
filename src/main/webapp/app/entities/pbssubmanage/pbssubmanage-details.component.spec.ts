/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import PbssubmanageDetails from './pbssubmanage-details.vue';
import PbssubmanageService from './pbssubmanage.service';
import AlertService from '@/shared/alert/alert.service';

type PbssubmanageDetailsComponentType = InstanceType<typeof PbssubmanageDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const pbssubmanageSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Pbssubmanage Management Detail Component', () => {
    let pbssubmanageServiceStub: SinonStubbedInstance<PbssubmanageService>;
    let mountOptions: MountingOptions<PbssubmanageDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      pbssubmanageServiceStub = sinon.createStubInstance<PbssubmanageService>(PbssubmanageService);

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
          pbssubmanageService: () => pbssubmanageServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        pbssubmanageServiceStub.find.resolves(pbssubmanageSample);
        route = {
          params: {
            pbssubmanageId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(PbssubmanageDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.pbssubmanage).toMatchObject(pbssubmanageSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        pbssubmanageServiceStub.find.resolves(pbssubmanageSample);
        const wrapper = shallowMount(PbssubmanageDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
