/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import DocumentmenuUpdate from './documentmenu-update.vue';
import DocumentmenuService from './documentmenu.service';
import AlertService from '@/shared/alert/alert.service';

type DocumentmenuUpdateComponentType = InstanceType<typeof DocumentmenuUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const documentmenuSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<DocumentmenuUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Documentmenu Management Update Component', () => {
    let comp: DocumentmenuUpdateComponentType;
    let documentmenuServiceStub: SinonStubbedInstance<DocumentmenuService>;

    beforeEach(() => {
      route = {};
      documentmenuServiceStub = sinon.createStubInstance<DocumentmenuService>(DocumentmenuService);
      documentmenuServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          documentmenuService: () => documentmenuServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(DocumentmenuUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.documentmenu = documentmenuSample;
        documentmenuServiceStub.update.resolves(documentmenuSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(documentmenuServiceStub.update.calledWith(documentmenuSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        documentmenuServiceStub.create.resolves(entity);
        const wrapper = shallowMount(DocumentmenuUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.documentmenu = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(documentmenuServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        documentmenuServiceStub.find.resolves(documentmenuSample);
        documentmenuServiceStub.retrieve.resolves([documentmenuSample]);

        // WHEN
        route = {
          params: {
            documentmenuId: '' + documentmenuSample.id,
          },
        };
        const wrapper = shallowMount(DocumentmenuUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.documentmenu).toMatchObject(documentmenuSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        documentmenuServiceStub.find.resolves(documentmenuSample);
        const wrapper = shallowMount(DocumentmenuUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
