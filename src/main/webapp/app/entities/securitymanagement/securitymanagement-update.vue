<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.securitymanagement.home.createOrEditLabel"
          data-cy="SecuritymanagementCreateUpdateHeading"
          v-text="t$('jHipster0App.securitymanagement.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="securitymanagement.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="securitymanagement.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.securitymanagement.name')" for="securitymanagement-name"></label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="securitymanagement-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.securitymanagement.description')"
              for="securitymanagement-description"
            ></label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="securitymanagement-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.securitymanagement.starttime')"
              for="securitymanagement-starttime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="securitymanagement-starttime"
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
                id="securitymanagement-starttime"
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
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.securitymanagement.endtime')"
              for="securitymanagement-endtime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="securitymanagement-endtime"
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
                id="securitymanagement-endtime"
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
            <label class="form-control-label" v-text="t$('jHipster0App.securitymanagement.wbs')" for="securitymanagement-wbs"></label>
            <select class="form-control" id="securitymanagement-wbs" data-cy="wbs" name="wbs" v-model="securitymanagement.wbs">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  securitymanagement.wbs && securitymanagementWbsOption.id === securitymanagement.wbs.id
                    ? securitymanagement.wbs
                    : securitymanagementWbsOption
                "
                v-for="securitymanagementWbsOption in securitymanagementWbs"
                :key="securitymanagementWbsOption.id"
              >
                {{ securitymanagementWbsOption.id }}
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
<script lang="ts" src="./securitymanagement-update.component.ts"></script>
