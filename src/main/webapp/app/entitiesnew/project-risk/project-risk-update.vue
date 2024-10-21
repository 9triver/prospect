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
            <el-input type="text" class="form-control" id="id" name="id" v-model="projectRisk.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectRisk.year')" for="project-risk-year"></label>
            <el-input
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
            <label class="form-control-label" v-text="t$('jy1App.projectRisk.name')" for="project-risk-name"></label>
            <el-input
              type="text"
              class="form-control"
              name="name"
              id="project-risk-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectRisk.riskcontent')" for="project-risk-riskcontent"></label>
            <el-input
              type="text"
              class="form-control"
              name="riskcontent"
              id="project-risk-riskcontent"
              data-cy="riskcontent"
              :class="{ valid: !v$.riskcontent.$invalid, invalid: v$.riskcontent.$invalid }"
              v-model="v$.riskcontent.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.projectRisk.identificationtime')"
              for="project-risk-identificationtime"
            ></label>
            <el-date-picker v-model="v$.identificationtime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectRisk.riskreason')" for="project-risk-riskreason"></label>
            <el-input
              type="text"
              class="form-control"
              name="riskreason"
              id="project-risk-riskreason"
              data-cy="riskreason"
              :class="{ valid: !v$.riskreason.$invalid, invalid: v$.riskreason.$invalid }"
              v-model="v$.riskreason.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectRisk.importantrange')" for="project-risk-importantrange"></label>
            <el-input
              type="text"
              class="form-control"
              name="importantrange"
              id="project-risk-importantrange"
              data-cy="importantrange"
              :class="{ valid: !v$.importantrange.$invalid, invalid: v$.importantrange.$invalid }"
              v-model="v$.importantrange.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.projectRisk.measuresandtimelimit')"
              for="project-risk-measuresandtimelimit"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="measuresandtimelimit"
              id="project-risk-measuresandtimelimit"
              data-cy="measuresandtimelimit"
              :class="{ valid: !v$.measuresandtimelimit.$invalid, invalid: v$.measuresandtimelimit.$invalid }"
              v-model="v$.measuresandtimelimit.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectRisk.conditions')" for="project-risk-conditions"></label>
            <el-input
              type="text"
              class="form-control"
              name="conditions"
              id="project-risk-conditions"
              data-cy="conditions"
              :class="{ valid: !v$.conditions.$invalid, invalid: v$.conditions.$invalid }"
              v-model="v$.conditions.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.projectRisk.closedloopindicator')"
              for="project-risk-closedloopindicator"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="closedloopindicator"
              id="project-risk-closedloopindicator"
              data-cy="closedloopindicator"
              :class="{ valid: !v$.closedloopindicator.$invalid, invalid: v$.closedloopindicator.$invalid }"
              v-model="v$.closedloopindicator.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectRisk.wbsid')" for="project-risk-wbsid"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="project-risk-wbsid"
              data-cy="wbsid"
              name="wbsid"
              v-model="projectRisk.wbsid"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="projectRisk.wbsid && projectwbsOption.id === projectRisk.wbsid.id ? projectRisk.wbsid : projectwbsOption"
                v-for="projectwbsOption in projectwbs"
                :key="projectwbsOption.id"
                :label="projectwbsOption.id"
                >{{ projectwbsOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectRisk.workbag')" for="project-risk-workbag"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="project-risk-workbag"
              data-cy="workbag"
              name="workbag"
              v-model="projectRisk.workbag"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="projectRisk.workbag && workbagOption.id === projectRisk.workbag.id ? projectRisk.workbag : workbagOption"
                v-for="workbagOption in workbags"
                :key="workbagOption.id"
                :label="workbagOption.id"
                >{{ workbagOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectRisk.frontlineid')" for="project-risk-frontlineid"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="project-risk-frontlineid"
              data-cy="frontlineid"
              name="frontlineid"
              v-model="projectRisk.frontlineid"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  projectRisk.frontlineid && frontlineOption.id === projectRisk.frontlineid.id ? projectRisk.frontlineid : frontlineOption
                "
                v-for="frontlineOption in frontlines"
                :key="frontlineOption.id"
                :label="frontlineOption.id"
                >{{ frontlineOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectRisk.systemLevel')" for="project-risk-systemLevel"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="project-risk-systemLevel"
              data-cy="systemLevel"
              name="systemLevel"
              v-model="projectRisk.systemLevel"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  projectRisk.systemLevel && systemLevelOption.id === projectRisk.systemLevel.id
                    ? projectRisk.systemLevel
                    : systemLevelOption
                "
                v-for="systemLevelOption in systemLevels"
                :key="systemLevelOption.id"
                :label="systemLevelOption.id"
                >{{ systemLevelOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectRisk.riskType')" for="project-risk-riskType"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="project-risk-riskType"
              data-cy="riskType"
              name="riskType"
              v-model="projectRisk.riskType"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="projectRisk.riskType && riskTypeOption.id === projectRisk.riskType.id ? projectRisk.riskType : riskTypeOption"
                v-for="riskTypeOption in riskTypes"
                :key="riskTypeOption.id"
                :label="riskTypeOption.id"
                >{{ riskTypeOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectRisk.riskLevel')" for="project-risk-riskLevel"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="project-risk-riskLevel"
              data-cy="riskLevel"
              name="riskLevel"
              v-model="projectRisk.riskLevel"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  projectRisk.riskLevel && riskLevelOption.id === projectRisk.riskLevel.id ? projectRisk.riskLevel : riskLevelOption
                "
                v-for="riskLevelOption in riskLevels"
                :key="riskLevelOption.id"
                :label="riskLevelOption.id"
                >{{ riskLevelOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectRisk.riskPossibility')" for="project-risk-riskPossibility"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="project-risk-riskPossibility"
              data-cy="riskPossibility"
              name="riskPossibility"
              v-model="projectRisk.riskPossibility"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  projectRisk.riskPossibility && riskPossibilityOption.id === projectRisk.riskPossibility.id
                    ? projectRisk.riskPossibility
                    : riskPossibilityOption
                "
                v-for="riskPossibilityOption in riskPossibilities"
                :key="riskPossibilityOption.id"
                :label="riskPossibilityOption.id"
                >{{ riskPossibilityOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectRisk.progressPlan')" for="project-risk-progressPlan"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="project-risk-progressPlans"
              data-cy="progressPlan"
              multiple
              name="progressPlan"
              v-if="projectRisk.progressPlans !== undefined"
              v-model="projectRisk.progressPlans"
            >
              <el-option
                v-bind:value="getSelected(projectRisk.progressPlans, progressPlanOption, 'id')"
                v-for="progressPlanOption in progressPlans"
                :key="progressPlanOption.id"
                :label="progressPlanOption.id"
                >{{ progressPlanOption.id }}</el-option
              >
            </el-select>
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
