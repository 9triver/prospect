/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Wbsmanage from './wbsmanage.vue';
import WbsmanageService from './wbsmanage.service';
import AlertService from '@/shared/alert/alert.service';

type WbsmanageComponentType = InstanceType<typeof Wbsmanage>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Wbsmanage Management Component', () => {
    let wbsmanageServiceStub: SinonStubbedInstance<WbsmanageService>;
    let mountOptions: MountingOptions<WbsmanageComponentType>['global'];

    beforeEach(() => {
      wbsmanageServiceStub = sinon.createStubInstance<WbsmanageService>(WbsmanageService);
      wbsmanageServiceStub.retrieve.resolves({ headers: {} });

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
          wbsmanageService: () => wbsmanageServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        wbsmanageServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Wbsmanage, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(wbsmanageServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.wbsmanages[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: WbsmanageComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Wbsmanage, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        wbsmanageServiceStub.retrieve.reset();
        wbsmanageServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        wbsmanageServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeWbsmanage();
        await comp.$nextTick(); // clear components

        // THEN
        expect(wbsmanageServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(wbsmanageServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
