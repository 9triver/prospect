/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Planmonitor from './planmonitor.vue';
import PlanmonitorService from './planmonitor.service';
import AlertService from '@/shared/alert/alert.service';

type PlanmonitorComponentType = InstanceType<typeof Planmonitor>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Planmonitor Management Component', () => {
    let planmonitorServiceStub: SinonStubbedInstance<PlanmonitorService>;
    let mountOptions: MountingOptions<PlanmonitorComponentType>['global'];

    beforeEach(() => {
      planmonitorServiceStub = sinon.createStubInstance<PlanmonitorService>(PlanmonitorService);
      planmonitorServiceStub.retrieve.resolves({ headers: {} });

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
          planmonitorService: () => planmonitorServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        planmonitorServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(Planmonitor, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(planmonitorServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.planmonitors[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: PlanmonitorComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Planmonitor, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        planmonitorServiceStub.retrieve.reset();
        planmonitorServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        planmonitorServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removePlanmonitor();
        await comp.$nextTick(); // clear components

        // THEN
        expect(planmonitorServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(planmonitorServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
