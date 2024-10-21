/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import FundsEstimation from './funds-estimation.vue';
import FundsEstimationService from './funds-estimation.service';
import AlertService from '@/shared/alert/alert.service';

type FundsEstimationComponentType = InstanceType<typeof FundsEstimation>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('FundsEstimation Management Component', () => {
    let fundsEstimationServiceStub: SinonStubbedInstance<FundsEstimationService>;
    let mountOptions: MountingOptions<FundsEstimationComponentType>['global'];

    beforeEach(() => {
      fundsEstimationServiceStub = sinon.createStubInstance<FundsEstimationService>(FundsEstimationService);
      fundsEstimationServiceStub.retrieve.resolves({ headers: {} });

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
          fundsEstimationService: () => fundsEstimationServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        fundsEstimationServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(FundsEstimation, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(fundsEstimationServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.fundsEstimations[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: FundsEstimationComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(FundsEstimation, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        fundsEstimationServiceStub.retrieve.reset();
        fundsEstimationServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        fundsEstimationServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeFundsEstimation();
        await comp.$nextTick(); // clear components

        // THEN
        expect(fundsEstimationServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(fundsEstimationServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
