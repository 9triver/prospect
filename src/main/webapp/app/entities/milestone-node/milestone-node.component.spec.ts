/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import MilestoneNode from './milestone-node.vue';
import MilestoneNodeService from './milestone-node.service';
import AlertService from '@/shared/alert/alert.service';

type MilestoneNodeComponentType = InstanceType<typeof MilestoneNode>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('MilestoneNode Management Component', () => {
    let milestoneNodeServiceStub: SinonStubbedInstance<MilestoneNodeService>;
    let mountOptions: MountingOptions<MilestoneNodeComponentType>['global'];

    beforeEach(() => {
      milestoneNodeServiceStub = sinon.createStubInstance<MilestoneNodeService>(MilestoneNodeService);
      milestoneNodeServiceStub.retrieve.resolves({ headers: {} });

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          bModal: bModalStub as any,
          'font-awesome-icon': true,
          'b-badge': true,
          'b-button': true,
          'router-link': true,
        },
        directives: {
          'b-modal': {},
        },
        provide: {
          alertService,
          milestoneNodeService: () => milestoneNodeServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        milestoneNodeServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(MilestoneNode, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(milestoneNodeServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.milestoneNodes[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: MilestoneNodeComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(MilestoneNode, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        milestoneNodeServiceStub.retrieve.reset();
        milestoneNodeServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        milestoneNodeServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeMilestoneNode();
        await comp.$nextTick(); // clear components

        // THEN
        expect(milestoneNodeServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(milestoneNodeServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
