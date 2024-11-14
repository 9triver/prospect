/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import ProjectBudget from './project-budget.vue';
import ProjectBudgetService from './project-budget.service';
import AlertService from '@/shared/alert/alert.service';

type ProjectBudgetComponentType = InstanceType<typeof ProjectBudget>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('ProjectBudget Management Component', () => {
    let projectBudgetServiceStub: SinonStubbedInstance<ProjectBudgetService>;
    let mountOptions: MountingOptions<ProjectBudgetComponentType>['global'];

    beforeEach(() => {
      projectBudgetServiceStub = sinon.createStubInstance<ProjectBudgetService>(ProjectBudgetService);
      projectBudgetServiceStub.retrieve.resolves({ headers: {} });

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
          projectBudgetService: () => projectBudgetServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        projectBudgetServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(ProjectBudget, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(projectBudgetServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.projectBudgets[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: ProjectBudgetComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(ProjectBudget, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        projectBudgetServiceStub.retrieve.reset();
        projectBudgetServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        projectBudgetServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeProjectBudget();
        await comp.$nextTick(); // clear components

        // THEN
        expect(projectBudgetServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(projectBudgetServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
