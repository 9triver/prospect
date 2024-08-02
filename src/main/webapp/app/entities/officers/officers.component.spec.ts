/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Officers from './officers.vue';
import OfficersService from './officers.service';
import AlertService from '@/shared/alert/alert.service';

type OfficersComponentType = InstanceType<typeof Officers>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Officers Management Component', () => {
    let officersServiceStub: SinonStubbedInstance<OfficersService>;
    let mountOptions: MountingOptions<OfficersComponentType>['global'];

    beforeEach(() => {
      officersServiceStub = sinon.createStubInstance<OfficersService>(OfficersService);
      officersServiceStub.retrieve.resolves({ headers: {} });

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
          officersService: () => officersServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        officersServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Officers, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(officersServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.officers[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: OfficersComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Officers, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        officersServiceStub.retrieve.reset();
        officersServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        officersServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeOfficers();
        await comp.$nextTick(); // clear components

        // THEN
        expect(officersServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(officersServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
