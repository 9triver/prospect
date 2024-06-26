<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.outsourcingmanagement.home.createOrEditLabel"
          data-cy="OutsourcingmanagementCreateUpdateHeading"
          v-text="t$('jHipster0App.outsourcingmanagement.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="outsourcingmanagement.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="outsourcingmanagement.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.outsourcingmanagement.name')"
              for="outsourcingmanagement-name"
            ></label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="outsourcingmanagement-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.outsourcingmanagement.description')"
              for="outsourcingmanagement-description"
            ></label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="outsourcingmanagement-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.outsourcingmanagement.starttime')"
              for="outsourcingmanagement-starttime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="outsourcingmanagement-starttime"
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
                id="outsourcingmanagement-starttime"
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
              v-text="t$('jHipster0App.outsourcingmanagement.endtime')"
              for="outsourcingmanagement-endtime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="outsourcingmanagement-endtime"
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
                id="outsourcingmanagement-endtime"
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
            <label class="form-control-label" v-text="t$('jHipster0App.outsourcingmanagement.wbs')" for="outsourcingmanagement-wbs"></label>
            <select class="form-control" id="outsourcingmanagement-wbs" data-cy="wbs" name="wbs" v-model="outsourcingmanagement.wbs">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  outsourcingmanagement.wbs && outsourcingmanagementWbsOption.id === outsourcingmanagement.wbs.id
                    ? outsourcingmanagement.wbs
                    : outsourcingmanagementWbsOption
                "
                v-for="outsourcingmanagementWbsOption in outsourcingmanagementWbs"
                :key="outsourcingmanagementWbsOption.id"
              >
                {{ outsourcingmanagementWbsOption.id }}
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
<script lang="ts" src="./outsourcingmanagement-update.component.ts"></script>
