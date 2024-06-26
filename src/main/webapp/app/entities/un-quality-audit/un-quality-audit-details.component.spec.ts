/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import UnQualityAuditDetails from './un-quality-audit-details.vue';
import UnQualityAuditService from './un-quality-audit.service';
import AlertService from '@/shared/alert/alert.service';

type UnQualityAuditDetailsComponentType = InstanceType<typeof UnQualityAuditDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const unQualityAuditSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('UnQualityAudit Management Detail Component', () => {
    let unQualityAuditServiceStub: SinonStubbedInstance<UnQualityAuditService>;
    let mountOptions: MountingOptions<UnQualityAuditDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      unQualityAuditServiceStub = sinon.createStubInstance<UnQualityAuditService>(UnQualityAuditService);

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
          unQualityAuditService: () => unQualityAuditServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        unQualityAuditServiceStub.find.resolves(unQualityAuditSample);
        route = {
          params: {
            unQualityAuditId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(UnQualityAuditDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.unQualityAudit).toMatchObject(unQualityAuditSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        unQualityAuditServiceStub.find.resolves(unQualityAuditSample);
        const wrapper = shallowMount(UnQualityAuditDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
