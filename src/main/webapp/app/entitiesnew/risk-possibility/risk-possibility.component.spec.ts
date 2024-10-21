/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import RiskPossibility from './risk-possibility.vue';
import RiskPossibilityService from './risk-possibility.service';
import AlertService from '@/shared/alert/alert.service';

type RiskPossibilityComponentType = InstanceType<typeof RiskPossibility>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('RiskPossibility Management Component', () => {
    let riskPossibilityServiceStub: SinonStubbedInstance<RiskPossibilityService>;
    let mountOptions: MountingOptions<RiskPossibilityComponentType>['global'];

    beforeEach(() => {
      riskPossibilityServiceStub = sinon.createStubInstance<RiskPossibilityService>(RiskPossibilityService);
      riskPossibilityServiceStub.retrieve.resolves({ headers: {} });

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
          riskPossibilityService: () => riskPossibilityServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        riskPossibilityServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(RiskPossibility, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(riskPossibilityServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.riskPossibilities[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: RiskPossibilityComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(RiskPossibility, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        riskPossibilityServiceStub.retrieve.reset();
        riskPossibilityServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        riskPossibilityServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeRiskPossibility();
        await comp.$nextTick(); // clear components

        // THEN
        expect(riskPossibilityServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(riskPossibilityServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
