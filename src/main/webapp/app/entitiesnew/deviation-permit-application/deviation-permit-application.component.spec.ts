/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import DeviationPermitApplication from './deviation-permit-application.vue';
import DeviationPermitApplicationService from './deviation-permit-application.service';
import AlertService from '@/shared/alert/alert.service';

type DeviationPermitApplicationComponentType = InstanceType<typeof DeviationPermitApplication>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('DeviationPermitApplication Management Component', () => {
    let deviationPermitApplicationServiceStub: SinonStubbedInstance<DeviationPermitApplicationService>;
    let mountOptions: MountingOptions<DeviationPermitApplicationComponentType>['global'];

    beforeEach(() => {
      deviationPermitApplicationServiceStub =
        sinon.createStubInstance<DeviationPermitApplicationService>(DeviationPermitApplicationService);
      deviationPermitApplicationServiceStub.retrieve.resolves({ headers: {} });

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
          deviationPermitApplicationService: () => deviationPermitApplicationServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        deviationPermitApplicationServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(DeviationPermitApplication, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(deviationPermitApplicationServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.deviationPermitApplications[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: DeviationPermitApplicationComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(DeviationPermitApplication, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        deviationPermitApplicationServiceStub.retrieve.reset();
        deviationPermitApplicationServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        deviationPermitApplicationServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeDeviationPermitApplication();
        await comp.$nextTick(); // clear components

        // THEN
        expect(deviationPermitApplicationServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(deviationPermitApplicationServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
