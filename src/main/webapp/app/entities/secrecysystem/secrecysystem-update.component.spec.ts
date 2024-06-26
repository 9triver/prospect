/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import SecrecysystemUpdate from './secrecysystem-update.vue';
import SecrecysystemService from './secrecysystem.service';
import AlertService from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';

type SecrecysystemUpdateComponentType = InstanceType<typeof SecrecysystemUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const secrecysystemSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<SecrecysystemUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Secrecysystem Management Update Component', () => {
    let comp: SecrecysystemUpdateComponentType;
    let secrecysystemServiceStub: SinonStubbedInstance<SecrecysystemService>;

    beforeEach(() => {
      route = {};
      secrecysystemServiceStub = sinon.createStubInstance<SecrecysystemService>(SecrecysystemService);
      secrecysystemServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          secrecysystemService: () => secrecysystemServiceStub,
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
        const wrapper = shallowMount(SecrecysystemUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.secrecysystem = secrecysystemSample;
        secrecysystemServiceStub.update.resolves(secrecysystemSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(secrecysystemServiceStub.update.calledWith(secrecysystemSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        secrecysystemServiceStub.create.resolves(entity);
        const wrapper = shallowMount(SecrecysystemUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.secrecysystem = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(secrecysystemServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        secrecysystemServiceStub.find.resolves(secrecysystemSample);
        secrecysystemServiceStub.retrieve.resolves([secrecysystemSample]);

        // WHEN
        route = {
          params: {
            secrecysystemId: '' + secrecysystemSample.id,
          },
        };
        const wrapper = shallowMount(SecrecysystemUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.secrecysystem).toMatchObject(secrecysystemSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        secrecysystemServiceStub.find.resolves(secrecysystemSample);
        const wrapper = shallowMount(SecrecysystemUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
