/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import SubjectDetails from './subject-details.vue';
import SubjectService from './subject.service';
import AlertService from '@/shared/alert/alert.service';

type SubjectDetailsComponentType = InstanceType<typeof SubjectDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const subjectSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Subject Management Detail Component', () => {
    let subjectServiceStub: SinonStubbedInstance<SubjectService>;
    let mountOptions: MountingOptions<SubjectDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      subjectServiceStub = sinon.createStubInstance<SubjectService>(SubjectService);

      alertService = new AlertService({
        i18n: { t: vitest.fn() } as any,
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          'font-awesome-icon': true,
          'router-link': true,
        },
        provide: {
          alertService,
          subjectService: () => subjectServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        subjectServiceStub.find.resolves(subjectSample);
        route = {
          params: {
            subjectId: '' + 123,
          },
        };
        const wrapper = shallowMount(SubjectDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.subject).toMatchObject(subjectSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        subjectServiceStub.find.resolves(subjectSample);
        const wrapper = shallowMount(SubjectDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
