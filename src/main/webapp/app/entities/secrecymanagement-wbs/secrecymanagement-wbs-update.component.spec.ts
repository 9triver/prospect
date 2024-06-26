/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import SecrecymanagementWbsUpdate from './secrecymanagement-wbs-update.vue';
import SecrecymanagementWbsService from './secrecymanagement-wbs.service';
import AlertService from '@/shared/alert/alert.service';

import SecrecysystemService from '@/entities/secrecysystem/secrecysystem.service';
import ProjectSecrecyService from '@/entities/project-secrecy/project-secrecy.service';

type SecrecymanagementWbsUpdateComponentType = InstanceType<typeof SecrecymanagementWbsUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const secrecymanagementWbsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<SecrecymanagementWbsUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('SecrecymanagementWbs Management Update Component', () => {
    let comp: SecrecymanagementWbsUpdateComponentType;
    let secrecymanagementWbsServiceStub: SinonStubbedInstance<SecrecymanagementWbsService>;

    beforeEach(() => {
      route = {};
      secrecymanagementWbsServiceStub = sinon.createStubInstance<SecrecymanagementWbsService>(SecrecymanagementWbsService);
      secrecymanagementWbsServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          secrecymanagementWbsService: () => secrecymanagementWbsServiceStub,
          secrecysystemService: () =>
            sinon.createStubInstance<SecrecysystemService>(SecrecysystemService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          projectSecrecyService: () =>
            sinon.createStubInstance<ProjectSecrecyService>(ProjectSecrecyService, {
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
        const wrapper = shallowMount(SecrecymanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.secrecymanagementWbs = secrecymanagementWbsSample;
        secrecymanagementWbsServiceStub.update.resolves(secrecymanagementWbsSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(secrecymanagementWbsServiceStub.update.calledWith(secrecymanagementWbsSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        secrecymanagementWbsServiceStub.create.resolves(entity);
        const wrapper = shallowMount(SecrecymanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.secrecymanagementWbs = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(secrecymanagementWbsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        secrecymanagementWbsServiceStub.find.resolves(secrecymanagementWbsSample);
        secrecymanagementWbsServiceStub.retrieve.resolves([secrecymanagementWbsSample]);

        // WHEN
        route = {
          params: {
            secrecymanagementWbsId: '' + secrecymanagementWbsSample.id,
          },
        };
        const wrapper = shallowMount(SecrecymanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.secrecymanagementWbs).toMatchObject(secrecymanagementWbsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        secrecymanagementWbsServiceStub.find.resolves(secrecymanagementWbsSample);
        const wrapper = shallowMount(SecrecymanagementWbsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
