/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Safetycheck from './safetycheck.vue';
import SafetycheckService from './safetycheck.service';
import AlertService from '@/shared/alert/alert.service';

type SafetycheckComponentType = InstanceType<typeof Safetycheck>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Safetycheck Management Component', () => {
    let safetycheckServiceStub: SinonStubbedInstance<SafetycheckService>;
    let mountOptions: MountingOptions<SafetycheckComponentType>['global'];

    beforeEach(() => {
      safetycheckServiceStub = sinon.createStubInstance<SafetycheckService>(SafetycheckService);
      safetycheckServiceStub.retrieve.resolves({ headers: {} });

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
          safetycheckService: () => safetycheckServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        safetycheckServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(Safetycheck, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(safetycheckServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.safetychecks[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: SafetycheckComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Safetycheck, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        safetycheckServiceStub.retrieve.reset();
        safetycheckServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        safetycheckServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeSafetycheck();
        await comp.$nextTick(); // clear components

        // THEN
        expect(safetycheckServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(safetycheckServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
