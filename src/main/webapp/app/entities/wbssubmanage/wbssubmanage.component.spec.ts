/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Wbssubmanage from './wbssubmanage.vue';
import WbssubmanageService from './wbssubmanage.service';
import AlertService from '@/shared/alert/alert.service';

type WbssubmanageComponentType = InstanceType<typeof Wbssubmanage>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Wbssubmanage Management Component', () => {
    let wbssubmanageServiceStub: SinonStubbedInstance<WbssubmanageService>;
    let mountOptions: MountingOptions<WbssubmanageComponentType>['global'];

    beforeEach(() => {
      wbssubmanageServiceStub = sinon.createStubInstance<WbssubmanageService>(WbssubmanageService);
      wbssubmanageServiceStub.retrieve.resolves({ headers: {} });

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
          wbssubmanageService: () => wbssubmanageServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        wbssubmanageServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Wbssubmanage, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(wbssubmanageServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.wbssubmanages[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: WbssubmanageComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Wbssubmanage, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        wbssubmanageServiceStub.retrieve.reset();
        wbssubmanageServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        wbssubmanageServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeWbssubmanage();
        await comp.$nextTick(); // clear components

        // THEN
        expect(wbssubmanageServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(wbssubmanageServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
