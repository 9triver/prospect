/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import RiskmanagementUpdate from './riskmanagement-update.vue';
import RiskmanagementService from './riskmanagement.service';
import AlertService from '@/shared/alert/alert.service';

import RiskmanagementWbsService from '@/entities/riskmanagement-wbs/riskmanagement-wbs.service';

type RiskmanagementUpdateComponentType = InstanceType<typeof RiskmanagementUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const riskmanagementSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<RiskmanagementUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Riskmanagement Management Update Component', () => {
    let comp: RiskmanagementUpdateComponentType;
    let riskmanagementServiceStub: SinonStubbedInstance<RiskmanagementService>;

    beforeEach(() => {
      route = {};
      riskmanagementServiceStub = sinon.createStubInstance<RiskmanagementService>(RiskmanagementService);
      riskmanagementServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          riskmanagementService: () => riskmanagementServiceStub,
          riskmanagementWbsService: () =>
            sinon.createStubInstance<RiskmanagementWbsService>(RiskmanagementWbsService, {
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
        const wrapper = shallowMount(RiskmanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.riskmanagement = riskmanagementSample;
        riskmanagementServiceStub.update.resolves(riskmanagementSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(riskmanagementServiceStub.update.calledWith(riskmanagementSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        riskmanagementServiceStub.create.resolves(entity);
        const wrapper = shallowMount(RiskmanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.riskmanagement = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(riskmanagementServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        riskmanagementServiceStub.find.resolves(riskmanagementSample);
        riskmanagementServiceStub.retrieve.resolves([riskmanagementSample]);

        // WHEN
        route = {
          params: {
            riskmanagementId: '' + riskmanagementSample.id,
          },
        };
        const wrapper = shallowMount(RiskmanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.riskmanagement).toMatchObject(riskmanagementSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        riskmanagementServiceStub.find.resolves(riskmanagementSample);
        const wrapper = shallowMount(RiskmanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
