/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Riskidentification from './riskidentification.vue';
import RiskidentificationService from './riskidentification.service';
import AlertService from '@/shared/alert/alert.service';

type RiskidentificationComponentType = InstanceType<typeof Riskidentification>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Riskidentification Management Component', () => {
    let riskidentificationServiceStub: SinonStubbedInstance<RiskidentificationService>;
    let mountOptions: MountingOptions<RiskidentificationComponentType>['global'];

    beforeEach(() => {
      riskidentificationServiceStub = sinon.createStubInstance<RiskidentificationService>(RiskidentificationService);
      riskidentificationServiceStub.retrieve.resolves({ headers: {} });

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
          riskidentificationService: () => riskidentificationServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        riskidentificationServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(Riskidentification, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(riskidentificationServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.riskidentifications[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: RiskidentificationComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Riskidentification, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        riskidentificationServiceStub.retrieve.reset();
        riskidentificationServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        riskidentificationServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeRiskidentification();
        await comp.$nextTick(); // clear components

        // THEN
        expect(riskidentificationServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(riskidentificationServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
