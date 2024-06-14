/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import EvaluationCriteria from './evaluation-criteria.vue';
import EvaluationCriteriaService from './evaluation-criteria.service';
import AlertService from '@/shared/alert/alert.service';

type EvaluationCriteriaComponentType = InstanceType<typeof EvaluationCriteria>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('EvaluationCriteria Management Component', () => {
    let evaluationCriteriaServiceStub: SinonStubbedInstance<EvaluationCriteriaService>;
    let mountOptions: MountingOptions<EvaluationCriteriaComponentType>['global'];

    beforeEach(() => {
      evaluationCriteriaServiceStub = sinon.createStubInstance<EvaluationCriteriaService>(EvaluationCriteriaService);
      evaluationCriteriaServiceStub.retrieve.resolves({ headers: {} });

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
          evaluationCriteriaService: () => evaluationCriteriaServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        evaluationCriteriaServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(EvaluationCriteria, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(evaluationCriteriaServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.evaluationCriteria[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: EvaluationCriteriaComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(EvaluationCriteria, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        evaluationCriteriaServiceStub.retrieve.reset();
        evaluationCriteriaServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        evaluationCriteriaServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeEvaluationCriteria();
        await comp.$nextTick(); // clear components

        // THEN
        expect(evaluationCriteriaServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(evaluationCriteriaServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
