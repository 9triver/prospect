/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Projectcharge from './projectcharge.vue';
import ProjectchargeService from './projectcharge.service';
import AlertService from '@/shared/alert/alert.service';

type ProjectchargeComponentType = InstanceType<typeof Projectcharge>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Projectcharge Management Component', () => {
    let projectchargeServiceStub: SinonStubbedInstance<ProjectchargeService>;
    let mountOptions: MountingOptions<ProjectchargeComponentType>['global'];

    beforeEach(() => {
      projectchargeServiceStub = sinon.createStubInstance<ProjectchargeService>(ProjectchargeService);
      projectchargeServiceStub.retrieve.resolves({ headers: {} });

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
          projectchargeService: () => projectchargeServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        projectchargeServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Projectcharge, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(projectchargeServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.projectcharges[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: ProjectchargeComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Projectcharge, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        projectchargeServiceStub.retrieve.reset();
        projectchargeServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        projectchargeServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeProjectcharge();
        await comp.$nextTick(); // clear components

        // THEN
        expect(projectchargeServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(projectchargeServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
