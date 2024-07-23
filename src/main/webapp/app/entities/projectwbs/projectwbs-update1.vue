<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.projectwbs.home.createOrEditLabel"
          data-cy="ProjectwbsCreateUpdateHeading"
          v-text="t$('jy1App.projectwbs.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="projectwbs.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="projectwbs.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.wbsname')" for="projectwbs-wbsname"></label>
            <input
              type="text"
              class="form-control"
              name="wbsname"
              id="projectwbs-wbsname"
              data-cy="wbsname"
              :class="{ valid: !v$.wbsname.$invalid, invalid: v$.wbsname.$invalid }"
              v-model="v$.wbsname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.pbsid')" for="projectwbs-pbsid"></label>
            <input
              type="text"
              class="form-control"
              name="pbsid"
              id="projectwbs-pbsid"
              data-cy="pbsid"
              :class="{ valid: !v$.pbsid.$invalid, invalid: v$.pbsid.$invalid }"
              v-model="v$.pbsid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.parentwbsid')" for="projectwbs-parentwbsid"></label>
            <input
              type="text"
              class="form-control"
              name="parentwbsid"
              id="projectwbs-parentwbsid"
              data-cy="parentwbsid"
              :class="{ valid: !v$.parentwbsid.$invalid, invalid: v$.parentwbsid.$invalid }"
              v-model="v$.parentwbsid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.description')" for="projectwbs-description"></label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="projectwbs-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.starttime')" for="projectwbs-starttime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="projectwbs-starttime"
                  v-model="v$.starttime.$model"
                  name="starttime"
                  class="form-control"
                  :locale="currentLanguage"
                  button-only
                  today-button
                  reset-button
                  close-button
                >
                </b-form-datepicker>
              </b-input-group-prepend>
              <b-form-input
                id="projectwbs-starttime"
                data-cy="starttime"
                type="text"
                class="form-control"
                name="starttime"
                :class="{ valid: !v$.starttime.$invalid, invalid: v$.starttime.$invalid }"
                v-model="v$.starttime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.endtime')" for="projectwbs-endtime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="projectwbs-endtime"
                  v-model="v$.endtime.$model"
                  name="endtime"
                  class="form-control"
                  :locale="currentLanguage"
                  button-only
                  today-button
                  reset-button
                  close-button
                >
                </b-form-datepicker>
              </b-input-group-prepend>
              <b-form-input
                id="projectwbs-endtime"
                data-cy="endtime"
                type="text"
                class="form-control"
                name="endtime"
                :class="{ valid: !v$.endtime.$invalid, invalid: v$.endtime.$invalid }"
                v-model="v$.endtime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.progress')" for="projectwbs-progress"></label>
            <input
              type="number"
              class="form-control"
              name="progress"
              id="projectwbs-progress"
              data-cy="progress"
              :class="{ valid: !v$.progress.$invalid, invalid: v$.progress.$invalid }"
              v-model.number="v$.progress.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.type')" for="projectwbs-type"></label>
            <input
              type="number"
              class="form-control"
              name="type"
              id="projectwbs-type"
              data-cy="type"
              :class="{ valid: !v$.type.$invalid, invalid: v$.type.$invalid }"
              v-model.number="v$.type.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.priorty')" for="projectwbs-priorty"></label>
            <input
              type="number"
              class="form-control"
              name="priorty"
              id="projectwbs-priorty"
              data-cy="priorty"
              :class="{ valid: !v$.priorty.$invalid, invalid: v$.priorty.$invalid }"
              v-model.number="v$.priorty.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.secretlevel')" for="projectwbs-secretlevel"></label>
            <select
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="projectwbs-secretlevel"
              data-cy="secretlevel"
            >
              <option
                v-for="secretlevel in secretlevelValues"
                :key="secretlevel"
                v-bind:value="secretlevel"
                v-bind:label="t$('jy1App.Secretlevel.' + secretlevel)"
              >
                {{ secretlevel }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.status')" for="projectwbs-status"></label>
            <select
              class="form-control"
              name="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
              id="projectwbs-status"
              data-cy="status"
            >
              <option
                v-for="projectStatus in projectStatusValues"
                :key="projectStatus"
                v-bind:value="projectStatus"
                v-bind:label="t$('jy1App.ProjectStatus.' + projectStatus)"
              >
                {{ projectStatus }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.auditStatus')" for="projectwbs-auditStatus"></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="projectwbs-auditStatus"
              data-cy="auditStatus"
            >
              <option
                v-for="auditStatus in auditStatusValues"
                :key="auditStatus"
                v-bind:value="auditStatus"
                v-bind:label="t$('jy1App.AuditStatus.' + auditStatus)"
              >
                {{ auditStatus }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.workbag')" for="projectwbs-workbag"></label>
            <input
              type="number"
              class="form-control"
              name="workbag"
              id="projectwbs-workbag"
              data-cy="workbag"
              :class="{ valid: !v$.workbag.$invalid, invalid: v$.workbag.$invalid }"
              v-model.number="v$.workbag.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.responsibleid')" for="projectwbs-responsibleid"></label>
            <select
              class="form-control"
              id="projectwbs-responsibleid"
              data-cy="responsibleid"
              name="responsibleid"
              v-model="projectwbs.responsibleid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectwbs.responsibleid && officersOption.id === projectwbs.responsibleid.id ? projectwbs.responsibleid : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.auditorid')" for="projectwbs-auditorid"></label>
            <select class="form-control" id="projectwbs-auditorid" data-cy="auditorid" name="auditorid" v-model="projectwbs.auditorid">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="projectwbs.auditorid && officersOption.id === projectwbs.auditorid.id ? projectwbs.auditorid : officersOption"
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.department')" for="projectwbs-department"></label>
            <select class="form-control" id="projectwbs-department" data-cy="department" name="department" v-model="projectwbs.department">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectwbs.department && departmentOption.id === projectwbs.department.id ? projectwbs.department : departmentOption
                "
                v-for="departmentOption in departments"
                :key="departmentOption.id"
              >
                {{ departmentOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectwbs.project')" for="projectwbs-project"></label>
            <select
              class="form-control"
              id="projectwbs-projects"
              data-cy="project"
              multiple
              name="project"
              v-if="projectwbs.projects !== undefined"
              v-model="projectwbs.projects"
            >
              <option
                v-bind:value="getSelected(projectwbs.projects, projectOption, 'id')"
                v-for="projectOption in projects"
                :key="projectOption.id"
              >
                {{ projectOption.id }}
              </option>
            </select>
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
<script lang="ts" src="./projectwbs-update.component.ts"></script>
