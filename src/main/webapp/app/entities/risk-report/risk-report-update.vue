<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.riskReport.home.createOrEditLabel"
          data-cy="RiskReportCreateUpdateHeading"
          v-text="t$('jy1App.riskReport.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="riskReport.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="riskReport.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.riskReport.type')" for="risk-report-type"></label>
            <input
              type="text"
              class="form-control"
              name="type"
              id="risk-report-type"
              data-cy="type"
              :class="{ valid: !v$.type.$invalid, invalid: v$.type.$invalid }"
              v-model="v$.type.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.riskReport.riskreportname')" for="risk-report-riskreportname"></label>
            <input
              type="text"
              class="form-control"
              name="riskreportname"
              id="risk-report-riskreportname"
              data-cy="riskreportname"
              :class="{ valid: !v$.riskreportname.$invalid, invalid: v$.riskreportname.$invalid }"
              v-model="v$.riskreportname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.riskReport.releasetime')" for="risk-report-releasetime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="risk-report-releasetime"
                  v-model="v$.releasetime.$model"
                  name="releasetime"
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
                id="risk-report-releasetime"
                data-cy="releasetime"
                type="text"
                class="form-control"
                name="releasetime"
                :class="{ valid: !v$.releasetime.$invalid, invalid: v$.releasetime.$invalid }"
                v-model="v$.releasetime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.riskReport.auditStatus')" for="risk-report-auditStatus"></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="risk-report-auditStatus"
              data-cy="auditStatus"
            >
              <option
                v-for="auditStatus in auditStatusValues"
                :key="auditStatus"
                v-bind:value="auditStatus"
                v-bind:label="t$('jy1App.AuditStatus.' + auditStatus)"
              >
                {{ auditStatus }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.riskReport.creatorid')" for="risk-report-creatorid"></label>
            <select class="form-control" id="risk-report-creatorid" data-cy="creatorid" name="creatorid" v-model="riskReport.creatorid">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="riskReport.creatorid && officersOption.id === riskReport.creatorid.id ? riskReport.creatorid : officersOption"
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.riskReport.auditorid')" for="risk-report-auditorid"></label>
            <select class="form-control" id="risk-report-auditorid" data-cy="auditorid" name="auditorid" v-model="riskReport.auditorid">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="riskReport.auditorid && officersOption.id === riskReport.auditorid.id ? riskReport.auditorid : officersOption"
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
<script lang="ts" src="./risk-report-update.component.ts"></script>
