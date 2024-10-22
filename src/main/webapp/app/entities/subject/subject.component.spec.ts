/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Subject from './subject.vue';
import SubjectService from './subject.service';
import AlertService from '@/shared/alert/alert.service';

type SubjectComponentType = InstanceType<typeof Subject>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Subject Management Component', () => {
    let subjectServiceStub: SinonStubbedInstance<SubjectService>;
    let mountOptions: MountingOptions<SubjectComponentType>['global'];

    beforeEach(() => {
      subjectServiceStub = sinon.createStubInstance<SubjectService>(SubjectService);
      subjectServiceStub.retrieve.resolves({ headers: {} });

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
          subjectService: () => subjectServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        subjectServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Subject, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(subjectServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.subjects[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: SubjectComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Subject, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        subjectServiceStub.retrieve.reset();
        subjectServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        subjectServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeSubject();
        await comp.$nextTick(); // clear components

        // THEN
        expect(subjectServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(subjectServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});