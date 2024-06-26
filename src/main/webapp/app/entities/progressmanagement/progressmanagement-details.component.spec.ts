/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProgressmanagementDetails from './progressmanagement-details.vue';
import ProgressmanagementService from './progressmanagement.service';
import AlertService from '@/shared/alert/alert.service';

type ProgressmanagementDetailsComponentType = InstanceType<typeof ProgressmanagementDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const progressmanagementSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Progressmanagement Management Detail Component', () => {
    let progressmanagementServiceStub: SinonStubbedInstance<ProgressmanagementService>;
    let mountOptions: MountingOptions<ProgressmanagementDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      progressmanagementServiceStub = sinon.createStubInstance<ProgressmanagementService>(ProgressmanagementService);

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
          progressmanagementService: () => progressmanagementServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        progressmanagementServiceStub.find.resolves(progressmanagementSample);
        route = {
          params: {
            progressmanagementId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(ProgressmanagementDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.progressmanagement).toMatchObject(progressmanagementSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        progressmanagementServiceStub.find.resolves(progressmanagementSample);
        const wrapper = shallowMount(ProgressmanagementDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
