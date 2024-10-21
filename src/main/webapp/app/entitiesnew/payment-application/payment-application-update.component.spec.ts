/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import PaymentApplicationUpdate from './payment-application-update.vue';
import PaymentApplicationService from './payment-application.service';
import AlertService from '@/shared/alert/alert.service';

import OutsourcingContractService from '@/entities/outsourcing-contract/outsourcing-contract.service';

type PaymentApplicationUpdateComponentType = InstanceType<typeof PaymentApplicationUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const paymentApplicationSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<PaymentApplicationUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('PaymentApplication Management Update Component', () => {
    let comp: PaymentApplicationUpdateComponentType;
    let paymentApplicationServiceStub: SinonStubbedInstance<PaymentApplicationService>;

    beforeEach(() => {
      route = {};
      paymentApplicationServiceStub = sinon.createStubInstance<PaymentApplicationService>(PaymentApplicationService);
      paymentApplicationServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          'font-awesome-icon': true,
          'b-input-group': true,
          'b-input-group-prepend': true,
          'b-form-datepicker': true,
          'b-form-input': true,
        },
        provide: {
          alertService,
          paymentApplicationService: () => paymentApplicationServiceStub,
          outsourcingContractService: () =>
            sinon.createStubInstance<OutsourcingContractService>(OutsourcingContractService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(PaymentApplicationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.paymentApplication = paymentApplicationSample;
        paymentApplicationServiceStub.update.resolves(paymentApplicationSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(paymentApplicationServiceStub.update.calledWith(paymentApplicationSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        paymentApplicationServiceStub.create.resolves(entity);
        const wrapper = shallowMount(PaymentApplicationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.paymentApplication = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(paymentApplicationServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        paymentApplicationServiceStub.find.resolves(paymentApplicationSample);
        paymentApplicationServiceStub.retrieve.resolves([paymentApplicationSample]);

        // WHEN
        route = {
          params: {
            paymentApplicationId: '' + paymentApplicationSample.id,
          },
        };
        const wrapper = shallowMount(PaymentApplicationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.paymentApplication).toMatchObject(paymentApplicationSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        paymentApplicationServiceStub.find.resolves(paymentApplicationSample);
        const wrapper = shallowMount(PaymentApplicationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
