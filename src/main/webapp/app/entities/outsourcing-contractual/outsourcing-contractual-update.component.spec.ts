/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import OutsourcingContractualUpdate from './outsourcing-contractual-update.vue';
import OutsourcingContractualService from './outsourcing-contractual.service';
import AlertService from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';
import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';

type OutsourcingContractualUpdateComponentType = InstanceType<typeof OutsourcingContractualUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const outsourcingContractualSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<OutsourcingContractualUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('OutsourcingContractual Management Update Component', () => {
    let comp: OutsourcingContractualUpdateComponentType;
    let outsourcingContractualServiceStub: SinonStubbedInstance<OutsourcingContractualService>;

    beforeEach(() => {
      route = {};
      outsourcingContractualServiceStub = sinon.createStubInstance<OutsourcingContractualService>(OutsourcingContractualService);
      outsourcingContractualServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          outsourcingContractualService: () => outsourcingContractualServiceStub,
          officersService: () =>
            sinon.createStubInstance<OfficersService>(OfficersService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
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
        const wrapper = shallowMount(OutsourcingContractualUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.outsourcingContractual = outsourcingContractualSample;
        outsourcingContractualServiceStub.update.resolves(outsourcingContractualSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(outsourcingContractualServiceStub.update.calledWith(outsourcingContractualSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        outsourcingContractualServiceStub.create.resolves(entity);
        const wrapper = shallowMount(OutsourcingContractualUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.outsourcingContractual = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(outsourcingContractualServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        outsourcingContractualServiceStub.find.resolves(outsourcingContractualSample);
        outsourcingContractualServiceStub.retrieve.resolves([outsourcingContractualSample]);

        // WHEN
        route = {
          params: {
            outsourcingContractualId: '' + outsourcingContractualSample.id,
          },
        };
        const wrapper = shallowMount(OutsourcingContractualUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.outsourcingContractual).toMatchObject(outsourcingContractualSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        outsourcingContractualServiceStub.find.resolves(outsourcingContractualSample);
        const wrapper = shallowMount(OutsourcingContractualUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
