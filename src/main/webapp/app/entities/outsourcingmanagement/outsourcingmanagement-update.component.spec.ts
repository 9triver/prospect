/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import OutsourcingmanagementUpdate from './outsourcingmanagement-update.vue';
import OutsourcingmanagementService from './outsourcingmanagement.service';
import AlertService from '@/shared/alert/alert.service';

import OutsourcingmanagementWbsService from '@/entities/outsourcingmanagement-wbs/outsourcingmanagement-wbs.service';

type OutsourcingmanagementUpdateComponentType = InstanceType<typeof OutsourcingmanagementUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const outsourcingmanagementSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<OutsourcingmanagementUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Outsourcingmanagement Management Update Component', () => {
    let comp: OutsourcingmanagementUpdateComponentType;
    let outsourcingmanagementServiceStub: SinonStubbedInstance<OutsourcingmanagementService>;

    beforeEach(() => {
      route = {};
      outsourcingmanagementServiceStub = sinon.createStubInstance<OutsourcingmanagementService>(OutsourcingmanagementService);
      outsourcingmanagementServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          outsourcingmanagementService: () => outsourcingmanagementServiceStub,
          outsourcingmanagementWbsService: () =>
            sinon.createStubInstance<OutsourcingmanagementWbsService>(OutsourcingmanagementWbsService, {
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
        const wrapper = shallowMount(OutsourcingmanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.outsourcingmanagement = outsourcingmanagementSample;
        outsourcingmanagementServiceStub.update.resolves(outsourcingmanagementSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(outsourcingmanagementServiceStub.update.calledWith(outsourcingmanagementSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        outsourcingmanagementServiceStub.create.resolves(entity);
        const wrapper = shallowMount(OutsourcingmanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.outsourcingmanagement = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(outsourcingmanagementServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        outsourcingmanagementServiceStub.find.resolves(outsourcingmanagementSample);
        outsourcingmanagementServiceStub.retrieve.resolves([outsourcingmanagementSample]);

        // WHEN
        route = {
          params: {
            outsourcingmanagementId: '' + outsourcingmanagementSample.id,
          },
        };
        const wrapper = shallowMount(OutsourcingmanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.outsourcingmanagement).toMatchObject(outsourcingmanagementSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        outsourcingmanagementServiceStub.find.resolves(outsourcingmanagementSample);
        const wrapper = shallowMount(OutsourcingmanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
