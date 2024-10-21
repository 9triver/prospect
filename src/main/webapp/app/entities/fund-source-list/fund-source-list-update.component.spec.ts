/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import FundSourceListUpdate from './fund-source-list-update.vue';
import FundSourceListService from './fund-source-list.service';
import AlertService from '@/shared/alert/alert.service';

import TransactionPaymentService from '@/entities/transaction-payment/transaction-payment.service';
import SporadicPurchasePaymentService from '@/entities/sporadic-purchase-payment/sporadic-purchase-payment.service';
import SharePaymentService from '@/entities/share-payment/share-payment.service';
import ContractPaymentService from '@/entities/contract-payment/contract-payment.service';

type FundSourceListUpdateComponentType = InstanceType<typeof FundSourceListUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const fundSourceListSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<FundSourceListUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('FundSourceList Management Update Component', () => {
    let comp: FundSourceListUpdateComponentType;
    let fundSourceListServiceStub: SinonStubbedInstance<FundSourceListService>;

    beforeEach(() => {
      route = {};
      fundSourceListServiceStub = sinon.createStubInstance<FundSourceListService>(FundSourceListService);
      fundSourceListServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          'font-awesome-icon': true,
          'b-input-group': true,
          'b-input-group-prepend': true,
          'b-form-datepicker': true,
          'b-form-input': true,
        },
        provide: {
          alertService,
          fundSourceListService: () => fundSourceListServiceStub,
          transactionPaymentService: () =>
            sinon.createStubInstance<TransactionPaymentService>(TransactionPaymentService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          sporadicPurchasePaymentService: () =>
            sinon.createStubInstance<SporadicPurchasePaymentService>(SporadicPurchasePaymentService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          sharePaymentService: () =>
            sinon.createStubInstance<SharePaymentService>(SharePaymentService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          contractPaymentService: () =>
            sinon.createStubInstance<ContractPaymentService>(ContractPaymentService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(FundSourceListUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.fundSourceList = fundSourceListSample;
        fundSourceListServiceStub.update.resolves(fundSourceListSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(fundSourceListServiceStub.update.calledWith(fundSourceListSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        fundSourceListServiceStub.create.resolves(entity);
        const wrapper = shallowMount(FundSourceListUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.fundSourceList = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(fundSourceListServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        fundSourceListServiceStub.find.resolves(fundSourceListSample);
        fundSourceListServiceStub.retrieve.resolves([fundSourceListSample]);

        // WHEN
        route = {
          params: {
            fundSourceListId: '' + fundSourceListSample.id,
          },
        };
        const wrapper = shallowMount(FundSourceListUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.fundSourceList).toMatchObject(fundSourceListSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        fundSourceListServiceStub.find.resolves(fundSourceListSample);
        const wrapper = shallowMount(FundSourceListUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
