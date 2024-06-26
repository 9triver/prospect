<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.qualitymanagement.home.createOrEditLabel"
          data-cy="QualitymanagementCreateUpdateHeading"
          v-text="t$('jHipster0App.qualitymanagement.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="qualitymanagement.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="qualitymanagement.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.qualitymanagement.name')" for="qualitymanagement-name"></label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="qualitymanagement-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.qualitymanagement.description')"
              for="qualitymanagement-description"
            ></label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="qualitymanagement-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.qualitymanagement.starttime')"
              for="qualitymanagement-starttime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="qualitymanagement-starttime"
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
                id="qualitymanagement-starttime"
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
            <label class="form-control-label" v-text="t$('jHipster0App.qualitymanagement.endtime')" for="qualitymanagement-endtime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="qualitymanagement-endtime"
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
                id="qualitymanagement-endtime"
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
            <label class="form-control-label" v-text="t$('jHipster0App.qualitymanagement.wbs')" for="qualitymanagement-wbs"></label>
            <select class="form-control" id="qualitymanagement-wbs" data-cy="wbs" name="wbs" v-model="qualitymanagement.wbs">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  qualitymanagement.wbs && qualitymanagementWbsOption.id === qualitymanagement.wbs.id
                    ? qualitymanagement.wbs
                    : qualitymanagementWbsOption
                "
                v-for="qualitymanagementWbsOption in qualitymanagementWbs"
                :key="qualitymanagementWbsOption.id"
              >
                {{ qualitymanagementWbsOption.id }}
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
<script lang="ts" src="./qualitymanagement-update.component.ts"></script>
