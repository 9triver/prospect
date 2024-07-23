/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Projectpbs from './projectpbs.vue';
import ProjectpbsService from './projectpbs.service';
import AlertService from '@/shared/alert/alert.service';

type ProjectpbsComponentType = InstanceType<typeof Projectpbs>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Projectpbs Management Component', () => {
    let projectpbsServiceStub: SinonStubbedInstance<ProjectpbsService>;
    let mountOptions: MountingOptions<ProjectpbsComponentType>['global'];

    beforeEach(() => {
      projectpbsServiceStub = sinon.createStubInstance<ProjectpbsService>(ProjectpbsService);
      projectpbsServiceStub.retrieve.resolves({ headers: {} });

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
          projectpbsService: () => projectpbsServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        projectpbsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(Projectpbs, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(projectpbsServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.projectpbs[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: ProjectpbsComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Projectpbs, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        projectpbsServiceStub.retrieve.reset();
        projectpbsServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        projectpbsServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeProjectpbs();
        await comp.$nextTick(); // clear components

        // THEN
        expect(projectpbsServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(projectpbsServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
