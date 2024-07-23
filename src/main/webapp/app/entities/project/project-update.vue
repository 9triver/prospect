<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.project.home.createOrEditLabel"
          data-cy="ProjectCreateUpdateHeading"
          v-text="t$('jy1App.project.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="project.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="project.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.project.projectname')" for="project-projectname"></label>
            <input
              type="text"
              class="form-control"
              name="projectname"
              id="project-projectname"
              data-cy="projectname"
              :class="{ valid: !v$.projectname.$invalid, invalid: v$.projectname.$invalid }"
              v-model="v$.projectname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.project.parentid')" for="project-parentid"></label>
            <input
              type="text"
              class="form-control"
              name="parentid"
              id="project-parentid"
              data-cy="parentid"
              :class="{ valid: !v$.parentid.$invalid, invalid: v$.parentid.$invalid }"
              v-model="v$.parentid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.project.pbsid')" for="project-pbsid"></label>
            <input
              type="text"
              class="form-control"
              name="pbsid"
              id="project-pbsid"
              data-cy="pbsid"
              :class="{ valid: !v$.pbsid.$invalid, invalid: v$.pbsid.$invalid }"
              v-model="v$.pbsid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.project.description')" for="project-description"></label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="project-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.project.number')" for="project-number"></label>
            <input
              type="number"
              class="form-control"
              name="number"
              id="project-number"
              data-cy="number"
              :class="{ valid: !v$.number.$invalid, invalid: v$.number.$invalid }"
              v-model.number="v$.number.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.project.projecttype')" for="project-projecttype"></label>
            <input
              type="number"
              class="form-control"
              name="projecttype"
              id="project-projecttype"
              data-cy="projecttype"
              :class="{ valid: !v$.projecttype.$invalid, invalid: v$.projecttype.$invalid }"
              v-model.number="v$.projecttype.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.project.priorty')" for="project-priorty"></label>
            <input
              type="number"
              class="form-control"
              name="priorty"
              id="project-priorty"
              data-cy="priorty"
              :class="{ valid: !v$.priorty.$invalid, invalid: v$.priorty.$invalid }"
              v-model.number="v$.priorty.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.project.createdate')" for="project-createdate"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="project-createdate"
                  v-model="v$.createdate.$model"
                  name="createdate"
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
                id="project-createdate"
                data-cy="createdate"
                type="text"
                class="form-control"
                name="createdate"
                :class="{ valid: !v$.createdate.$invalid, invalid: v$.createdate.$invalid }"
                v-model="v$.createdate.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.project.secretlevel')" for="project-secretlevel"></label>
            <select
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="project-secretlevel"
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
            <label class="form-control-label" v-text="t$('jy1App.project.status')" for="project-status"></label>
            <select
              class="form-control"
              name="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
              id="project-status"
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
            <label class="form-control-label" v-text="t$('jy1App.project.auditStatus')" for="project-auditStatus"></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="project-auditStatus"
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
            <label class="form-control-label" v-text="t$('jy1App.project.progress')" for="project-progress"></label>
            <input
              type="number"
              class="form-control"
              name="progress"
              id="project-progress"
              data-cy="progress"
              :class="{ valid: !v$.progress.$invalid, invalid: v$.progress.$invalid }"
              v-model.number="v$.progress.$model"
            />
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.project.projectpbs')" for="project-projectpbs"></label>
            <select
              class="form-control"
              id="project-projectpbs"
              data-cy="projectpbs"
              multiple
              name="projectpbs"
              v-if="project.projectpbs !== undefined"
              v-model="project.projectpbs"
            >
              <option
                v-bind:value="getSelected(project.projectpbs, projectpbsOption, 'id')"
                v-for="projectpbsOption in projectpbs"
                :key="projectpbsOption.id"
              >
                {{ projectpbsOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.project.projectwbs')" for="project-projectwbs"></label>
            <select
              class="form-control"
              id="project-projectwbs"
              data-cy="projectwbs"
              multiple
              name="projectwbs"
              v-if="project.projectwbs !== undefined"
              v-model="project.projectwbs"
            >
              <option
                v-bind:value="getSelected(project.projectwbs, projectwbsOption, 'id')"
                v-for="projectwbsOption in projectwbs"
                :key="projectwbsOption.id"
              >
                {{ projectwbsOption.id }}
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
<script lang="ts" src="./project-update.component.ts"></script>
