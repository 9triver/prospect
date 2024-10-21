/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import PlanReturns from './plan-returns.vue';
import PlanReturnsService from './plan-returns.service';
import AlertService from '@/shared/alert/alert.service';

type PlanReturnsComponentType = InstanceType<typeof PlanReturns>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('PlanReturns Management Component', () => {
    let planReturnsServiceStub: SinonStubbedInstance<PlanReturnsService>;
    let mountOptions: MountingOptions<PlanReturnsComponentType>['global'];

    beforeEach(() => {
      planReturnsServiceStub = sinon.createStubInstance<PlanReturnsService>(PlanReturnsService);
      planReturnsServiceStub.retrieve.resolves({ headers: {} });

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
          planReturnsService: () => planReturnsServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        planReturnsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(PlanReturns, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(planReturnsServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.planReturns[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: PlanReturnsComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(PlanReturns, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        planReturnsServiceStub.retrieve.reset();
        planReturnsServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        planReturnsServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removePlanReturns();
        await comp.$nextTick(); // clear components

        // THEN
        expect(planReturnsServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(planReturnsServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
