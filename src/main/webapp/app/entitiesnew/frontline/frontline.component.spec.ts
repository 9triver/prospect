/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Frontline from './frontline.vue';
import FrontlineService from './frontline.service';
import AlertService from '@/shared/alert/alert.service';

type FrontlineComponentType = InstanceType<typeof Frontline>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Frontline Management Component', () => {
    let frontlineServiceStub: SinonStubbedInstance<FrontlineService>;
    let mountOptions: MountingOptions<FrontlineComponentType>['global'];

    beforeEach(() => {
      frontlineServiceStub = sinon.createStubInstance<FrontlineService>(FrontlineService);
      frontlineServiceStub.retrieve.resolves({ headers: {} });

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
          frontlineService: () => frontlineServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        frontlineServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(Frontline, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(frontlineServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.frontlines[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: FrontlineComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Frontline, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        frontlineServiceStub.retrieve.reset();
        frontlineServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        frontlineServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeFrontline();
        await comp.$nextTick(); // clear components

        // THEN
        expect(frontlineServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(frontlineServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
