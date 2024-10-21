/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import RiskLevelUpdate from './risk-level-update.vue';
import RiskLevelService from './risk-level.service';
import AlertService from '@/shared/alert/alert.service';

type RiskLevelUpdateComponentType = InstanceType<typeof RiskLevelUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const riskLevelSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<RiskLevelUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('RiskLevel Management Update Component', () => {
    let comp: RiskLevelUpdateComponentType;
    let riskLevelServiceStub: SinonStubbedInstance<RiskLevelService>;

    beforeEach(() => {
      route = {};
      riskLevelServiceStub = sinon.createStubInstance<RiskLevelService>(RiskLevelService);
      riskLevelServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          riskLevelService: () => riskLevelServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(RiskLevelUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.riskLevel = riskLevelSample;
        riskLevelServiceStub.update.resolves(riskLevelSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(riskLevelServiceStub.update.calledWith(riskLevelSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        riskLevelServiceStub.create.resolves(entity);
        const wrapper = shallowMount(RiskLevelUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.riskLevel = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(riskLevelServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        riskLevelServiceStub.find.resolves(riskLevelSample);
        riskLevelServiceStub.retrieve.resolves([riskLevelSample]);

        // WHEN
        route = {
          params: {
            riskLevelId: '' + riskLevelSample.id,
          },
        };
        const wrapper = shallowMount(RiskLevelUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.riskLevel).toMatchObject(riskLevelSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        riskLevelServiceStub.find.resolves(riskLevelSample);
        const wrapper = shallowMount(RiskLevelUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
