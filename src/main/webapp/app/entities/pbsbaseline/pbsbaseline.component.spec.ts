/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Pbsbaseline from './pbsbaseline.vue';
import PbsbaselineService from './pbsbaseline.service';
import AlertService from '@/shared/alert/alert.service';

type PbsbaselineComponentType = InstanceType<typeof Pbsbaseline>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Pbsbaseline Management Component', () => {
    let pbsbaselineServiceStub: SinonStubbedInstance<PbsbaselineService>;
    let mountOptions: MountingOptions<PbsbaselineComponentType>['global'];

    beforeEach(() => {
      pbsbaselineServiceStub = sinon.createStubInstance<PbsbaselineService>(PbsbaselineService);
      pbsbaselineServiceStub.retrieve.resolves({ headers: {} });

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
          pbsbaselineService: () => pbsbaselineServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        pbsbaselineServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Pbsbaseline, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(pbsbaselineServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.pbsbaselines[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: PbsbaselineComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Pbsbaseline, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        pbsbaselineServiceStub.retrieve.reset();
        pbsbaselineServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        pbsbaselineServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removePbsbaseline();
        await comp.$nextTick(); // clear components

        // THEN
        expect(pbsbaselineServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(pbsbaselineServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
