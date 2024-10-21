/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import WorkbagDetails from './workbag-details.vue';
import WorkbagService from './workbag.service';
import AlertService from '@/shared/alert/alert.service';

type WorkbagDetailsComponentType = InstanceType<typeof WorkbagDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const workbagSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Workbag Management Detail Component', () => {
    let workbagServiceStub: SinonStubbedInstance<WorkbagService>;
    let mountOptions: MountingOptions<WorkbagDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      workbagServiceStub = sinon.createStubInstance<WorkbagService>(WorkbagService);

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
          workbagService: () => workbagServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        workbagServiceStub.find.resolves(workbagSample);
        route = {
          params: {
            workbagId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(WorkbagDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.workbag).toMatchObject(workbagSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        workbagServiceStub.find.resolves(workbagSample);
        const wrapper = shallowMount(WorkbagDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
