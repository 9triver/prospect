/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import CommunicationDictionary from './communication-dictionary.vue';
import CommunicationDictionaryService from './communication-dictionary.service';
import AlertService from '@/shared/alert/alert.service';

type CommunicationDictionaryComponentType = InstanceType<typeof CommunicationDictionary>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('CommunicationDictionary Management Component', () => {
    let communicationDictionaryServiceStub: SinonStubbedInstance<CommunicationDictionaryService>;
    let mountOptions: MountingOptions<CommunicationDictionaryComponentType>['global'];

    beforeEach(() => {
      communicationDictionaryServiceStub = sinon.createStubInstance<CommunicationDictionaryService>(CommunicationDictionaryService);
      communicationDictionaryServiceStub.retrieve.resolves({ headers: {} });

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
          communicationDictionaryService: () => communicationDictionaryServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        communicationDictionaryServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(CommunicationDictionary, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(communicationDictionaryServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.communicationDictionaries[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: CommunicationDictionaryComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(CommunicationDictionary, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        communicationDictionaryServiceStub.retrieve.reset();
        communicationDictionaryServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        communicationDictionaryServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeCommunicationDictionary();
        await comp.$nextTick(); // clear components

        // THEN
        expect(communicationDictionaryServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(communicationDictionaryServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
