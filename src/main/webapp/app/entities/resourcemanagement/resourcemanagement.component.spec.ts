/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Resourcemanagement from './resourcemanagement.vue';
import ResourcemanagementService from './resourcemanagement.service';
import AlertService from '@/shared/alert/alert.service';

type ResourcemanagementComponentType = InstanceType<typeof Resourcemanagement>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Resourcemanagement Management Component', () => {
    let resourcemanagementServiceStub: SinonStubbedInstance<ResourcemanagementService>;
    let mountOptions: MountingOptions<ResourcemanagementComponentType>['global'];

    beforeEach(() => {
      resourcemanagementServiceStub = sinon.createStubInstance<ResourcemanagementService>(ResourcemanagementService);
      resourcemanagementServiceStub.retrieve.resolves({ headers: {} });

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
          resourcemanagementService: () => resourcemanagementServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        resourcemanagementServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Resourcemanagement, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(resourcemanagementServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.resourcemanagements[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: ResourcemanagementComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Resourcemanagement, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        resourcemanagementServiceStub.retrieve.reset();
        resourcemanagementServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        resourcemanagementServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeResourcemanagement();
        await comp.$nextTick(); // clear components

        // THEN
        expect(resourcemanagementServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(resourcemanagementServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
