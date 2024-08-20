/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import DocumentmenuDetails from './documentmenu-details.vue';
import DocumentmenuService from './documentmenu.service';
import AlertService from '@/shared/alert/alert.service';

type DocumentmenuDetailsComponentType = InstanceType<typeof DocumentmenuDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const documentmenuSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Documentmenu Management Detail Component', () => {
    let documentmenuServiceStub: SinonStubbedInstance<DocumentmenuService>;
    let mountOptions: MountingOptions<DocumentmenuDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      documentmenuServiceStub = sinon.createStubInstance<DocumentmenuService>(DocumentmenuService);

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
          documentmenuService: () => documentmenuServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        documentmenuServiceStub.find.resolves(documentmenuSample);
        route = {
          params: {
            documentmenuId: '' + 123,
          },
        };
        const wrapper = shallowMount(DocumentmenuDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.documentmenu).toMatchObject(documentmenuSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        documentmenuServiceStub.find.resolves(documentmenuSample);
        const wrapper = shallowMount(DocumentmenuDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
