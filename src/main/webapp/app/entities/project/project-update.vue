<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.project.home.createOrEditLabel"
          data-cy="ProjectCreateUpdateHeading"
          v-text="t$('jHipster0App.project.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="project.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="project.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.project.projectname')" for="project-projectname"></label>
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
            <label class="form-control-label" v-text="t$('jHipster0App.project.description')" for="project-description"></label>
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
            <label class="form-control-label" v-text="t$('jHipster0App.project.number')" for="project-number"></label>
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
            <label class="form-control-label" v-text="t$('jHipster0App.project.projecttype')" for="project-projecttype"></label>
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
            <label class="form-control-label" v-text="t$('jHipster0App.project.responsiblename')" for="project-responsiblename"></label>
            <input
              type="text"
              class="form-control"
              name="responsiblename"
              id="project-responsiblename"
              data-cy="responsiblename"
              :class="{ valid: !v$.responsiblename.$invalid, invalid: v$.responsiblename.$invalid }"
              v-model="v$.responsiblename.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.project.duedate')" for="project-duedate"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="project-duedate"
                  v-model="v$.duedate.$model"
                  name="duedate"
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
                id="project-duedate"
                data-cy="duedate"
                type="text"
                class="form-control"
                name="duedate"
                :class="{ valid: !v$.duedate.$invalid, invalid: v$.duedate.$invalid }"
                v-model="v$.duedate.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.project.priorty')" for="project-priorty"></label>
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
            <label class="form-control-label" v-text="t$('jHipster0App.project.status')" for="project-status"></label>
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
                v-bind:label="t$('jHipster0App.ProjectStatus.' + projectStatus)"
              >
                {{ projectStatus }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.project.auditStatus')" for="project-auditStatus"></label>
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
                v-bind:label="t$('jHipster0App.AuditStatus.' + auditStatus)"
              >
                {{ auditStatus }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.project.projectwbs')" for="project-projectwbs"></label>
            <select class="form-control" id="project-projectwbs" data-cy="projectwbs" name="projectwbs" v-model="project.projectwbs">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="project.projectwbs && projectwbsOption.id === project.projectwbs.id ? project.projectwbs : projectwbsOption"
                v-for="projectwbsOption in projectwbs"
                :key="projectwbsOption.id"
              >
                {{ projectwbsOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.project.responsibleid')" for="project-responsibleid"></label>
            <select
              class="form-control"
              id="project-responsibleid"
              data-cy="responsibleid"
              name="responsibleid"
              v-model="project.responsibleid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  project.responsibleid && officersOption.id === project.responsibleid.id ? project.responsibleid : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.project.auditorid')" for="project-auditorid"></label>
            <select class="form-control" id="project-auditorid" data-cy="auditorid" name="auditorid" v-model="project.auditorid">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="project.auditorid && officersOption.id === project.auditorid.id ? project.auditorid : officersOption"
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.project.projectSecrecy')" for="project-projectSecrecy"></label>
            <select
              class="form-control"
              id="project-projectSecrecy"
              data-cy="projectSecrecy"
              name="projectSecrecy"
              v-model="project.projectSecrecy"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  project.projectSecrecy && projectSecrecyOption.id === project.projectSecrecy.id
                    ? project.projectSecrecy
                    : projectSecrecyOption
                "
                v-for="projectSecrecyOption in projectSecrecies"
                :key="projectSecrecyOption.id"
              >
                {{ projectSecrecyOption.id }}
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
