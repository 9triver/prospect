/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Project from './project.vue';
import ProjectService from './project.service';
import AlertService from '@/shared/alert/alert.service';

type ProjectComponentType = InstanceType<typeof Project>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Project Management Component', () => {
    let projectServiceStub: SinonStubbedInstance<ProjectService>;
    let mountOptions: MountingOptions<ProjectComponentType>['global'];

    beforeEach(() => {
      projectServiceStub = sinon.createStubInstance<ProjectService>(ProjectService);
      projectServiceStub.retrieve.resolves({ headers: {} });

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
          projectService: () => projectServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        projectServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Project, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(projectServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.projects[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: ProjectComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Project, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        projectServiceStub.retrieve.reset();
        projectServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        projectServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeProject();
        await comp.$nextTick(); // clear components

        // THEN
        expect(projectServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(projectServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
