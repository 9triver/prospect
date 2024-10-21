/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import SharePaymentUpdate from './share-payment-update.vue';
import SharePaymentService from './share-payment.service';
import AlertService from '@/shared/alert/alert.service';

type SharePaymentUpdateComponentType = InstanceType<typeof SharePaymentUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const sharePaymentSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<SharePaymentUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('SharePayment Management Update Component', () => {
    let comp: SharePaymentUpdateComponentType;
    let sharePaymentServiceStub: SinonStubbedInstance<SharePaymentService>;

    beforeEach(() => {
      route = {};
      sharePaymentServiceStub = sinon.createStubInstance<SharePaymentService>(SharePaymentService);
      sharePaymentServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          sharePaymentService: () => sharePaymentServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(SharePaymentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.sharePayment = sharePaymentSample;
        sharePaymentServiceStub.update.resolves(sharePaymentSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(sharePaymentServiceStub.update.calledWith(sharePaymentSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        sharePaymentServiceStub.create.resolves(entity);
        const wrapper = shallowMount(SharePaymentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.sharePayment = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(sharePaymentServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        sharePaymentServiceStub.find.resolves(sharePaymentSample);
        sharePaymentServiceStub.retrieve.resolves([sharePaymentSample]);

        // WHEN
        route = {
          params: {
            sharePaymentId: '' + sharePaymentSample.id,
          },
        };
        const wrapper = shallowMount(SharePaymentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.sharePayment).toMatchObject(sharePaymentSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        sharePaymentServiceStub.find.resolves(sharePaymentSample);
        const wrapper = shallowMount(SharePaymentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
