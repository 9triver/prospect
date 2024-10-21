/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import LeaveApplicationInfoUpdate from './leave-application-info-update.vue';
import LeaveApplicationInfoService from './leave-application-info.service';
import AlertService from '@/shared/alert/alert.service';

type LeaveApplicationInfoUpdateComponentType = InstanceType<typeof LeaveApplicationInfoUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const leaveApplicationInfoSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<LeaveApplicationInfoUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('LeaveApplicationInfo Management Update Component', () => {
    let comp: LeaveApplicationInfoUpdateComponentType;
    let leaveApplicationInfoServiceStub: SinonStubbedInstance<LeaveApplicationInfoService>;

    beforeEach(() => {
      route = {};
      leaveApplicationInfoServiceStub = sinon.createStubInstance<LeaveApplicationInfoService>(LeaveApplicationInfoService);
      leaveApplicationInfoServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          leaveApplicationInfoService: () => leaveApplicationInfoServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(LeaveApplicationInfoUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.leaveApplicationInfo = leaveApplicationInfoSample;
        leaveApplicationInfoServiceStub.update.resolves(leaveApplicationInfoSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(leaveApplicationInfoServiceStub.update.calledWith(leaveApplicationInfoSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        leaveApplicationInfoServiceStub.create.resolves(entity);
        const wrapper = shallowMount(LeaveApplicationInfoUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.leaveApplicationInfo = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(leaveApplicationInfoServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        leaveApplicationInfoServiceStub.find.resolves(leaveApplicationInfoSample);
        leaveApplicationInfoServiceStub.retrieve.resolves([leaveApplicationInfoSample]);

        // WHEN
        route = {
          params: {
            leaveApplicationInfoId: '' + leaveApplicationInfoSample.id,
          },
        };
        const wrapper = shallowMount(LeaveApplicationInfoUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.leaveApplicationInfo).toMatchObject(leaveApplicationInfoSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        leaveApplicationInfoServiceStub.find.resolves(leaveApplicationInfoSample);
        const wrapper = shallowMount(LeaveApplicationInfoUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
