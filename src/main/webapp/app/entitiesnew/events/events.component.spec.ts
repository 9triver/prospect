/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Events from './events.vue';
import EventsService from './events.service';
import AlertService from '@/shared/alert/alert.service';

type EventsComponentType = InstanceType<typeof Events>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Events Management Component', () => {
    let eventsServiceStub: SinonStubbedInstance<EventsService>;
    let mountOptions: MountingOptions<EventsComponentType>['global'];

    beforeEach(() => {
      eventsServiceStub = sinon.createStubInstance<EventsService>(EventsService);
      eventsServiceStub.retrieve.resolves({ headers: {} });

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          bModal: bModalStub as any,
          'font-awesome-icon': true,
          'b-badge': true,
          'b-button': true,
          'router-link': true,
        },
        directives: {
          'b-modal': {},
        },
        provide: {
          alertService,
          eventsService: () => eventsServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        eventsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(Events, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(eventsServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.events[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: EventsComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Events, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        eventsServiceStub.retrieve.reset();
        eventsServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        eventsServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeEvents();
        await comp.$nextTick(); // clear components

        // THEN
        expect(eventsServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(eventsServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
