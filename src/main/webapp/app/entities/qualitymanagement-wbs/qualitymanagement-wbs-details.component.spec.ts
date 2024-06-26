/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import QualitymanagementWbsDetails from './qualitymanagement-wbs-details.vue';
import QualitymanagementWbsService from './qualitymanagement-wbs.service';
import AlertService from '@/shared/alert/alert.service';

type QualitymanagementWbsDetailsComponentType = InstanceType<typeof QualitymanagementWbsDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const qualitymanagementWbsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('QualitymanagementWbs Management Detail Component', () => {
    let qualitymanagementWbsServiceStub: SinonStubbedInstance<QualitymanagementWbsService>;
    let mountOptions: MountingOptions<QualitymanagementWbsDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      qualitymanagementWbsServiceStub = sinon.createStubInstance<QualitymanagementWbsService>(QualitymanagementWbsService);

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
          qualitymanagementWbsService: () => qualitymanagementWbsServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        qualitymanagementWbsServiceStub.find.resolves(qualitymanagementWbsSample);
        route = {
          params: {
            qualitymanagementWbsId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(QualitymanagementWbsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.qualitymanagementWbs).toMatchObject(qualitymanagementWbsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        qualitymanagementWbsServiceStub.find.resolves(qualitymanagementWbsSample);
        const wrapper = shallowMount(QualitymanagementWbsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
