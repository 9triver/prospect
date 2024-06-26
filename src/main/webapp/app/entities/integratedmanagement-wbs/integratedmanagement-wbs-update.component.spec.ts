/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import IntegratedmanagementWbsUpdate from './integratedmanagement-wbs-update.vue';
import IntegratedmanagementWbsService from './integratedmanagement-wbs.service';
import AlertService from '@/shared/alert/alert.service';

import PlanstrategyService from '@/entities/planstrategy/planstrategy.service';
import ComprehensivecontrolService from '@/entities/comprehensivecontrol/comprehensivecontrol.service';
import DocumentService from '@/entities/document/document.service';
import ComprehensiveledgerService from '@/entities/comprehensiveledger/comprehensiveledger.service';
import CycleplanService from '@/entities/cycleplan/cycleplan.service';
import AnnualplanService from '@/entities/annualplan/annualplan.service';
import MonthplanService from '@/entities/monthplan/monthplan.service';
import PlanreturnsService from '@/entities/planreturns/planreturns.service';
import PlanmonitorService from '@/entities/planmonitor/planmonitor.service';
import PlanexecuteService from '@/entities/planexecute/planexecute.service';
import ProjectchargeService from '@/entities/projectcharge/projectcharge.service';

type IntegratedmanagementWbsUpdateComponentType = InstanceType<typeof IntegratedmanagementWbsUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const integratedmanagementWbsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<IntegratedmanagementWbsUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('IntegratedmanagementWbs Management Update Component', () => {
    let comp: IntegratedmanagementWbsUpdateComponentType;
    let integratedmanagementWbsServiceStub: SinonStubbedInstance<IntegratedmanagementWbsService>;

    beforeEach(() => {
      route = {};
      integratedmanagementWbsServiceStub = sinon.createStubInstance<IntegratedmanagementWbsService>(IntegratedmanagementWbsService);
      integratedmanagementWbsServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          integratedmanagementWbsService: () => integratedmanagementWbsServiceStub,
          planstrategyService: () =>
            sinon.createStubInstance<PlanstrategyService>(PlanstrategyService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          comprehensivecontrolService: () =>
            sinon.createStubInstance<ComprehensivecontrolService>(ComprehensivecontrolService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          documentService: () =>
            sinon.createStubInstance<DocumentService>(DocumentService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          comprehensiveledgerService: () =>
            sinon.createStubInstance<ComprehensiveledgerService>(ComprehensiveledgerService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          cycleplanService: () =>
            sinon.createStubInstance<CycleplanService>(CycleplanService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          annualplanService: () =>
            sinon.createStubInstance<AnnualplanService>(AnnualplanService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          monthplanService: () =>
            sinon.createStubInstance<MonthplanService>(MonthplanService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          planreturnsService: () =>
            sinon.createStubInstance<PlanreturnsService>(PlanreturnsService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          planmonitorService: () =>
            sinon.createStubInstance<PlanmonitorService>(PlanmonitorService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          planexecuteService: () =>
            sinon.createStubInstance<PlanexecuteService>(PlanexecuteService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          projectchargeService: () =>
            sinon.createStubInstance<ProjectchargeService>(ProjectchargeService, {
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
        const wrapper = shallowMount(IntegratedmanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.integratedmanagementWbs = integratedmanagementWbsSample;
        integratedmanagementWbsServiceStub.update.resolves(integratedmanagementWbsSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(integratedmanagementWbsServiceStub.update.calledWith(integratedmanagementWbsSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        integratedmanagementWbsServiceStub.create.resolves(entity);
        const wrapper = shallowMount(IntegratedmanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.integratedmanagementWbs = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(integratedmanagementWbsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        integratedmanagementWbsServiceStub.find.resolves(integratedmanagementWbsSample);
        integratedmanagementWbsServiceStub.retrieve.resolves([integratedmanagementWbsSample]);

        // WHEN
        route = {
          params: {
            integratedmanagementWbsId: '' + integratedmanagementWbsSample.id,
          },
        };
        const wrapper = shallowMount(IntegratedmanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.integratedmanagementWbs).toMatchObject(integratedmanagementWbsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        integratedmanagementWbsServiceStub.find.resolves(integratedmanagementWbsSample);
        const wrapper = shallowMount(IntegratedmanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
