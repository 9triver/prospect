/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import PbsbaselineDetails from './pbsbaseline-details.vue';
import PbsbaselineService from './pbsbaseline.service';
import AlertService from '@/shared/alert/alert.service';

type PbsbaselineDetailsComponentType = InstanceType<typeof PbsbaselineDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const pbsbaselineSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Pbsbaseline Management Detail Component', () => {
    let pbsbaselineServiceStub: SinonStubbedInstance<PbsbaselineService>;
    let mountOptions: MountingOptions<PbsbaselineDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      pbsbaselineServiceStub = sinon.createStubInstance<PbsbaselineService>(PbsbaselineService);

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
          pbsbaselineService: () => pbsbaselineServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        pbsbaselineServiceStub.find.resolves(pbsbaselineSample);
        route = {
          params: {
            pbsbaselineId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(PbsbaselineDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.pbsbaseline).toMatchObject(pbsbaselineSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        pbsbaselineServiceStub.find.resolves(pbsbaselineSample);
        const wrapper = shallowMount(PbsbaselineDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
