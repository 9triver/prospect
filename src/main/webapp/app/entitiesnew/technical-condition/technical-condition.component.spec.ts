/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import TechnicalCondition from './technical-condition.vue';
import TechnicalConditionService from './technical-condition.service';
import AlertService from '@/shared/alert/alert.service';

type TechnicalConditionComponentType = InstanceType<typeof TechnicalCondition>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('TechnicalCondition Management Component', () => {
    let technicalConditionServiceStub: SinonStubbedInstance<TechnicalConditionService>;
    let mountOptions: MountingOptions<TechnicalConditionComponentType>['global'];

    beforeEach(() => {
      technicalConditionServiceStub = sinon.createStubInstance<TechnicalConditionService>(TechnicalConditionService);
      technicalConditionServiceStub.retrieve.resolves({ headers: {} });

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
          technicalConditionService: () => technicalConditionServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        technicalConditionServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(TechnicalCondition, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(technicalConditionServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.technicalConditions[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: TechnicalConditionComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(TechnicalCondition, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        technicalConditionServiceStub.retrieve.reset();
        technicalConditionServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        technicalConditionServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeTechnicalCondition();
        await comp.$nextTick(); // clear components

        // THEN
        expect(technicalConditionServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(technicalConditionServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
