/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import MilestoneNodeUpdate from './milestone-node-update.vue';
import MilestoneNodeService from './milestone-node.service';
import AlertService from '@/shared/alert/alert.service';

import OutsourcingContractService from '@/entities/outsourcing-contract/outsourcing-contract.service';

type MilestoneNodeUpdateComponentType = InstanceType<typeof MilestoneNodeUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const milestoneNodeSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<MilestoneNodeUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('MilestoneNode Management Update Component', () => {
    let comp: MilestoneNodeUpdateComponentType;
    let milestoneNodeServiceStub: SinonStubbedInstance<MilestoneNodeService>;

    beforeEach(() => {
      route = {};
      milestoneNodeServiceStub = sinon.createStubInstance<MilestoneNodeService>(MilestoneNodeService);
      milestoneNodeServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          milestoneNodeService: () => milestoneNodeServiceStub,
          outsourcingContractService: () =>
            sinon.createStubInstance<OutsourcingContractService>(OutsourcingContractService, {
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
        const wrapper = shallowMount(MilestoneNodeUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.milestoneNode = milestoneNodeSample;
        milestoneNodeServiceStub.update.resolves(milestoneNodeSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(milestoneNodeServiceStub.update.calledWith(milestoneNodeSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        milestoneNodeServiceStub.create.resolves(entity);
        const wrapper = shallowMount(MilestoneNodeUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.milestoneNode = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(milestoneNodeServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        milestoneNodeServiceStub.find.resolves(milestoneNodeSample);
        milestoneNodeServiceStub.retrieve.resolves([milestoneNodeSample]);

        // WHEN
        route = {
          params: {
            milestoneNodeId: '' + milestoneNodeSample.id,
          },
        };
        const wrapper = shallowMount(MilestoneNodeUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.milestoneNode).toMatchObject(milestoneNodeSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        milestoneNodeServiceStub.find.resolves(milestoneNodeSample);
        const wrapper = shallowMount(MilestoneNodeUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
