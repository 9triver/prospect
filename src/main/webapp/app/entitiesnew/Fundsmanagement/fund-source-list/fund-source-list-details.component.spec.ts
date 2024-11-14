/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import FundSourceListDetails from './fund-source-list-details.vue';
import FundSourceListService from './fund-source-list.service';
import AlertService from '@/shared/alert/alert.service';

type FundSourceListDetailsComponentType = InstanceType<typeof FundSourceListDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const fundSourceListSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('FundSourceList Management Detail Component', () => {
    let fundSourceListServiceStub: SinonStubbedInstance<FundSourceListService>;
    let mountOptions: MountingOptions<FundSourceListDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      fundSourceListServiceStub = sinon.createStubInstance<FundSourceListService>(FundSourceListService);

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
          fundSourceListService: () => fundSourceListServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        fundSourceListServiceStub.find.resolves(fundSourceListSample);
        route = {
          params: {
            fundSourceListId: '' + 123,
          },
        };
        const wrapper = shallowMount(FundSourceListDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.fundSourceList).toMatchObject(fundSourceListSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        fundSourceListServiceStub.find.resolves(fundSourceListSample);
        const wrapper = shallowMount(FundSourceListDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
