/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Wbsbaseline from './wbsbaseline.vue';
import WbsbaselineService from './wbsbaseline.service';
import AlertService from '@/shared/alert/alert.service';

type WbsbaselineComponentType = InstanceType<typeof Wbsbaseline>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Wbsbaseline Management Component', () => {
    let wbsbaselineServiceStub: SinonStubbedInstance<WbsbaselineService>;
    let mountOptions: MountingOptions<WbsbaselineComponentType>['global'];

    beforeEach(() => {
      wbsbaselineServiceStub = sinon.createStubInstance<WbsbaselineService>(WbsbaselineService);
      wbsbaselineServiceStub.retrieve.resolves({ headers: {} });

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
          wbsbaselineService: () => wbsbaselineServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        wbsbaselineServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Wbsbaseline, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(wbsbaselineServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.wbsbaselines[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: WbsbaselineComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Wbsbaseline, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        wbsbaselineServiceStub.retrieve.reset();
        wbsbaselineServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        wbsbaselineServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeWbsbaseline();
        await comp.$nextTick(); // clear components

        // THEN
        expect(wbsbaselineServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(wbsbaselineServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
