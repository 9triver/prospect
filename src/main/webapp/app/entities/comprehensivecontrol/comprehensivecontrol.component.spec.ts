/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Comprehensivecontrol from './comprehensivecontrol.vue';
import ComprehensivecontrolService from './comprehensivecontrol.service';
import AlertService from '@/shared/alert/alert.service';

type ComprehensivecontrolComponentType = InstanceType<typeof Comprehensivecontrol>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Comprehensivecontrol Management Component', () => {
    let comprehensivecontrolServiceStub: SinonStubbedInstance<ComprehensivecontrolService>;
    let mountOptions: MountingOptions<ComprehensivecontrolComponentType>['global'];

    beforeEach(() => {
      comprehensivecontrolServiceStub = sinon.createStubInstance<ComprehensivecontrolService>(ComprehensivecontrolService);
      comprehensivecontrolServiceStub.retrieve.resolves({ headers: {} });

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
          comprehensivecontrolService: () => comprehensivecontrolServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        comprehensivecontrolServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Comprehensivecontrol, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comprehensivecontrolServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.comprehensivecontrols[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: ComprehensivecontrolComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Comprehensivecontrol, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        comprehensivecontrolServiceStub.retrieve.reset();
        comprehensivecontrolServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        comprehensivecontrolServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeComprehensivecontrol();
        await comp.$nextTick(); // clear components

        // THEN
        expect(comprehensivecontrolServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(comprehensivecontrolServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
