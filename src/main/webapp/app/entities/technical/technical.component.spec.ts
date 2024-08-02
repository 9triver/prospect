/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Technical from './technical.vue';
import TechnicalService from './technical.service';
import AlertService from '@/shared/alert/alert.service';

type TechnicalComponentType = InstanceType<typeof Technical>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Technical Management Component', () => {
    let technicalServiceStub: SinonStubbedInstance<TechnicalService>;
    let mountOptions: MountingOptions<TechnicalComponentType>['global'];

    beforeEach(() => {
      technicalServiceStub = sinon.createStubInstance<TechnicalService>(TechnicalService);
      technicalServiceStub.retrieve.resolves({ headers: {} });

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
          technicalService: () => technicalServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        technicalServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(Technical, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(technicalServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.technicals[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: TechnicalComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Technical, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        technicalServiceStub.retrieve.reset();
        technicalServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        technicalServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeTechnical();
        await comp.$nextTick(); // clear components

        // THEN
        expect(technicalServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(technicalServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
