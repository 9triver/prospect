/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import TotalbudgetUpdate from './totalbudget-update.vue';
import TotalbudgetService from './totalbudget.service';
import AlertService from '@/shared/alert/alert.service';

type TotalbudgetUpdateComponentType = InstanceType<typeof TotalbudgetUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const totalbudgetSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<TotalbudgetUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Totalbudget Management Update Component', () => {
    let comp: TotalbudgetUpdateComponentType;
    let totalbudgetServiceStub: SinonStubbedInstance<TotalbudgetService>;

    beforeEach(() => {
      route = {};
      totalbudgetServiceStub = sinon.createStubInstance<TotalbudgetService>(TotalbudgetService);
      totalbudgetServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          totalbudgetService: () => totalbudgetServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(TotalbudgetUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.totalbudget = totalbudgetSample;
        totalbudgetServiceStub.update.resolves(totalbudgetSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(totalbudgetServiceStub.update.calledWith(totalbudgetSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        totalbudgetServiceStub.create.resolves(entity);
        const wrapper = shallowMount(TotalbudgetUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.totalbudget = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(totalbudgetServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        totalbudgetServiceStub.find.resolves(totalbudgetSample);
        totalbudgetServiceStub.retrieve.resolves([totalbudgetSample]);

        // WHEN
        route = {
          params: {
            totalbudgetId: '' + totalbudgetSample.id,
          },
        };
        const wrapper = shallowMount(TotalbudgetUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.totalbudget).toMatchObject(totalbudgetSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        totalbudgetServiceStub.find.resolves(totalbudgetSample);
        const wrapper = shallowMount(TotalbudgetUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
