/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Monthplan from './monthplan.vue';
import MonthplanService from './monthplan.service';
import AlertService from '@/shared/alert/alert.service';

type MonthplanComponentType = InstanceType<typeof Monthplan>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Monthplan Management Component', () => {
    let monthplanServiceStub: SinonStubbedInstance<MonthplanService>;
    let mountOptions: MountingOptions<MonthplanComponentType>['global'];

    beforeEach(() => {
      monthplanServiceStub = sinon.createStubInstance<MonthplanService>(MonthplanService);
      monthplanServiceStub.retrieve.resolves({ headers: {} });

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
          monthplanService: () => monthplanServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        monthplanServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(Monthplan, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(monthplanServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.monthplans[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: MonthplanComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Monthplan, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        monthplanServiceStub.retrieve.reset();
        monthplanServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        monthplanServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeMonthplan();
        await comp.$nextTick(); // clear components

        // THEN
        expect(monthplanServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(monthplanServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
