/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import CommunicationRecord from './communication-record.vue';
import CommunicationRecordService from './communication-record.service';
import AlertService from '@/shared/alert/alert.service';

type CommunicationRecordComponentType = InstanceType<typeof CommunicationRecord>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('CommunicationRecord Management Component', () => {
    let communicationRecordServiceStub: SinonStubbedInstance<CommunicationRecordService>;
    let mountOptions: MountingOptions<CommunicationRecordComponentType>['global'];

    beforeEach(() => {
      communicationRecordServiceStub = sinon.createStubInstance<CommunicationRecordService>(CommunicationRecordService);
      communicationRecordServiceStub.retrieve.resolves({ headers: {} });

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
          communicationRecordService: () => communicationRecordServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        communicationRecordServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(CommunicationRecord, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(communicationRecordServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.communicationRecords[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: CommunicationRecordComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(CommunicationRecord, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        communicationRecordServiceStub.retrieve.reset();
        communicationRecordServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        communicationRecordServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeCommunicationRecord();
        await comp.$nextTick(); // clear components

        // THEN
        expect(communicationRecordServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(communicationRecordServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
