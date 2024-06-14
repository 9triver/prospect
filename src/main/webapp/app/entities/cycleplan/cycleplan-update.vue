<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster3App.cycleplan.home.createOrEditLabel"
          data-cy="CycleplanCreateUpdateHeading"
          v-text="t$('jHipster3App.cycleplan.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="cycleplan.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="cycleplan.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.cycleplan.cycleplanid')" for="cycleplan-cycleplanid"></label>
            <input
              type="number"
              class="form-control"
              name="cycleplanid"
              id="cycleplan-cycleplanid"
              data-cy="cycleplanid"
              :class="{ valid: !v$.cycleplanid.$invalid, invalid: v$.cycleplanid.$invalid }"
              v-model.number="v$.cycleplanid.$model"
            />
            <div v-if="v$.cycleplanid.$anyDirty && v$.cycleplanid.$invalid">
              <small class="form-text text-danger" v-for="error of v$.cycleplanid.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.cycleplan.cycleplanname')" for="cycleplan-cycleplanname"></label>
            <input
              type="text"
              class="form-control"
              name="cycleplanname"
              id="cycleplan-cycleplanname"
              data-cy="cycleplanname"
              :class="{ valid: !v$.cycleplanname.$invalid, invalid: v$.cycleplanname.$invalid }"
              v-model="v$.cycleplanname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.cycleplan.secretlevel')" for="cycleplan-secretlevel"></label>
            <select
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="cycleplan-secretlevel"
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
            <label class="form-control-label" v-text="t$('jHipster3App.cycleplan.starttime')" for="cycleplan-starttime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="cycleplan-starttime"
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
                id="cycleplan-starttime"
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
            <label class="form-control-label" v-text="t$('jHipster3App.cycleplan.endtime')" for="cycleplan-endtime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="cycleplan-endtime"
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
                id="cycleplan-endtime"
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
            <label class="form-control-label" v-text="t$('jHipster3App.cycleplan.actualstarttime')" for="cycleplan-actualstarttime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="cycleplan-actualstarttime"
                  v-model="v$.actualstarttime.$model"
                  name="actualstarttime"
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
                id="cycleplan-actualstarttime"
                data-cy="actualstarttime"
                type="text"
                class="form-control"
                name="actualstarttime"
                :class="{ valid: !v$.actualstarttime.$invalid, invalid: v$.actualstarttime.$invalid }"
                v-model="v$.actualstarttime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.cycleplan.actualendtime')" for="cycleplan-actualendtime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="cycleplan-actualendtime"
                  v-model="v$.actualendtime.$model"
                  name="actualendtime"
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
                id="cycleplan-actualendtime"
                data-cy="actualendtime"
                type="text"
                class="form-control"
                name="actualendtime"
                :class="{ valid: !v$.actualendtime.$invalid, invalid: v$.actualendtime.$invalid }"
                v-model="v$.actualendtime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.cycleplan.responsiblename')" for="cycleplan-responsiblename"></label>
            <input
              type="text"
              class="form-control"
              name="responsiblename"
              id="cycleplan-responsiblename"
              data-cy="responsiblename"
              :class="{ valid: !v$.responsiblename.$invalid, invalid: v$.responsiblename.$invalid }"
              v-model="v$.responsiblename.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.cycleplan.status')" for="cycleplan-status"></label>
            <select
              class="form-control"
              name="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
              id="cycleplan-status"
              data-cy="status"
            >
              <option
                v-for="cycleplanstatus in cycleplanstatusValues"
                :key="cycleplanstatus"
                v-bind:value="cycleplanstatus"
                v-bind:label="t$('jHipster3App.Cycleplanstatus.' + cycleplanstatus)"
              >
                {{ cycleplanstatus }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.cycleplan.auditStatus')" for="cycleplan-auditStatus"></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="cycleplan-auditStatus"
              data-cy="auditStatus"
            >
              <option
                v-for="auditStatus in auditStatusValues"
                :key="auditStatus"
                v-bind:value="auditStatus"
                v-bind:label="t$('jHipster3App.AuditStatus.' + auditStatus)"
              >
                {{ auditStatus }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.cycleplan.document')" for="cycleplan-document"></label>
            <select class="form-control" id="cycleplan-document" data-cy="document" name="document" v-model="cycleplan.document">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="cycleplan.document && documentOption.id === cycleplan.document.id ? cycleplan.document : documentOption"
                v-for="documentOption in documents"
                :key="documentOption.id"
              >
                {{ documentOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.cycleplan.annualplan')" for="cycleplan-annualplan"></label>
            <select class="form-control" id="cycleplan-annualplan" data-cy="annualplan" name="annualplan" v-model="cycleplan.annualplan">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  cycleplan.annualplan && annualplanOption.id === cycleplan.annualplan.id ? cycleplan.annualplan : annualplanOption
                "
                v-for="annualplanOption in annualplans"
                :key="annualplanOption.id"
              >
                {{ annualplanOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.cycleplan.monthplan')" for="cycleplan-monthplan"></label>
            <select class="form-control" id="cycleplan-monthplan" data-cy="monthplan" name="monthplan" v-model="cycleplan.monthplan">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="cycleplan.monthplan && monthplanOption.id === cycleplan.monthplan.id ? cycleplan.monthplan : monthplanOption"
                v-for="monthplanOption in monthplans"
                :key="monthplanOption.id"
              >
                {{ monthplanOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.cycleplan.projectcharge')" for="cycleplan-projectcharge"></label>
            <select
              class="form-control"
              id="cycleplan-projectcharge"
              data-cy="projectcharge"
              name="projectcharge"
              v-model="cycleplan.projectcharge"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  cycleplan.projectcharge && projectchargeOption.id === cycleplan.projectcharge.id
                    ? cycleplan.projectcharge
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
            <label class="form-control-label" v-text="t$('jHipster3App.cycleplan.responsibleid')" for="cycleplan-responsibleid"></label>
            <select
              class="form-control"
              id="cycleplan-responsibleid"
              data-cy="responsibleid"
              name="responsibleid"
              v-model="cycleplan.responsibleid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  cycleplan.responsibleid && officersOption.id === cycleplan.responsibleid.id ? cycleplan.responsibleid : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.cycleplan.auditorid')" for="cycleplan-auditorid"></label>
            <select class="form-control" id="cycleplan-auditorid" data-cy="auditorid" name="auditorid" v-model="cycleplan.auditorid">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="cycleplan.auditorid && officersOption.id === cycleplan.auditorid.id ? cycleplan.auditorid : officersOption"
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
<script lang="ts" src="./cycleplan-update.component.ts"></script>
