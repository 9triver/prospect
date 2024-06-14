/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ResourcemanagementUpdate from './resourcemanagement-update.vue';
import ResourcemanagementService from './resourcemanagement.service';
import AlertService from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';

type ResourcemanagementUpdateComponentType = InstanceType<typeof ResourcemanagementUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const resourcemanagementSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ResourcemanagementUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Resourcemanagement Management Update Component', () => {
    let comp: ResourcemanagementUpdateComponentType;
    let resourcemanagementServiceStub: SinonStubbedInstance<ResourcemanagementService>;

    beforeEach(() => {
      route = {};
      resourcemanagementServiceStub = sinon.createStubInstance<ResourcemanagementService>(ResourcemanagementService);
      resourcemanagementServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          resourcemanagementService: () => resourcemanagementServiceStub,
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
        const wrapper = shallowMount(ResourcemanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.resourcemanagement = resourcemanagementSample;
        resourcemanagementServiceStub.update.resolves(resourcemanagementSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(resourcemanagementServiceStub.update.calledWith(resourcemanagementSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        resourcemanagementServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ResourcemanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.resourcemanagement = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(resourcemanagementServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        resourcemanagementServiceStub.find.resolves(resourcemanagementSample);
        resourcemanagementServiceStub.retrieve.resolves([resourcemanagementSample]);

        // WHEN
        route = {
          params: {
            resourcemanagementId: '' + resourcemanagementSample.id,
          },
        };
        const wrapper = shallowMount(ResourcemanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.resourcemanagement).toMatchObject(resourcemanagementSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        resourcemanagementServiceStub.find.resolves(resourcemanagementSample);
        const wrapper = shallowMount(ResourcemanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
