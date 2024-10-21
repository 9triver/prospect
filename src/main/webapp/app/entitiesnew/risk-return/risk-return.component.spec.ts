/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import RiskReturn from './risk-return.vue';
import RiskReturnService from './risk-return.service';
import AlertService from '@/shared/alert/alert.service';

type RiskReturnComponentType = InstanceType<typeof RiskReturn>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('RiskReturn Management Component', () => {
    let riskReturnServiceStub: SinonStubbedInstance<RiskReturnService>;
    let mountOptions: MountingOptions<RiskReturnComponentType>['global'];

    beforeEach(() => {
      riskReturnServiceStub = sinon.createStubInstance<RiskReturnService>(RiskReturnService);
      riskReturnServiceStub.retrieve.resolves({ headers: {} });

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
          riskReturnService: () => riskReturnServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        riskReturnServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(RiskReturn, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(riskReturnServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.riskReturns[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: RiskReturnComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(RiskReturn, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        riskReturnServiceStub.retrieve.reset();
        riskReturnServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        riskReturnServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeRiskReturn();
        await comp.$nextTick(); // clear components

        // THEN
        expect(riskReturnServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(riskReturnServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
