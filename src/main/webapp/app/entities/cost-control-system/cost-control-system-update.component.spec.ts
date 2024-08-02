/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import CostControlSystemUpdate from './cost-control-system-update.vue';
import CostControlSystemService from './cost-control-system.service';
import AlertService from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';
import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import ContractService from '@/entities/contract/contract.service';

type CostControlSystemUpdateComponentType = InstanceType<typeof CostControlSystemUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const costControlSystemSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<CostControlSystemUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('CostControlSystem Management Update Component', () => {
    let comp: CostControlSystemUpdateComponentType;
    let costControlSystemServiceStub: SinonStubbedInstance<CostControlSystemService>;

    beforeEach(() => {
      route = {};
      costControlSystemServiceStub = sinon.createStubInstance<CostControlSystemService>(CostControlSystemService);
      costControlSystemServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          costControlSystemService: () => costControlSystemServiceStub,
          officersService: () =>
            sinon.createStubInstance<OfficersService>(OfficersService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          projectwbsService: () =>
            sinon.createStubInstance<ProjectwbsService>(ProjectwbsService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          contractService: () =>
            sinon.createStubInstance<ContractService>(ContractService, {
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
        const wrapper = shallowMount(CostControlSystemUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.costControlSystem = costControlSystemSample;
        costControlSystemServiceStub.update.resolves(costControlSystemSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(costControlSystemServiceStub.update.calledWith(costControlSystemSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        costControlSystemServiceStub.create.resolves(entity);
        const wrapper = shallowMount(CostControlSystemUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.costControlSystem = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(costControlSystemServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        costControlSystemServiceStub.find.resolves(costControlSystemSample);
        costControlSystemServiceStub.retrieve.resolves([costControlSystemSample]);

        // WHEN
        route = {
          params: {
            costControlSystemId: '' + costControlSystemSample.id,
          },
        };
        const wrapper = shallowMount(CostControlSystemUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.costControlSystem).toMatchObject(costControlSystemSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        costControlSystemServiceStub.find.resolves(costControlSystemSample);
        const wrapper = shallowMount(CostControlSystemUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
