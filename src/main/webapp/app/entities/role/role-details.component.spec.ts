/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import RoleDetails from './role-details.vue';
import RoleService from './role.service';
import AlertService from '@/shared/alert/alert.service';

type RoleDetailsComponentType = InstanceType<typeof RoleDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const roleSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Role Management Detail Component', () => {
    let roleServiceStub: SinonStubbedInstance<RoleService>;
    let mountOptions: MountingOptions<RoleDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      roleServiceStub = sinon.createStubInstance<RoleService>(RoleService);

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
          roleService: () => roleServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        roleServiceStub.find.resolves(roleSample);
        route = {
          params: {
            roleId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(RoleDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.role).toMatchObject(roleSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        roleServiceStub.find.resolves(roleSample);
        const wrapper = shallowMount(RoleDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
