/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Pbssubmanage from './pbssubmanage.vue';
import PbssubmanageService from './pbssubmanage.service';
import AlertService from '@/shared/alert/alert.service';

type PbssubmanageComponentType = InstanceType<typeof Pbssubmanage>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Pbssubmanage Management Component', () => {
    let pbssubmanageServiceStub: SinonStubbedInstance<PbssubmanageService>;
    let mountOptions: MountingOptions<PbssubmanageComponentType>['global'];

    beforeEach(() => {
      pbssubmanageServiceStub = sinon.createStubInstance<PbssubmanageService>(PbssubmanageService);
      pbssubmanageServiceStub.retrieve.resolves({ headers: {} });

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
          pbssubmanageService: () => pbssubmanageServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        pbssubmanageServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Pbssubmanage, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(pbssubmanageServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.pbssubmanages[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: PbssubmanageComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Pbssubmanage, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        pbssubmanageServiceStub.retrieve.reset();
        pbssubmanageServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        pbssubmanageServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removePbssubmanage();
        await comp.$nextTick(); // clear components

        // THEN
        expect(pbssubmanageServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(pbssubmanageServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
