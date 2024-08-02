<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.progressPlan.home.createOrEditLabel"
          data-cy="ProgressPlanCreateUpdateHeading"
          v-text="t$('jy1App.progressPlan.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="progressPlan.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="progressPlan.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.progressPlan.planname')" for="progress-plan-planname"></label>
            <input
              type="text"
              class="form-control"
              name="planname"
              id="progress-plan-planname"
              data-cy="planname"
              :class="{ valid: !v$.planname.$invalid, invalid: v$.planname.$invalid }"
              v-model="v$.planname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.progressPlan.secretlevel')" for="progress-plan-secretlevel"></label>
            <select
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="progress-plan-secretlevel"
              data-cy="secretlevel"
            >
              <option
                v-for="secretlevel in secretlevelValues"
                :key="secretlevel"
                v-bind:value="secretlevel"
                v-bind:label="t$('jy1App.Secretlevel.' + secretlevel)"
              >
                {{ secretlevel }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.progressPlan.plantype')" for="progress-plan-plantype"></label>
            <input
              type="number"
              class="form-control"
              name="plantype"
              id="progress-plan-plantype"
              data-cy="plantype"
              :class="{ valid: !v$.plantype.$invalid, invalid: v$.plantype.$invalid }"
              v-model.number="v$.plantype.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.progressPlan.planlevel')" for="progress-plan-planlevel"></label>
            <select
              class="form-control"
              name="planlevel"
              :class="{ valid: !v$.planlevel.$invalid, invalid: v$.planlevel.$invalid }"
              v-model="v$.planlevel.$model"
              id="progress-plan-planlevel"
              data-cy="planlevel"
            >
              <option
                v-for="planLevel in planLevelValues"
                :key="planLevel"
                v-bind:value="planLevel"
                v-bind:label="t$('jy1App.PlanLevel.' + planLevel)"
              >
                {{ planLevel }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.progressPlan.planstage')" for="progress-plan-planstage"></label>
            <input
              type="text"
              class="form-control"
              name="planstage"
              id="progress-plan-planstage"
              data-cy="planstage"
              :class="{ valid: !v$.planstage.$invalid, invalid: v$.planstage.$invalid }"
              v-model="v$.planstage.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.progressPlan.readytime')" for="progress-plan-readytime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="progress-plan-readytime"
                  v-model="v$.readytime.$model"
                  name="readytime"
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
                id="progress-plan-readytime"
                data-cy="readytime"
                type="text"
                class="form-control"
                name="readytime"
                :class="{ valid: !v$.readytime.$invalid, invalid: v$.readytime.$invalid }"
                v-model="v$.readytime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.progressPlan.description')" for="progress-plan-description"></label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="progress-plan-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.progressPlan.deliverables')" for="progress-plan-deliverables"></label>
            <input
              type="text"
              class="form-control"
              name="deliverables"
              id="progress-plan-deliverables"
              data-cy="deliverables"
              :class="{ valid: !v$.deliverables.$invalid, invalid: v$.deliverables.$invalid }"
              v-model="v$.deliverables.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.progressPlan.planobjectives')" for="progress-plan-planobjectives"></label>
            <input
              type="text"
              class="form-control"
              name="planobjectives"
              id="progress-plan-planobjectives"
              data-cy="planobjectives"
              :class="{ valid: !v$.planobjectives.$invalid, invalid: v$.planobjectives.$invalid }"
              v-model="v$.planobjectives.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.progressPlan.preplan')" for="progress-plan-preplan"></label>
            <input
              type="text"
              class="form-control"
              name="preplan"
              id="progress-plan-preplan"
              data-cy="preplan"
              :class="{ valid: !v$.preplan.$invalid, invalid: v$.preplan.$invalid }"
              v-model="v$.preplan.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.progressPlan.starttime')" for="progress-plan-starttime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="progress-plan-starttime"
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
                id="progress-plan-starttime"
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
            <label class="form-control-label" v-text="t$('jy1App.progressPlan.endtime')" for="progress-plan-endtime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="progress-plan-endtime"
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
                id="progress-plan-endtime"
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
            <label
              class="form-control-label"
              v-text="t$('jy1App.progressPlan.actualstarttime')"
              for="progress-plan-actualstarttime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="progress-plan-actualstarttime"
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
                id="progress-plan-actualstarttime"
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
            <label class="form-control-label" v-text="t$('jy1App.progressPlan.actualendtime')" for="progress-plan-actualendtime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="progress-plan-actualendtime"
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
                id="progress-plan-actualendtime"
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
            <label class="form-control-label" v-text="t$('jy1App.progressPlan.progress')" for="progress-plan-progress"></label>
            <input
              type="number"
              class="form-control"
              name="progress"
              id="progress-plan-progress"
              data-cy="progress"
              :class="{ valid: !v$.progress.$invalid, invalid: v$.progress.$invalid }"
              v-model.number="v$.progress.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.progressPlan.progresstype')" for="progress-plan-progresstype"></label>
            <select
              class="form-control"
              name="progresstype"
              :class="{ valid: !v$.progresstype.$invalid, invalid: v$.progresstype.$invalid }"
              v-model="v$.progresstype.$model"
              id="progress-plan-progresstype"
              data-cy="progresstype"
            >
              <option
                v-for="progressstatus in progressstatusValues"
                :key="progressstatus"
                v-bind:value="progressstatus"
                v-bind:label="t$('jy1App.Progressstatus.' + progressstatus)"
              >
                {{ progressstatus }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.progressPlan.iskey')" for="progress-plan-iskey"></label>
            <input
              type="number"
              class="form-control"
              name="iskey"
              id="progress-plan-iskey"
              data-cy="iskey"
              :class="{ valid: !v$.iskey.$invalid, invalid: v$.iskey.$invalid }"
              v-model.number="v$.iskey.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.progressPlan.status')" for="progress-plan-status"></label>
            <select
              class="form-control"
              name="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
              id="progress-plan-status"
              data-cy="status"
            >
              <option
                v-for="planstatus in planstatusValues"
                :key="planstatus"
                v-bind:value="planstatus"
                v-bind:label="t$('jy1App.Planstatus.' + planstatus)"
              >
                {{ planstatus }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.progressPlan.auditStatus')" for="progress-plan-auditStatus"></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="progress-plan-auditStatus"
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
            <label class="form-control-label" v-text="t$('jy1App.progressPlan.remark')" for="progress-plan-remark"></label>
            <input
              type="text"
              class="form-control"
              name="remark"
              id="progress-plan-remark"
              data-cy="remark"
              :class="{ valid: !v$.remark.$invalid, invalid: v$.remark.$invalid }"
              v-model="v$.remark.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.progressPlan.responsibleperson')"
              for="progress-plan-responsibleperson"
            ></label>
            <select
              class="form-control"
              id="progress-plan-responsibleperson"
              data-cy="responsibleperson"
              name="responsibleperson"
              v-model="progressPlan.responsibleperson"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  progressPlan.responsibleperson && officersOption.id === progressPlan.responsibleperson.id
                    ? progressPlan.responsibleperson
                    : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.progressPlan.cooperatingperson')"
              for="progress-plan-cooperatingperson"
            ></label>
            <select
              class="form-control"
              id="progress-plan-cooperatingperson"
              data-cy="cooperatingperson"
              name="cooperatingperson"
              v-model="progressPlan.cooperatingperson"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  progressPlan.cooperatingperson && officersOption.id === progressPlan.cooperatingperson.id
                    ? progressPlan.cooperatingperson
                    : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.progressPlan.auditorid')" for="progress-plan-auditorid"></label>
            <select class="form-control" id="progress-plan-auditorid" data-cy="auditorid" name="auditorid" v-model="progressPlan.auditorid">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  progressPlan.auditorid && officersOption.id === progressPlan.auditorid.id ? progressPlan.auditorid : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.progressPlan.responsibledepartment')"
              for="progress-plan-responsibledepartment"
            ></label>
            <select
              class="form-control"
              id="progress-plan-responsibledepartment"
              data-cy="responsibledepartment"
              name="responsibledepartment"
              v-model="progressPlan.responsibledepartment"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  progressPlan.responsibledepartment && departmentOption.id === progressPlan.responsibledepartment.id
                    ? progressPlan.responsibledepartment
                    : departmentOption
                "
                v-for="departmentOption in departments"
                :key="departmentOption.id"
              >
                {{ departmentOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.progressPlan.cooperatingdepartment')"
              for="progress-plan-cooperatingdepartment"
            ></label>
            <select
              class="form-control"
              id="progress-plan-cooperatingdepartment"
              data-cy="cooperatingdepartment"
              name="cooperatingdepartment"
              v-model="progressPlan.cooperatingdepartment"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  progressPlan.cooperatingdepartment && departmentOption.id === progressPlan.cooperatingdepartment.id
                    ? progressPlan.cooperatingdepartment
                    : departmentOption
                "
                v-for="departmentOption in departments"
                :key="departmentOption.id"
              >
                {{ departmentOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.progressPlan.planReturns')" for="progress-plan-planReturns"></label>
            <select
              class="form-control"
              id="progress-plan-planReturns"
              data-cy="planReturns"
              name="planReturns"
              v-model="progressPlan.planReturns"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  progressPlan.planReturns && planReturnsOption.id === progressPlan.planReturns.id
                    ? progressPlan.planReturns
                    : planReturnsOption
                "
                v-for="planReturnsOption in planReturns"
                :key="planReturnsOption.id"
              >
                {{ planReturnsOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.progressPlan.projectwbs')" for="progress-plan-projectwbs"></label>
            <select
              class="form-control"
              id="progress-plan-projectwbs"
              data-cy="projectwbs"
              multiple
              name="projectwbs"
              v-if="progressPlan.projectwbs !== undefined"
              v-model="progressPlan.projectwbs"
            >
              <option
                v-bind:value="getSelected(progressPlan.projectwbs, projectwbsOption, 'id')"
                v-for="projectwbsOption in projectwbs"
                :key="projectwbsOption.id"
              >
                {{ projectwbsOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.progressPlan.projectRisk')" for="progress-plan-projectRisk"></label>
            <select
              class="form-control"
              id="progress-plan-projectRisks"
              data-cy="projectRisk"
              multiple
              name="projectRisk"
              v-if="progressPlan.projectRisks !== undefined"
              v-model="progressPlan.projectRisks"
            >
              <option
                v-bind:value="getSelected(progressPlan.projectRisks, projectRiskOption, 'id')"
                v-for="projectRiskOption in projectRisks"
                :key="projectRiskOption.id"
              >
                {{ projectRiskOption.id }}
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
<script lang="ts" src="./progress-plan-update.component.ts"></script>
