/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProjectRiskDetails from './project-risk-details.vue';
import ProjectRiskService from './project-risk.service';
import AlertService from '@/shared/alert/alert.service';

type ProjectRiskDetailsComponentType = InstanceType<typeof ProjectRiskDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const projectRiskSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('ProjectRisk Management Detail Component', () => {
    let projectRiskServiceStub: SinonStubbedInstance<ProjectRiskService>;
    let mountOptions: MountingOptions<ProjectRiskDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      projectRiskServiceStub = sinon.createStubInstance<ProjectRiskService>(ProjectRiskService);

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
          projectRiskService: () => projectRiskServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        projectRiskServiceStub.find.resolves(projectRiskSample);
        route = {
          params: {
            projectRiskId: '' + 123,
          },
        };
        const wrapper = shallowMount(ProjectRiskDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.projectRisk).toMatchObject(projectRiskSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        projectRiskServiceStub.find.resolves(projectRiskSample);
        const wrapper = shallowMount(ProjectRiskDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
