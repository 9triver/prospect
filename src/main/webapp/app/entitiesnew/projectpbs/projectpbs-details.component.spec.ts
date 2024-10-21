/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProjectpbsDetails from './projectpbs-details.vue';
import ProjectpbsService from './projectpbs.service';
import AlertService from '@/shared/alert/alert.service';

type ProjectpbsDetailsComponentType = InstanceType<typeof ProjectpbsDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const projectpbsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Projectpbs Management Detail Component', () => {
    let projectpbsServiceStub: SinonStubbedInstance<ProjectpbsService>;
    let mountOptions: MountingOptions<ProjectpbsDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      projectpbsServiceStub = sinon.createStubInstance<ProjectpbsService>(ProjectpbsService);

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
          projectpbsService: () => projectpbsServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        projectpbsServiceStub.find.resolves(projectpbsSample);
        route = {
          params: {
            projectpbsId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(ProjectpbsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.projectpbs).toMatchObject(projectpbsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        projectpbsServiceStub.find.resolves(projectpbsSample);
        const wrapper = shallowMount(ProjectpbsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
