/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import AnnualplanDetails from './annualplan-details.vue';
import AnnualplanService from './annualplan.service';
import AlertService from '@/shared/alert/alert.service';

type AnnualplanDetailsComponentType = InstanceType<typeof AnnualplanDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const annualplanSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Annualplan Management Detail Component', () => {
    let annualplanServiceStub: SinonStubbedInstance<AnnualplanService>;
    let mountOptions: MountingOptions<AnnualplanDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      annualplanServiceStub = sinon.createStubInstance<AnnualplanService>(AnnualplanService);

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
          annualplanService: () => annualplanServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        annualplanServiceStub.find.resolves(annualplanSample);
        route = {
          params: {
            annualplanId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(AnnualplanDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.annualplan).toMatchObject(annualplanSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        annualplanServiceStub.find.resolves(annualplanSample);
        const wrapper = shallowMount(AnnualplanDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
