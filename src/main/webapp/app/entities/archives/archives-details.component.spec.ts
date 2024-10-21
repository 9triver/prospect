/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ArchivesDetails from './archives-details.vue';
import ArchivesService from './archives.service';
import AlertService from '@/shared/alert/alert.service';

type ArchivesDetailsComponentType = InstanceType<typeof ArchivesDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const archivesSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Archives Management Detail Component', () => {
    let archivesServiceStub: SinonStubbedInstance<ArchivesService>;
    let mountOptions: MountingOptions<ArchivesDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      archivesServiceStub = sinon.createStubInstance<ArchivesService>(ArchivesService);

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
          archivesService: () => archivesServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        archivesServiceStub.find.resolves(archivesSample);
        route = {
          params: {
            archivesId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(ArchivesDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.archives).toMatchObject(archivesSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        archivesServiceStub.find.resolves(archivesSample);
        const wrapper = shallowMount(ArchivesDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
