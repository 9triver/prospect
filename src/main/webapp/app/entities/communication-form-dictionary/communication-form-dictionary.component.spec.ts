/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import CommunicationFormDictionary from './communication-form-dictionary.vue';
import CommunicationFormDictionaryService from './communication-form-dictionary.service';
import AlertService from '@/shared/alert/alert.service';

type CommunicationFormDictionaryComponentType = InstanceType<typeof CommunicationFormDictionary>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('CommunicationFormDictionary Management Component', () => {
    let communicationFormDictionaryServiceStub: SinonStubbedInstance<CommunicationFormDictionaryService>;
    let mountOptions: MountingOptions<CommunicationFormDictionaryComponentType>['global'];

    beforeEach(() => {
      communicationFormDictionaryServiceStub =
        sinon.createStubInstance<CommunicationFormDictionaryService>(CommunicationFormDictionaryService);
      communicationFormDictionaryServiceStub.retrieve.resolves({ headers: {} });

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
          communicationFormDictionaryService: () => communicationFormDictionaryServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        communicationFormDictionaryServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(CommunicationFormDictionary, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(communicationFormDictionaryServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.communicationFormDictionaries[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: CommunicationFormDictionaryComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(CommunicationFormDictionary, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        communicationFormDictionaryServiceStub.retrieve.reset();
        communicationFormDictionaryServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        communicationFormDictionaryServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeCommunicationFormDictionary();
        await comp.$nextTick(); // clear components

        // THEN
        expect(communicationFormDictionaryServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(communicationFormDictionaryServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
