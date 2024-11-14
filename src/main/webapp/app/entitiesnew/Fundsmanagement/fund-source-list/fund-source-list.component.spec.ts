/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import FundSourceList from './fund-source-list.vue';
import FundSourceListService from './fund-source-list.service';
import AlertService from '@/shared/alert/alert.service';

type FundSourceListComponentType = InstanceType<typeof FundSourceList>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('FundSourceList Management Component', () => {
    let fundSourceListServiceStub: SinonStubbedInstance<FundSourceListService>;
    let mountOptions: MountingOptions<FundSourceListComponentType>['global'];

    beforeEach(() => {
      fundSourceListServiceStub = sinon.createStubInstance<FundSourceListService>(FundSourceListService);
      fundSourceListServiceStub.retrieve.resolves({ headers: {} });

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
          fundSourceListService: () => fundSourceListServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        fundSourceListServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(FundSourceList, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(fundSourceListServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.fundSourceLists[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: FundSourceListComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(FundSourceList, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        fundSourceListServiceStub.retrieve.reset();
        fundSourceListServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        fundSourceListServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeFundSourceList();
        await comp.$nextTick(); // clear components

        // THEN
        expect(fundSourceListServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(fundSourceListServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
