/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProjectdeliverablesDetails from './projectdeliverables-details.vue';
import ProjectdeliverablesService from './projectdeliverables.service';
import AlertService from '@/shared/alert/alert.service';

type ProjectdeliverablesDetailsComponentType = InstanceType<typeof ProjectdeliverablesDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const projectdeliverablesSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Projectdeliverables Management Detail Component', () => {
    let projectdeliverablesServiceStub: SinonStubbedInstance<ProjectdeliverablesService>;
    let mountOptions: MountingOptions<ProjectdeliverablesDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      projectdeliverablesServiceStub = sinon.createStubInstance<ProjectdeliverablesService>(ProjectdeliverablesService);

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
          projectdeliverablesService: () => projectdeliverablesServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        projectdeliverablesServiceStub.find.resolves(projectdeliverablesSample);
        route = {
          params: {
            projectdeliverablesId: '' + 123,
          },
        };
        const wrapper = shallowMount(ProjectdeliverablesDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.projectdeliverables).toMatchObject(projectdeliverablesSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        projectdeliverablesServiceStub.find.resolves(projectdeliverablesSample);
        const wrapper = shallowMount(ProjectdeliverablesDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
