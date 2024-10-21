/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import RiskLevel from './risk-level.vue';
import RiskLevelService from './risk-level.service';
import AlertService from '@/shared/alert/alert.service';

type RiskLevelComponentType = InstanceType<typeof RiskLevel>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('RiskLevel Management Component', () => {
    let riskLevelServiceStub: SinonStubbedInstance<RiskLevelService>;
    let mountOptions: MountingOptions<RiskLevelComponentType>['global'];

    beforeEach(() => {
      riskLevelServiceStub = sinon.createStubInstance<RiskLevelService>(RiskLevelService);
      riskLevelServiceStub.retrieve.resolves({ headers: {} });

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
          riskLevelService: () => riskLevelServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        riskLevelServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(RiskLevel, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(riskLevelServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.riskLevels[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: RiskLevelComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(RiskLevel, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        riskLevelServiceStub.retrieve.reset();
        riskLevelServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        riskLevelServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeRiskLevel();
        await comp.$nextTick(); // clear components

        // THEN
        expect(riskLevelServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(riskLevelServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
