/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import KeyNodeInspectionUpdate from './key-node-inspection-update.vue';
import KeyNodeInspectionService from './key-node-inspection.service';
import AlertService from '@/shared/alert/alert.service';

type KeyNodeInspectionUpdateComponentType = InstanceType<typeof KeyNodeInspectionUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const keyNodeInspectionSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<KeyNodeInspectionUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('KeyNodeInspection Management Update Component', () => {
    let comp: KeyNodeInspectionUpdateComponentType;
    let keyNodeInspectionServiceStub: SinonStubbedInstance<KeyNodeInspectionService>;

    beforeEach(() => {
      route = {};
      keyNodeInspectionServiceStub = sinon.createStubInstance<KeyNodeInspectionService>(KeyNodeInspectionService);
      keyNodeInspectionServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          keyNodeInspectionService: () => keyNodeInspectionServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(KeyNodeInspectionUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.keyNodeInspection = keyNodeInspectionSample;
        keyNodeInspectionServiceStub.update.resolves(keyNodeInspectionSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(keyNodeInspectionServiceStub.update.calledWith(keyNodeInspectionSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        keyNodeInspectionServiceStub.create.resolves(entity);
        const wrapper = shallowMount(KeyNodeInspectionUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.keyNodeInspection = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(keyNodeInspectionServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        keyNodeInspectionServiceStub.find.resolves(keyNodeInspectionSample);
        keyNodeInspectionServiceStub.retrieve.resolves([keyNodeInspectionSample]);

        // WHEN
        route = {
          params: {
            keyNodeInspectionId: '' + keyNodeInspectionSample.id,
          },
        };
        const wrapper = shallowMount(KeyNodeInspectionUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.keyNodeInspection).toMatchObject(keyNodeInspectionSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        keyNodeInspectionServiceStub.find.resolves(keyNodeInspectionSample);
        const wrapper = shallowMount(KeyNodeInspectionUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
