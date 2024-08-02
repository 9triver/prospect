/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import ProjectRisk from './project-risk.vue';
import ProjectRiskService from './project-risk.service';
import AlertService from '@/shared/alert/alert.service';

type ProjectRiskComponentType = InstanceType<typeof ProjectRisk>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('ProjectRisk Management Component', () => {
    let projectRiskServiceStub: SinonStubbedInstance<ProjectRiskService>;
    let mountOptions: MountingOptions<ProjectRiskComponentType>['global'];

    beforeEach(() => {
      projectRiskServiceStub = sinon.createStubInstance<ProjectRiskService>(ProjectRiskService);
      projectRiskServiceStub.retrieve.resolves({ headers: {} });

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
          projectRiskService: () => projectRiskServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        projectRiskServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(ProjectRisk, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(projectRiskServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.projectRisks[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: ProjectRiskComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(ProjectRisk, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        projectRiskServiceStub.retrieve.reset();
        projectRiskServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        projectRiskServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeProjectRisk();
        await comp.$nextTick(); // clear components

        // THEN
        expect(projectRiskServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(projectRiskServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
