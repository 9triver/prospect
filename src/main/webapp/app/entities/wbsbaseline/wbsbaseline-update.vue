<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster3App.wbsbaseline.home.createOrEditLabel"
          data-cy="WbsbaselineCreateUpdateHeading"
          v-text="t$('jHipster3App.wbsbaseline.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="wbsbaseline.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="wbsbaseline.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.wbsbaseline.formid')" for="wbsbaseline-formid"></label>
            <input
              type="text"
              class="form-control"
              name="formid"
              id="wbsbaseline-formid"
              data-cy="formid"
              :class="{ valid: !v$.formid.$invalid, invalid: v$.formid.$invalid }"
              v-model="v$.formid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.wbsbaseline.secretlevel')" for="wbsbaseline-secretlevel"></label>
            <select
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="wbsbaseline-secretlevel"
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
              v-text="t$('jHipster3App.wbsbaseline.requestdeportment')"
              for="wbsbaseline-requestdeportment"
            ></label>
            <input
              type="text"
              class="form-control"
              name="requestdeportment"
              id="wbsbaseline-requestdeportment"
              data-cy="requestdeportment"
              :class="{ valid: !v$.requestdeportment.$invalid, invalid: v$.requestdeportment.$invalid }"
              v-model="v$.requestdeportment.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.wbsbaseline.chargetype')" for="wbsbaseline-chargetype"></label>
            <input
              type="number"
              class="form-control"
              name="chargetype"
              id="wbsbaseline-chargetype"
              data-cy="chargetype"
              :class="{ valid: !v$.chargetype.$invalid, invalid: v$.chargetype.$invalid }"
              v-model.number="v$.chargetype.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.wbsbaseline.chargecontent')" for="wbsbaseline-chargecontent"></label>
            <input
              type="text"
              class="form-control"
              name="chargecontent"
              id="wbsbaseline-chargecontent"
              data-cy="chargecontent"
              :class="{ valid: !v$.chargecontent.$invalid, invalid: v$.chargecontent.$invalid }"
              v-model="v$.chargecontent.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.wbsbaseline.status')" for="wbsbaseline-status"></label>
            <input
              type="number"
              class="form-control"
              name="status"
              id="wbsbaseline-status"
              data-cy="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model.number="v$.status.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.wbsbaseline.version')" for="wbsbaseline-version"></label>
            <input
              type="number"
              class="form-control"
              name="version"
              id="wbsbaseline-version"
              data-cy="version"
              :class="{ valid: !v$.version.$invalid, invalid: v$.version.$invalid }"
              v-model.number="v$.version.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.wbsbaseline.remark')" for="wbsbaseline-remark"></label>
            <input
              type="text"
              class="form-control"
              name="remark"
              id="wbsbaseline-remark"
              data-cy="remark"
              :class="{ valid: !v$.remark.$invalid, invalid: v$.remark.$invalid }"
              v-model="v$.remark.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.wbsbaseline.projectcharge')" for="wbsbaseline-projectcharge"></label>
            <select
              class="form-control"
              id="wbsbaseline-projectcharge"
              data-cy="projectcharge"
              name="projectcharge"
              v-model="wbsbaseline.projectcharge"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  wbsbaseline.projectcharge && projectchargeOption.id === wbsbaseline.projectcharge.id
                    ? wbsbaseline.projectcharge
                    : projectchargeOption
                "
                v-for="projectchargeOption in projectcharges"
                :key="projectchargeOption.id"
              >
                {{ projectchargeOption.id }}
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
<script lang="ts" src="./wbsbaseline-update.component.ts"></script>
