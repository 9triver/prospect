/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import LedgerUpdate from './ledger-update.vue';
import LedgerService from './ledger.service';
import AlertService from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';

type LedgerUpdateComponentType = InstanceType<typeof LedgerUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const ledgerSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<LedgerUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Ledger Management Update Component', () => {
    let comp: LedgerUpdateComponentType;
    let ledgerServiceStub: SinonStubbedInstance<LedgerService>;

    beforeEach(() => {
      route = {};
      ledgerServiceStub = sinon.createStubInstance<LedgerService>(LedgerService);
      ledgerServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          'font-awesome-icon': true,
          'b-input-group': true,
          'b-input-group-prepend': true,
          'b-form-datepicker': true,
          'b-form-input': true,
        },
        provide: {
          alertService,
          ledgerService: () => ledgerServiceStub,
          officersService: () =>
            sinon.createStubInstance<OfficersService>(OfficersService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(LedgerUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.ledger = ledgerSample;
        ledgerServiceStub.update.resolves(ledgerSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(ledgerServiceStub.update.calledWith(ledgerSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        ledgerServiceStub.create.resolves(entity);
        const wrapper = shallowMount(LedgerUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.ledger = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(ledgerServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        ledgerServiceStub.find.resolves(ledgerSample);
        ledgerServiceStub.retrieve.resolves([ledgerSample]);

        // WHEN
        route = {
          params: {
            ledgerId: '' + ledgerSample.id,
          },
        };
        const wrapper = shallowMount(LedgerUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.ledger).toMatchObject(ledgerSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        ledgerServiceStub.find.resolves(ledgerSample);
        const wrapper = shallowMount(LedgerUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
