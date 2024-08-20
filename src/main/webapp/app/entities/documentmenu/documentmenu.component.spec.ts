/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Documentmenu from './documentmenu.vue';
import DocumentmenuService from './documentmenu.service';
import AlertService from '@/shared/alert/alert.service';

type DocumentmenuComponentType = InstanceType<typeof Documentmenu>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Documentmenu Management Component', () => {
    let documentmenuServiceStub: SinonStubbedInstance<DocumentmenuService>;
    let mountOptions: MountingOptions<DocumentmenuComponentType>['global'];

    beforeEach(() => {
      documentmenuServiceStub = sinon.createStubInstance<DocumentmenuService>(DocumentmenuService);
      documentmenuServiceStub.retrieve.resolves({ headers: {} });

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
          documentmenuService: () => documentmenuServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        documentmenuServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Documentmenu, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(documentmenuServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.documentmenus[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: DocumentmenuComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Documentmenu, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        documentmenuServiceStub.retrieve.reset();
        documentmenuServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        documentmenuServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeDocumentmenu();
        await comp.$nextTick(); // clear components

        // THEN
        expect(documentmenuServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(documentmenuServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
