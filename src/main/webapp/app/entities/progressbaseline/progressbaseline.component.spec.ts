/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Progressbaseline from './progressbaseline.vue';
import ProgressbaselineService from './progressbaseline.service';
import AlertService from '@/shared/alert/alert.service';

type ProgressbaselineComponentType = InstanceType<typeof Progressbaseline>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Progressbaseline Management Component', () => {
    let progressbaselineServiceStub: SinonStubbedInstance<ProgressbaselineService>;
    let mountOptions: MountingOptions<ProgressbaselineComponentType>['global'];

    beforeEach(() => {
      progressbaselineServiceStub = sinon.createStubInstance<ProgressbaselineService>(ProgressbaselineService);
      progressbaselineServiceStub.retrieve.resolves({ headers: {} });

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
          progressbaselineService: () => progressbaselineServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        progressbaselineServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 'ABC' }] });

        // WHEN
        const wrapper = shallowMount(Progressbaseline, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(progressbaselineServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.progressbaselines[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
      });
    });
    describe('Handles', () => {
      let comp: ProgressbaselineComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Progressbaseline, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        progressbaselineServiceStub.retrieve.reset();
        progressbaselineServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        progressbaselineServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 'ABC' });

        comp.removeProgressbaseline();
        await comp.$nextTick(); // clear components

        // THEN
        expect(progressbaselineServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(progressbaselineServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
