/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ApprovalAgentUpdate from './approval-agent-update.vue';
import ApprovalAgentService from './approval-agent.service';
import AlertService from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';

type ApprovalAgentUpdateComponentType = InstanceType<typeof ApprovalAgentUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const approvalAgentSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ApprovalAgentUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('ApprovalAgent Management Update Component', () => {
    let comp: ApprovalAgentUpdateComponentType;
    let approvalAgentServiceStub: SinonStubbedInstance<ApprovalAgentService>;

    beforeEach(() => {
      route = {};
      approvalAgentServiceStub = sinon.createStubInstance<ApprovalAgentService>(ApprovalAgentService);
      approvalAgentServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          approvalAgentService: () => approvalAgentServiceStub,
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
        const wrapper = shallowMount(ApprovalAgentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.approvalAgent = approvalAgentSample;
        approvalAgentServiceStub.update.resolves(approvalAgentSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(approvalAgentServiceStub.update.calledWith(approvalAgentSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        approvalAgentServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ApprovalAgentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.approvalAgent = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(approvalAgentServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        approvalAgentServiceStub.find.resolves(approvalAgentSample);
        approvalAgentServiceStub.retrieve.resolves([approvalAgentSample]);

        // WHEN
        route = {
          params: {
            approvalAgentId: '' + approvalAgentSample.id,
          },
        };
        const wrapper = shallowMount(ApprovalAgentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.approvalAgent).toMatchObject(approvalAgentSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        approvalAgentServiceStub.find.resolves(approvalAgentSample);
        const wrapper = shallowMount(ApprovalAgentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
