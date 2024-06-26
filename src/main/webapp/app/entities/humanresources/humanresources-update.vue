<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.humanresources.home.createOrEditLabel"
          data-cy="HumanresourcesCreateUpdateHeading"
          v-text="t$('jHipster0App.humanresources.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="humanresources.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="humanresources.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.humanresources.name')" for="humanresources-name"></label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="humanresources-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.humanresources.outdeportment')"
              for="humanresources-outdeportment"
            ></label>
            <input
              type="text"
              class="form-control"
              name="outdeportment"
              id="humanresources-outdeportment"
              data-cy="outdeportment"
              :class="{ valid: !v$.outdeportment.$invalid, invalid: v$.outdeportment.$invalid }"
              v-model="v$.outdeportment.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.humanresources.indeportment')"
              for="humanresources-indeportment"
            ></label>
            <input
              type="text"
              class="form-control"
              name="indeportment"
              id="humanresources-indeportment"
              data-cy="indeportment"
              :class="{ valid: !v$.indeportment.$invalid, invalid: v$.indeportment.$invalid }"
              v-model="v$.indeportment.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.humanresources.adjusttime')" for="humanresources-adjusttime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="humanresources-adjusttime"
                  v-model="v$.adjusttime.$model"
                  name="adjusttime"
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
                id="humanresources-adjusttime"
                data-cy="adjusttime"
                type="text"
                class="form-control"
                name="adjusttime"
                :class="{ valid: !v$.adjusttime.$invalid, invalid: v$.adjusttime.$invalid }"
                v-model="v$.adjusttime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.humanresources.projectname')"
              for="humanresources-projectname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="projectname"
              id="humanresources-projectname"
              data-cy="projectname"
              :class="{ valid: !v$.projectname.$invalid, invalid: v$.projectname.$invalid }"
              v-model="v$.projectname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.humanresources.deportment')" for="humanresources-deportment"></label>
            <input
              type="text"
              class="form-control"
              name="deportment"
              id="humanresources-deportment"
              data-cy="deportment"
              :class="{ valid: !v$.deportment.$invalid, invalid: v$.deportment.$invalid }"
              v-model="v$.deportment.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.humanresources.projectleader')"
              for="humanresources-projectleader"
            ></label>
            <input
              type="text"
              class="form-control"
              name="projectleader"
              id="humanresources-projectleader"
              data-cy="projectleader"
              :class="{ valid: !v$.projectleader.$invalid, invalid: v$.projectleader.$invalid }"
              v-model="v$.projectleader.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.humanresources.secretlevel')"
              for="humanresources-secretlevel"
            ></label>
            <select
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="humanresources-secretlevel"
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
              v-text="t$('jHipster0App.humanresources.auditStatus')"
              for="humanresources-auditStatus"
            ></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="humanresources-auditStatus"
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
            <label class="form-control-label" v-text="t$('jHipster0App.humanresources.project')" for="humanresources-project"></label>
            <select class="form-control" id="humanresources-project" data-cy="project" name="project" v-model="humanresources.project">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  humanresources.project && projectOption.id === humanresources.project.id ? humanresources.project : projectOption
                "
                v-for="projectOption in projects"
                :key="projectOption.id"
              >
                {{ projectOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.humanresources.creatorid')" for="humanresources-creatorid"></label>
            <select
              class="form-control"
              id="humanresources-creatorid"
              data-cy="creatorid"
              name="creatorid"
              v-model="humanresources.creatorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  humanresources.creatorid && officersOption.id === humanresources.creatorid.id ? humanresources.creatorid : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.humanresources.auditorid')" for="humanresources-auditorid"></label>
            <select
              class="form-control"
              id="humanresources-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="humanresources.auditorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  humanresources.auditorid && officersOption.id === humanresources.auditorid.id ? humanresources.auditorid : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
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
<script lang="ts" src="./humanresources-update.component.ts"></script>
