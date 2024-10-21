import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import CommunicationRecordService from './communication-record.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ProjectwbsService from '@/entities/projectwbs/projectwbs.service';
import { type IProjectwbs } from '@/shared/model/projectwbs.model';
import WorkbagService from '@/entities/workbag/workbag.service';
import { type IWorkbag } from '@/shared/model/workbag.model';
import CommunicationDictionaryService from '@/entities/communication-dictionary/communication-dictionary.service';
import { type ICommunicationDictionary } from '@/shared/model/communication-dictionary.model';
import CommunicationFormDictionaryService from '@/entities/communication-form-dictionary/communication-form-dictionary.service';
import { type ICommunicationFormDictionary } from '@/shared/model/communication-form-dictionary.model';
import { type ICommunicationRecord, CommunicationRecord } from '@/shared/model/communication-record.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CommunicationRecordUpdate',
  setup() {
    const communicationRecordService = inject('communicationRecordService', () => new CommunicationRecordService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const communicationRecord: Ref<ICommunicationRecord> = ref(new CommunicationRecord());

    const projectwbsService = inject('projectwbsService', () => new ProjectwbsService());

    const projectwbs: Ref<IProjectwbs[]> = ref([]);

    const workbagService = inject('workbagService', () => new WorkbagService());

    const workbags: Ref<IWorkbag[]> = ref([]);

    const communicationDictionaryService = inject('communicationDictionaryService', () => new CommunicationDictionaryService());

    const communicationDictionaries: Ref<ICommunicationDictionary[]> = ref([]);

    const communicationFormDictionaryService = inject('communicationFormDictionaryService', () => new CommunicationFormDictionaryService());

    const communicationFormDictionaries: Ref<ICommunicationFormDictionary[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'zh-cn'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveCommunicationRecord = async communicationRecordId => {
      try {
        const res = await communicationRecordService().find(communicationRecordId);
        communicationRecord.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.communicationRecordId) {
      retrieveCommunicationRecord(route.params.communicationRecordId);
    }

    const initRelationships = () => {
      projectwbsService()
        .retrieve()
        .then(res => {
          projectwbs.value = res.data;
        });
      workbagService()
        .retrieve()
        .then(res => {
          workbags.value = res.data;
        });
      communicationDictionaryService()
        .retrieve()
        .then(res => {
          communicationDictionaries.value = res.data;
        });
      communicationFormDictionaryService()
        .retrieve()
        .then(res => {
          communicationFormDictionaries.value = res.data;
        });
    };

    initRelationships();

    const { t: t$ } = useI18n();
    const validations = useValidation();
    const validationRules = {
      wbsid: {},
      wbsname: {},
      workbagid: {},
      associationmeetingname: {},
      communicationtime: {},
      communicationlocation: {},
      communicationcontent: {},
      auditorid: {},
      auditorname: {},
      remarks: {},
      projectwbs: {},
      workbag: {},
      communication: {},
      workCommunicationForm: {},
    };
    const v$ = useVuelidate(validationRules, communicationRecord as any);
    v$.value.$validate();

    return {
      communicationRecordService,
      alertService,
      communicationRecord,
      previousState,
      isSaving,
      currentLanguage,
      projectwbs,
      workbags,
      communicationDictionaries,
      communicationFormDictionaries,
      v$,
      t$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.communicationRecord.id) {
        this.communicationRecordService()
          .update(this.communicationRecord)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo(this.t$('jy1App.communicationRecord.updated', { param: param.id }));
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.communicationRecordService()
          .create(this.communicationRecord)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess(this.t$('jy1App.communicationRecord.created', { param: param.id }).toString());
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
