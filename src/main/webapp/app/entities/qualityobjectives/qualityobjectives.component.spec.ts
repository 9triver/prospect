/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Qualityobjectives from './qualityobjectives.vue';
import QualityobjectivesService from './qualityobjectives.service';
import AlertService from '@/shared/alert/alert.service';

type QualityobjectivesComponentType = InstanceType<typeof Qualityobjectives>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Qualityobjectives Management Component', () => {
    let qualityobjectivesServiceStub: SinonStubbedInstance<QualityobjectivesService>;
    let mountOptions: MountingOptions<QualityobjectivesComponentType>['global'];

    beforeEach(() => {
      qualityobjectivesServiceStub = sinon.createStubInstance<QualityobjectivesService>(QualityobjectivesService);
      qualityobjectivesServiceStub.retrieve.resolves({ headers: {} });

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
          qualityobjectivesService: () => qualityobjectivesServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        qualityobjectivesServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Qualityobjectives, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(qualityobjectivesServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.qualityobjectives[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: QualityobjectivesComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Qualityobjectives, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        qualityobjectivesServiceStub.retrieve.reset();
        qualityobjectivesServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        qualityobjectivesServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeQualityobjectives();
        await comp.$nextTick(); // clear components

        // THEN
        expect(qualityobjectivesServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(qualityobjectivesServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
