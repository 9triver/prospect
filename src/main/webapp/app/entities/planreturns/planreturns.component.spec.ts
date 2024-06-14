/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Planreturns from './planreturns.vue';
import PlanreturnsService from './planreturns.service';
import AlertService from '@/shared/alert/alert.service';

type PlanreturnsComponentType = InstanceType<typeof Planreturns>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Planreturns Management Component', () => {
    let planreturnsServiceStub: SinonStubbedInstance<PlanreturnsService>;
    let mountOptions: MountingOptions<PlanreturnsComponentType>['global'];

    beforeEach(() => {
      planreturnsServiceStub = sinon.createStubInstance<PlanreturnsService>(PlanreturnsService);
      planreturnsServiceStub.retrieve.resolves({ headers: {} });

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
          planreturnsService: () => planreturnsServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        planreturnsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Planreturns, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(planreturnsServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.planreturns[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: PlanreturnsComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Planreturns, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        planreturnsServiceStub.retrieve.reset();
        planreturnsServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        planreturnsServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removePlanreturns();
        await comp.$nextTick(); // clear components

        // THEN
        expect(planreturnsServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(planreturnsServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
