/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Riskreport from './riskreport.vue';
import RiskreportService from './riskreport.service';
import AlertService from '@/shared/alert/alert.service';

type RiskreportComponentType = InstanceType<typeof Riskreport>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Riskreport Management Component', () => {
    let riskreportServiceStub: SinonStubbedInstance<RiskreportService>;
    let mountOptions: MountingOptions<RiskreportComponentType>['global'];

    beforeEach(() => {
      riskreportServiceStub = sinon.createStubInstance<RiskreportService>(RiskreportService);
      riskreportServiceStub.retrieve.resolves({ headers: {} });

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
          riskreportService: () => riskreportServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        riskreportServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Riskreport, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(riskreportServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.riskreports[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: RiskreportComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Riskreport, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        riskreportServiceStub.retrieve.reset();
        riskreportServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        riskreportServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeRiskreport();
        await comp.$nextTick(); // clear components

        // THEN
        expect(riskreportServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(riskreportServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
