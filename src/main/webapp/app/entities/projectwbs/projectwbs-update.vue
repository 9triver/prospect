<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.projectwbs.home.createOrEditLabel"
          data-cy="ProjectwbsCreateUpdateHeading"
          v-text="t$('jy1App.projectwbs.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="projectwbs.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="projectwbs.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.wbsname')" for="projectwbs-wbsname"></label>
            <el-input
              type="text"
              class="form-control"
              name="wbsname"
              id="projectwbs-wbsname"
              data-cy="wbsname"
              :class="{ valid: !v$.wbsname.$invalid, invalid: v$.wbsname.$invalid }"
              v-model="v$.wbsname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.parentwbsid')" for="projectwbs-parentwbsid"></label>
            <el-input
              type="text"
              class="form-control"
              name="parentwbsid"
              id="projectwbs-parentwbsid"
              data-cy="parentwbsid"
              :class="{ valid: !v$.parentwbsid.$invalid, invalid: v$.parentwbsid.$invalid }"
              v-model="v$.parentwbsid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.description')" for="projectwbs-description"></label>
            <el-input
              type="text"
              class="form-control"
              name="description"
              id="projectwbs-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.belongfrontline')" for="projectwbs-belongfrontline"></label>
            <el-input
              type="text"
              class="form-control"
              name="belongfrontline"
              id="projectwbs-belongfrontline"
              data-cy="belongfrontline"
              :class="{ valid: !v$.belongfrontline.$invalid, invalid: v$.belongfrontline.$invalid }"
              v-model="v$.belongfrontline.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.starttime')" for="projectwbs-starttime"></label>
            <el-date-picker v-model="v$.starttime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.endtime')" for="projectwbs-endtime"></label>
            <el-date-picker v-model="v$.endtime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.progress')" for="projectwbs-progress"></label>
            <el-input
              type="number"
              class="form-control"
              name="progress"
              id="projectwbs-progress"
              data-cy="progress"
              :class="{ valid: !v$.progress.$invalid, invalid: v$.progress.$invalid }"
              v-model.number="v$.progress.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.type')" for="projectwbs-type"></label>
            <el-input
              type="number"
              class="form-control"
              name="type"
              id="projectwbs-type"
              data-cy="type"
              :class="{ valid: !v$.type.$invalid, invalid: v$.type.$invalid }"
              v-model.number="v$.type.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.priorty')" for="projectwbs-priorty"></label>
            <el-input
              type="number"
              class="form-control"
              name="priorty"
              id="projectwbs-priorty"
              data-cy="priorty"
              :class="{ valid: !v$.priorty.$invalid, invalid: v$.priorty.$invalid }"
              v-model.number="v$.priorty.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.secretlevel')" for="projectwbs-secretlevel"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="projectwbs-secretlevel"
              data-cy="secretlevel"
            >
              <el-option
                v-for="secretlevel in secretlevelValues"
                :key="secretlevel"
                v-bind:value="secretlevel"
                v-bind:label="t$('jy1App.Secretlevel.' + secretlevel)"
                >{{ secretlevel }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.deliverables')" for="projectwbs-deliverables"></label>
            <el-input
              type="text"
              class="form-control"
              name="deliverables"
              id="projectwbs-deliverables"
              data-cy="deliverables"
              :class="{ valid: !v$.deliverables.$invalid, invalid: v$.deliverables.$invalid }"
              v-model="v$.deliverables.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.status')" for="projectwbs-status"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
              id="projectwbs-status"
              data-cy="status"
            >
              <el-option
                v-for="projectStatus in projectStatusValues"
                :key="projectStatus"
                v-bind:value="projectStatus"
                v-bind:label="t$('jy1App.ProjectStatus.' + projectStatus)"
                >{{ projectStatus }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.auditStatus')" for="projectwbs-auditStatus"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="projectwbs-auditStatus"
              data-cy="auditStatus"
            >
              <el-option
                v-for="auditStatus in auditStatusValues"
                :key="auditStatus"
                v-bind:value="auditStatus"
                v-bind:label="t$('jy1App.AuditStatus.' + auditStatus)"
                >{{ auditStatus }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.workbagid')" for="projectwbs-workbagid"></label>
            <el-input
              type="text"
              class="form-control"
              name="workbagid"
              id="projectwbs-workbagid"
              data-cy="workbagid"
              :class="{ valid: !v$.workbagid.$invalid, invalid: v$.workbagid.$invalid }"
              v-model="v$.workbagid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.projectpbs')" for="projectwbs-projectpbs"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="projectwbs-projectpbs"
              data-cy="projectpbs"
              name="projectpbs"
              v-model="projectwbs.projectpbs"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  projectwbs.projectpbs && projectpbsOption.id === projectwbs.projectpbs.id ? projectwbs.projectpbs : projectpbsOption
                "
                v-for="projectpbsOption in projectpbs"
                :key="projectpbsOption.id"
                :label="projectpbsOption.id"
                >{{ projectpbsOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.responsibleperson')" for="projectwbs-responsibleperson"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="projectwbs-responsibleperson"
              data-cy="responsibleperson"
              name="responsibleperson"
              v-model="projectwbs.responsibleperson"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  projectwbs.responsibleperson && hrManagementOption.id === projectwbs.responsibleperson.id
                    ? projectwbs.responsibleperson
                    : hrManagementOption
                "
                v-for="hrManagementOption in hrManagements"
                :key="hrManagementOption.id"
                :label="hrManagementOption.id"
                >{{ hrManagementOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.technicaldirector')" for="projectwbs-technicaldirector"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="projectwbs-technicaldirector"
              data-cy="technicaldirector"
              name="technicaldirector"
              v-model="projectwbs.technicaldirector"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  projectwbs.technicaldirector && hrManagementOption.id === projectwbs.technicaldirector.id
                    ? projectwbs.technicaldirector
                    : hrManagementOption
                "
                v-for="hrManagementOption in hrManagements"
                :key="hrManagementOption.id"
                :label="hrManagementOption.id"
                >{{ hrManagementOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.knowingpeople')" for="projectwbs-knowingpeople"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="projectwbs-knowingpeople"
              data-cy="knowingpeople"
              name="knowingpeople"
              v-model="projectwbs.knowingpeople"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  projectwbs.knowingpeople && hrManagementOption.id === projectwbs.knowingpeople.id
                    ? projectwbs.knowingpeople
                    : hrManagementOption
                "
                v-for="hrManagementOption in hrManagements"
                :key="hrManagementOption.id"
                :label="hrManagementOption.id"
                >{{ hrManagementOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.auditorid')" for="projectwbs-auditorid"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="projectwbs-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="projectwbs.auditorid"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  projectwbs.auditorid && hrManagementOption.id === projectwbs.auditorid.id ? projectwbs.auditorid : hrManagementOption
                "
                v-for="hrManagementOption in hrManagements"
                :key="hrManagementOption.id"
                :label="hrManagementOption.id"
                >{{ hrManagementOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.projectwbs.responsibledepartment')"
              for="projectwbs-responsibledepartment"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="projectwbs-responsibledepartment"
              data-cy="responsibledepartment"
              name="responsibledepartment"
              v-model="projectwbs.responsibledepartment"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  projectwbs.responsibledepartment && departmentOption.id === projectwbs.responsibledepartment.id
                    ? projectwbs.responsibledepartment
                    : departmentOption
                "
                v-for="departmentOption in departments"
                :key="departmentOption.id"
                :label="departmentOption.id"
                >{{ departmentOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectwbs.projectdeliverables')" for="projectwbs-projectdeliverables"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="projectwbs-projectdeliverables"
              data-cy="projectdeliverables"
              multiple
              name="projectdeliverables"
              v-if="projectwbs.projectdeliverables !== undefined"
              v-model="projectwbs.projectdeliverables"
            >
              <el-option
                v-bind:value="getSelected(projectwbs.projectdeliverables, projectdeliverablesOption, 'id')"
                v-for="projectdeliverablesOption in projectdeliverables"
                :key="projectdeliverablesOption.id"
                :label="projectdeliverablesOption.id"
                >{{ projectdeliverablesOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectwbs.relevantdepartment')" for="projectwbs-relevantdepartment"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="projectwbs-relevantdepartments"
              data-cy="relevantdepartment"
              multiple
              name="relevantdepartment"
              v-if="projectwbs.relevantdepartments !== undefined"
              v-model="projectwbs.relevantdepartments"
            >
              <el-option
                v-bind:value="getSelected(projectwbs.relevantdepartments, departmentOption, 'id')"
                v-for="departmentOption in departments"
                :key="departmentOption.id"
                :label="departmentOption.id"
                >{{ departmentOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectwbs.workbag')" for="projectwbs-workbag"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="projectwbs-workbags"
              data-cy="workbag"
              multiple
              name="workbag"
              v-if="projectwbs.workbags !== undefined"
              v-model="projectwbs.workbags"
            >
              <el-option
                v-bind:value="getSelected(projectwbs.workbags, workbagOption, 'id')"
                v-for="workbagOption in workbags"
                :key="workbagOption.id"
                :label="workbagOption.id"
                >{{ workbagOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectwbs.progressPlan')" for="projectwbs-progressPlan"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="projectwbs-progressPlans"
              data-cy="progressPlan"
              multiple
              name="progressPlan"
              v-if="projectwbs.progressPlans !== undefined"
              v-model="projectwbs.progressPlans"
            >
              <el-option
                v-bind:value="getSelected(projectwbs.progressPlans, progressPlanOption, 'id')"
                v-for="progressPlanOption in progressPlans"
                :key="progressPlanOption.id"
                :label="progressPlanOption.id"
                >{{ progressPlanOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectwbs.projectBudget')" for="projectwbs-projectBudget"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="projectwbs-projectBudgets"
              data-cy="projectBudget"
              multiple
              name="projectBudget"
              v-if="projectwbs.projectBudgets !== undefined"
              v-model="projectwbs.projectBudgets"
            >
              <el-option
                v-bind:value="getSelected(projectwbs.projectBudgets, projectBudgetOption, 'id')"
                v-for="projectBudgetOption in projectBudgets"
                :key="projectBudgetOption.id"
                :label="projectBudgetOption.id"
                >{{ projectBudgetOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectwbs.project')" for="projectwbs-project"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="projectwbs-projects"
              data-cy="project"
              multiple
              name="project"
              v-if="projectwbs.projects !== undefined"
              v-model="projectwbs.projects"
            >
              <el-option
                v-bind:value="getSelected(projectwbs.projects, projectOption, 'id')"
                v-for="projectOption in projects"
                :key="projectOption.id"
                :label="projectOption.id"
                >{{ projectOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectwbs.fundsEstimation')" for="projectwbs-fundsEstimation"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="projectwbs-fundsEstimations"
              data-cy="fundsEstimation"
              multiple
              name="fundsEstimation"
              v-if="projectwbs.fundsEstimations !== undefined"
              v-model="projectwbs.fundsEstimations"
            >
              <el-option
                v-bind:value="getSelected(projectwbs.fundsEstimations, fundsEstimationOption, 'id')"
                v-for="fundsEstimationOption in fundsEstimations"
                :key="fundsEstimationOption.id"
                :label="fundsEstimationOption.id"
                >{{ fundsEstimationOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectwbs.contractCostBudget')" for="projectwbs-contractCostBudget"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="projectwbs-contractCostBudgets"
              data-cy="contractCostBudget"
              multiple
              name="contractCostBudget"
              v-if="projectwbs.contractCostBudgets !== undefined"
              v-model="projectwbs.contractCostBudgets"
            >
              <el-option
                v-bind:value="getSelected(projectwbs.contractCostBudgets, contractCostBudgetOption, 'id')"
                v-for="contractCostBudgetOption in contractCostBudgets"
                :key="contractCostBudgetOption.id"
                :label="contractCostBudgetOption.id"
                >{{ contractCostBudgetOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectwbs.costControlSystem')" for="projectwbs-costControlSystem"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="projectwbs-costControlSystems"
              data-cy="costControlSystem"
              multiple
              name="costControlSystem"
              v-if="projectwbs.costControlSystems !== undefined"
              v-model="projectwbs.costControlSystems"
            >
              <el-option
                v-bind:value="getSelected(projectwbs.costControlSystems, costControlSystemOption, 'id')"
                v-for="costControlSystemOption in costControlSystems"
                :key="costControlSystemOption.id"
                :label="costControlSystemOption.id"
                >{{ costControlSystemOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectwbs.outsourcingContractual')" for="projectwbs-outsourcingContractual"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="projectwbs-outsourcingContractuals"
              data-cy="outsourcingContractual"
              multiple
              name="outsourcingContractual"
              v-if="projectwbs.outsourcingContractuals !== undefined"
              v-model="projectwbs.outsourcingContractuals"
            >
              <el-option
                v-bind:value="getSelected(projectwbs.outsourcingContractuals, outsourcingContractualOption, 'id')"
                v-for="outsourcingContractualOption in outsourcingContractuals"
                :key="outsourcingContractualOption.id"
                :label="outsourcingContractualOption.id"
                >{{ outsourcingContractualOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectwbs.outsourcingPurchasePlan')" for="projectwbs-outsourcingPurchasePlan"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="projectwbs-outsourcingPurchasePlans"
              data-cy="outsourcingPurchasePlan"
              multiple
              name="outsourcingPurchasePlan"
              v-if="projectwbs.outsourcingPurchasePlans !== undefined"
              v-model="projectwbs.outsourcingPurchasePlans"
            >
              <el-option
                v-bind:value="getSelected(projectwbs.outsourcingPurchasePlans, outsourcingPurchasePlanOption, 'id')"
                v-for="outsourcingPurchasePlanOption in outsourcingPurchasePlans"
                :key="outsourcingPurchasePlanOption.id"
                :label="outsourcingPurchasePlanOption.id"
                >{{ outsourcingPurchasePlanOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectwbs.technical')" for="projectwbs-technical"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="projectwbs-technicals"
              data-cy="technical"
              multiple
              name="technical"
              v-if="projectwbs.technicals !== undefined"
              v-model="projectwbs.technicals"
            >
              <el-option
                v-bind:value="getSelected(projectwbs.technicals, technicalOption, 'id')"
                v-for="technicalOption in technicals"
                :key="technicalOption.id"
                :label="technicalOption.id"
                >{{ technicalOption.id }}</el-option
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
<script lang="ts" src="./projectwbs-update.component.ts"></script>
