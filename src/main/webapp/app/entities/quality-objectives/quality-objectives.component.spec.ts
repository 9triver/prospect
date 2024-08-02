/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import QualityObjectives from './quality-objectives.vue';
import QualityObjectivesService from './quality-objectives.service';
import AlertService from '@/shared/alert/alert.service';

type QualityObjectivesComponentType = InstanceType<typeof QualityObjectives>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('QualityObjectives Management Component', () => {
    let qualityObjectivesServiceStub: SinonStubbedInstance<QualityObjectivesService>;
    let mountOptions: MountingOptions<QualityObjectivesComponentType>['global'];

    beforeEach(() => {
      qualityObjectivesServiceStub = sinon.createStubInstance<QualityObjectivesService>(QualityObjectivesService);
      qualityObjectivesServiceStub.retrieve.resolves({ headers: {} });

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
          qualityObjectivesService: () => qualityObjectivesServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        qualityObjectivesServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(QualityObjectives, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(qualityObjectivesServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.qualityObjectives[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: QualityObjectivesComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(QualityObjectives, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        qualityObjectivesServiceStub.retrieve.reset();
        qualityObjectivesServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        qualityObjectivesServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeQualityObjectives();
        await comp.$nextTick(); // clear components

        // THEN
        expect(qualityObjectivesServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(qualityObjectivesServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
