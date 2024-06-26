/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ResourcemanagementWbsUpdate from './resourcemanagement-wbs-update.vue';
import ResourcemanagementWbsService from './resourcemanagement-wbs.service';
import AlertService from '@/shared/alert/alert.service';

import ProjectHumanresourcesplanService from '@/entities/project-humanresourcesplan/project-humanresourcesplan.service';
import ProjectremitService from '@/entities/projectremit/projectremit.service';
import HumanresourcesService from '@/entities/humanresources/humanresources.service';

type ResourcemanagementWbsUpdateComponentType = InstanceType<typeof ResourcemanagementWbsUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const resourcemanagementWbsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ResourcemanagementWbsUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('ResourcemanagementWbs Management Update Component', () => {
    let comp: ResourcemanagementWbsUpdateComponentType;
    let resourcemanagementWbsServiceStub: SinonStubbedInstance<ResourcemanagementWbsService>;

    beforeEach(() => {
      route = {};
      resourcemanagementWbsServiceStub = sinon.createStubInstance<ResourcemanagementWbsService>(ResourcemanagementWbsService);
      resourcemanagementWbsServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          resourcemanagementWbsService: () => resourcemanagementWbsServiceStub,
          projectHumanresourcesplanService: () =>
            sinon.createStubInstance<ProjectHumanresourcesplanService>(ProjectHumanresourcesplanService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          projectremitService: () =>
            sinon.createStubInstance<ProjectremitService>(ProjectremitService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          humanresourcesService: () =>
            sinon.createStubInstance<HumanresourcesService>(HumanresourcesService, {
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
        const wrapper = shallowMount(ResourcemanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.resourcemanagementWbs = resourcemanagementWbsSample;
        resourcemanagementWbsServiceStub.update.resolves(resourcemanagementWbsSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(resourcemanagementWbsServiceStub.update.calledWith(resourcemanagementWbsSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        resourcemanagementWbsServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ResourcemanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.resourcemanagementWbs = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(resourcemanagementWbsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        resourcemanagementWbsServiceStub.find.resolves(resourcemanagementWbsSample);
        resourcemanagementWbsServiceStub.retrieve.resolves([resourcemanagementWbsSample]);

        // WHEN
        route = {
          params: {
            resourcemanagementWbsId: '' + resourcemanagementWbsSample.id,
          },
        };
        const wrapper = shallowMount(ResourcemanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.resourcemanagementWbs).toMatchObject(resourcemanagementWbsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        resourcemanagementWbsServiceStub.find.resolves(resourcemanagementWbsSample);
        const wrapper = shallowMount(ResourcemanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
