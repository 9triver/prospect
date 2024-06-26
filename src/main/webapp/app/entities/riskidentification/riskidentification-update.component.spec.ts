/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import RiskidentificationUpdate from './riskidentification-update.vue';
import RiskidentificationService from './riskidentification.service';
import AlertService from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';

type RiskidentificationUpdateComponentType = InstanceType<typeof RiskidentificationUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const riskidentificationSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<RiskidentificationUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Riskidentification Management Update Component', () => {
    let comp: RiskidentificationUpdateComponentType;
    let riskidentificationServiceStub: SinonStubbedInstance<RiskidentificationService>;

    beforeEach(() => {
      route = {};
      riskidentificationServiceStub = sinon.createStubInstance<RiskidentificationService>(RiskidentificationService);
      riskidentificationServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          riskidentificationService: () => riskidentificationServiceStub,
          officersService: () =>
            sinon.createStubInstance<OfficersService>(OfficersService, {
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
        const wrapper = shallowMount(RiskidentificationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.riskidentification = riskidentificationSample;
        riskidentificationServiceStub.update.resolves(riskidentificationSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(riskidentificationServiceStub.update.calledWith(riskidentificationSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        riskidentificationServiceStub.create.resolves(entity);
        const wrapper = shallowMount(RiskidentificationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.riskidentification = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(riskidentificationServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        riskidentificationServiceStub.find.resolves(riskidentificationSample);
        riskidentificationServiceStub.retrieve.resolves([riskidentificationSample]);

        // WHEN
        route = {
          params: {
            riskidentificationId: '' + riskidentificationSample.id,
          },
        };
        const wrapper = shallowMount(RiskidentificationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.riskidentification).toMatchObject(riskidentificationSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        riskidentificationServiceStub.find.resolves(riskidentificationSample);
        const wrapper = shallowMount(RiskidentificationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
