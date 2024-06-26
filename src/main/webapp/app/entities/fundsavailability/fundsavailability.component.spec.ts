/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Fundsavailability from './fundsavailability.vue';
import FundsavailabilityService from './fundsavailability.service';
import AlertService from '@/shared/alert/alert.service';

type FundsavailabilityComponentType = InstanceType<typeof Fundsavailability>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Fundsavailability Management Component', () => {
    let fundsavailabilityServiceStub: SinonStubbedInstance<FundsavailabilityService>;
    let mountOptions: MountingOptions<FundsavailabilityComponentType>['global'];

    beforeEach(() => {
      fundsavailabilityServiceStub = sinon.createStubInstance<FundsavailabilityService>(FundsavailabilityService);
      fundsavailabilityServiceStub.retrieve.resolves({ headers: {} });

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
          fundsavailabilityService: () => fundsavailabilityServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        fundsavailabilityServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(Fundsavailability, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(fundsavailabilityServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.fundsavailabilities[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: FundsavailabilityComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Fundsavailability, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        fundsavailabilityServiceStub.retrieve.reset();
        fundsavailabilityServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        fundsavailabilityServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeFundsavailability();
        await comp.$nextTick(); // clear components

        // THEN
        expect(fundsavailabilityServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(fundsavailabilityServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
