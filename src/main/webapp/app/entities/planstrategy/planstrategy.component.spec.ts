/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Planstrategy from './planstrategy.vue';
import PlanstrategyService from './planstrategy.service';
import AlertService from '@/shared/alert/alert.service';

type PlanstrategyComponentType = InstanceType<typeof Planstrategy>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Planstrategy Management Component', () => {
    let planstrategyServiceStub: SinonStubbedInstance<PlanstrategyService>;
    let mountOptions: MountingOptions<PlanstrategyComponentType>['global'];

    beforeEach(() => {
      planstrategyServiceStub = sinon.createStubInstance<PlanstrategyService>(PlanstrategyService);
      planstrategyServiceStub.retrieve.resolves({ headers: {} });

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
          planstrategyService: () => planstrategyServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        planstrategyServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(Planstrategy, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(planstrategyServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.planstrategies[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: PlanstrategyComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Planstrategy, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        planstrategyServiceStub.retrieve.reset();
        planstrategyServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        planstrategyServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removePlanstrategy();
        await comp.$nextTick(); // clear components

        // THEN
        expect(planstrategyServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(planstrategyServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
