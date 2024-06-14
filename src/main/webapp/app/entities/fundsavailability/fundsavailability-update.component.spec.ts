/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import FundsavailabilityUpdate from './fundsavailability-update.vue';
import FundsavailabilityService from './fundsavailability.service';
import AlertService from '@/shared/alert/alert.service';

import FundsmanagementService from '@/entities/fundsmanagement/fundsmanagement.service';

type FundsavailabilityUpdateComponentType = InstanceType<typeof FundsavailabilityUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const fundsavailabilitySample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<FundsavailabilityUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Fundsavailability Management Update Component', () => {
    let comp: FundsavailabilityUpdateComponentType;
    let fundsavailabilityServiceStub: SinonStubbedInstance<FundsavailabilityService>;

    beforeEach(() => {
      route = {};
      fundsavailabilityServiceStub = sinon.createStubInstance<FundsavailabilityService>(FundsavailabilityService);
      fundsavailabilityServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          fundsavailabilityService: () => fundsavailabilityServiceStub,
          fundsmanagementService: () =>
            sinon.createStubInstance<FundsmanagementService>(FundsmanagementService, {
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
        const wrapper = shallowMount(FundsavailabilityUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.fundsavailability = fundsavailabilitySample;
        fundsavailabilityServiceStub.update.resolves(fundsavailabilitySample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(fundsavailabilityServiceStub.update.calledWith(fundsavailabilitySample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        fundsavailabilityServiceStub.create.resolves(entity);
        const wrapper = shallowMount(FundsavailabilityUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.fundsavailability = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(fundsavailabilityServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        fundsavailabilityServiceStub.find.resolves(fundsavailabilitySample);
        fundsavailabilityServiceStub.retrieve.resolves([fundsavailabilitySample]);

        // WHEN
        route = {
          params: {
            fundsavailabilityId: '' + fundsavailabilitySample.id,
          },
        };
        const wrapper = shallowMount(FundsavailabilityUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.fundsavailability).toMatchObject(fundsavailabilitySample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        fundsavailabilityServiceStub.find.resolves(fundsavailabilitySample);
        const wrapper = shallowMount(FundsavailabilityUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
