/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import RiskTypeUpdate from './risk-type-update.vue';
import RiskTypeService from './risk-type.service';
import AlertService from '@/shared/alert/alert.service';

type RiskTypeUpdateComponentType = InstanceType<typeof RiskTypeUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const riskTypeSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<RiskTypeUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('RiskType Management Update Component', () => {
    let comp: RiskTypeUpdateComponentType;
    let riskTypeServiceStub: SinonStubbedInstance<RiskTypeService>;

    beforeEach(() => {
      route = {};
      riskTypeServiceStub = sinon.createStubInstance<RiskTypeService>(RiskTypeService);
      riskTypeServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          riskTypeService: () => riskTypeServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(RiskTypeUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.riskType = riskTypeSample;
        riskTypeServiceStub.update.resolves(riskTypeSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(riskTypeServiceStub.update.calledWith(riskTypeSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        riskTypeServiceStub.create.resolves(entity);
        const wrapper = shallowMount(RiskTypeUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.riskType = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(riskTypeServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        riskTypeServiceStub.find.resolves(riskTypeSample);
        riskTypeServiceStub.retrieve.resolves([riskTypeSample]);

        // WHEN
        route = {
          params: {
            riskTypeId: '' + riskTypeSample.id,
          },
        };
        const wrapper = shallowMount(RiskTypeUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.riskType).toMatchObject(riskTypeSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        riskTypeServiceStub.find.resolves(riskTypeSample);
        const wrapper = shallowMount(RiskTypeUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
