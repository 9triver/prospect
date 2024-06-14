/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProjectSecrecyDetails from './project-secrecy-details.vue';
import ProjectSecrecyService from './project-secrecy.service';
import AlertService from '@/shared/alert/alert.service';

type ProjectSecrecyDetailsComponentType = InstanceType<typeof ProjectSecrecyDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const projectSecrecySample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('ProjectSecrecy Management Detail Component', () => {
    let projectSecrecyServiceStub: SinonStubbedInstance<ProjectSecrecyService>;
    let mountOptions: MountingOptions<ProjectSecrecyDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      projectSecrecyServiceStub = sinon.createStubInstance<ProjectSecrecyService>(ProjectSecrecyService);

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
          projectSecrecyService: () => projectSecrecyServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        projectSecrecyServiceStub.find.resolves(projectSecrecySample);
        route = {
          params: {
            projectSecrecyId: '' + 123,
          },
        };
        const wrapper = shallowMount(ProjectSecrecyDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.projectSecrecy).toMatchObject(projectSecrecySample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        projectSecrecyServiceStub.find.resolves(projectSecrecySample);
        const wrapper = shallowMount(ProjectSecrecyDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
