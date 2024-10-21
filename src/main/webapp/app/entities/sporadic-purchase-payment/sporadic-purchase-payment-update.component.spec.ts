/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import SporadicPurchasePaymentUpdate from './sporadic-purchase-payment-update.vue';
import SporadicPurchasePaymentService from './sporadic-purchase-payment.service';
import AlertService from '@/shared/alert/alert.service';

type SporadicPurchasePaymentUpdateComponentType = InstanceType<typeof SporadicPurchasePaymentUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const sporadicPurchasePaymentSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<SporadicPurchasePaymentUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('SporadicPurchasePayment Management Update Component', () => {
    let comp: SporadicPurchasePaymentUpdateComponentType;
    let sporadicPurchasePaymentServiceStub: SinonStubbedInstance<SporadicPurchasePaymentService>;

    beforeEach(() => {
      route = {};
      sporadicPurchasePaymentServiceStub = sinon.createStubInstance<SporadicPurchasePaymentService>(SporadicPurchasePaymentService);
      sporadicPurchasePaymentServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          sporadicPurchasePaymentService: () => sporadicPurchasePaymentServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(SporadicPurchasePaymentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.sporadicPurchasePayment = sporadicPurchasePaymentSample;
        sporadicPurchasePaymentServiceStub.update.resolves(sporadicPurchasePaymentSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(sporadicPurchasePaymentServiceStub.update.calledWith(sporadicPurchasePaymentSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        sporadicPurchasePaymentServiceStub.create.resolves(entity);
        const wrapper = shallowMount(SporadicPurchasePaymentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.sporadicPurchasePayment = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(sporadicPurchasePaymentServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        sporadicPurchasePaymentServiceStub.find.resolves(sporadicPurchasePaymentSample);
        sporadicPurchasePaymentServiceStub.retrieve.resolves([sporadicPurchasePaymentSample]);

        // WHEN
        route = {
          params: {
            sporadicPurchasePaymentId: '' + sporadicPurchasePaymentSample.id,
          },
        };
        const wrapper = shallowMount(SporadicPurchasePaymentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.sporadicPurchasePayment).toMatchObject(sporadicPurchasePaymentSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        sporadicPurchasePaymentServiceStub.find.resolves(sporadicPurchasePaymentSample);
        const wrapper = shallowMount(SporadicPurchasePaymentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
