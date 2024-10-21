/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EventsDetails from './events-details.vue';
import EventsService from './events.service';
import AlertService from '@/shared/alert/alert.service';

type EventsDetailsComponentType = InstanceType<typeof EventsDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const eventsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Events Management Detail Component', () => {
    let eventsServiceStub: SinonStubbedInstance<EventsService>;
    let mountOptions: MountingOptions<EventsDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      eventsServiceStub = sinon.createStubInstance<EventsService>(EventsService);

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
          eventsService: () => eventsServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        eventsServiceStub.find.resolves(eventsSample);
        route = {
          params: {
            eventsId: '' + 'ABC',
          },
        };
        const wrapper = shallowMount(EventsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.events).toMatchObject(eventsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        eventsServiceStub.find.resolves(eventsSample);
        const wrapper = shallowMount(EventsDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
