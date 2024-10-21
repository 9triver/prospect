/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Workbag from './workbag.vue';
import WorkbagService from './workbag.service';
import AlertService from '@/shared/alert/alert.service';

type WorkbagComponentType = InstanceType<typeof Workbag>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Workbag Management Component', () => {
    let workbagServiceStub: SinonStubbedInstance<WorkbagService>;
    let mountOptions: MountingOptions<WorkbagComponentType>['global'];

    beforeEach(() => {
      workbagServiceStub = sinon.createStubInstance<WorkbagService>(WorkbagService);
      workbagServiceStub.retrieve.resolves({ headers: {} });

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
          workbagService: () => workbagServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        workbagServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(Workbag, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(workbagServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.workbags[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: WorkbagComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Workbag, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        workbagServiceStub.retrieve.reset();
        workbagServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        workbagServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeWorkbag();
        await comp.$nextTick(); // clear components

        // THEN
        expect(workbagServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(workbagServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
