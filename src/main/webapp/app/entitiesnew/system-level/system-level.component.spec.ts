/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import SystemLevel from './system-level.vue';
import SystemLevelService from './system-level.service';
import AlertService from '@/shared/alert/alert.service';

type SystemLevelComponentType = InstanceType<typeof SystemLevel>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('SystemLevel Management Component', () => {
    let systemLevelServiceStub: SinonStubbedInstance<SystemLevelService>;
    let mountOptions: MountingOptions<SystemLevelComponentType>['global'];

    beforeEach(() => {
      systemLevelServiceStub = sinon.createStubInstance<SystemLevelService>(SystemLevelService);
      systemLevelServiceStub.retrieve.resolves({ headers: {} });

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
          systemLevelService: () => systemLevelServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        systemLevelServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(SystemLevel, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(systemLevelServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.systemLevels[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: SystemLevelComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(SystemLevel, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        systemLevelServiceStub.retrieve.reset();
        systemLevelServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        systemLevelServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeSystemLevel();
        await comp.$nextTick(); // clear components

        // THEN
        expect(systemLevelServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(systemLevelServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
