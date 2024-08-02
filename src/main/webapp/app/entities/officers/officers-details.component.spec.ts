/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import OfficersDetails from './officers-details.vue';
import OfficersService from './officers.service';
import AlertService from '@/shared/alert/alert.service';

type OfficersDetailsComponentType = InstanceType<typeof OfficersDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const officersSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Officers Management Detail Component', () => {
    let officersServiceStub: SinonStubbedInstance<OfficersService>;
    let mountOptions: MountingOptions<OfficersDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      officersServiceStub = sinon.createStubInstance<OfficersService>(OfficersService);

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
          officersService: () => officersServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        officersServiceStub.find.resolves(officersSample);
        route = {
          params: {
            officersId: '' + 123,
          },
        };
        const wrapper = shallowMount(OfficersDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.officers).toMatchObject(officersSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        officersServiceStub.find.resolves(officersSample);
        const wrapper = shallowMount(OfficersDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
