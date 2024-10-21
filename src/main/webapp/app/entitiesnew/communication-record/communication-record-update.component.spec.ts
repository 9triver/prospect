/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import CommunicationRecordUpdate from './communication-record-update.vue';
import CommunicationRecordService from './communication-record.service';
import AlertService from '@/shared/alert/alert.service';

import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import WorkbagService from '@/entities/workbag/workbag.service';
import CommunicationDictionaryService from '@/entities/communication-dictionary/communication-dictionary.service';
import CommunicationFormDictionaryService from '@/entities/communication-form-dictionary/communication-form-dictionary.service';

type CommunicationRecordUpdateComponentType = InstanceType<typeof CommunicationRecordUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const communicationRecordSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<CommunicationRecordUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('CommunicationRecord Management Update Component', () => {
    let comp: CommunicationRecordUpdateComponentType;
    let communicationRecordServiceStub: SinonStubbedInstance<CommunicationRecordService>;

    beforeEach(() => {
      route = {};
      communicationRecordServiceStub = sinon.createStubInstance<CommunicationRecordService>(CommunicationRecordService);
      communicationRecordServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          communicationRecordService: () => communicationRecordServiceStub,
          projectwbsService: () =>
            sinon.createStubInstance<ProjectwbsService>(ProjectwbsService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          workbagService: () =>
            sinon.createStubInstance<WorkbagService>(WorkbagService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          communicationDictionaryService: () =>
            sinon.createStubInstance<CommunicationDictionaryService>(CommunicationDictionaryService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          communicationFormDictionaryService: () =>
            sinon.createStubInstance<CommunicationFormDictionaryService>(CommunicationFormDictionaryService, {
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
        const wrapper = shallowMount(CommunicationRecordUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.communicationRecord = communicationRecordSample;
        communicationRecordServiceStub.update.resolves(communicationRecordSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(communicationRecordServiceStub.update.calledWith(communicationRecordSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        communicationRecordServiceStub.create.resolves(entity);
        const wrapper = shallowMount(CommunicationRecordUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.communicationRecord = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(communicationRecordServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        communicationRecordServiceStub.find.resolves(communicationRecordSample);
        communicationRecordServiceStub.retrieve.resolves([communicationRecordSample]);

        // WHEN
        route = {
          params: {
            communicationRecordId: '' + communicationRecordSample.id,
          },
        };
        const wrapper = shallowMount(CommunicationRecordUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.communicationRecord).toMatchObject(communicationRecordSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        communicationRecordServiceStub.find.resolves(communicationRecordSample);
        const wrapper = shallowMount(CommunicationRecordUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
