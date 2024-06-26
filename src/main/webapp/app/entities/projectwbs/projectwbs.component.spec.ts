/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Projectwbs from './projectwbs.vue';
import ProjectwbsService from './projectwbs.service';
import AlertService from '@/shared/alert/alert.service';

type ProjectwbsComponentType = InstanceType<typeof Projectwbs>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Projectwbs Management Component', () => {
    let projectwbsServiceStub: SinonStubbedInstance<ProjectwbsService>;
    let mountOptions: MountingOptions<ProjectwbsComponentType>['global'];

    beforeEach(() => {
      projectwbsServiceStub = sinon.createStubInstance<ProjectwbsService>(ProjectwbsService);
      projectwbsServiceStub.retrieve.resolves({ headers: {} });

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
          projectwbsService: () => projectwbsServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        projectwbsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(Projectwbs, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(projectwbsServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.projectwbs[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: ProjectwbsComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Projectwbs, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        projectwbsServiceStub.retrieve.reset();
        projectwbsServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        projectwbsServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeProjectwbs();
        await comp.$nextTick(); // clear components

        // THEN
        expect(projectwbsServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(projectwbsServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
