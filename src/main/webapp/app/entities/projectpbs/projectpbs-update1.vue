<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.projectpbs.home.createOrEditLabel"
          data-cy="ProjectpbsCreateUpdateHeading"
          v-text="t$('jy1App.projectpbs.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="projectpbs.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="projectpbs.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.pbsname')" for="projectpbs-pbsname"></label>
            <input
              type="text"
              class="form-control"
              name="pbsname"
              id="projectpbs-pbsname"
              data-cy="pbsname"
              :class="{ valid: !v$.pbsname.$invalid, invalid: v$.pbsname.$invalid }"
              v-model="v$.pbsname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.parentpbsid')" for="projectpbs-parentpbsid"></label>
            <input
              type="text"
              class="form-control"
              name="parentpbsid"
              id="projectpbs-parentpbsid"
              data-cy="parentpbsid"
              :class="{ valid: !v$.parentpbsid.$invalid, invalid: v$.parentpbsid.$invalid }"
              v-model="v$.parentpbsid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.description')" for="projectpbs-description"></label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="projectpbs-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.starttime')" for="projectpbs-starttime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="projectpbs-starttime"
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
                id="projectpbs-starttime"
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
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.endtime')" for="projectpbs-endtime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="projectpbs-endtime"
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
                id="projectpbs-endtime"
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
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.progress')" for="projectpbs-progress"></label>
            <input
              type="number"
              class="form-control"
              name="progress"
              id="projectpbs-progress"
              data-cy="progress"
              :class="{ valid: !v$.progress.$invalid, invalid: v$.progress.$invalid }"
              v-model.number="v$.progress.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.type')" for="projectpbs-type"></label>
            <input
              type="number"
              class="form-control"
              name="type"
              id="projectpbs-type"
              data-cy="type"
              :class="{ valid: !v$.type.$invalid, invalid: v$.type.$invalid }"
              v-model.number="v$.type.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.priorty')" for="projectpbs-priorty"></label>
            <input
              type="number"
              class="form-control"
              name="priorty"
              id="projectpbs-priorty"
              data-cy="priorty"
              :class="{ valid: !v$.priorty.$invalid, invalid: v$.priorty.$invalid }"
              v-model.number="v$.priorty.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.secretlevel')" for="projectpbs-secretlevel"></label>
            <select
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="projectpbs-secretlevel"
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
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.status')" for="projectpbs-status"></label>
            <select
              class="form-control"
              name="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
              id="projectpbs-status"
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
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.auditStatus')" for="projectpbs-auditStatus"></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="projectpbs-auditStatus"
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
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.wbsid')" for="projectpbs-wbsid"></label>
            <input
              type="text"
              class="form-control"
              name="wbsid"
              id="projectpbs-wbsid"
              data-cy="wbsid"
              :class="{ valid: !v$.wbsid.$invalid, invalid: v$.wbsid.$invalid }"
              v-model="v$.wbsid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.workbag')" for="projectpbs-workbag"></label>
            <input
              type="number"
              class="form-control"
              name="workbag"
              id="projectpbs-workbag"
              data-cy="workbag"
              :class="{ valid: !v$.workbag.$invalid, invalid: v$.workbag.$invalid }"
              v-model.number="v$.workbag.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.responsibleid')" for="projectpbs-responsibleid"></label>
            <select
              class="form-control"
              id="projectpbs-responsibleid"
              data-cy="responsibleid"
              name="responsibleid"
              v-model="projectpbs.responsibleid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectpbs.responsibleid && officersOption.id === projectpbs.responsibleid.id ? projectpbs.responsibleid : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.auditorid')" for="projectpbs-auditorid"></label>
            <select class="form-control" id="projectpbs-auditorid" data-cy="auditorid" name="auditorid" v-model="projectpbs.auditorid">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="projectpbs.auditorid && officersOption.id === projectpbs.auditorid.id ? projectpbs.auditorid : officersOption"
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectpbs.department')" for="projectpbs-department"></label>
            <select class="form-control" id="projectpbs-department" data-cy="department" name="department" v-model="projectpbs.department">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectpbs.department && departmentOption.id === projectpbs.department.id ? projectpbs.department : departmentOption
                "
                v-for="departmentOption in departments"
                :key="departmentOption.id"
              >
                {{ departmentOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectpbs.project')" for="projectpbs-project"></label>
            <select
              class="form-control"
              id="projectpbs-projects"
              data-cy="project"
              multiple
              name="project"
              v-if="projectpbs.projects !== undefined"
              v-model="projectpbs.projects"
            >
              <option
                v-bind:value="getSelected(projectpbs.projects, projectOption, 'id')"
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
<script lang="ts" src="./projectpbs-update.component.ts"></script>
