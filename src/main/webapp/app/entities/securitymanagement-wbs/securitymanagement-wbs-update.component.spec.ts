/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import SecuritymanagementWbsUpdate from './securitymanagement-wbs-update.vue';
import SecuritymanagementWbsService from './securitymanagement-wbs.service';
import AlertService from '@/shared/alert/alert.service';

import AnnualSecurityPlanService from '@/entities/annual-security-plan/annual-security-plan.service';
import SafetycheckService from '@/entities/safetycheck/safetycheck.service';

type SecuritymanagementWbsUpdateComponentType = InstanceType<typeof SecuritymanagementWbsUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const securitymanagementWbsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<SecuritymanagementWbsUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('SecuritymanagementWbs Management Update Component', () => {
    let comp: SecuritymanagementWbsUpdateComponentType;
    let securitymanagementWbsServiceStub: SinonStubbedInstance<SecuritymanagementWbsService>;

    beforeEach(() => {
      route = {};
      securitymanagementWbsServiceStub = sinon.createStubInstance<SecuritymanagementWbsService>(SecuritymanagementWbsService);
      securitymanagementWbsServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          securitymanagementWbsService: () => securitymanagementWbsServiceStub,
          annualSecurityPlanService: () =>
            sinon.createStubInstance<AnnualSecurityPlanService>(AnnualSecurityPlanService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          safetycheckService: () =>
            sinon.createStubInstance<SafetycheckService>(SafetycheckService, {
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
        const wrapper = shallowMount(SecuritymanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.securitymanagementWbs = securitymanagementWbsSample;
        securitymanagementWbsServiceStub.update.resolves(securitymanagementWbsSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(securitymanagementWbsServiceStub.update.calledWith(securitymanagementWbsSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        securitymanagementWbsServiceStub.create.resolves(entity);
        const wrapper = shallowMount(SecuritymanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.securitymanagementWbs = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(securitymanagementWbsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        securitymanagementWbsServiceStub.find.resolves(securitymanagementWbsSample);
        securitymanagementWbsServiceStub.retrieve.resolves([securitymanagementWbsSample]);

        // WHEN
        route = {
          params: {
            securitymanagementWbsId: '' + securitymanagementWbsSample.id,
          },
        };
        const wrapper = shallowMount(SecuritymanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.securitymanagementWbs).toMatchObject(securitymanagementWbsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        securitymanagementWbsServiceStub.find.resolves(securitymanagementWbsSample);
        const wrapper = shallowMount(SecuritymanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
