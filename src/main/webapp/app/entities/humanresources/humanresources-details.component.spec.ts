/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import HumanresourcesDetails from './humanresources-details.vue';
import HumanresourcesService from './humanresources.service';
import AlertService from '@/shared/alert/alert.service';

type HumanresourcesDetailsComponentType = InstanceType<typeof HumanresourcesDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const humanresourcesSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Humanresources Management Detail Component', () => {
    let humanresourcesServiceStub: SinonStubbedInstance<HumanresourcesService>;
    let mountOptions: MountingOptions<HumanresourcesDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      humanresourcesServiceStub = sinon.createStubInstance<HumanresourcesService>(HumanresourcesService);

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
          humanresourcesService: () => humanresourcesServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        humanresourcesServiceStub.find.resolves(humanresourcesSample);
        route = {
          params: {
            humanresourcesId: '' + 123,
          },
        };
        const wrapper = shallowMount(HumanresourcesDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.humanresources).toMatchObject(humanresourcesSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        humanresourcesServiceStub.find.resolves(humanresourcesSample);
        const wrapper = shallowMount(HumanresourcesDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
