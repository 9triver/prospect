/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProjectTotalwbsDetails from './projecttotalwbs-details.vue';
import ProjectTotalwbsService from './projecttotalwbs.service';
import AlertService from '@/shared/alert/alert.service';

type ProjectTotalwbsDetailsComponentType = InstanceType<typeof ProjectTotalwbsDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const projectTotalwbsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('ProjectTotalwbs Management Detail Component', () => {
    let projectTotalwbsServiceStub: SinonStubbedInstance<ProjectTotalwbsService>;
    let mountOptions: MountingOptions<ProjectTotalwbsDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      projectTotalwbsServiceStub = sinon.createStubInstance<ProjectTotalwbsService>(ProjectTotalwbsService);

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
          projectTotalwbsService: () => projectTotalwbsServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        projectTotalwbsServiceStub.find.resolves(projectTotalwbsSample);
        route = {
          params: {
            projectTotalwbsId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(ProjectTotalwbsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.projectTotalwbs).toMatchObject(projectTotalwbsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        projectTotalwbsServiceStub.find.resolves(projectTotalwbsSample);
        const wrapper = shallowMount(ProjectTotalwbsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
