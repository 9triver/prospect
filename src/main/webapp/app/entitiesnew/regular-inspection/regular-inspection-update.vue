<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.regularInspection.home.createOrEditLabel"
          data-cy="RegularInspectionCreateUpdateHeading"
          v-text="t$('jy1App.regularInspection.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="regularInspection.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="regularInspection.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.regularInspection.name')" for="regular-inspection-name"></label>
            <el-input
              type="text"
              class="form-control"
              name="name"
              id="regular-inspection-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.regularInspection.workbagid')" for="regular-inspection-workbagid"></label>
            <el-input
              type="text"
              class="form-control"
              name="workbagid"
              id="regular-inspection-workbagid"
              data-cy="workbagid"
              :class="{ valid: !v$.workbagid.$invalid, invalid: v$.workbagid.$invalid }"
              v-model="v$.workbagid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.regularInspection.workbagname')"
              for="regular-inspection-workbagname"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="workbagname"
              id="regular-inspection-workbagname"
              data-cy="workbagname"
              :class="{ valid: !v$.workbagname.$invalid, invalid: v$.workbagname.$invalid }"
              v-model="v$.workbagname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.regularInspection.type')" for="regular-inspection-type"></label>
            <el-input
              type="text"
              class="form-control"
              name="type"
              id="regular-inspection-type"
              data-cy="type"
              :class="{ valid: !v$.type.$invalid, invalid: v$.type.$invalid }"
              v-model="v$.type.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.regularInspection.secretlevel')"
              for="regular-inspection-secretlevel"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="regular-inspection-secretlevel"
              data-cy="secretlevel"
            >
              <el-option
                v-for="secretlevel in secretlevelValues"
                :key="secretlevel"
                v-bind:value="secretlevel"
                v-bind:label="t$('jy1App.Secretlevel.' + secretlevel)"
                >{{ secretlevel }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.regularInspection.standard')" for="regular-inspection-standard"></label>
            <el-input
              type="text"
              class="form-control"
              name="standard"
              id="regular-inspection-standard"
              data-cy="standard"
              :class="{ valid: !v$.standard.$invalid, invalid: v$.standard.$invalid }"
              v-model="v$.standard.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.regularInspection.measurementmethod')"
              for="regular-inspection-measurementmethod"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="measurementmethod"
              id="regular-inspection-measurementmethod"
              data-cy="measurementmethod"
              :class="{ valid: !v$.measurementmethod.$invalid, invalid: v$.measurementmethod.$invalid }"
              v-model="v$.measurementmethod.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.regularInspection.checkresult')"
              for="regular-inspection-checkresult"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="checkresult"
              id="regular-inspection-checkresult"
              data-cy="checkresult"
              :class="{ valid: !v$.checkresult.$invalid, invalid: v$.checkresult.$invalid }"
              v-model="v$.checkresult.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.regularInspection.checktarget')"
              for="regular-inspection-checktarget"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="checktarget"
              id="regular-inspection-checktarget"
              data-cy="checktarget"
              :class="{ valid: !v$.checktarget.$invalid, invalid: v$.checktarget.$invalid }"
              v-model="v$.checktarget.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.regularInspection.checktime')" for="regular-inspection-checktime"></label>
            <el-date-picker v-model="v$.checktime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.regularInspection.checkcompletion')"
              for="regular-inspection-checkcompletion"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="checkcompletion"
              id="regular-inspection-checkcompletion"
              data-cy="checkcompletion"
              :class="{ valid: !v$.checkcompletion.$invalid, invalid: v$.checkcompletion.$invalid }"
              v-model="v$.checkcompletion.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.regularInspection.checkstatus')"
              for="regular-inspection-checkstatus"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="checkstatus"
              id="regular-inspection-checkstatus"
              data-cy="checkstatus"
              :class="{ valid: !v$.checkstatus.$invalid, invalid: v$.checkstatus.$invalid }"
              v-model="v$.checkstatus.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.regularInspection.auditStatus')"
              for="regular-inspection-auditStatus"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="regular-inspection-auditStatus"
              data-cy="auditStatus"
            >
              <el-option
                v-for="auditStatus in auditStatusValues"
                :key="auditStatus"
                v-bind:value="auditStatus"
                v-bind:label="t$('jy1App.AuditStatus.' + auditStatus)"
                >{{ auditStatus }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.regularInspection.responsibleperson')"
              for="regular-inspection-responsibleperson"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="regular-inspection-responsibleperson"
              data-cy="responsibleperson"
              name="responsibleperson"
              v-model="regularInspection.responsibleperson"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  regularInspection.responsibleperson && hrManagementOption.id === regularInspection.responsibleperson.id
                    ? regularInspection.responsibleperson
                    : hrManagementOption
                "
                v-for="hrManagementOption in hrManagements"
                :key="hrManagementOption.id"
                :label="hrManagementOption.id"
                >{{ hrManagementOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.regularInspection.designer')" for="regular-inspection-designer"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="regular-inspection-designer"
              data-cy="designer"
              name="designer"
              v-model="regularInspection.designer"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  regularInspection.designer && hrManagementOption.id === regularInspection.designer.id
                    ? regularInspection.designer
                    : hrManagementOption
                "
                v-for="hrManagementOption in hrManagements"
                :key="hrManagementOption.id"
                :label="hrManagementOption.id"
                >{{ hrManagementOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.regularInspection.checkperson')"
              for="regular-inspection-checkperson"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="regular-inspection-checkperson"
              data-cy="checkperson"
              name="checkperson"
              v-model="regularInspection.checkperson"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  regularInspection.checkperson && hrManagementOption.id === regularInspection.checkperson.id
                    ? regularInspection.checkperson
                    : hrManagementOption
                "
                v-for="hrManagementOption in hrManagements"
                :key="hrManagementOption.id"
                :label="hrManagementOption.id"
                >{{ hrManagementOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.regularInspection.workbag')" for="regular-inspection-workbag"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="regular-inspection-workbag"
              data-cy="workbag"
              name="workbag"
              v-model="regularInspection.workbag"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  regularInspection.workbag && workbagOption.id === regularInspection.workbag.id ? regularInspection.workbag : workbagOption
                "
                v-for="workbagOption in workbags"
                :key="workbagOption.id"
                :label="workbagOption.id"
                >{{ workbagOption.id }}</el-option
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
<script lang="ts" src="./regular-inspection-update.component.ts"></script>
