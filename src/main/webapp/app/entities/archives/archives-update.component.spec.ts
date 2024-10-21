/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ArchivesUpdate from './archives-update.vue';
import ArchivesService from './archives.service';
import AlertService from '@/shared/alert/alert.service';

import HrManagementService from '@/entities/hr-management/hr-management.service';

type ArchivesUpdateComponentType = InstanceType<typeof ArchivesUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const archivesSample = { id: 'ABC' };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ArchivesUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Archives Management Update Component', () => {
    let comp: ArchivesUpdateComponentType;
    let archivesServiceStub: SinonStubbedInstance<ArchivesService>;

    beforeEach(() => {
      route = {};
      archivesServiceStub = sinon.createStubInstance<ArchivesService>(ArchivesService);
      archivesServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          archivesService: () => archivesServiceStub,
          hrManagementService: () =>
            sinon.createStubInstance<HrManagementService>(HrManagementService, {
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
        const wrapper = shallowMount(ArchivesUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.archives = archivesSample;
        archivesServiceStub.update.resolves(archivesSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(archivesServiceStub.update.calledWith(archivesSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        archivesServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ArchivesUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.archives = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(archivesServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        archivesServiceStub.find.resolves(archivesSample);
        archivesServiceStub.retrieve.resolves([archivesSample]);

        // WHEN
        route = {
          params: {
            archivesId: '' + archivesSample.id,
          },
        };
        const wrapper = shallowMount(ArchivesUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.archives).toMatchObject(archivesSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        archivesServiceStub.find.resolves(archivesSample);
        const wrapper = shallowMount(ArchivesUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
