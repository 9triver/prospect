/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import ProjectSecrecy from './project-secrecy.vue';
import ProjectSecrecyService from './project-secrecy.service';
import AlertService from '@/shared/alert/alert.service';

type ProjectSecrecyComponentType = InstanceType<typeof ProjectSecrecy>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('ProjectSecrecy Management Component', () => {
    let projectSecrecyServiceStub: SinonStubbedInstance<ProjectSecrecyService>;
    let mountOptions: MountingOptions<ProjectSecrecyComponentType>['global'];

    beforeEach(() => {
      projectSecrecyServiceStub = sinon.createStubInstance<ProjectSecrecyService>(ProjectSecrecyService);
      projectSecrecyServiceStub.retrieve.resolves({ headers: {} });

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
          projectSecrecyService: () => projectSecrecyServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        projectSecrecyServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(ProjectSecrecy, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(projectSecrecyServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.projectSecrecies[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: ProjectSecrecyComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(ProjectSecrecy, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        projectSecrecyServiceStub.retrieve.reset();
        projectSecrecyServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        projectSecrecyServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeProjectSecrecy();
        await comp.$nextTick(); // clear components

        // THEN
        expect(projectSecrecyServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(projectSecrecyServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
