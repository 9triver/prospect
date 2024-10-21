/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Projectdeliverables from './projectdeliverables.vue';
import ProjectdeliverablesService from './projectdeliverables.service';
import AlertService from '@/shared/alert/alert.service';

type ProjectdeliverablesComponentType = InstanceType<typeof Projectdeliverables>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Projectdeliverables Management Component', () => {
    let projectdeliverablesServiceStub: SinonStubbedInstance<ProjectdeliverablesService>;
    let mountOptions: MountingOptions<ProjectdeliverablesComponentType>['global'];

    beforeEach(() => {
      projectdeliverablesServiceStub = sinon.createStubInstance<ProjectdeliverablesService>(ProjectdeliverablesService);
      projectdeliverablesServiceStub.retrieve.resolves({ headers: {} });

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
          projectdeliverablesService: () => projectdeliverablesServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        projectdeliverablesServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Projectdeliverables, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(projectdeliverablesServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.projectdeliverables[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: ProjectdeliverablesComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Projectdeliverables, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        projectdeliverablesServiceStub.retrieve.reset();
        projectdeliverablesServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        projectdeliverablesServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeProjectdeliverables();
        await comp.$nextTick(); // clear components

        // THEN
        expect(projectdeliverablesServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(projectdeliverablesServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
