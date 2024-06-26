/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import FundsmanagementUpdate from './fundsmanagement-update.vue';
import FundsmanagementService from './fundsmanagement.service';
import AlertService from '@/shared/alert/alert.service';

import FundsmanagementWbsService from '@/entities/fundsmanagement-wbs/fundsmanagement-wbs.service';

type FundsmanagementUpdateComponentType = InstanceType<typeof FundsmanagementUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const fundsmanagementSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<FundsmanagementUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Fundsmanagement Management Update Component', () => {
    let comp: FundsmanagementUpdateComponentType;
    let fundsmanagementServiceStub: SinonStubbedInstance<FundsmanagementService>;

    beforeEach(() => {
      route = {};
      fundsmanagementServiceStub = sinon.createStubInstance<FundsmanagementService>(FundsmanagementService);
      fundsmanagementServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          fundsmanagementService: () => fundsmanagementServiceStub,
          fundsmanagementWbsService: () =>
            sinon.createStubInstance<FundsmanagementWbsService>(FundsmanagementWbsService, {
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
        const wrapper = shallowMount(FundsmanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.fundsmanagement = fundsmanagementSample;
        fundsmanagementServiceStub.update.resolves(fundsmanagementSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(fundsmanagementServiceStub.update.calledWith(fundsmanagementSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        fundsmanagementServiceStub.create.resolves(entity);
        const wrapper = shallowMount(FundsmanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.fundsmanagement = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(fundsmanagementServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        fundsmanagementServiceStub.find.resolves(fundsmanagementSample);
        fundsmanagementServiceStub.retrieve.resolves([fundsmanagementSample]);

        // WHEN
        route = {
          params: {
            fundsmanagementId: '' + fundsmanagementSample.id,
          },
        };
        const wrapper = shallowMount(FundsmanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.fundsmanagement).toMatchObject(fundsmanagementSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        fundsmanagementServiceStub.find.resolves(fundsmanagementSample);
        const wrapper = shallowMount(FundsmanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
