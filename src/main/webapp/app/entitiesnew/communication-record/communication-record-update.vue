<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.communicationRecord.home.createOrEditLabel"
          data-cy="CommunicationRecordCreateUpdateHeading"
          v-text="t$('jy1App.communicationRecord.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="communicationRecord.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="communicationRecord.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.communicationRecord.wbsid')" for="communication-record-wbsid"></label>
            <el-input
              type="text"
              class="form-control"
              name="wbsid"
              id="communication-record-wbsid"
              data-cy="wbsid"
              :class="{ valid: !v$.wbsid.$invalid, invalid: v$.wbsid.$invalid }"
              v-model="v$.wbsid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.communicationRecord.wbsname')" for="communication-record-wbsname"></label>
            <el-input
              type="text"
              class="form-control"
              name="wbsname"
              id="communication-record-wbsname"
              data-cy="wbsname"
              :class="{ valid: !v$.wbsname.$invalid, invalid: v$.wbsname.$invalid }"
              v-model="v$.wbsname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.communicationRecord.workbagid')"
              for="communication-record-workbagid"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="workbagid"
              id="communication-record-workbagid"
              data-cy="workbagid"
              :class="{ valid: !v$.workbagid.$invalid, invalid: v$.workbagid.$invalid }"
              v-model="v$.workbagid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.communicationRecord.associationmeetingname')"
              for="communication-record-associationmeetingname"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="associationmeetingname"
              id="communication-record-associationmeetingname"
              data-cy="associationmeetingname"
              :class="{ valid: !v$.associationmeetingname.$invalid, invalid: v$.associationmeetingname.$invalid }"
              v-model="v$.associationmeetingname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.communicationRecord.communicationtime')"
              for="communication-record-communicationtime"
            ></label>
            <el-date-picker v-model="v$.communicationtime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.communicationRecord.communicationlocation')"
              for="communication-record-communicationlocation"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="communicationlocation"
              id="communication-record-communicationlocation"
              data-cy="communicationlocation"
              :class="{ valid: !v$.communicationlocation.$invalid, invalid: v$.communicationlocation.$invalid }"
              v-model="v$.communicationlocation.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.communicationRecord.communicationcontent')"
              for="communication-record-communicationcontent"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="communicationcontent"
              id="communication-record-communicationcontent"
              data-cy="communicationcontent"
              :class="{ valid: !v$.communicationcontent.$invalid, invalid: v$.communicationcontent.$invalid }"
              v-model="v$.communicationcontent.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.communicationRecord.auditorid')"
              for="communication-record-auditorid"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="auditorid"
              id="communication-record-auditorid"
              data-cy="auditorid"
              :class="{ valid: !v$.auditorid.$invalid, invalid: v$.auditorid.$invalid }"
              v-model="v$.auditorid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.communicationRecord.auditorname')"
              for="communication-record-auditorname"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="auditorname"
              id="communication-record-auditorname"
              data-cy="auditorname"
              :class="{ valid: !v$.auditorname.$invalid, invalid: v$.auditorname.$invalid }"
              v-model="v$.auditorname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.communicationRecord.remarks')" for="communication-record-remarks"></label>
            <el-input
              type="text"
              class="form-control"
              name="remarks"
              id="communication-record-remarks"
              data-cy="remarks"
              :class="{ valid: !v$.remarks.$invalid, invalid: v$.remarks.$invalid }"
              v-model="v$.remarks.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.communicationRecord.projectwbs')"
              for="communication-record-projectwbs"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="communication-record-projectwbs"
              data-cy="projectwbs"
              name="projectwbs"
              v-model="communicationRecord.projectwbs"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  communicationRecord.projectwbs && projectwbsOption.id === communicationRecord.projectwbs.id
                    ? communicationRecord.projectwbs
                    : projectwbsOption
                "
                v-for="projectwbsOption in projectwbs"
                :key="projectwbsOption.id"
                :label="projectwbsOption.id"
                >{{ projectwbsOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.communicationRecord.workbag')" for="communication-record-workbag"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="communication-record-workbag"
              data-cy="workbag"
              name="workbag"
              v-model="communicationRecord.workbag"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  communicationRecord.workbag && workbagOption.id === communicationRecord.workbag.id
                    ? communicationRecord.workbag
                    : workbagOption
                "
                v-for="workbagOption in workbags"
                :key="workbagOption.id"
                :label="workbagOption.id"
                >{{ workbagOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.communicationRecord.communication')"
              for="communication-record-communication"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="communication-record-communication"
              data-cy="communication"
              name="communication"
              v-model="communicationRecord.communication"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  communicationRecord.communication && communicationDictionaryOption.id === communicationRecord.communication.id
                    ? communicationRecord.communication
                    : communicationDictionaryOption
                "
                v-for="communicationDictionaryOption in communicationDictionaries"
                :key="communicationDictionaryOption.id"
                :label="communicationDictionaryOption.id"
                >{{ communicationDictionaryOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.communicationRecord.workCommunicationForm')"
              for="communication-record-workCommunicationForm"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="communication-record-workCommunicationForm"
              data-cy="workCommunicationForm"
              name="workCommunicationForm"
              v-model="communicationRecord.workCommunicationForm"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  communicationRecord.workCommunicationForm &&
                  communicationFormDictionaryOption.id === communicationRecord.workCommunicationForm.id
                    ? communicationRecord.workCommunicationForm
                    : communicationFormDictionaryOption
                "
                v-for="communicationFormDictionaryOption in communicationFormDictionaries"
                :key="communicationFormDictionaryOption.id"
                :label="communicationFormDictionaryOption.id"
                >{{ communicationFormDictionaryOption.id }}</el-option
              >
            </el-select>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.cancel')"></span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="v$.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.save')"></span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./communication-record-update.component.ts"></script>
