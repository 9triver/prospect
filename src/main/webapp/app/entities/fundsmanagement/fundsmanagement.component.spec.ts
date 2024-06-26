/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Fundsmanagement from './fundsmanagement.vue';
import FundsmanagementService from './fundsmanagement.service';
import AlertService from '@/shared/alert/alert.service';

type FundsmanagementComponentType = InstanceType<typeof Fundsmanagement>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Fundsmanagement Management Component', () => {
    let fundsmanagementServiceStub: SinonStubbedInstance<FundsmanagementService>;
    let mountOptions: MountingOptions<FundsmanagementComponentType>['global'];

    beforeEach(() => {
      fundsmanagementServiceStub = sinon.createStubInstance<FundsmanagementService>(FundsmanagementService);
      fundsmanagementServiceStub.retrieve.resolves({ headers: {} });

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
          fundsmanagementService: () => fundsmanagementServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        fundsmanagementServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(Fundsmanagement, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(fundsmanagementServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.fundsmanagements[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: FundsmanagementComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Fundsmanagement, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        fundsmanagementServiceStub.retrieve.reset();
        fundsmanagementServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        fundsmanagementServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeFundsmanagement();
        await comp.$nextTick(); // clear components

        // THEN
        expect(fundsmanagementServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(fundsmanagementServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
