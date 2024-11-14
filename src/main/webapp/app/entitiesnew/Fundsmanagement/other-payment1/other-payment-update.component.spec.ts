/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import OtherPaymentUpdate from './other-payment-update.vue';
import OtherPaymentService from './other-payment.service';
import AlertService from '@/shared/alert/alert.service';

type OtherPaymentUpdateComponentType = InstanceType<typeof OtherPaymentUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const otherPaymentSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<OtherPaymentUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('OtherPayment Management Update Component', () => {
    let comp: OtherPaymentUpdateComponentType;
    let otherPaymentServiceStub: SinonStubbedInstance<OtherPaymentService>;

    beforeEach(() => {
      route = {};
      otherPaymentServiceStub = sinon.createStubInstance<OtherPaymentService>(OtherPaymentService);
      otherPaymentServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          otherPaymentService: () => otherPaymentServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(OtherPaymentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.otherPayment = otherPaymentSample;
        otherPaymentServiceStub.update.resolves(otherPaymentSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(otherPaymentServiceStub.update.calledWith(otherPaymentSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        otherPaymentServiceStub.create.resolves(entity);
        const wrapper = shallowMount(OtherPaymentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.otherPayment = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(otherPaymentServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        otherPaymentServiceStub.find.resolves(otherPaymentSample);
        otherPaymentServiceStub.retrieve.resolves([otherPaymentSample]);

        // WHEN
        route = {
          params: {
            otherPaymentId: '' + otherPaymentSample.id,
          },
        };
        const wrapper = shallowMount(OtherPaymentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.otherPayment).toMatchObject(otherPaymentSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        otherPaymentServiceStub.find.resolves(otherPaymentSample);
        const wrapper = shallowMount(OtherPaymentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
