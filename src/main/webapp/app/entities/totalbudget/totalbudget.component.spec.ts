/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Totalbudget from './totalbudget.vue';
import TotalbudgetService from './totalbudget.service';
import AlertService from '@/shared/alert/alert.service';

type TotalbudgetComponentType = InstanceType<typeof Totalbudget>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Totalbudget Management Component', () => {
    let totalbudgetServiceStub: SinonStubbedInstance<TotalbudgetService>;
    let mountOptions: MountingOptions<TotalbudgetComponentType>['global'];

    beforeEach(() => {
      totalbudgetServiceStub = sinon.createStubInstance<TotalbudgetService>(TotalbudgetService);
      totalbudgetServiceStub.retrieve.resolves({ headers: {} });

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
          totalbudgetService: () => totalbudgetServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        totalbudgetServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(Totalbudget, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(totalbudgetServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.totalbudgets[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: TotalbudgetComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Totalbudget, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        totalbudgetServiceStub.retrieve.reset();
        totalbudgetServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        totalbudgetServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeTotalbudget();
        await comp.$nextTick(); // clear components

        // THEN
        expect(totalbudgetServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(totalbudgetServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
