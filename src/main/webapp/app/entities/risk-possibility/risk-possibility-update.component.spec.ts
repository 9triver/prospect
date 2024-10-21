/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import RiskPossibilityUpdate from './risk-possibility-update.vue';
import RiskPossibilityService from './risk-possibility.service';
import AlertService from '@/shared/alert/alert.service';

type RiskPossibilityUpdateComponentType = InstanceType<typeof RiskPossibilityUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const riskPossibilitySample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<RiskPossibilityUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('RiskPossibility Management Update Component', () => {
    let comp: RiskPossibilityUpdateComponentType;
    let riskPossibilityServiceStub: SinonStubbedInstance<RiskPossibilityService>;

    beforeEach(() => {
      route = {};
      riskPossibilityServiceStub = sinon.createStubInstance<RiskPossibilityService>(RiskPossibilityService);
      riskPossibilityServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          riskPossibilityService: () => riskPossibilityServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(RiskPossibilityUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.riskPossibility = riskPossibilitySample;
        riskPossibilityServiceStub.update.resolves(riskPossibilitySample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(riskPossibilityServiceStub.update.calledWith(riskPossibilitySample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        riskPossibilityServiceStub.create.resolves(entity);
        const wrapper = shallowMount(RiskPossibilityUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.riskPossibility = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(riskPossibilityServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        riskPossibilityServiceStub.find.resolves(riskPossibilitySample);
        riskPossibilityServiceStub.retrieve.resolves([riskPossibilitySample]);

        // WHEN
        route = {
          params: {
            riskPossibilityId: '' + riskPossibilitySample.id,
          },
        };
        const wrapper = shallowMount(RiskPossibilityUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.riskPossibility).toMatchObject(riskPossibilitySample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        riskPossibilityServiceStub.find.resolves(riskPossibilitySample);
        const wrapper = shallowMount(RiskPossibilityUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
