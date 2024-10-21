/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProjectremitDetails from './projectremit-details.vue';
import ProjectremitService from './projectremit.service';
import AlertService from '@/shared/alert/alert.service';

type ProjectremitDetailsComponentType = InstanceType<typeof ProjectremitDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const projectremitSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Projectremit Management Detail Component', () => {
    let projectremitServiceStub: SinonStubbedInstance<ProjectremitService>;
    let mountOptions: MountingOptions<ProjectremitDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      projectremitServiceStub = sinon.createStubInstance<ProjectremitService>(ProjectremitService);

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
          projectremitService: () => projectremitServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        projectremitServiceStub.find.resolves(projectremitSample);
        route = {
          params: {
            projectremitId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(ProjectremitDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.projectremit).toMatchObject(projectremitSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        projectremitServiceStub.find.resolves(projectremitSample);
        const wrapper = shallowMount(ProjectremitDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
