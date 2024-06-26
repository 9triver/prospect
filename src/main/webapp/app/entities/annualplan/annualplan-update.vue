<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.annualplan.home.createOrEditLabel"
          data-cy="AnnualplanCreateUpdateHeading"
          v-text="t$('jHipster0App.annualplan.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="annualplan.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="annualplan.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.annualplan.annualplanname')" for="annualplan-annualplanname"></label>
            <input
              type="text"
              class="form-control"
              name="annualplanname"
              id="annualplan-annualplanname"
              data-cy="annualplanname"
              :class="{ valid: !v$.annualplanname.$invalid, invalid: v$.annualplanname.$invalid }"
              v-model="v$.annualplanname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.annualplan.year')" for="annualplan-year"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="annualplan-year"
                  v-model="v$.year.$model"
                  name="year"
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
                id="annualplan-year"
                data-cy="year"
                type="text"
                class="form-control"
                name="year"
                :class="{ valid: !v$.year.$invalid, invalid: v$.year.$invalid }"
                v-model="v$.year.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.annualplan.secretlevel')" for="annualplan-secretlevel"></label>
            <select
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="annualplan-secretlevel"
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
            <label class="form-control-label" v-text="t$('jHipster0App.annualplan.creatorname')" for="annualplan-creatorname"></label>
            <input
              type="text"
              class="form-control"
              name="creatorname"
              id="annualplan-creatorname"
              data-cy="creatorname"
              :class="{ valid: !v$.creatorname.$invalid, invalid: v$.creatorname.$invalid }"
              v-model="v$.creatorname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.annualplan.status')" for="annualplan-status"></label>
            <select
              class="form-control"
              name="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
              id="annualplan-status"
              data-cy="status"
            >
              <option
                v-for="annualplanstatus in annualplanstatusValues"
                :key="annualplanstatus"
                v-bind:value="annualplanstatus"
                v-bind:label="t$('jHipster0App.Annualplanstatus.' + annualplanstatus)"
              >
                {{ annualplanstatus }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.annualplan.auditStatus')" for="annualplan-auditStatus"></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="annualplan-auditStatus"
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
            <label class="form-control-label" v-text="t$('jHipster0App.annualplan.document')" for="annualplan-document"></label>
            <select class="form-control" id="annualplan-document" data-cy="document" name="document" v-model="annualplan.document">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="annualplan.document && documentOption.id === annualplan.document.id ? annualplan.document : documentOption"
                v-for="documentOption in documents"
                :key="documentOption.id"
              >
                {{ documentOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.annualplan.monthplan')" for="annualplan-monthplan"></label>
            <select class="form-control" id="annualplan-monthplan" data-cy="monthplan" name="monthplan" v-model="annualplan.monthplan">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  annualplan.monthplan && monthplanOption.id === annualplan.monthplan.id ? annualplan.monthplan : monthplanOption
                "
                v-for="monthplanOption in monthplans"
                :key="monthplanOption.id"
              >
                {{ monthplanOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.annualplan.projectcharge')" for="annualplan-projectcharge"></label>
            <select
              class="form-control"
              id="annualplan-projectcharge"
              data-cy="projectcharge"
              name="projectcharge"
              v-model="annualplan.projectcharge"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  annualplan.projectcharge && projectchargeOption.id === annualplan.projectcharge.id
                    ? annualplan.projectcharge
                    : projectchargeOption
                "
                v-for="projectchargeOption in projectcharges"
                :key="projectchargeOption.id"
              >
                {{ projectchargeOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.annualplan.creatorid')" for="annualplan-creatorid"></label>
            <select class="form-control" id="annualplan-creatorid" data-cy="creatorid" name="creatorid" v-model="annualplan.creatorid">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="annualplan.creatorid && officersOption.id === annualplan.creatorid.id ? annualplan.creatorid : officersOption"
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.annualplan.auditorid')" for="annualplan-auditorid"></label>
            <select class="form-control" id="annualplan-auditorid" data-cy="auditorid" name="auditorid" v-model="annualplan.auditorid">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="annualplan.auditorid && officersOption.id === annualplan.auditorid.id ? annualplan.auditorid : officersOption"
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
<script lang="ts" src="./annualplan-update.component.ts"></script>
