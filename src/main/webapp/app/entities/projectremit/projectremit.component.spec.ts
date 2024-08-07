/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Projectremit from './projectremit.vue';
import ProjectremitService from './projectremit.service';
import AlertService from '@/shared/alert/alert.service';

type ProjectremitComponentType = InstanceType<typeof Projectremit>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Projectremit Management Component', () => {
    let projectremitServiceStub: SinonStubbedInstance<ProjectremitService>;
    let mountOptions: MountingOptions<ProjectremitComponentType>['global'];

    beforeEach(() => {
      projectremitServiceStub = sinon.createStubInstance<ProjectremitService>(ProjectremitService);
      projectremitServiceStub.retrieve.resolves({ headers: {} });

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
          projectremitService: () => projectremitServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        projectremitServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(Projectremit, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(projectremitServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.projectremits[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: ProjectremitComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Projectremit, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        projectremitServiceStub.retrieve.reset();
        projectremitServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        projectremitServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeProjectremit();
        await comp.$nextTick(); // clear components

        // THEN
        expect(projectremitServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(projectremitServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});