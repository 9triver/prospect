/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Progressplanreturns from './progressplanreturns.vue';
import ProgressplanreturnsService from './progressplanreturns.service';
import AlertService from '@/shared/alert/alert.service';

type ProgressplanreturnsComponentType = InstanceType<typeof Progressplanreturns>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Progressplanreturns Management Component', () => {
    let progressplanreturnsServiceStub: SinonStubbedInstance<ProgressplanreturnsService>;
    let mountOptions: MountingOptions<ProgressplanreturnsComponentType>['global'];

    beforeEach(() => {
      progressplanreturnsServiceStub = sinon.createStubInstance<ProgressplanreturnsService>(ProgressplanreturnsService);
      progressplanreturnsServiceStub.retrieve.resolves({ headers: {} });

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
          progressplanreturnsService: () => progressplanreturnsServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        progressplanreturnsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(Progressplanreturns, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(progressplanreturnsServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.progressplanreturns[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: ProgressplanreturnsComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Progressplanreturns, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        progressplanreturnsServiceStub.retrieve.reset();
        progressplanreturnsServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        progressplanreturnsServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeProgressplanreturns();
        await comp.$nextTick(); // clear components

        // THEN
        expect(progressplanreturnsServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(progressplanreturnsServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
