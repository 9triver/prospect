/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import QualityReturns from './quality-returns.vue';
import QualityReturnsService from './quality-returns.service';
import AlertService from '@/shared/alert/alert.service';

type QualityReturnsComponentType = InstanceType<typeof QualityReturns>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('QualityReturns Management Component', () => {
    let qualityReturnsServiceStub: SinonStubbedInstance<QualityReturnsService>;
    let mountOptions: MountingOptions<QualityReturnsComponentType>['global'];

    beforeEach(() => {
      qualityReturnsServiceStub = sinon.createStubInstance<QualityReturnsService>(QualityReturnsService);
      qualityReturnsServiceStub.retrieve.resolves({ headers: {} });

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
          qualityReturnsService: () => qualityReturnsServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        qualityReturnsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(QualityReturns, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(qualityReturnsServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.qualityReturns[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: QualityReturnsComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(QualityReturns, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        qualityReturnsServiceStub.retrieve.reset();
        qualityReturnsServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        qualityReturnsServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeQualityReturns();
        await comp.$nextTick(); // clear components

        // THEN
        expect(qualityReturnsServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(qualityReturnsServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
