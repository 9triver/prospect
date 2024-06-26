/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ComprehensiveledgerUpdate from './comprehensiveledger-update.vue';
import ComprehensiveledgerService from './comprehensiveledger.service';
import AlertService from '@/shared/alert/alert.service';

type ComprehensiveledgerUpdateComponentType = InstanceType<typeof ComprehensiveledgerUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const comprehensiveledgerSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ComprehensiveledgerUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Comprehensiveledger Management Update Component', () => {
    let comp: ComprehensiveledgerUpdateComponentType;
    let comprehensiveledgerServiceStub: SinonStubbedInstance<ComprehensiveledgerService>;

    beforeEach(() => {
      route = {};
      comprehensiveledgerServiceStub = sinon.createStubInstance<ComprehensiveledgerService>(ComprehensiveledgerService);
      comprehensiveledgerServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          comprehensiveledgerService: () => comprehensiveledgerServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(ComprehensiveledgerUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.comprehensiveledger = comprehensiveledgerSample;
        comprehensiveledgerServiceStub.update.resolves(comprehensiveledgerSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(comprehensiveledgerServiceStub.update.calledWith(comprehensiveledgerSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comprehensiveledgerServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ComprehensiveledgerUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.comprehensiveledger = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(comprehensiveledgerServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        comprehensiveledgerServiceStub.find.resolves(comprehensiveledgerSample);
        comprehensiveledgerServiceStub.retrieve.resolves([comprehensiveledgerSample]);

        // WHEN
        route = {
          params: {
            comprehensiveledgerId: '' + comprehensiveledgerSample.id,
          },
        };
        const wrapper = shallowMount(ComprehensiveledgerUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.comprehensiveledger).toMatchObject(comprehensiveledgerSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comprehensiveledgerServiceStub.find.resolves(comprehensiveledgerSample);
        const wrapper = shallowMount(ComprehensiveledgerUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
