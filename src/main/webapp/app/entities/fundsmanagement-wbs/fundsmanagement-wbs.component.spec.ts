/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import FundsmanagementWbs from './fundsmanagement-wbs.vue';
import FundsmanagementWbsService from './fundsmanagement-wbs.service';
import AlertService from '@/shared/alert/alert.service';

type FundsmanagementWbsComponentType = InstanceType<typeof FundsmanagementWbs>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('FundsmanagementWbs Management Component', () => {
    let fundsmanagementWbsServiceStub: SinonStubbedInstance<FundsmanagementWbsService>;
    let mountOptions: MountingOptions<FundsmanagementWbsComponentType>['global'];

    beforeEach(() => {
      fundsmanagementWbsServiceStub = sinon.createStubInstance<FundsmanagementWbsService>(FundsmanagementWbsService);
      fundsmanagementWbsServiceStub.retrieve.resolves({ headers: {} });

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
          fundsmanagementWbsService: () => fundsmanagementWbsServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        fundsmanagementWbsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(FundsmanagementWbs, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(fundsmanagementWbsServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.fundsmanagementWbs[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: FundsmanagementWbsComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(FundsmanagementWbs, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        fundsmanagementWbsServiceStub.retrieve.reset();
        fundsmanagementWbsServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        fundsmanagementWbsServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeFundsmanagementWbs();
        await comp.$nextTick(); // clear components

        // THEN
        expect(fundsmanagementWbsServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(fundsmanagementWbsServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
