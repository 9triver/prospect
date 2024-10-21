/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Work from './work.vue';
import WorkService from './work.service';
import AlertService from '@/shared/alert/alert.service';

type WorkComponentType = InstanceType<typeof Work>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Work Management Component', () => {
    let workServiceStub: SinonStubbedInstance<WorkService>;
    let mountOptions: MountingOptions<WorkComponentType>['global'];

    beforeEach(() => {
      workServiceStub = sinon.createStubInstance<WorkService>(WorkService);
      workServiceStub.retrieve.resolves({ headers: {} });

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
          workService: () => workServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        workServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(Work, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(workServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.works[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: WorkComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Work, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        workServiceStub.retrieve.reset();
        workServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        workServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeWork();
        await comp.$nextTick(); // clear components

        // THEN
        expect(workServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(workServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
