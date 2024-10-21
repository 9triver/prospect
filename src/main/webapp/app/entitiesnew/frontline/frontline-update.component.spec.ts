/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import FrontlineUpdate from './frontline-update.vue';
import FrontlineService from './frontline.service';
import AlertService from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';

type FrontlineUpdateComponentType = InstanceType<typeof FrontlineUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const frontlineSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<FrontlineUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Frontline Management Update Component', () => {
    let comp: FrontlineUpdateComponentType;
    let frontlineServiceStub: SinonStubbedInstance<FrontlineService>;

    beforeEach(() => {
      route = {};
      frontlineServiceStub = sinon.createStubInstance<FrontlineService>(FrontlineService);
      frontlineServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          frontlineService: () => frontlineServiceStub,
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
        const wrapper = shallowMount(FrontlineUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.frontline = frontlineSample;
        frontlineServiceStub.update.resolves(frontlineSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(frontlineServiceStub.update.calledWith(frontlineSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        frontlineServiceStub.create.resolves(entity);
        const wrapper = shallowMount(FrontlineUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.frontline = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(frontlineServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        frontlineServiceStub.find.resolves(frontlineSample);
        frontlineServiceStub.retrieve.resolves([frontlineSample]);

        // WHEN
        route = {
          params: {
            frontlineId: '' + frontlineSample.id,
          },
        };
        const wrapper = shallowMount(FrontlineUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.frontline).toMatchObject(frontlineSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        frontlineServiceStub.find.resolves(frontlineSample);
        const wrapper = shallowMount(FrontlineUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
