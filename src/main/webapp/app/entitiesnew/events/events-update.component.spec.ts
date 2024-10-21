/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import EventsUpdate from './events-update.vue';
import EventsService from './events.service';
import AlertService from '@/shared/alert/alert.service';

type EventsUpdateComponentType = InstanceType<typeof EventsUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const eventsSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<EventsUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Events Management Update Component', () => {
    let comp: EventsUpdateComponentType;
    let eventsServiceStub: SinonStubbedInstance<EventsService>;

    beforeEach(() => {
      route = {};
      eventsServiceStub = sinon.createStubInstance<EventsService>(EventsService);
      eventsServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          'font-awesome-icon': true,
          'b-input-group': true,
          'b-input-group-prepend': true,
          'b-form-datepicker': true,
          'b-form-input': true,
        },
        provide: {
          alertService,
          eventsService: () => eventsServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(EventsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.events = eventsSample;
        eventsServiceStub.update.resolves(eventsSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(eventsServiceStub.update.calledWith(eventsSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        eventsServiceStub.create.resolves(entity);
        const wrapper = shallowMount(EventsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.events = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(eventsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        eventsServiceStub.find.resolves(eventsSample);
        eventsServiceStub.retrieve.resolves([eventsSample]);

        // WHEN
        route = {
          params: {
            eventsId: '' + eventsSample.id,
          },
        };
        const wrapper = shallowMount(EventsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.events).toMatchObject(eventsSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        eventsServiceStub.find.resolves(eventsSample);
        const wrapper = shallowMount(EventsUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
