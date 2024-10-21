/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Deliverables from './deliverables.vue';
import DeliverablesService from './deliverables.service';
import AlertService from '@/shared/alert/alert.service';

type DeliverablesComponentType = InstanceType<typeof Deliverables>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Deliverables Management Component', () => {
    let deliverablesServiceStub: SinonStubbedInstance<DeliverablesService>;
    let mountOptions: MountingOptions<DeliverablesComponentType>['global'];

    beforeEach(() => {
      deliverablesServiceStub = sinon.createStubInstance<DeliverablesService>(DeliverablesService);
      deliverablesServiceStub.retrieve.resolves({ headers: {} });

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
          deliverablesService: () => deliverablesServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        deliverablesServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Deliverables, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(deliverablesServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.deliverables[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: DeliverablesComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Deliverables, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        deliverablesServiceStub.retrieve.reset();
        deliverablesServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        deliverablesServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeDeliverables();
        await comp.$nextTick(); // clear components

        // THEN
        expect(deliverablesServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(deliverablesServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
