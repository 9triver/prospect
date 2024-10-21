/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import QualityObjectivesDictionaryUpdate from './quality-objectives-dictionary-update.vue';
import QualityObjectivesDictionaryService from './quality-objectives-dictionary.service';
import AlertService from '@/shared/alert/alert.service';

type QualityObjectivesDictionaryUpdateComponentType = InstanceType<typeof QualityObjectivesDictionaryUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const qualityObjectivesDictionarySample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<QualityObjectivesDictionaryUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('QualityObjectivesDictionary Management Update Component', () => {
    let comp: QualityObjectivesDictionaryUpdateComponentType;
    let qualityObjectivesDictionaryServiceStub: SinonStubbedInstance<QualityObjectivesDictionaryService>;

    beforeEach(() => {
      route = {};
      qualityObjectivesDictionaryServiceStub =
        sinon.createStubInstance<QualityObjectivesDictionaryService>(QualityObjectivesDictionaryService);
      qualityObjectivesDictionaryServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          qualityObjectivesDictionaryService: () => qualityObjectivesDictionaryServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(QualityObjectivesDictionaryUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.qualityObjectivesDictionary = qualityObjectivesDictionarySample;
        qualityObjectivesDictionaryServiceStub.update.resolves(qualityObjectivesDictionarySample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(qualityObjectivesDictionaryServiceStub.update.calledWith(qualityObjectivesDictionarySample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        qualityObjectivesDictionaryServiceStub.create.resolves(entity);
        const wrapper = shallowMount(QualityObjectivesDictionaryUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.qualityObjectivesDictionary = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(qualityObjectivesDictionaryServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        qualityObjectivesDictionaryServiceStub.find.resolves(qualityObjectivesDictionarySample);
        qualityObjectivesDictionaryServiceStub.retrieve.resolves([qualityObjectivesDictionarySample]);

        // WHEN
        route = {
          params: {
            qualityObjectivesDictionaryId: '' + qualityObjectivesDictionarySample.id,
          },
        };
        const wrapper = shallowMount(QualityObjectivesDictionaryUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.qualityObjectivesDictionary).toMatchObject(qualityObjectivesDictionarySample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        qualityObjectivesDictionaryServiceStub.find.resolves(qualityObjectivesDictionarySample);
        const wrapper = shallowMount(QualityObjectivesDictionaryUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
