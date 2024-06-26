/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Riskmanagement from './riskmanagement.vue';
import RiskmanagementService from './riskmanagement.service';
import AlertService from '@/shared/alert/alert.service';

type RiskmanagementComponentType = InstanceType<typeof Riskmanagement>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Riskmanagement Management Component', () => {
    let riskmanagementServiceStub: SinonStubbedInstance<RiskmanagementService>;
    let mountOptions: MountingOptions<RiskmanagementComponentType>['global'];

    beforeEach(() => {
      riskmanagementServiceStub = sinon.createStubInstance<RiskmanagementService>(RiskmanagementService);
      riskmanagementServiceStub.retrieve.resolves({ headers: {} });

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
          riskmanagementService: () => riskmanagementServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        riskmanagementServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(Riskmanagement, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(riskmanagementServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.riskmanagements[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: RiskmanagementComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Riskmanagement, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        riskmanagementServiceStub.retrieve.reset();
        riskmanagementServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        riskmanagementServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeRiskmanagement();
        await comp.$nextTick(); // clear components

        // THEN
        expect(riskmanagementServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(riskmanagementServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
