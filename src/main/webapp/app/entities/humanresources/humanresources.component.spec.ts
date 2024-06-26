/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Humanresources from './humanresources.vue';
import HumanresourcesService from './humanresources.service';
import AlertService from '@/shared/alert/alert.service';

type HumanresourcesComponentType = InstanceType<typeof Humanresources>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Humanresources Management Component', () => {
    let humanresourcesServiceStub: SinonStubbedInstance<HumanresourcesService>;
    let mountOptions: MountingOptions<HumanresourcesComponentType>['global'];

    beforeEach(() => {
      humanresourcesServiceStub = sinon.createStubInstance<HumanresourcesService>(HumanresourcesService);
      humanresourcesServiceStub.retrieve.resolves({ headers: {} });

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
          humanresourcesService: () => humanresourcesServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        humanresourcesServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(Humanresources, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(humanresourcesServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.humanresources[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: HumanresourcesComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Humanresources, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        humanresourcesServiceStub.retrieve.reset();
        humanresourcesServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        humanresourcesServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeHumanresources();
        await comp.$nextTick(); // clear components

        // THEN
        expect(humanresourcesServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(humanresourcesServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
