<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.monthplan.home.createOrEditLabel"
          data-cy="MonthplanCreateUpdateHeading"
          v-text="t$('jHipster0App.monthplan.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="monthplan.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="monthplan.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.monthplan.monthplanname')" for="monthplan-monthplanname"></label>
            <input
              type="text"
              class="form-control"
              name="monthplanname"
              id="monthplan-monthplanname"
              data-cy="monthplanname"
              :class="{ valid: !v$.monthplanname.$invalid, invalid: v$.monthplanname.$invalid }"
              v-model="v$.monthplanname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.monthplan.month')" for="monthplan-month"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="monthplan-month"
                  v-model="v$.month.$model"
                  name="month"
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
                id="monthplan-month"
                data-cy="month"
                type="text"
                class="form-control"
                name="month"
                :class="{ valid: !v$.month.$invalid, invalid: v$.month.$invalid }"
                v-model="v$.month.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.monthplan.secretlevel')" for="monthplan-secretlevel"></label>
            <select
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="monthplan-secretlevel"
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
            <label class="form-control-label" v-text="t$('jHipster0App.monthplan.creatorname')" for="monthplan-creatorname"></label>
            <input
              type="text"
              class="form-control"
              name="creatorname"
              id="monthplan-creatorname"
              data-cy="creatorname"
              :class="{ valid: !v$.creatorname.$invalid, invalid: v$.creatorname.$invalid }"
              v-model="v$.creatorname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.monthplan.status')" for="monthplan-status"></label>
            <select
              class="form-control"
              name="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
              id="monthplan-status"
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
            <label class="form-control-label" v-text="t$('jHipster0App.monthplan.auditStatus')" for="monthplan-auditStatus"></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="monthplan-auditStatus"
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
            <label class="form-control-label" v-text="t$('jHipster0App.monthplan.document')" for="monthplan-document"></label>
            <select class="form-control" id="monthplan-document" data-cy="document" name="document" v-model="monthplan.document">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="monthplan.document && documentOption.id === monthplan.document.id ? monthplan.document : documentOption"
                v-for="documentOption in documents"
                :key="documentOption.id"
              >
                {{ documentOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.monthplan.planreturns')" for="monthplan-planreturns"></label>
            <select
              class="form-control"
              id="monthplan-planreturns"
              data-cy="planreturns"
              name="planreturns"
              v-model="monthplan.planreturns"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  monthplan.planreturns && planexecuteOption.id === monthplan.planreturns.id ? monthplan.planreturns : planexecuteOption
                "
                v-for="planexecuteOption in planexecutes"
                :key="planexecuteOption.id"
              >
                {{ planexecuteOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.monthplan.projectcharge')" for="monthplan-projectcharge"></label>
            <select
              class="form-control"
              id="monthplan-projectcharge"
              data-cy="projectcharge"
              name="projectcharge"
              v-model="monthplan.projectcharge"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  monthplan.projectcharge && projectchargeOption.id === monthplan.projectcharge.id
                    ? monthplan.projectcharge
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
            <label class="form-control-label" v-text="t$('jHipster0App.monthplan.creatorid')" for="monthplan-creatorid"></label>
            <select class="form-control" id="monthplan-creatorid" data-cy="creatorid" name="creatorid" v-model="monthplan.creatorid">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="monthplan.creatorid && officersOption.id === monthplan.creatorid.id ? monthplan.creatorid : officersOption"
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.monthplan.auditorid')" for="monthplan-auditorid"></label>
            <select class="form-control" id="monthplan-auditorid" data-cy="auditorid" name="auditorid" v-model="monthplan.auditorid">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="monthplan.auditorid && officersOption.id === monthplan.auditorid.id ? monthplan.auditorid : officersOption"
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
<script lang="ts" src="./monthplan-update.component.ts"></script>
