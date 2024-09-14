/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import LeaveApplicationInfo from './leave-application-info.vue';
import LeaveApplicationInfoService from './leave-application-info.service';
import AlertService from '@/shared/alert/alert.service';

type LeaveApplicationInfoComponentType = InstanceType<typeof LeaveApplicationInfo>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('LeaveApplicationInfo Management Component', () => {
    let leaveApplicationInfoServiceStub: SinonStubbedInstance<LeaveApplicationInfoService>;
    let mountOptions: MountingOptions<LeaveApplicationInfoComponentType>['global'];

    beforeEach(() => {
      leaveApplicationInfoServiceStub = sinon.createStubInstance<LeaveApplicationInfoService>(LeaveApplicationInfoService);
      leaveApplicationInfoServiceStub.retrieve.resolves({ headers: {} });

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          bModal: bModalStub as any,
          'font-awesome-icon': true,
          'b-badge': true,
          'b-button': true,
          'router-link': true,
        },
        directives: {
          'b-modal': {},
        },
        provide: {
          alertService,
          leaveApplicationInfoService: () => leaveApplicationInfoServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        leaveApplicationInfoServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(LeaveApplicationInfo, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(leaveApplicationInfoServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.leaveApplicationInfos[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: LeaveApplicationInfoComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(LeaveApplicationInfo, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        leaveApplicationInfoServiceStub.retrieve.reset();
        leaveApplicationInfoServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        leaveApplicationInfoServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeLeaveApplicationInfo();
        await comp.$nextTick(); // clear components

        // THEN
        expect(leaveApplicationInfoServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(leaveApplicationInfoServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
