/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProjectchargeDetails from './projectcharge-details.vue';
import ProjectchargeService from './projectcharge.service';
import AlertService from '@/shared/alert/alert.service';

type ProjectchargeDetailsComponentType = InstanceType<typeof ProjectchargeDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const projectchargeSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Projectcharge Management Detail Component', () => {
    let projectchargeServiceStub: SinonStubbedInstance<ProjectchargeService>;
    let mountOptions: MountingOptions<ProjectchargeDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      projectchargeServiceStub = sinon.createStubInstance<ProjectchargeService>(ProjectchargeService);

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
          projectchargeService: () => projectchargeServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        projectchargeServiceStub.find.resolves(projectchargeSample);
        route = {
          params: {
            projectchargeId: '' + 123,
          },
        };
        const wrapper = shallowMount(ProjectchargeDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.projectcharge).toMatchObject(projectchargeSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        projectchargeServiceStub.find.resolves(projectchargeSample);
        const wrapper = shallowMount(ProjectchargeDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
