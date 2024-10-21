/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import RiskType from './risk-type.vue';
import RiskTypeService from './risk-type.service';
import AlertService from '@/shared/alert/alert.service';

type RiskTypeComponentType = InstanceType<typeof RiskType>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('RiskType Management Component', () => {
    let riskTypeServiceStub: SinonStubbedInstance<RiskTypeService>;
    let mountOptions: MountingOptions<RiskTypeComponentType>['global'];

    beforeEach(() => {
      riskTypeServiceStub = sinon.createStubInstance<RiskTypeService>(RiskTypeService);
      riskTypeServiceStub.retrieve.resolves({ headers: {} });

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
          riskTypeService: () => riskTypeServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        riskTypeServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(RiskType, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(riskTypeServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.riskTypes[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: RiskTypeComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(RiskType, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        riskTypeServiceStub.retrieve.reset();
        riskTypeServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        riskTypeServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeRiskType();
        await comp.$nextTick(); // clear components

        // THEN
        expect(riskTypeServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(riskTypeServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
