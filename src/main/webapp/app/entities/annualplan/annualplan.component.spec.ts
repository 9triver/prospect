/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Annualplan from './annualplan.vue';
import AnnualplanService from './annualplan.service';
import AlertService from '@/shared/alert/alert.service';

type AnnualplanComponentType = InstanceType<typeof Annualplan>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Annualplan Management Component', () => {
    let annualplanServiceStub: SinonStubbedInstance<AnnualplanService>;
    let mountOptions: MountingOptions<AnnualplanComponentType>['global'];

    beforeEach(() => {
      annualplanServiceStub = sinon.createStubInstance<AnnualplanService>(AnnualplanService);
      annualplanServiceStub.retrieve.resolves({ headers: {} });

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
          annualplanService: () => annualplanServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        annualplanServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(Annualplan, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(annualplanServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.annualplans[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: AnnualplanComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Annualplan, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        annualplanServiceStub.retrieve.reset();
        annualplanServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        annualplanServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeAnnualplan();
        await comp.$nextTick(); // clear components

        // THEN
        expect(annualplanServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(annualplanServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
