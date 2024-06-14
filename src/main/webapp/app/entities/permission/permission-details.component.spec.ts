/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import PermissionDetails from './permission-details.vue';
import PermissionService from './permission.service';
import AlertService from '@/shared/alert/alert.service';

type PermissionDetailsComponentType = InstanceType<typeof PermissionDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const permissionSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Permission Management Detail Component', () => {
    let permissionServiceStub: SinonStubbedInstance<PermissionService>;
    let mountOptions: MountingOptions<PermissionDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      permissionServiceStub = sinon.createStubInstance<PermissionService>(PermissionService);

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
          permissionService: () => permissionServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        permissionServiceStub.find.resolves(permissionSample);
        route = {
          params: {
            permissionId: '' + 123,
          },
        };
        const wrapper = shallowMount(PermissionDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.permission).toMatchObject(permissionSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        permissionServiceStub.find.resolves(permissionSample);
        const wrapper = shallowMount(PermissionDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
