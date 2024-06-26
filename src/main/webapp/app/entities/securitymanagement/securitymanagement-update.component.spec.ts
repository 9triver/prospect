/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import SecuritymanagementUpdate from './securitymanagement-update.vue';
import SecuritymanagementService from './securitymanagement.service';
import AlertService from '@/shared/alert/alert.service';

import SecuritymanagementWbsService from '@/entities/securitymanagement-wbs/securitymanagement-wbs.service';

type SecuritymanagementUpdateComponentType = InstanceType<typeof SecuritymanagementUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const securitymanagementSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<SecuritymanagementUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Securitymanagement Management Update Component', () => {
    let comp: SecuritymanagementUpdateComponentType;
    let securitymanagementServiceStub: SinonStubbedInstance<SecuritymanagementService>;

    beforeEach(() => {
      route = {};
      securitymanagementServiceStub = sinon.createStubInstance<SecuritymanagementService>(SecuritymanagementService);
      securitymanagementServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          securitymanagementService: () => securitymanagementServiceStub,
          securitymanagementWbsService: () =>
            sinon.createStubInstance<SecuritymanagementWbsService>(SecuritymanagementWbsService, {
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
        const wrapper = shallowMount(SecuritymanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.securitymanagement = securitymanagementSample;
        securitymanagementServiceStub.update.resolves(securitymanagementSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(securitymanagementServiceStub.update.calledWith(securitymanagementSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        securitymanagementServiceStub.create.resolves(entity);
        const wrapper = shallowMount(SecuritymanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.securitymanagement = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(securitymanagementServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        securitymanagementServiceStub.find.resolves(securitymanagementSample);
        securitymanagementServiceStub.retrieve.resolves([securitymanagementSample]);

        // WHEN
        route = {
          params: {
            securitymanagementId: '' + securitymanagementSample.id,
          },
        };
        const wrapper = shallowMount(SecuritymanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.securitymanagement).toMatchObject(securitymanagementSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        securitymanagementServiceStub.find.resolves(securitymanagementSample);
        const wrapper = shallowMount(SecuritymanagementUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
