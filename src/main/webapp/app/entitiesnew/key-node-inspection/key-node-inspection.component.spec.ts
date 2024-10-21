/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import KeyNodeInspection from './key-node-inspection.vue';
import KeyNodeInspectionService from './key-node-inspection.service';
import AlertService from '@/shared/alert/alert.service';

type KeyNodeInspectionComponentType = InstanceType<typeof KeyNodeInspection>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('KeyNodeInspection Management Component', () => {
    let keyNodeInspectionServiceStub: SinonStubbedInstance<KeyNodeInspectionService>;
    let mountOptions: MountingOptions<KeyNodeInspectionComponentType>['global'];

    beforeEach(() => {
      keyNodeInspectionServiceStub = sinon.createStubInstance<KeyNodeInspectionService>(KeyNodeInspectionService);
      keyNodeInspectionServiceStub.retrieve.resolves({ headers: {} });

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
          keyNodeInspectionService: () => keyNodeInspectionServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        keyNodeInspectionServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(KeyNodeInspection, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(keyNodeInspectionServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.keyNodeInspections[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: KeyNodeInspectionComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(KeyNodeInspection, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        keyNodeInspectionServiceStub.retrieve.reset();
        keyNodeInspectionServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        keyNodeInspectionServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeKeyNodeInspection();
        await comp.$nextTick(); // clear components

        // THEN
        expect(keyNodeInspectionServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(keyNodeInspectionServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
