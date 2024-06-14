/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Planexecute from './planexecute.vue';
import PlanexecuteService from './planexecute.service';
import AlertService from '@/shared/alert/alert.service';

type PlanexecuteComponentType = InstanceType<typeof Planexecute>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Planexecute Management Component', () => {
    let planexecuteServiceStub: SinonStubbedInstance<PlanexecuteService>;
    let mountOptions: MountingOptions<PlanexecuteComponentType>['global'];

    beforeEach(() => {
      planexecuteServiceStub = sinon.createStubInstance<PlanexecuteService>(PlanexecuteService);
      planexecuteServiceStub.retrieve.resolves({ headers: {} });

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
          planexecuteService: () => planexecuteServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        planexecuteServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Planexecute, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(planexecuteServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.planexecutes[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: PlanexecuteComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Planexecute, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        planexecuteServiceStub.retrieve.reset();
        planexecuteServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        planexecuteServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removePlanexecute();
        await comp.$nextTick(); // clear components

        // THEN
        expect(planexecuteServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(planexecuteServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
