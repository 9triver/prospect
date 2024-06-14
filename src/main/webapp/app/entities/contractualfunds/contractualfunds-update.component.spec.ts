/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ContractualfundsUpdate from './contractualfunds-update.vue';
import ContractualfundsService from './contractualfunds.service';
import AlertService from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';

type ContractualfundsUpdateComponentType = InstanceType<typeof ContractualfundsUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const contractualfundsSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ContractualfundsUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Contractualfunds Management Update Component', () => {
    let comp: ContractualfundsUpdateComponentType;
    let contractualfundsServiceStub: SinonStubbedInstance<ContractualfundsService>;

    beforeEach(() => {
      route = {};
      contractualfundsServiceStub = sinon.createStubInstance<ContractualfundsService>(ContractualfundsService);
      contractualfundsServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          contractualfundsService: () => contractualfundsServiceStub,
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
        const wrapper = shallowMount(ContractualfundsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.contractualfunds = contractualfundsSample;
        contractualfundsServiceStub.update.resolves(contractualfundsSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(contractualfundsServiceStub.update.calledWith(contractualfundsSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        contractualfundsServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ContractualfundsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.contractualfunds = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(contractualfundsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        contractualfundsServiceStub.find.resolves(contractualfundsSample);
        contractualfundsServiceStub.retrieve.resolves([contractualfundsSample]);

        // WHEN
        route = {
          params: {
            contractualfundsId: '' + contractualfundsSample.id,
          },
        };
        const wrapper = shallowMount(ContractualfundsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.contractualfunds).toMatchObject(contractualfundsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        contractualfundsServiceStub.find.resolves(contractualfundsSample);
        const wrapper = shallowMount(ContractualfundsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
