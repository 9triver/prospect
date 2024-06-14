/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProjectDetails from './project-details.vue';
import ProjectService from './project.service';
import AlertService from '@/shared/alert/alert.service';

type ProjectDetailsComponentType = InstanceType<typeof ProjectDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const projectSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Project Management Detail Component', () => {
    let projectServiceStub: SinonStubbedInstance<ProjectService>;
    let mountOptions: MountingOptions<ProjectDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      projectServiceStub = sinon.createStubInstance<ProjectService>(ProjectService);

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
          projectService: () => projectServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        projectServiceStub.find.resolves(projectSample);
        route = {
          params: {
            projectId: '' + 123,
          },
        };
        const wrapper = shallowMount(ProjectDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.project).toMatchObject(projectSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        projectServiceStub.find.resolves(projectSample);
        const wrapper = shallowMount(ProjectDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
