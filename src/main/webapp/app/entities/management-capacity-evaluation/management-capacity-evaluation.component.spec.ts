/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import ManagementCapacityEvaluation from './management-capacity-evaluation.vue';
import ManagementCapacityEvaluationService from './management-capacity-evaluation.service';
import AlertService from '@/shared/alert/alert.service';

type ManagementCapacityEvaluationComponentType = InstanceType<typeof ManagementCapacityEvaluation>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('ManagementCapacityEvaluation Management Component', () => {
    let managementCapacityEvaluationServiceStub: SinonStubbedInstance<ManagementCapacityEvaluationService>;
    let mountOptions: MountingOptions<ManagementCapacityEvaluationComponentType>['global'];

    beforeEach(() => {
      managementCapacityEvaluationServiceStub =
        sinon.createStubInstance<ManagementCapacityEvaluationService>(ManagementCapacityEvaluationService);
      managementCapacityEvaluationServiceStub.retrieve.resolves({ headers: {} });

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
          managementCapacityEvaluationService: () => managementCapacityEvaluationServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        managementCapacityEvaluationServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(ManagementCapacityEvaluation, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(managementCapacityEvaluationServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.managementCapacityEvaluations[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: ManagementCapacityEvaluationComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(ManagementCapacityEvaluation, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        managementCapacityEvaluationServiceStub.retrieve.reset();
        managementCapacityEvaluationServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        managementCapacityEvaluationServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeManagementCapacityEvaluation();
        await comp.$nextTick(); // clear components

        // THEN
        expect(managementCapacityEvaluationServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(managementCapacityEvaluationServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
