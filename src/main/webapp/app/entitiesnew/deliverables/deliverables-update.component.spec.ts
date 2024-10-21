/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import DeliverablesUpdate from './deliverables-update.vue';
import DeliverablesService from './deliverables.service';
import AlertService from '@/shared/alert/alert.service';

type DeliverablesUpdateComponentType = InstanceType<typeof DeliverablesUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const deliverablesSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<DeliverablesUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Deliverables Management Update Component', () => {
    let comp: DeliverablesUpdateComponentType;
    let deliverablesServiceStub: SinonStubbedInstance<DeliverablesService>;

    beforeEach(() => {
      route = {};
      deliverablesServiceStub = sinon.createStubInstance<DeliverablesService>(DeliverablesService);
      deliverablesServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          deliverablesService: () => deliverablesServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(DeliverablesUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.deliverables = deliverablesSample;
        deliverablesServiceStub.update.resolves(deliverablesSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(deliverablesServiceStub.update.calledWith(deliverablesSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        deliverablesServiceStub.create.resolves(entity);
        const wrapper = shallowMount(DeliverablesUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.deliverables = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(deliverablesServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        deliverablesServiceStub.find.resolves(deliverablesSample);
        deliverablesServiceStub.retrieve.resolves([deliverablesSample]);

        // WHEN
        route = {
          params: {
            deliverablesId: '' + deliverablesSample.id,
          },
        };
        const wrapper = shallowMount(DeliverablesUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.deliverables).toMatchObject(deliverablesSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        deliverablesServiceStub.find.resolves(deliverablesSample);
        const wrapper = shallowMount(DeliverablesUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
