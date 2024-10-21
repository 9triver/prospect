/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import Letter from './letter.vue';
import LetterService from './letter.service';
import AlertService from '@/shared/alert/alert.service';

type LetterComponentType = InstanceType<typeof Letter>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('Letter Management Component', () => {
    let letterServiceStub: SinonStubbedInstance<LetterService>;
    let mountOptions: MountingOptions<LetterComponentType>['global'];

    beforeEach(() => {
      letterServiceStub = sinon.createStubInstance<LetterService>(LetterService);
      letterServiceStub.retrieve.resolves({ headers: {} });

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
          letterService: () => letterServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        letterServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(Letter, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(letterServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.letters[0]).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
    describe('Handles', () => {
      let comp: LetterComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(Letter, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        letterServiceStub.retrieve.reset();
        letterServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        letterServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeLetter();
        await comp.$nextTick(); // clear components

        // THEN
        expect(letterServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(letterServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
