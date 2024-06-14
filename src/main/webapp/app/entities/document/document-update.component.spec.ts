/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import DocumentUpdate from './document-update.vue';
import DocumentService from './document.service';
import AlertService from '@/shared/alert/alert.service';

import OfficersService from '@/entities/officers/officers.service';

type DocumentUpdateComponentType = InstanceType<typeof DocumentUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const documentSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<DocumentUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Document Management Update Component', () => {
    let comp: DocumentUpdateComponentType;
    let documentServiceStub: SinonStubbedInstance<DocumentService>;

    beforeEach(() => {
      route = {};
      documentServiceStub = sinon.createStubInstance<DocumentService>(DocumentService);
      documentServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          'font-awesome-icon': true,
          'b-input-group': true,
          'b-input-group-prepend': true,
          'b-form-datepicker': true,
          'b-form-input': true,
        },
        provide: {
          alertService,
          documentService: () => documentServiceStub,
          officersService: () =>
            sinon.createStubInstance<OfficersService>(OfficersService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(DocumentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.document = documentSample;
        documentServiceStub.update.resolves(documentSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(documentServiceStub.update.calledWith(documentSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        documentServiceStub.create.resolves(entity);
        const wrapper = shallowMount(DocumentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.document = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(documentServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        documentServiceStub.find.resolves(documentSample);
        documentServiceStub.retrieve.resolves([documentSample]);

        // WHEN
        route = {
          params: {
            documentId: '' + documentSample.id,
          },
        };
        const wrapper = shallowMount(DocumentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.document).toMatchObject(documentSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        documentServiceStub.find.resolves(documentSample);
        const wrapper = shallowMount(DocumentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
