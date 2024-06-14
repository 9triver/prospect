<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster3App.resourcemanagement.home.createOrEditLabel"
          data-cy="ResourcemanagementCreateUpdateHeading"
          v-text="t$('jHipster3App.resourcemanagement.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="resourcemanagement.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="resourcemanagement.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.resourcemanagement.resourceid')"
              for="resourcemanagement-resourceid"
            ></label>
            <input
              type="number"
              class="form-control"
              name="resourceid"
              id="resourcemanagement-resourceid"
              data-cy="resourceid"
              :class="{ valid: !v$.resourceid.$invalid, invalid: v$.resourceid.$invalid }"
              v-model.number="v$.resourceid.$model"
            />
            <div v-if="v$.resourceid.$anyDirty && v$.resourceid.$invalid">
              <small class="form-text text-danger" v-for="error of v$.resourceid.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.resourcemanagement.projectname')"
              for="resourcemanagement-projectname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="projectname"
              id="resourcemanagement-projectname"
              data-cy="projectname"
              :class="{ valid: !v$.projectname.$invalid, invalid: v$.projectname.$invalid }"
              v-model="v$.projectname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.resourcemanagement.clientname')"
              for="resourcemanagement-clientname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="clientname"
              id="resourcemanagement-clientname"
              data-cy="clientname"
              :class="{ valid: !v$.clientname.$invalid, invalid: v$.clientname.$invalid }"
              v-model="v$.clientname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.resourcemanagement.plandate')"
              for="resourcemanagement-plandate"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="resourcemanagement-plandate"
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
                id="resourcemanagement-plandate"
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
              v-text="t$('jHipster3App.resourcemanagement.creatorname')"
              for="resourcemanagement-creatorname"
            ></label>
            <input
              type="text"
              class="form-control"
              name="creatorname"
              id="resourcemanagement-creatorname"
              data-cy="creatorname"
              :class="{ valid: !v$.creatorname.$invalid, invalid: v$.creatorname.$invalid }"
              v-model="v$.creatorname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.resourcemanagement.secretlevel')"
              for="resourcemanagement-secretlevel"
            ></label>
            <select
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="resourcemanagement-secretlevel"
              data-cy="secretlevel"
            >
              <option
                v-for="secretlevel in secretlevelValues"
                :key="secretlevel"
                v-bind:value="secretlevel"
                v-bind:label="t$('jHipster3App.Secretlevel.' + secretlevel)"
              >
                {{ secretlevel }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.resourcemanagement.auditStatus')"
              for="resourcemanagement-auditStatus"
            ></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="resourcemanagement-auditStatus"
              data-cy="auditStatus"
            >
              <option
                v-for="auditStatus in auditStatusValues"
                :key="auditStatus"
                v-bind:value="auditStatus"
                v-bind:label="t$('jHipster3App.AuditStatus.' + auditStatus)"
              >
                {{ auditStatus }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.resourcemanagement.creatorid')"
              for="resourcemanagement-creatorid"
            ></label>
            <select
              class="form-control"
              id="resourcemanagement-creatorid"
              data-cy="creatorid"
              name="creatorid"
              v-model="resourcemanagement.creatorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  resourcemanagement.creatorid && officersOption.id === resourcemanagement.creatorid.id
                    ? resourcemanagement.creatorid
                    : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.resourcemanagement.auditorid')"
              for="resourcemanagement-auditorid"
            ></label>
            <select
              class="form-control"
              id="resourcemanagement-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="resourcemanagement.auditorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  resourcemanagement.auditorid && officersOption.id === resourcemanagement.auditorid.id
                    ? resourcemanagement.auditorid
                    : officersOption
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
<script lang="ts" src="./resourcemanagement-update.component.ts"></script>
