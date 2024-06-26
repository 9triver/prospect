/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProgressmanagementWbsDetails from './progressmanagement-wbs-details.vue';
import ProgressmanagementWbsService from './progressmanagement-wbs.service';
import AlertService from '@/shared/alert/alert.service';

type ProgressmanagementWbsDetailsComponentType = InstanceType<typeof ProgressmanagementWbsDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const progressmanagementWbsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('ProgressmanagementWbs Management Detail Component', () => {
    let progressmanagementWbsServiceStub: SinonStubbedInstance<ProgressmanagementWbsService>;
    let mountOptions: MountingOptions<ProgressmanagementWbsDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      progressmanagementWbsServiceStub = sinon.createStubInstance<ProgressmanagementWbsService>(ProgressmanagementWbsService);

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
          progressmanagementWbsService: () => progressmanagementWbsServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        progressmanagementWbsServiceStub.find.resolves(progressmanagementWbsSample);
        route = {
          params: {
            progressmanagementWbsId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(ProgressmanagementWbsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.progressmanagementWbs).toMatchObject(progressmanagementWbsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        progressmanagementWbsServiceStub.find.resolves(progressmanagementWbsSample);
        const wrapper = shallowMount(ProgressmanagementWbsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
