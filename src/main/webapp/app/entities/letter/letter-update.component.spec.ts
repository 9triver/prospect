/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import LetterUpdate from './letter-update.vue';
import LetterService from './letter.service';
import AlertService from '@/shared/alert/alert.service';

import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import WorkbagService from '@/entities/workbag/workbag.service';
import FrontlineService from '@/entities/frontline/frontline.service';
import DepartmentService from '@/entities/department/department.service';
import HrManagementService from '@/entities/hr-management/hr-management.service';

type LetterUpdateComponentType = InstanceType<typeof LetterUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const letterSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<LetterUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Letter Management Update Component', () => {
    let comp: LetterUpdateComponentType;
    let letterServiceStub: SinonStubbedInstance<LetterService>;

    beforeEach(() => {
      route = {};
      letterServiceStub = sinon.createStubInstance<LetterService>(LetterService);
      letterServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          letterService: () => letterServiceStub,
          projectwbsService: () =>
            sinon.createStubInstance<ProjectwbsService>(ProjectwbsService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          workbagService: () =>
            sinon.createStubInstance<WorkbagService>(WorkbagService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          frontlineService: () =>
            sinon.createStubInstance<FrontlineService>(FrontlineService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          departmentService: () =>
            sinon.createStubInstance<DepartmentService>(DepartmentService, {
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
        const wrapper = shallowMount(LetterUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.letter = letterSample;
        letterServiceStub.update.resolves(letterSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(letterServiceStub.update.calledWith(letterSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        letterServiceStub.create.resolves(entity);
        const wrapper = shallowMount(LetterUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.letter = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(letterServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        letterServiceStub.find.resolves(letterSample);
        letterServiceStub.retrieve.resolves([letterSample]);

        // WHEN
        route = {
          params: {
            letterId: '' + letterSample.id,
          },
        };
        const wrapper = shallowMount(LetterUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.letter).toMatchObject(letterSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        letterServiceStub.find.resolves(letterSample);
        const wrapper = shallowMount(LetterUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
