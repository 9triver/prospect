/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ProjectchargeUpdate from './projectcharge-update.vue';
import ProjectchargeService from './projectcharge.service';
import AlertService from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';

type ProjectchargeUpdateComponentType = InstanceType<typeof ProjectchargeUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const projectchargeSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ProjectchargeUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Projectcharge Management Update Component', () => {
    let comp: ProjectchargeUpdateComponentType;
    let projectchargeServiceStub: SinonStubbedInstance<ProjectchargeService>;

    beforeEach(() => {
      route = {};
      projectchargeServiceStub = sinon.createStubInstance<ProjectchargeService>(ProjectchargeService);
      projectchargeServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          projectchargeService: () => projectchargeServiceStub,
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
        const wrapper = shallowMount(ProjectchargeUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.projectcharge = projectchargeSample;
        projectchargeServiceStub.update.resolves(projectchargeSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(projectchargeServiceStub.update.calledWith(projectchargeSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        projectchargeServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ProjectchargeUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.projectcharge = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(projectchargeServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        projectchargeServiceStub.find.resolves(projectchargeSample);
        projectchargeServiceStub.retrieve.resolves([projectchargeSample]);

        // WHEN
        route = {
          params: {
            projectchargeId: '' + projectchargeSample.id,
          },
        };
        const wrapper = shallowMount(ProjectchargeUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.projectcharge).toMatchObject(projectchargeSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        projectchargeServiceStub.find.resolves(projectchargeSample);
        const wrapper = shallowMount(ProjectchargeUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
