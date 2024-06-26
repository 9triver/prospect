<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.projectHumanresourcesplan.home.createOrEditLabel"
          data-cy="ProjectHumanresourcesplanCreateUpdateHeading"
          v-text="t$('jHipster0App.projectHumanresourcesplan.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="projectHumanresourcesplan.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="projectHumanresourcesplan.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.projectHumanresourcesplan.projectname')"
              for="project-humanresourcesplan-projectname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="projectname"
              id="project-humanresourcesplan-projectname"
              data-cy="projectname"
              :class="{ valid: !v$.projectname.$invalid, invalid: v$.projectname.$invalid }"
              v-model="v$.projectname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.projectHumanresourcesplan.clientname')"
              for="project-humanresourcesplan-clientname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="clientname"
              id="project-humanresourcesplan-clientname"
              data-cy="clientname"
              :class="{ valid: !v$.clientname.$invalid, invalid: v$.clientname.$invalid }"
              v-model="v$.clientname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.projectHumanresourcesplan.plandate')"
              for="project-humanresourcesplan-plandate"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="project-humanresourcesplan-plandate"
                  v-model="v$.plandate.$model"
                  name="plandate"
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
                id="project-humanresourcesplan-plandate"
                data-cy="plandate"
                type="text"
                class="form-control"
                name="plandate"
                :class="{ valid: !v$.plandate.$invalid, invalid: v$.plandate.$invalid }"
                v-model="v$.plandate.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.projectHumanresourcesplan.secretlevel')"
              for="project-humanresourcesplan-secretlevel"
            ></label>
            <select
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="project-humanresourcesplan-secretlevel"
              data-cy="secretlevel"
            >
              <option
                v-for="secretlevel in secretlevelValues"
                :key="secretlevel"
                v-bind:value="secretlevel"
                v-bind:label="t$('jHipster0App.Secretlevel.' + secretlevel)"
              >
                {{ secretlevel }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.projectHumanresourcesplan.auditStatus')"
              for="project-humanresourcesplan-auditStatus"
            ></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="project-humanresourcesplan-auditStatus"
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
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.projectHumanresourcesplan.project')"
              for="project-humanresourcesplan-project"
            ></label>
            <select
              class="form-control"
              id="project-humanresourcesplan-project"
              data-cy="project"
              name="project"
              v-model="projectHumanresourcesplan.project"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectHumanresourcesplan.project && projectOption.id === projectHumanresourcesplan.project.id
                    ? projectHumanresourcesplan.project
                    : projectOption
                "
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
<script lang="ts" src="./project-humanresourcesplan-update.component.ts"></script>
