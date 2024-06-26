/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import ProgressmanagementWbs from './progressmanagement-wbs.vue';
import ProgressmanagementWbsService from './progressmanagement-wbs.service';
import AlertService from '@/shared/alert/alert.service';

type ProgressmanagementWbsComponentType = InstanceType<typeof ProgressmanagementWbs>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('ProgressmanagementWbs Management Component', () => {
    let progressmanagementWbsServiceStub: SinonStubbedInstance<ProgressmanagementWbsService>;
    let mountOptions: MountingOptions<ProgressmanagementWbsComponentType>['global'];

    beforeEach(() => {
      progressmanagementWbsServiceStub = sinon.createStubInstance<ProgressmanagementWbsService>(ProgressmanagementWbsService);
      progressmanagementWbsServiceStub.retrieve.resolves({ headers: {} });

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
          progressmanagementWbsService: () => progressmanagementWbsServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        progressmanagementWbsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(ProgressmanagementWbs, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(progressmanagementWbsServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.progressmanagementWbs[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: ProgressmanagementWbsComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(ProgressmanagementWbs, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        progressmanagementWbsServiceStub.retrieve.reset();
        progressmanagementWbsServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        progressmanagementWbsServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeProgressmanagementWbs();
        await comp.$nextTick(); // clear components

        // THEN
        expect(progressmanagementWbsServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(progressmanagementWbsServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
