/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Archives from './archives.vue';
import ArchivesService from './archives.service';
import AlertService from '@/shared/alert/alert.service';

type ArchivesComponentType = InstanceType<typeof Archives>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Archives Management Component', () => {
    let archivesServiceStub: SinonStubbedInstance<ArchivesService>;
    let mountOptions: MountingOptions<ArchivesComponentType>['global'];

    beforeEach(() => {
      archivesServiceStub = sinon.createStubInstance<ArchivesService>(ArchivesService);
      archivesServiceStub.retrieve.resolves({ headers: {} });

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
          archivesService: () => archivesServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        archivesServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(Archives, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(archivesServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.archives[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: ArchivesComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Archives, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        archivesServiceStub.retrieve.reset();
        archivesServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        archivesServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeArchives();
        await comp.$nextTick(); // clear components

        // THEN
        expect(archivesServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(archivesServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
