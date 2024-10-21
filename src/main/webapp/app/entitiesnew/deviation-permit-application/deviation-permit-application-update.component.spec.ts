/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import DeviationPermitApplicationUpdate from './deviation-permit-application-update.vue';
import DeviationPermitApplicationService from './deviation-permit-application.service';
import AlertService from '@/shared/alert/alert.service';

import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';

type DeviationPermitApplicationUpdateComponentType = InstanceType<typeof DeviationPermitApplicationUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const deviationPermitApplicationSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<DeviationPermitApplicationUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('DeviationPermitApplication Management Update Component', () => {
    let comp: DeviationPermitApplicationUpdateComponentType;
    let deviationPermitApplicationServiceStub: SinonStubbedInstance<DeviationPermitApplicationService>;

    beforeEach(() => {
      route = {};
      deviationPermitApplicationServiceStub =
        sinon.createStubInstance<DeviationPermitApplicationService>(DeviationPermitApplicationService);
      deviationPermitApplicationServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          deviationPermitApplicationService: () => deviationPermitApplicationServiceStub,
          projectwbsService: () =>
            sinon.createStubInstance<ProjectwbsService>(ProjectwbsService, {
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
        const wrapper = shallowMount(DeviationPermitApplicationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.deviationPermitApplication = deviationPermitApplicationSample;
        deviationPermitApplicationServiceStub.update.resolves(deviationPermitApplicationSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(deviationPermitApplicationServiceStub.update.calledWith(deviationPermitApplicationSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        deviationPermitApplicationServiceStub.create.resolves(entity);
        const wrapper = shallowMount(DeviationPermitApplicationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.deviationPermitApplication = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(deviationPermitApplicationServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        deviationPermitApplicationServiceStub.find.resolves(deviationPermitApplicationSample);
        deviationPermitApplicationServiceStub.retrieve.resolves([deviationPermitApplicationSample]);

        // WHEN
        route = {
          params: {
            deviationPermitApplicationId: '' + deviationPermitApplicationSample.id,
          },
        };
        const wrapper = shallowMount(DeviationPermitApplicationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.deviationPermitApplication).toMatchObject(deviationPermitApplicationSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        deviationPermitApplicationServiceStub.find.resolves(deviationPermitApplicationSample);
        const wrapper = shallowMount(DeviationPermitApplicationUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
