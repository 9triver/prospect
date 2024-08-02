/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import CostControlSystem from './cost-control-system.vue';
import CostControlSystemService from './cost-control-system.service';
import AlertService from '@/shared/alert/alert.service';

type CostControlSystemComponentType = InstanceType<typeof CostControlSystem>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('CostControlSystem Management Component', () => {
    let costControlSystemServiceStub: SinonStubbedInstance<CostControlSystemService>;
    let mountOptions: MountingOptions<CostControlSystemComponentType>['global'];

    beforeEach(() => {
      costControlSystemServiceStub = sinon.createStubInstance<CostControlSystemService>(CostControlSystemService);
      costControlSystemServiceStub.retrieve.resolves({ headers: {} });

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
          costControlSystemService: () => costControlSystemServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        costControlSystemServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(CostControlSystem, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(costControlSystemServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.costControlSystems[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: CostControlSystemComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(CostControlSystem, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        costControlSystemServiceStub.retrieve.reset();
        costControlSystemServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        costControlSystemServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeCostControlSystem();
        await comp.$nextTick(); // clear components

        // THEN
        expect(costControlSystemServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(costControlSystemServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
