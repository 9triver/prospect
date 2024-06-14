/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Comprehensiveledger from './comprehensiveledger.vue';
import ComprehensiveledgerService from './comprehensiveledger.service';
import AlertService from '@/shared/alert/alert.service';

type ComprehensiveledgerComponentType = InstanceType<typeof Comprehensiveledger>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Comprehensiveledger Management Component', () => {
    let comprehensiveledgerServiceStub: SinonStubbedInstance<ComprehensiveledgerService>;
    let mountOptions: MountingOptions<ComprehensiveledgerComponentType>['global'];

    beforeEach(() => {
      comprehensiveledgerServiceStub = sinon.createStubInstance<ComprehensiveledgerService>(ComprehensiveledgerService);
      comprehensiveledgerServiceStub.retrieve.resolves({ headers: {} });

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
          comprehensiveledgerService: () => comprehensiveledgerServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        comprehensiveledgerServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Comprehensiveledger, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comprehensiveledgerServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.comprehensiveledgers[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: ComprehensiveledgerComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Comprehensiveledger, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        comprehensiveledgerServiceStub.retrieve.reset();
        comprehensiveledgerServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        comprehensiveledgerServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeComprehensiveledger();
        await comp.$nextTick(); // clear components

        // THEN
        expect(comprehensiveledgerServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(comprehensiveledgerServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
