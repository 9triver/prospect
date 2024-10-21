/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProjectwbsDetails from './projectwbs-details.vue';
import ProjectwbsService from './projectwbs.service';
import AlertService from '@/shared/alert/alert.service';

type ProjectwbsDetailsComponentType = InstanceType<typeof ProjectwbsDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const projectwbsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Projectwbs Management Detail Component', () => {
    let projectwbsServiceStub: SinonStubbedInstance<ProjectwbsService>;
    let mountOptions: MountingOptions<ProjectwbsDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      projectwbsServiceStub = sinon.createStubInstance<ProjectwbsService>(ProjectwbsService);

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
          projectwbsService: () => projectwbsServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        projectwbsServiceStub.find.resolves(projectwbsSample);
        route = {
          params: {
            projectwbsId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(ProjectwbsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.projectwbs).toMatchObject(projectwbsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        projectwbsServiceStub.find.resolves(projectwbsSample);
        const wrapper = shallowMount(ProjectwbsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
