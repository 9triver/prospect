/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import RegularInspection from './regular-inspection.vue';
import RegularInspectionService from './regular-inspection.service';
import AlertService from '@/shared/alert/alert.service';

type RegularInspectionComponentType = InstanceType<typeof RegularInspection>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('RegularInspection Management Component', () => {
    let regularInspectionServiceStub: SinonStubbedInstance<RegularInspectionService>;
    let mountOptions: MountingOptions<RegularInspectionComponentType>['global'];

    beforeEach(() => {
      regularInspectionServiceStub = sinon.createStubInstance<RegularInspectionService>(RegularInspectionService);
      regularInspectionServiceStub.retrieve.resolves({ headers: {} });

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
          regularInspectionService: () => regularInspectionServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        regularInspectionServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(RegularInspection, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(regularInspectionServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.regularInspections[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: RegularInspectionComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(RegularInspection, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        regularInspectionServiceStub.retrieve.reset();
        regularInspectionServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        regularInspectionServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeRegularInspection();
        await comp.$nextTick(); // clear components

        // THEN
        expect(regularInspectionServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(regularInspectionServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
