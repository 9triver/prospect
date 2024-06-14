/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Progressmanagement from './progressmanagement.vue';
import ProgressmanagementService from './progressmanagement.service';
import AlertService from '@/shared/alert/alert.service';

type ProgressmanagementComponentType = InstanceType<typeof Progressmanagement>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Progressmanagement Management Component', () => {
    let progressmanagementServiceStub: SinonStubbedInstance<ProgressmanagementService>;
    let mountOptions: MountingOptions<ProgressmanagementComponentType>['global'];

    beforeEach(() => {
      progressmanagementServiceStub = sinon.createStubInstance<ProgressmanagementService>(ProgressmanagementService);
      progressmanagementServiceStub.retrieve.resolves({ headers: {} });

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
          progressmanagementService: () => progressmanagementServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        progressmanagementServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Progressmanagement, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(progressmanagementServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.progressmanagements[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: ProgressmanagementComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Progressmanagement, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        progressmanagementServiceStub.retrieve.reset();
        progressmanagementServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        progressmanagementServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeProgressmanagement();
        await comp.$nextTick(); // clear components

        // THEN
        expect(progressmanagementServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(progressmanagementServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
