/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import FundsmanagementWbsUpdate from './fundsmanagement-wbs-update.vue';
import FundsmanagementWbsService from './fundsmanagement-wbs.service';
import AlertService from '@/shared/alert/alert.service';

import AuditedbudgetService from '@/entities/auditedbudget/auditedbudget.service';
import TotalbudgetService from '@/entities/totalbudget/totalbudget.service';
import UnitbudgetService from '@/entities/unitbudget/unitbudget.service';
import FundsavailabilityService from '@/entities/fundsavailability/fundsavailability.service';
import ContractualfundsService from '@/entities/contractualfunds/contractualfunds.service';

type FundsmanagementWbsUpdateComponentType = InstanceType<typeof FundsmanagementWbsUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const fundsmanagementWbsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<FundsmanagementWbsUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('FundsmanagementWbs Management Update Component', () => {
    let comp: FundsmanagementWbsUpdateComponentType;
    let fundsmanagementWbsServiceStub: SinonStubbedInstance<FundsmanagementWbsService>;

    beforeEach(() => {
      route = {};
      fundsmanagementWbsServiceStub = sinon.createStubInstance<FundsmanagementWbsService>(FundsmanagementWbsService);
      fundsmanagementWbsServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          fundsmanagementWbsService: () => fundsmanagementWbsServiceStub,
          auditedbudgetService: () =>
            sinon.createStubInstance<AuditedbudgetService>(AuditedbudgetService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          totalbudgetService: () =>
            sinon.createStubInstance<TotalbudgetService>(TotalbudgetService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          unitbudgetService: () =>
            sinon.createStubInstance<UnitbudgetService>(UnitbudgetService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          fundsavailabilityService: () =>
            sinon.createStubInstance<FundsavailabilityService>(FundsavailabilityService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          contractualfundsService: () =>
            sinon.createStubInstance<ContractualfundsService>(ContractualfundsService, {
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
        const wrapper = shallowMount(FundsmanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.fundsmanagementWbs = fundsmanagementWbsSample;
        fundsmanagementWbsServiceStub.update.resolves(fundsmanagementWbsSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(fundsmanagementWbsServiceStub.update.calledWith(fundsmanagementWbsSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        fundsmanagementWbsServiceStub.create.resolves(entity);
        const wrapper = shallowMount(FundsmanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.fundsmanagementWbs = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(fundsmanagementWbsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        fundsmanagementWbsServiceStub.find.resolves(fundsmanagementWbsSample);
        fundsmanagementWbsServiceStub.retrieve.resolves([fundsmanagementWbsSample]);

        // WHEN
        route = {
          params: {
            fundsmanagementWbsId: '' + fundsmanagementWbsSample.id,
          },
        };
        const wrapper = shallowMount(FundsmanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.fundsmanagementWbs).toMatchObject(fundsmanagementWbsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        fundsmanagementWbsServiceStub.find.resolves(fundsmanagementWbsSample);
        const wrapper = shallowMount(FundsmanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
