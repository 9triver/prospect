/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import DocumentDetails from './document-details.vue';
import DocumentService from './document.service';
import AlertService from '@/shared/alert/alert.service';

type DocumentDetailsComponentType = InstanceType<typeof DocumentDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const documentSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Document Management Detail Component', () => {
    let documentServiceStub: SinonStubbedInstance<DocumentService>;
    let mountOptions: MountingOptions<DocumentDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      documentServiceStub = sinon.createStubInstance<DocumentService>(DocumentService);

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          'font-awesome-icon': true,
          'router-link': true,
        },
        provide: {
          alertService,
          documentService: () => documentServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        documentServiceStub.find.resolves(documentSample);
        route = {
          params: {
            documentId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(DocumentDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.document).toMatchObject(documentSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        documentServiceStub.find.resolves(documentSample);
        const wrapper = shallowMount(DocumentDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
