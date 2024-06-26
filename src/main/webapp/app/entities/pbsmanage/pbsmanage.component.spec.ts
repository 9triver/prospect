/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Pbsmanage from './pbsmanage.vue';
import PbsmanageService from './pbsmanage.service';
import AlertService from '@/shared/alert/alert.service';

type PbsmanageComponentType = InstanceType<typeof Pbsmanage>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Pbsmanage Management Component', () => {
    let pbsmanageServiceStub: SinonStubbedInstance<PbsmanageService>;
    let mountOptions: MountingOptions<PbsmanageComponentType>['global'];

    beforeEach(() => {
      pbsmanageServiceStub = sinon.createStubInstance<PbsmanageService>(PbsmanageService);
      pbsmanageServiceStub.retrieve.resolves({ headers: {} });

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
          pbsmanageService: () => pbsmanageServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        pbsmanageServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(Pbsmanage, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(pbsmanageServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.pbsmanages[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: PbsmanageComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Pbsmanage, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        pbsmanageServiceStub.retrieve.reset();
        pbsmanageServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        pbsmanageServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removePbsmanage();
        await comp.$nextTick(); // clear components

        // THEN
        expect(pbsmanageServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(pbsmanageServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
