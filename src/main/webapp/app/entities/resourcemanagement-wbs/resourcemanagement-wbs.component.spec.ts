/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import ResourcemanagementWbs from './resourcemanagement-wbs.vue';
import ResourcemanagementWbsService from './resourcemanagement-wbs.service';
import AlertService from '@/shared/alert/alert.service';

type ResourcemanagementWbsComponentType = InstanceType<typeof ResourcemanagementWbs>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('ResourcemanagementWbs Management Component', () => {
    let resourcemanagementWbsServiceStub: SinonStubbedInstance<ResourcemanagementWbsService>;
    let mountOptions: MountingOptions<ResourcemanagementWbsComponentType>['global'];

    beforeEach(() => {
      resourcemanagementWbsServiceStub = sinon.createStubInstance<ResourcemanagementWbsService>(ResourcemanagementWbsService);
      resourcemanagementWbsServiceStub.retrieve.resolves({ headers: {} });

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
          resourcemanagementWbsService: () => resourcemanagementWbsServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        resourcemanagementWbsServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(ResourcemanagementWbs, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(resourcemanagementWbsServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.resourcemanagementWbs[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: ResourcemanagementWbsComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(ResourcemanagementWbs, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        resourcemanagementWbsServiceStub.retrieve.reset();
        resourcemanagementWbsServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        resourcemanagementWbsServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeResourcemanagementWbs();
        await comp.$nextTick(); // clear components

        // THEN
        expect(resourcemanagementWbsServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(resourcemanagementWbsServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
