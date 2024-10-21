/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import CustomerSatisfactionUpdate from './customer-satisfaction-update.vue';
import CustomerSatisfactionService from './customer-satisfaction.service';
import AlertService from '@/shared/alert/alert.service';

import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';

type CustomerSatisfactionUpdateComponentType = InstanceType<typeof CustomerSatisfactionUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const customerSatisfactionSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<CustomerSatisfactionUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('CustomerSatisfaction Management Update Component', () => {
    let comp: CustomerSatisfactionUpdateComponentType;
    let customerSatisfactionServiceStub: SinonStubbedInstance<CustomerSatisfactionService>;

    beforeEach(() => {
      route = {};
      customerSatisfactionServiceStub = sinon.createStubInstance<CustomerSatisfactionService>(CustomerSatisfactionService);
      customerSatisfactionServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          customerSatisfactionService: () => customerSatisfactionServiceStub,
          projectwbsService: () =>
            sinon.createStubInstance<ProjectwbsService>(ProjectwbsService, {
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
        const wrapper = shallowMount(CustomerSatisfactionUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.customerSatisfaction = customerSatisfactionSample;
        customerSatisfactionServiceStub.update.resolves(customerSatisfactionSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(customerSatisfactionServiceStub.update.calledWith(customerSatisfactionSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        customerSatisfactionServiceStub.create.resolves(entity);
        const wrapper = shallowMount(CustomerSatisfactionUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.customerSatisfaction = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(customerSatisfactionServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        customerSatisfactionServiceStub.find.resolves(customerSatisfactionSample);
        customerSatisfactionServiceStub.retrieve.resolves([customerSatisfactionSample]);

        // WHEN
        route = {
          params: {
            customerSatisfactionId: '' + customerSatisfactionSample.id,
          },
        };
        const wrapper = shallowMount(CustomerSatisfactionUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.customerSatisfaction).toMatchObject(customerSatisfactionSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        customerSatisfactionServiceStub.find.resolves(customerSatisfactionSample);
        const wrapper = shallowMount(CustomerSatisfactionUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
