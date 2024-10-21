/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import RiskReturnUpdate from './risk-return-update.vue';
import RiskReturnService from './risk-return.service';
import AlertService from '@/shared/alert/alert.service';

import ProjectRiskService from '@/entities/project-risk/project-risk.service';
import HrManagementService from '@/entities/hr-management/hr-management.service';

type RiskReturnUpdateComponentType = InstanceType<typeof RiskReturnUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const riskReturnSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<RiskReturnUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('RiskReturn Management Update Component', () => {
    let comp: RiskReturnUpdateComponentType;
    let riskReturnServiceStub: SinonStubbedInstance<RiskReturnService>;

    beforeEach(() => {
      route = {};
      riskReturnServiceStub = sinon.createStubInstance<RiskReturnService>(RiskReturnService);
      riskReturnServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          riskReturnService: () => riskReturnServiceStub,
          projectRiskService: () =>
            sinon.createStubInstance<ProjectRiskService>(ProjectRiskService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          hrManagementService: () =>
            sinon.createStubInstance<HrManagementService>(HrManagementService, {
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
        const wrapper = shallowMount(RiskReturnUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.riskReturn = riskReturnSample;
        riskReturnServiceStub.update.resolves(riskReturnSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(riskReturnServiceStub.update.calledWith(riskReturnSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        riskReturnServiceStub.create.resolves(entity);
        const wrapper = shallowMount(RiskReturnUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.riskReturn = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(riskReturnServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        riskReturnServiceStub.find.resolves(riskReturnSample);
        riskReturnServiceStub.retrieve.resolves([riskReturnSample]);

        // WHEN
        route = {
          params: {
            riskReturnId: '' + riskReturnSample.id,
          },
        };
        const wrapper = shallowMount(RiskReturnUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.riskReturn).toMatchObject(riskReturnSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        riskReturnServiceStub.find.resolves(riskReturnSample);
        const wrapper = shallowMount(RiskReturnUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
