/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import SubjectUpdate from './subject-update.vue';
import SubjectService from './subject.service';
import AlertService from '@/shared/alert/alert.service';

type SubjectUpdateComponentType = InstanceType<typeof SubjectUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const subjectSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<SubjectUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Subject Management Update Component', () => {
    let comp: SubjectUpdateComponentType;
    let subjectServiceStub: SinonStubbedInstance<SubjectService>;

    beforeEach(() => {
      route = {};
      subjectServiceStub = sinon.createStubInstance<SubjectService>(SubjectService);
      subjectServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          subjectService: () => subjectServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(SubjectUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.subject = subjectSample;
        subjectServiceStub.update.resolves(subjectSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(subjectServiceStub.update.calledWith(subjectSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        subjectServiceStub.create.resolves(entity);
        const wrapper = shallowMount(SubjectUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.subject = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(subjectServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        subjectServiceStub.find.resolves(subjectSample);
        subjectServiceStub.retrieve.resolves([subjectSample]);

        // WHEN
        route = {
          params: {
            subjectId: '' + subjectSample.id,
          },
        };
        const wrapper = shallowMount(SubjectUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.subject).toMatchObject(subjectSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        subjectServiceStub.find.resolves(subjectSample);
        const wrapper = shallowMount(SubjectUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
