/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import CommunicationFormDictionaryUpdate from './communication-form-dictionary-update.vue';
import CommunicationFormDictionaryService from './communication-form-dictionary.service';
import AlertService from '@/shared/alert/alert.service';

type CommunicationFormDictionaryUpdateComponentType = InstanceType<typeof CommunicationFormDictionaryUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const communicationFormDictionarySample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<CommunicationFormDictionaryUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('CommunicationFormDictionary Management Update Component', () => {
    let comp: CommunicationFormDictionaryUpdateComponentType;
    let communicationFormDictionaryServiceStub: SinonStubbedInstance<CommunicationFormDictionaryService>;

    beforeEach(() => {
      route = {};
      communicationFormDictionaryServiceStub =
        sinon.createStubInstance<CommunicationFormDictionaryService>(CommunicationFormDictionaryService);
      communicationFormDictionaryServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          communicationFormDictionaryService: () => communicationFormDictionaryServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(CommunicationFormDictionaryUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.communicationFormDictionary = communicationFormDictionarySample;
        communicationFormDictionaryServiceStub.update.resolves(communicationFormDictionarySample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(communicationFormDictionaryServiceStub.update.calledWith(communicationFormDictionarySample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        communicationFormDictionaryServiceStub.create.resolves(entity);
        const wrapper = shallowMount(CommunicationFormDictionaryUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.communicationFormDictionary = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(communicationFormDictionaryServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        communicationFormDictionaryServiceStub.find.resolves(communicationFormDictionarySample);
        communicationFormDictionaryServiceStub.retrieve.resolves([communicationFormDictionarySample]);

        // WHEN
        route = {
          params: {
            communicationFormDictionaryId: '' + communicationFormDictionarySample.id,
          },
        };
        const wrapper = shallowMount(CommunicationFormDictionaryUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.communicationFormDictionary).toMatchObject(communicationFormDictionarySample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        communicationFormDictionaryServiceStub.find.resolves(communicationFormDictionarySample);
        const wrapper = shallowMount(CommunicationFormDictionaryUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
