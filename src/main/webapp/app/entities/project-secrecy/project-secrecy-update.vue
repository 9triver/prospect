<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.projectSecrecy.home.createOrEditLabel"
          data-cy="ProjectSecrecyCreateUpdateHeading"
          v-text="t$('jHipster0App.projectSecrecy.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="projectSecrecy.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="projectSecrecy.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.projectSecrecy.projectname')"
              for="project-secrecy-projectname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="projectname"
              id="project-secrecy-projectname"
              data-cy="projectname"
              :class="{ valid: !v$.projectname.$invalid, invalid: v$.projectname.$invalid }"
              v-model="v$.projectname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.projectSecrecy.description')"
              for="project-secrecy-description"
            ></label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="project-secrecy-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.projectSecrecy.createtime')"
              for="project-secrecy-createtime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="project-secrecy-createtime"
                  v-model="v$.createtime.$model"
                  name="createtime"
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
                id="project-secrecy-createtime"
                data-cy="createtime"
                type="text"
                class="form-control"
                name="createtime"
                :class="{ valid: !v$.createtime.$invalid, invalid: v$.createtime.$invalid }"
                v-model="v$.createtime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.projectSecrecy.auditStatus')"
              for="project-secrecy-auditStatus"
            ></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="project-secrecy-auditStatus"
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
              v-text="t$('jHipster0App.projectSecrecy.secrecysystem')"
              for="project-secrecy-secrecysystem"
            ></label>
            <select
              class="form-control"
              id="project-secrecy-secrecysystem"
              data-cy="secrecysystem"
              name="secrecysystem"
              v-model="projectSecrecy.secrecysystem"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectSecrecy.secrecysystem && secrecysystemOption.id === projectSecrecy.secrecysystem.id
                    ? projectSecrecy.secrecysystem
                    : secrecysystemOption
                "
                v-for="secrecysystemOption in secrecysystems"
                :key="secrecysystemOption.id"
              >
                {{ secrecysystemOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.projectSecrecy.creatorid')" for="project-secrecy-creatorid"></label>
            <select
              class="form-control"
              id="project-secrecy-creatorid"
              data-cy="creatorid"
              name="creatorid"
              v-model="projectSecrecy.creatorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectSecrecy.creatorid && officersOption.id === projectSecrecy.creatorid.id ? projectSecrecy.creatorid : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.projectSecrecy.auditorid')" for="project-secrecy-auditorid"></label>
            <select
              class="form-control"
              id="project-secrecy-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="projectSecrecy.auditorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectSecrecy.auditorid && officersOption.id === projectSecrecy.auditorid.id ? projectSecrecy.auditorid : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.projectSecrecy.projectid')" for="project-secrecy-projectid"></label>
            <select
              class="form-control"
              id="project-secrecy-projectid"
              data-cy="projectid"
              name="projectid"
              v-model="projectSecrecy.projectid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectSecrecy.projectid && projectOption.id === projectSecrecy.projectid.id ? projectSecrecy.projectid : projectOption
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
<script lang="ts" src="./project-secrecy-update.component.ts"></script>
