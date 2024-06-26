<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.projectcharge.home.createOrEditLabel"
          data-cy="ProjectchargeCreateUpdateHeading"
          v-text="t$('jHipster0App.projectcharge.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="projectcharge.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="projectcharge.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.projectcharge.projectname')" for="projectcharge-projectname"></label>
            <input
              type="text"
              class="form-control"
              name="projectname"
              id="projectcharge-projectname"
              data-cy="projectname"
              :class="{ valid: !v$.projectname.$invalid, invalid: v$.projectname.$invalid }"
              v-model="v$.projectname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.projectcharge.formid')" for="projectcharge-formid"></label>
            <input
              type="text"
              class="form-control"
              name="formid"
              id="projectcharge-formid"
              data-cy="formid"
              :class="{ valid: !v$.formid.$invalid, invalid: v$.formid.$invalid }"
              v-model="v$.formid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.projectcharge.starttime')" for="projectcharge-starttime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="projectcharge-starttime"
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
                id="projectcharge-starttime"
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
            <label class="form-control-label" v-text="t$('jHipster0App.projectcharge.endtime')" for="projectcharge-endtime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="projectcharge-endtime"
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
                id="projectcharge-endtime"
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
            <label class="form-control-label" v-text="t$('jHipster0App.projectcharge.secretlevel')" for="projectcharge-secretlevel"></label>
            <select
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="projectcharge-secretlevel"
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
              v-text="t$('jHipster0App.projectcharge.requestdeportment')"
              for="projectcharge-requestdeportment"
            ></label>
            <input
              type="text"
              class="form-control"
              name="requestdeportment"
              id="projectcharge-requestdeportment"
              data-cy="requestdeportment"
              :class="{ valid: !v$.requestdeportment.$invalid, invalid: v$.requestdeportment.$invalid }"
              v-model="v$.requestdeportment.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.projectcharge.chargetype')" for="projectcharge-chargetype"></label>
            <input
              type="number"
              class="form-control"
              name="chargetype"
              id="projectcharge-chargetype"
              data-cy="chargetype"
              :class="{ valid: !v$.chargetype.$invalid, invalid: v$.chargetype.$invalid }"
              v-model.number="v$.chargetype.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster0App.projectcharge.chargecontent')"
              for="projectcharge-chargecontent"
            ></label>
            <input
              type="text"
              class="form-control"
              name="chargecontent"
              id="projectcharge-chargecontent"
              data-cy="chargecontent"
              :class="{ valid: !v$.chargecontent.$invalid, invalid: v$.chargecontent.$invalid }"
              v-model="v$.chargecontent.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.projectcharge.creatorid')" for="projectcharge-creatorid"></label>
            <select
              class="form-control"
              id="projectcharge-creatorid"
              data-cy="creatorid"
              name="creatorid"
              v-model="projectcharge.creatorid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectcharge.creatorid && officersOption.id === projectcharge.creatorid.id ? projectcharge.creatorid : officersOption
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
<script lang="ts" src="./projectcharge-update.component.ts"></script>
