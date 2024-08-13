/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import ProjectTotalwbs from './projecttotalwbs.vue';
import ProjectTotalwbsService from './projecttotalwbs.service';
import AlertService from '@/shared/alert/alert.service';

type ProjectTotalwbsComponentType = InstanceType<typeof ProjectTotalwbs>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('ProjectTotalwbs Management Component', () => {
    let projectTotalwbsServiceStub: SinonStubbedInstance<ProjectTotalwbsService>;
    let mountOptions: MountingOptions<ProjectTotalwbsComponentType>['global'];

    beforeEach(() => {
      projectTotalwbsServiceStub = sinon.createStubInstance<ProjectTotalwbsService>(ProjectTotalwbsService);
      projectTotalwbsServiceStub.retrieve.resolves({ headers: {} });

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
          projectTotalwbsService: () => projectTotalwbsServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        projectTotalwbsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(ProjectTotalwbs, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(projectTotalwbsServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.projectTotalwbs[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: ProjectTotalwbsComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(ProjectTotalwbs, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        projectTotalwbsServiceStub.retrieve.reset();
        projectTotalwbsServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        projectTotalwbsServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeProjectTotalwbs();
        await comp.$nextTick(); // clear components

        // THEN
        expect(projectTotalwbsServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(projectTotalwbsServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
