/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import SecrecysystemDetails from './secrecysystem-details.vue';
import SecrecysystemService from './secrecysystem.service';
import AlertService from '@/shared/alert/alert.service';

type SecrecysystemDetailsComponentType = InstanceType<typeof SecrecysystemDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const secrecysystemSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Secrecysystem Management Detail Component', () => {
    let secrecysystemServiceStub: SinonStubbedInstance<SecrecysystemService>;
    let mountOptions: MountingOptions<SecrecysystemDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      secrecysystemServiceStub = sinon.createStubInstance<SecrecysystemService>(SecrecysystemService);

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
          secrecysystemService: () => secrecysystemServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        secrecysystemServiceStub.find.resolves(secrecysystemSample);
        route = {
          params: {
            secrecysystemId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(SecrecysystemDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.secrecysystem).toMatchObject(secrecysystemSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        secrecysystemServiceStub.find.resolves(secrecysystemSample);
        const wrapper = shallowMount(SecrecysystemDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
