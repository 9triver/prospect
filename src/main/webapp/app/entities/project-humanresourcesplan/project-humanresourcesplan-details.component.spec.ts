/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProjectHumanresourcesplanDetails from './project-humanresourcesplan-details.vue';
import ProjectHumanresourcesplanService from './project-humanresourcesplan.service';
import AlertService from '@/shared/alert/alert.service';

type ProjectHumanresourcesplanDetailsComponentType = InstanceType<typeof ProjectHumanresourcesplanDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const projectHumanresourcesplanSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('ProjectHumanresourcesplan Management Detail Component', () => {
    let projectHumanresourcesplanServiceStub: SinonStubbedInstance<ProjectHumanresourcesplanService>;
    let mountOptions: MountingOptions<ProjectHumanresourcesplanDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      projectHumanresourcesplanServiceStub = sinon.createStubInstance<ProjectHumanresourcesplanService>(ProjectHumanresourcesplanService);

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
          projectHumanresourcesplanService: () => projectHumanresourcesplanServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        projectHumanresourcesplanServiceStub.find.resolves(projectHumanresourcesplanSample);
        route = {
          params: {
            projectHumanresourcesplanId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(ProjectHumanresourcesplanDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.projectHumanresourcesplan).toMatchObject(projectHumanresourcesplanSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        projectHumanresourcesplanServiceStub.find.resolves(projectHumanresourcesplanSample);
        const wrapper = shallowMount(ProjectHumanresourcesplanDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
