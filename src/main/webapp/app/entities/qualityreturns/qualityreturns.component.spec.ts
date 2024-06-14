/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Qualityreturns from './qualityreturns.vue';
import QualityreturnsService from './qualityreturns.service';
import AlertService from '@/shared/alert/alert.service';

type QualityreturnsComponentType = InstanceType<typeof Qualityreturns>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Qualityreturns Management Component', () => {
    let qualityreturnsServiceStub: SinonStubbedInstance<QualityreturnsService>;
    let mountOptions: MountingOptions<QualityreturnsComponentType>['global'];

    beforeEach(() => {
      qualityreturnsServiceStub = sinon.createStubInstance<QualityreturnsService>(QualityreturnsService);
      qualityreturnsServiceStub.retrieve.resolves({ headers: {} });

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
          qualityreturnsService: () => qualityreturnsServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        qualityreturnsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Qualityreturns, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(qualityreturnsServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.qualityreturns[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: QualityreturnsComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Qualityreturns, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        qualityreturnsServiceStub.retrieve.reset();
        qualityreturnsServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        qualityreturnsServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeQualityreturns();
        await comp.$nextTick(); // clear components

        // THEN
        expect(qualityreturnsServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(qualityreturnsServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
