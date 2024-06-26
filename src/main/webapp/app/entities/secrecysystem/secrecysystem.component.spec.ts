/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Secrecysystem from './secrecysystem.vue';
import SecrecysystemService from './secrecysystem.service';
import AlertService from '@/shared/alert/alert.service';

type SecrecysystemComponentType = InstanceType<typeof Secrecysystem>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Secrecysystem Management Component', () => {
    let secrecysystemServiceStub: SinonStubbedInstance<SecrecysystemService>;
    let mountOptions: MountingOptions<SecrecysystemComponentType>['global'];

    beforeEach(() => {
      secrecysystemServiceStub = sinon.createStubInstance<SecrecysystemService>(SecrecysystemService);
      secrecysystemServiceStub.retrieve.resolves({ headers: {} });

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
          secrecysystemService: () => secrecysystemServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        secrecysystemServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(Secrecysystem, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(secrecysystemServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.secrecysystems[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: SecrecysystemComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Secrecysystem, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        secrecysystemServiceStub.retrieve.reset();
        secrecysystemServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        secrecysystemServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeSecrecysystem();
        await comp.$nextTick(); // clear components

        // THEN
        expect(secrecysystemServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(secrecysystemServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
