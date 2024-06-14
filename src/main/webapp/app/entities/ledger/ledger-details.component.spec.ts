/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import LedgerDetails from './ledger-details.vue';
import LedgerService from './ledger.service';
import AlertService from '@/shared/alert/alert.service';

type LedgerDetailsComponentType = InstanceType<typeof LedgerDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const ledgerSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Ledger Management Detail Component', () => {
    let ledgerServiceStub: SinonStubbedInstance<LedgerService>;
    let mountOptions: MountingOptions<LedgerDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      ledgerServiceStub = sinon.createStubInstance<LedgerService>(LedgerService);

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
          ledgerService: () => ledgerServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        ledgerServiceStub.find.resolves(ledgerSample);
        route = {
          params: {
            ledgerId: '' + 123,
          },
        };
        const wrapper = shallowMount(LedgerDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.ledger).toMatchObject(ledgerSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        ledgerServiceStub.find.resolves(ledgerSample);
        const wrapper = shallowMount(LedgerDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
