/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import HumanresourcesUpdate from './humanresources-update.vue';
import HumanresourcesService from './humanresources.service';
import AlertService from '@/shared/alert/alert.service';

import ProjectService from '@/entities/project/project.service';
import OfficersService from '@/entities/officers/officers.service';

type HumanresourcesUpdateComponentType = InstanceType<typeof HumanresourcesUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const humanresourcesSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<HumanresourcesUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Humanresources Management Update Component', () => {
    let comp: HumanresourcesUpdateComponentType;
    let humanresourcesServiceStub: SinonStubbedInstance<HumanresourcesService>;

    beforeEach(() => {
      route = {};
      humanresourcesServiceStub = sinon.createStubInstance<HumanresourcesService>(HumanresourcesService);
      humanresourcesServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          humanresourcesService: () => humanresourcesServiceStub,
          projectService: () =>
            sinon.createStubInstance<ProjectService>(ProjectService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
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
        const wrapper = shallowMount(HumanresourcesUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.humanresources = humanresourcesSample;
        humanresourcesServiceStub.update.resolves(humanresourcesSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(humanresourcesServiceStub.update.calledWith(humanresourcesSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        humanresourcesServiceStub.create.resolves(entity);
        const wrapper = shallowMount(HumanresourcesUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.humanresources = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(humanresourcesServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        humanresourcesServiceStub.find.resolves(humanresourcesSample);
        humanresourcesServiceStub.retrieve.resolves([humanresourcesSample]);

        // WHEN
        route = {
          params: {
            humanresourcesId: '' + humanresourcesSample.id,
          },
        };
        const wrapper = shallowMount(HumanresourcesUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.humanresources).toMatchObject(humanresourcesSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        humanresourcesServiceStub.find.resolves(humanresourcesSample);
        const wrapper = shallowMount(HumanresourcesUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
