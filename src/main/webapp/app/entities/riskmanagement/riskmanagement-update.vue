<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.riskmanagement.home.createOrEditLabel"
          data-cy="RiskmanagementCreateUpdateHeading"
          v-text="t$('jHipster0App.riskmanagement.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="riskmanagement.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="riskmanagement.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.riskmanagement.name')" for="riskmanagement-name"></label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="riskmanagement-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.riskmanagement.description')"
              for="riskmanagement-description"
            ></label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="riskmanagement-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.riskmanagement.starttime')" for="riskmanagement-starttime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="riskmanagement-starttime"
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
                id="riskmanagement-starttime"
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
            <label class="form-control-label" v-text="t$('jHipster0App.riskmanagement.endtime')" for="riskmanagement-endtime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="riskmanagement-endtime"
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
                id="riskmanagement-endtime"
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
            <label class="form-control-label" v-text="t$('jHipster0App.riskmanagement.wbs')" for="riskmanagement-wbs"></label>
            <select class="form-control" id="riskmanagement-wbs" data-cy="wbs" name="wbs" v-model="riskmanagement.wbs">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  riskmanagement.wbs && riskmanagementWbsOption.id === riskmanagement.wbs.id ? riskmanagement.wbs : riskmanagementWbsOption
                "
                v-for="riskmanagementWbsOption in riskmanagementWbs"
                :key="riskmanagementWbsOption.id"
              >
                {{ riskmanagementWbsOption.id }}
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
<script lang="ts" src="./riskmanagement-update.component.ts"></script>
