/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Document from './document.vue';
import DocumentService from './document.service';
import AlertService from '@/shared/alert/alert.service';

type DocumentComponentType = InstanceType<typeof Document>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Document Management Component', () => {
    let documentServiceStub: SinonStubbedInstance<DocumentService>;
    let mountOptions: MountingOptions<DocumentComponentType>['global'];

    beforeEach(() => {
      documentServiceStub = sinon.createStubInstance<DocumentService>(DocumentService);
      documentServiceStub.retrieve.resolves({ headers: {} });

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
          documentService: () => documentServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        documentServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(Document, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(documentServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.documents[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: DocumentComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Document, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        documentServiceStub.retrieve.reset();
        documentServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        documentServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeDocument();
        await comp.$nextTick(); // clear components

        // THEN
        expect(documentServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(documentServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
