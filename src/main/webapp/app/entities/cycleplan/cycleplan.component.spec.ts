/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Cycleplan from './cycleplan.vue';
import CycleplanService from './cycleplan.service';
import AlertService from '@/shared/alert/alert.service';

type CycleplanComponentType = InstanceType<typeof Cycleplan>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Cycleplan Management Component', () => {
    let cycleplanServiceStub: SinonStubbedInstance<CycleplanService>;
    let mountOptions: MountingOptions<CycleplanComponentType>['global'];

    beforeEach(() => {
      cycleplanServiceStub = sinon.createStubInstance<CycleplanService>(CycleplanService);
      cycleplanServiceStub.retrieve.resolves({ headers: {} });

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
          cycleplanService: () => cycleplanServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        cycleplanServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(Cycleplan, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(cycleplanServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.cycleplans[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: CycleplanComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Cycleplan, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        cycleplanServiceStub.retrieve.reset();
        cycleplanServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        cycleplanServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeCycleplan();
        await comp.$nextTick(); // clear components

        // THEN
        expect(cycleplanServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(cycleplanServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
