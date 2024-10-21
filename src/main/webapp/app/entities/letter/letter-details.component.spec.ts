/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import LetterDetails from './letter-details.vue';
import LetterService from './letter.service';
import AlertService from '@/shared/alert/alert.service';

type LetterDetailsComponentType = InstanceType<typeof LetterDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const letterSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Letter Management Detail Component', () => {
    let letterServiceStub: SinonStubbedInstance<LetterService>;
    let mountOptions: MountingOptions<LetterDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      letterServiceStub = sinon.createStubInstance<LetterService>(LetterService);

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
          letterService: () => letterServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        letterServiceStub.find.resolves(letterSample);
        route = {
          params: {
            letterId: '' + 123,
          },
        };
        const wrapper = shallowMount(LetterDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.letter).toMatchObject(letterSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        letterServiceStub.find.resolves(letterSample);
        const wrapper = shallowMount(LetterDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
