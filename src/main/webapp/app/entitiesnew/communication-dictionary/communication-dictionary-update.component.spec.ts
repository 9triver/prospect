/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import CommunicationDictionaryUpdate from './communication-dictionary-update.vue';
import CommunicationDictionaryService from './communication-dictionary.service';
import AlertService from '@/shared/alert/alert.service';

type CommunicationDictionaryUpdateComponentType = InstanceType<typeof CommunicationDictionaryUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const communicationDictionarySample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<CommunicationDictionaryUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('CommunicationDictionary Management Update Component', () => {
    let comp: CommunicationDictionaryUpdateComponentType;
    let communicationDictionaryServiceStub: SinonStubbedInstance<CommunicationDictionaryService>;

    beforeEach(() => {
      route = {};
      communicationDictionaryServiceStub = sinon.createStubInstance<CommunicationDictionaryService>(CommunicationDictionaryService);
      communicationDictionaryServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          communicationDictionaryService: () => communicationDictionaryServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(CommunicationDictionaryUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.communicationDictionary = communicationDictionarySample;
        communicationDictionaryServiceStub.update.resolves(communicationDictionarySample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(communicationDictionaryServiceStub.update.calledWith(communicationDictionarySample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        communicationDictionaryServiceStub.create.resolves(entity);
        const wrapper = shallowMount(CommunicationDictionaryUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.communicationDictionary = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(communicationDictionaryServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        communicationDictionaryServiceStub.find.resolves(communicationDictionarySample);
        communicationDictionaryServiceStub.retrieve.resolves([communicationDictionarySample]);

        // WHEN
        route = {
          params: {
            communicationDictionaryId: '' + communicationDictionarySample.id,
          },
        };
        const wrapper = shallowMount(CommunicationDictionaryUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.communicationDictionary).toMatchObject(communicationDictionarySample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        communicationDictionaryServiceStub.find.resolves(communicationDictionarySample);
        const wrapper = shallowMount(CommunicationDictionaryUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
