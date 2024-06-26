/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import AuditedbudgetDetails from './auditedbudget-details.vue';
import AuditedbudgetService from './auditedbudget.service';
import AlertService from '@/shared/alert/alert.service';

type AuditedbudgetDetailsComponentType = InstanceType<typeof AuditedbudgetDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const auditedbudgetSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Auditedbudget Management Detail Component', () => {
    let auditedbudgetServiceStub: SinonStubbedInstance<AuditedbudgetService>;
    let mountOptions: MountingOptions<AuditedbudgetDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      auditedbudgetServiceStub = sinon.createStubInstance<AuditedbudgetService>(AuditedbudgetService);

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
          auditedbudgetService: () => auditedbudgetServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        auditedbudgetServiceStub.find.resolves(auditedbudgetSample);
        route = {
          params: {
            auditedbudgetId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(AuditedbudgetDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.auditedbudget).toMatchObject(auditedbudgetSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        auditedbudgetServiceStub.find.resolves(auditedbudgetSample);
        const wrapper = shallowMount(AuditedbudgetDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
