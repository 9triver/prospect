/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Unitbudget from './unitbudget.vue';
import UnitbudgetService from './unitbudget.service';
import AlertService from '@/shared/alert/alert.service';

type UnitbudgetComponentType = InstanceType<typeof Unitbudget>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Unitbudget Management Component', () => {
    let unitbudgetServiceStub: SinonStubbedInstance<UnitbudgetService>;
    let mountOptions: MountingOptions<UnitbudgetComponentType>['global'];

    beforeEach(() => {
      unitbudgetServiceStub = sinon.createStubInstance<UnitbudgetService>(UnitbudgetService);
      unitbudgetServiceStub.retrieve.resolves({ headers: {} });

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
          unitbudgetService: () => unitbudgetServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        unitbudgetServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(Unitbudget, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(unitbudgetServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.unitbudgets[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: UnitbudgetComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Unitbudget, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        unitbudgetServiceStub.retrieve.reset();
        unitbudgetServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        unitbudgetServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeUnitbudget();
        await comp.$nextTick(); // clear components

        // THEN
        expect(unitbudgetServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(unitbudgetServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
