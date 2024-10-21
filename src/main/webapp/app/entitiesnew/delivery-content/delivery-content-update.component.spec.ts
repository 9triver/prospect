/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import DeliveryContentUpdate from './delivery-content-update.vue';
import DeliveryContentService from './delivery-content.service';
import AlertService from '@/shared/alert/alert.service';

import OutsourcingContractService from '@/entities/outsourcing-contract/outsourcing-contract.service';

type DeliveryContentUpdateComponentType = InstanceType<typeof DeliveryContentUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const deliveryContentSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<DeliveryContentUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('DeliveryContent Management Update Component', () => {
    let comp: DeliveryContentUpdateComponentType;
    let deliveryContentServiceStub: SinonStubbedInstance<DeliveryContentService>;

    beforeEach(() => {
      route = {};
      deliveryContentServiceStub = sinon.createStubInstance<DeliveryContentService>(DeliveryContentService);
      deliveryContentServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          deliveryContentService: () => deliveryContentServiceStub,
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
        const wrapper = shallowMount(DeliveryContentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.deliveryContent = deliveryContentSample;
        deliveryContentServiceStub.update.resolves(deliveryContentSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(deliveryContentServiceStub.update.calledWith(deliveryContentSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        deliveryContentServiceStub.create.resolves(entity);
        const wrapper = shallowMount(DeliveryContentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.deliveryContent = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(deliveryContentServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        deliveryContentServiceStub.find.resolves(deliveryContentSample);
        deliveryContentServiceStub.retrieve.resolves([deliveryContentSample]);

        // WHEN
        route = {
          params: {
            deliveryContentId: '' + deliveryContentSample.id,
          },
        };
        const wrapper = shallowMount(DeliveryContentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.deliveryContent).toMatchObject(deliveryContentSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        deliveryContentServiceStub.find.resolves(deliveryContentSample);
        const wrapper = shallowMount(DeliveryContentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
