/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import SecrecymanagementUpdate from './secrecymanagement-update.vue';
import SecrecymanagementService from './secrecymanagement.service';
import AlertService from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';

type SecrecymanagementUpdateComponentType = InstanceType<typeof SecrecymanagementUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const secrecymanagementSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<SecrecymanagementUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Secrecymanagement Management Update Component', () => {
    let comp: SecrecymanagementUpdateComponentType;
    let secrecymanagementServiceStub: SinonStubbedInstance<SecrecymanagementService>;

    beforeEach(() => {
      route = {};
      secrecymanagementServiceStub = sinon.createStubInstance<SecrecymanagementService>(SecrecymanagementService);
      secrecymanagementServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          secrecymanagementService: () => secrecymanagementServiceStub,
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
        const wrapper = shallowMount(SecrecymanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.secrecymanagement = secrecymanagementSample;
        secrecymanagementServiceStub.update.resolves(secrecymanagementSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(secrecymanagementServiceStub.update.calledWith(secrecymanagementSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        secrecymanagementServiceStub.create.resolves(entity);
        const wrapper = shallowMount(SecrecymanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.secrecymanagement = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(secrecymanagementServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        secrecymanagementServiceStub.find.resolves(secrecymanagementSample);
        secrecymanagementServiceStub.retrieve.resolves([secrecymanagementSample]);

        // WHEN
        route = {
          params: {
            secrecymanagementId: '' + secrecymanagementSample.id,
          },
        };
        const wrapper = shallowMount(SecrecymanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.secrecymanagement).toMatchObject(secrecymanagementSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        secrecymanagementServiceStub.find.resolves(secrecymanagementSample);
        const wrapper = shallowMount(SecrecymanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
