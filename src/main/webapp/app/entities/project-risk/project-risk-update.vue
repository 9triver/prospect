<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.projectRisk.home.createOrEditLabel"
          data-cy="ProjectRiskCreateUpdateHeading"
          v-text="t$('jy1App.projectRisk.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="projectRisk.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="projectRisk.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectRisk.year')" for="project-risk-year"></label>
            <input
              type="number"
              class="form-control"
              name="year"
              id="project-risk-year"
              data-cy="year"
              :class="{ valid: !v$.year.$invalid, invalid: v$.year.$invalid }"
              v-model.number="v$.year.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectRisk.nodename')" for="project-risk-nodename"></label>
            <input
              type="text"
              class="form-control"
              name="nodename"
              id="project-risk-nodename"
              data-cy="nodename"
              :class="{ valid: !v$.nodename.$invalid, invalid: v$.nodename.$invalid }"
              v-model="v$.nodename.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectRisk.risktype')" for="project-risk-risktype"></label>
            <input
              type="number"
              class="form-control"
              name="risktype"
              id="project-risk-risktype"
              data-cy="risktype"
              :class="{ valid: !v$.risktype.$invalid, invalid: v$.risktype.$invalid }"
              v-model.number="v$.risktype.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectRisk.decumentid')" for="project-risk-decumentid"></label>
            <input
              type="number"
              class="form-control"
              name="decumentid"
              id="project-risk-decumentid"
              data-cy="decumentid"
              :class="{ valid: !v$.decumentid.$invalid, invalid: v$.decumentid.$invalid }"
              v-model.number="v$.decumentid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectRisk.version')" for="project-risk-version"></label>
            <input
              type="number"
              class="form-control"
              name="version"
              id="project-risk-version"
              data-cy="version"
              :class="{ valid: !v$.version.$invalid, invalid: v$.version.$invalid }"
              v-model.number="v$.version.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectRisk.usetime')" for="project-risk-usetime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="project-risk-usetime"
                  v-model="v$.usetime.$model"
                  name="usetime"
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
                id="project-risk-usetime"
                data-cy="usetime"
                type="text"
                class="form-control"
                name="usetime"
                :class="{ valid: !v$.usetime.$invalid, invalid: v$.usetime.$invalid }"
                v-model="v$.usetime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectRisk.systemlevel')" for="project-risk-systemlevel"></label>
            <input
              type="number"
              class="form-control"
              name="systemlevel"
              id="project-risk-systemlevel"
              data-cy="systemlevel"
              :class="{ valid: !v$.systemlevel.$invalid, invalid: v$.systemlevel.$invalid }"
              v-model.number="v$.systemlevel.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectRisk.risklevel')" for="project-risk-risklevel"></label>
            <select
              class="form-control"
              name="risklevel"
              :class="{ valid: !v$.risklevel.$invalid, invalid: v$.risklevel.$invalid }"
              v-model="v$.risklevel.$model"
              id="project-risk-risklevel"
              data-cy="risklevel"
            >
              <option
                v-for="risklevel in risklevelValues"
                :key="risklevel"
                v-bind:value="risklevel"
                v-bind:label="t$('jy1App.Risklevel.' + risklevel)"
              >
                {{ risklevel }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectRisk.limitationtime')" for="project-risk-limitationtime"></label>
            <input
              type="text"
              class="form-control"
              name="limitationtime"
              id="project-risk-limitationtime"
              data-cy="limitationtime"
              :class="{ valid: !v$.limitationtime.$invalid, invalid: v$.limitationtime.$invalid }"
              v-model="v$.limitationtime.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectRisk.closetype')" for="project-risk-closetype"></label>
            <input
              type="number"
              class="form-control"
              name="closetype"
              id="project-risk-closetype"
              data-cy="closetype"
              :class="{ valid: !v$.closetype.$invalid, invalid: v$.closetype.$invalid }"
              v-model.number="v$.closetype.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectRisk.riskReport')" for="project-risk-riskReport"></label>
            <select
              class="form-control"
              id="project-risk-riskReport"
              data-cy="riskReport"
              name="riskReport"
              v-model="projectRisk.riskReport"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectRisk.riskReport && riskReportOption.id === projectRisk.riskReport.id ? projectRisk.riskReport : riskReportOption
                "
                v-for="riskReportOption in riskReports"
                :key="riskReportOption.id"
              >
                {{ riskReportOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectRisk.creatorid')" for="project-risk-creatorid"></label>
            <select class="form-control" id="project-risk-creatorid" data-cy="creatorid" name="creatorid" v-model="projectRisk.creatorid">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectRisk.creatorid && officersOption.id === projectRisk.creatorid.id ? projectRisk.creatorid : officersOption
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
              v-text="t$('jy1App.projectRisk.responsibleperson')"
              for="project-risk-responsibleperson"
            ></label>
            <select
              class="form-control"
              id="project-risk-responsibleperson"
              data-cy="responsibleperson"
              name="responsibleperson"
              v-model="projectRisk.responsibleperson"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectRisk.responsibleperson && officersOption.id === projectRisk.responsibleperson.id
                    ? projectRisk.responsibleperson
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
            <label class="form-control-label" v-text="t$('jy1App.projectRisk.auditorid')" for="project-risk-auditorid"></label>
            <select class="form-control" id="project-risk-auditorid" data-cy="auditorid" name="auditorid" v-model="projectRisk.auditorid">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectRisk.auditorid && officersOption.id === projectRisk.auditorid.id ? projectRisk.auditorid : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectRisk.projectwbs')" for="project-risk-projectwbs"></label>
            <select
              class="form-control"
              id="project-risk-projectwbs"
              data-cy="projectwbs"
              multiple
              name="projectwbs"
              v-if="projectRisk.projectwbs !== undefined"
              v-model="projectRisk.projectwbs"
            >
              <option
                v-bind:value="getSelected(projectRisk.projectwbs, projectwbsOption, 'id')"
                v-for="projectwbsOption in projectwbs"
                :key="projectwbsOption.id"
              >
                {{ projectwbsOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectRisk.progressPlan')" for="project-risk-progressPlan"></label>
            <select
              class="form-control"
              id="project-risk-progressPlans"
              data-cy="progressPlan"
              multiple
              name="progressPlan"
              v-if="projectRisk.progressPlans !== undefined"
              v-model="projectRisk.progressPlans"
            >
              <option
                v-bind:value="getSelected(projectRisk.progressPlans, progressPlanOption, 'id')"
                v-for="progressPlanOption in progressPlans"
                :key="progressPlanOption.id"
              >
                {{ progressPlanOption.id }}
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
<script lang="ts" src="./project-risk-update.component.ts"></script>
