<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.fundsmanagement.home.createOrEditLabel"
          data-cy="FundsmanagementCreateUpdateHeading"
          v-text="t$('jHipster0App.fundsmanagement.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="fundsmanagement.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="fundsmanagement.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.fundsmanagement.name')" for="fundsmanagement-name"></label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="fundsmanagement-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.fundsmanagement.description')"
              for="fundsmanagement-description"
            ></label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="fundsmanagement-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.fundsmanagement.starttime')" for="fundsmanagement-starttime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="fundsmanagement-starttime"
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
                id="fundsmanagement-starttime"
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
            <label class="form-control-label" v-text="t$('jHipster0App.fundsmanagement.endtime')" for="fundsmanagement-endtime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="fundsmanagement-endtime"
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
                id="fundsmanagement-endtime"
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
            <label class="form-control-label" v-text="t$('jHipster0App.fundsmanagement.wbs')" for="fundsmanagement-wbs"></label>
            <select class="form-control" id="fundsmanagement-wbs" data-cy="wbs" name="wbs" v-model="fundsmanagement.wbs">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  fundsmanagement.wbs && fundsmanagementWbsOption.id === fundsmanagement.wbs.id
                    ? fundsmanagement.wbs
                    : fundsmanagementWbsOption
                "
                v-for="fundsmanagementWbsOption in fundsmanagementWbs"
                :key="fundsmanagementWbsOption.id"
              >
                {{ fundsmanagementWbsOption.id }}
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
<script lang="ts" src="./fundsmanagement-update.component.ts"></script>
