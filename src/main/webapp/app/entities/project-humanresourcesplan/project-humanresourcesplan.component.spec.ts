/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import ProjectHumanresourcesplan from './project-humanresourcesplan.vue';
import ProjectHumanresourcesplanService from './project-humanresourcesplan.service';
import AlertService from '@/shared/alert/alert.service';

type ProjectHumanresourcesplanComponentType = InstanceType<typeof ProjectHumanresourcesplan>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('ProjectHumanresourcesplan Management Component', () => {
    let projectHumanresourcesplanServiceStub: SinonStubbedInstance<ProjectHumanresourcesplanService>;
    let mountOptions: MountingOptions<ProjectHumanresourcesplanComponentType>['global'];

    beforeEach(() => {
      projectHumanresourcesplanServiceStub = sinon.createStubInstance<ProjectHumanresourcesplanService>(ProjectHumanresourcesplanService);
      projectHumanresourcesplanServiceStub.retrieve.resolves({ headers: {} });

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
          projectHumanresourcesplanService: () => projectHumanresourcesplanServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        projectHumanresourcesplanServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(ProjectHumanresourcesplan, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(projectHumanresourcesplanServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.projectHumanresourcesplans[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: ProjectHumanresourcesplanComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(ProjectHumanresourcesplan, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        projectHumanresourcesplanServiceStub.retrieve.reset();
        projectHumanresourcesplanServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        projectHumanresourcesplanServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeProjectHumanresourcesplan();
        await comp.$nextTick(); // clear components

        // THEN
        expect(projectHumanresourcesplanServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(projectHumanresourcesplanServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
