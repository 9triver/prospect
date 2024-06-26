/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Progressplan from './progressplan.vue';
import ProgressplanService from './progressplan.service';
import AlertService from '@/shared/alert/alert.service';

type ProgressplanComponentType = InstanceType<typeof Progressplan>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Progressplan Management Component', () => {
    let progressplanServiceStub: SinonStubbedInstance<ProgressplanService>;
    let mountOptions: MountingOptions<ProgressplanComponentType>['global'];

    beforeEach(() => {
      progressplanServiceStub = sinon.createStubInstance<ProgressplanService>(ProgressplanService);
      progressplanServiceStub.retrieve.resolves({ headers: {} });

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
          progressplanService: () => progressplanServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        progressplanServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(Progressplan, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(progressplanServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.progressplans[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: ProgressplanComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Progressplan, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        progressplanServiceStub.retrieve.reset();
        progressplanServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        progressplanServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeProgressplan();
        await comp.$nextTick(); // clear components

        // THEN
        expect(progressplanServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(progressplanServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
