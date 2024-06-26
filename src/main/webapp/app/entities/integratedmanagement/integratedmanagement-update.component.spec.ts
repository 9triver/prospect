/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import IntegratedmanagementUpdate from './integratedmanagement-update.vue';
import IntegratedmanagementService from './integratedmanagement.service';
import AlertService from '@/shared/alert/alert.service';

import IntegratedmanagementWbsService from '@/entities/integratedmanagement-wbs/integratedmanagement-wbs.service';

type IntegratedmanagementUpdateComponentType = InstanceType<typeof IntegratedmanagementUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const integratedmanagementSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<IntegratedmanagementUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Integratedmanagement Management Update Component', () => {
    let comp: IntegratedmanagementUpdateComponentType;
    let integratedmanagementServiceStub: SinonStubbedInstance<IntegratedmanagementService>;

    beforeEach(() => {
      route = {};
      integratedmanagementServiceStub = sinon.createStubInstance<IntegratedmanagementService>(IntegratedmanagementService);
      integratedmanagementServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          integratedmanagementService: () => integratedmanagementServiceStub,
          integratedmanagementWbsService: () =>
            sinon.createStubInstance<IntegratedmanagementWbsService>(IntegratedmanagementWbsService, {
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
        const wrapper = shallowMount(IntegratedmanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.integratedmanagement = integratedmanagementSample;
        integratedmanagementServiceStub.update.resolves(integratedmanagementSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(integratedmanagementServiceStub.update.calledWith(integratedmanagementSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        integratedmanagementServiceStub.create.resolves(entity);
        const wrapper = shallowMount(IntegratedmanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.integratedmanagement = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(integratedmanagementServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        integratedmanagementServiceStub.find.resolves(integratedmanagementSample);
        integratedmanagementServiceStub.retrieve.resolves([integratedmanagementSample]);

        // WHEN
        route = {
          params: {
            integratedmanagementId: '' + integratedmanagementSample.id,
          },
        };
        const wrapper = shallowMount(IntegratedmanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.integratedmanagement).toMatchObject(integratedmanagementSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        integratedmanagementServiceStub.find.resolves(integratedmanagementSample);
        const wrapper = shallowMount(IntegratedmanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
