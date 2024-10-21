/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import QualityObjectivesDictionary from './quality-objectives-dictionary.vue';
import QualityObjectivesDictionaryService from './quality-objectives-dictionary.service';
import AlertService from '@/shared/alert/alert.service';

type QualityObjectivesDictionaryComponentType = InstanceType<typeof QualityObjectivesDictionary>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('QualityObjectivesDictionary Management Component', () => {
    let qualityObjectivesDictionaryServiceStub: SinonStubbedInstance<QualityObjectivesDictionaryService>;
    let mountOptions: MountingOptions<QualityObjectivesDictionaryComponentType>['global'];

    beforeEach(() => {
      qualityObjectivesDictionaryServiceStub =
        sinon.createStubInstance<QualityObjectivesDictionaryService>(QualityObjectivesDictionaryService);
      qualityObjectivesDictionaryServiceStub.retrieve.resolves({ headers: {} });

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
          qualityObjectivesDictionaryService: () => qualityObjectivesDictionaryServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        qualityObjectivesDictionaryServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(QualityObjectivesDictionary, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(qualityObjectivesDictionaryServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.qualityObjectivesDictionaries[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: QualityObjectivesDictionaryComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(QualityObjectivesDictionary, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        qualityObjectivesDictionaryServiceStub.retrieve.reset();
        qualityObjectivesDictionaryServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        qualityObjectivesDictionaryServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeQualityObjectivesDictionary();
        await comp.$nextTick(); // clear components

        // THEN
        expect(qualityObjectivesDictionaryServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(qualityObjectivesDictionaryServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
