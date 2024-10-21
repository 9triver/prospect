/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import SubjectCostBudget from './subject-cost-budget.vue';
import SubjectCostBudgetService from './subject-cost-budget.service';
import AlertService from '@/shared/alert/alert.service';

type SubjectCostBudgetComponentType = InstanceType<typeof SubjectCostBudget>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('SubjectCostBudget Management Component', () => {
    let subjectCostBudgetServiceStub: SinonStubbedInstance<SubjectCostBudgetService>;
    let mountOptions: MountingOptions<SubjectCostBudgetComponentType>['global'];

    beforeEach(() => {
      subjectCostBudgetServiceStub = sinon.createStubInstance<SubjectCostBudgetService>(SubjectCostBudgetService);
      subjectCostBudgetServiceStub.retrieve.resolves({ headers: {} });

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
          subjectCostBudgetService: () => subjectCostBudgetServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        subjectCostBudgetServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(SubjectCostBudget, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(subjectCostBudgetServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.subjectCostBudgets[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: SubjectCostBudgetComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(SubjectCostBudget, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        subjectCostBudgetServiceStub.retrieve.reset();
        subjectCostBudgetServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        subjectCostBudgetServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeSubjectCostBudget();
        await comp.$nextTick(); // clear components

        // THEN
        expect(subjectCostBudgetServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(subjectCostBudgetServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
