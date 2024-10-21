/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Qualitytozero from './qualitytozero.vue';
import QualitytozeroService from './qualitytozero.service';
import AlertService from '@/shared/alert/alert.service';

type QualitytozeroComponentType = InstanceType<typeof Qualitytozero>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Qualitytozero Management Component', () => {
    let qualitytozeroServiceStub: SinonStubbedInstance<QualitytozeroService>;
    let mountOptions: MountingOptions<QualitytozeroComponentType>['global'];

    beforeEach(() => {
      qualitytozeroServiceStub = sinon.createStubInstance<QualitytozeroService>(QualitytozeroService);
      qualitytozeroServiceStub.retrieve.resolves({ headers: {} });

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
          qualitytozeroService: () => qualitytozeroServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        qualitytozeroServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Qualitytozero, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(qualitytozeroServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.qualitytozeros[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: QualitytozeroComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Qualitytozero, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        qualitytozeroServiceStub.retrieve.reset();
        qualitytozeroServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        qualitytozeroServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeQualitytozero();
        await comp.$nextTick(); // clear components

        // THEN
        expect(qualitytozeroServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(qualitytozeroServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
