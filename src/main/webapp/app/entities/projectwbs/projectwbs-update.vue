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
            <input type="text" class="form-control" id="id" name="id" v-model="projectwbs.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.wbsname')" for="projectwbs-wbsname"></label>
            <input
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
            <input
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
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.pbsid')" for="projectwbs-pbsid"></label>
            <input
              type="text"
              class="form-control"
              name="pbsid"
              id="projectwbs-pbsid"
              data-cy="pbsid"
              :class="{ valid: !v$.pbsid.$invalid, invalid: v$.pbsid.$invalid }"
              v-model="v$.pbsid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.description')" for="projectwbs-description"></label>
            <input
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
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.belongfront')" for="projectwbs-belongfront"></label>
            <input
              type="text"
              class="form-control"
              name="belongfront"
              id="projectwbs-belongfront"
              data-cy="belongfront"
              :class="{ valid: !v$.belongfront.$invalid, invalid: v$.belongfront.$invalid }"
              v-model="v$.belongfront.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.starttime')" for="projectwbs-starttime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="projectwbs-starttime"
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
                id="projectwbs-starttime"
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
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.endtime')" for="projectwbs-endtime"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="projectwbs-endtime"
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
                id="projectwbs-endtime"
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
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.progress')" for="projectwbs-progress"></label>
            <input
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
            <input
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
            <input
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
            <select
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="projectwbs-secretlevel"
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
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.deliverables')" for="projectwbs-deliverables"></label>
            <input
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
            <select
              class="form-control"
              name="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
              id="projectwbs-status"
              data-cy="status"
            >
              <option
                v-for="projectStatus in projectStatusValues"
                :key="projectStatus"
                v-bind:value="projectStatus"
                v-bind:label="t$('jy1App.ProjectStatus.' + projectStatus)"
              >
                {{ projectStatus }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.auditStatus')" for="projectwbs-auditStatus"></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="projectwbs-auditStatus"
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
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.workbag')" for="projectwbs-workbag"></label>
            <input
              type="number"
              class="form-control"
              name="workbag"
              id="projectwbs-workbag"
              data-cy="workbag"
              :class="{ valid: !v$.workbag.$invalid, invalid: v$.workbag.$invalid }"
              v-model.number="v$.workbag.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.responsibleperson')" for="projectwbs-responsibleperson"></label>
            <select
              class="form-control"
              id="projectwbs-responsibleperson"
              data-cy="responsibleperson"
              name="responsibleperson"
              v-model="projectwbs.responsibleperson"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectwbs.responsibleperson && officersOption.id === projectwbs.responsibleperson.id
                    ? projectwbs.responsibleperson
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
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.technicaldirector')" for="projectwbs-technicaldirector"></label>
            <select
              class="form-control"
              id="projectwbs-technicaldirector"
              data-cy="technicaldirector"
              name="technicaldirector"
              v-model="projectwbs.technicaldirector"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectwbs.technicaldirector && officersOption.id === projectwbs.technicaldirector.id
                    ? projectwbs.technicaldirector
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
              v-text="t$('jy1App.projectwbs.administrativedirector')"
              for="projectwbs-administrativedirector"
            ></label>
            <select
              class="form-control"
              id="projectwbs-administrativedirector"
              data-cy="administrativedirector"
              name="administrativedirector"
              v-model="projectwbs.administrativedirector"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectwbs.administrativedirector && officersOption.id === projectwbs.administrativedirector.id
                    ? projectwbs.administrativedirector
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
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.knowingpeople')" for="projectwbs-knowingpeople"></label>
            <select
              class="form-control"
              id="projectwbs-knowingpeople"
              data-cy="knowingpeople"
              name="knowingpeople"
              v-model="projectwbs.knowingpeople"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectwbs.knowingpeople && officersOption.id === projectwbs.knowingpeople.id ? projectwbs.knowingpeople : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.auditorid')" for="projectwbs-auditorid"></label>
            <select class="form-control" id="projectwbs-auditorid" data-cy="auditorid" name="auditorid" v-model="projectwbs.auditorid">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="projectwbs.auditorid && officersOption.id === projectwbs.auditorid.id ? projectwbs.auditorid : officersOption"
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
              v-text="t$('jy1App.projectwbs.responsibledepartment')"
              for="projectwbs-responsibledepartment"
            ></label>
            <select
              class="form-control"
              id="projectwbs-responsibledepartment"
              data-cy="responsibledepartment"
              name="responsibledepartment"
              v-model="projectwbs.responsibledepartment"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectwbs.responsibledepartment && departmentOption.id === projectwbs.responsibledepartment.id
                    ? projectwbs.responsibledepartment
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
              v-text="t$('jy1App.projectwbs.relevantdepartment')"
              for="projectwbs-relevantdepartment"
            ></label>
            <select
              class="form-control"
              id="projectwbs-relevantdepartment"
              data-cy="relevantdepartment"
              name="relevantdepartment"
              v-model="projectwbs.relevantdepartment"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectwbs.relevantdepartment && departmentOption.id === projectwbs.relevantdepartment.id
                    ? projectwbs.relevantdepartment
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
            <label class="form-control-label" v-text="t$('jy1App.projectwbs.department')" for="projectwbs-department"></label>
            <select class="form-control" id="projectwbs-department" data-cy="department" name="department" v-model="projectwbs.department">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectwbs.department && departmentOption.id === projectwbs.department.id ? projectwbs.department : departmentOption
                "
                v-for="departmentOption in departments"
                :key="departmentOption.id"
              >
                {{ departmentOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectwbs.project')" for="projectwbs-project"></label>
            <select
              class="form-control"
              id="projectwbs-projects"
              data-cy="project"
              multiple
              name="project"
              v-if="projectwbs.projects !== undefined"
              v-model="projectwbs.projects"
            >
              <option
                v-bind:value="getSelected(projectwbs.projects, projectOption, 'id')"
                v-for="projectOption in projects"
                :key="projectOption.id"
              >
                {{ projectOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectwbs.projectpbs')" for="projectwbs-projectpbs"></label>
            <select
              class="form-control"
              id="projectwbs-projectpbs"
              data-cy="projectpbs"
              multiple
              name="projectpbs"
              v-if="projectwbs.projectpbs !== undefined"
              v-model="projectwbs.projectpbs"
            >
              <option
                v-bind:value="getSelected(projectwbs.projectpbs, projectpbsOption, 'id')"
                v-for="projectpbsOption in projectpbs"
                :key="projectpbsOption.id"
              >
                {{ projectpbsOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectwbs.progressPlan')" for="projectwbs-progressPlan"></label>
            <select
              class="form-control"
              id="projectwbs-progressPlans"
              data-cy="progressPlan"
              multiple
              name="progressPlan"
              v-if="projectwbs.progressPlans !== undefined"
              v-model="projectwbs.progressPlans"
            >
              <option
                v-bind:value="getSelected(projectwbs.progressPlans, progressPlanOption, 'id')"
                v-for="progressPlanOption in progressPlans"
                :key="progressPlanOption.id"
              >
                {{ progressPlanOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectwbs.fundsEstimation')" for="projectwbs-fundsEstimation"></label>
            <select
              class="form-control"
              id="projectwbs-fundsEstimations"
              data-cy="fundsEstimation"
              multiple
              name="fundsEstimation"
              v-if="projectwbs.fundsEstimations !== undefined"
              v-model="projectwbs.fundsEstimations"
            >
              <option
                v-bind:value="getSelected(projectwbs.fundsEstimations, fundsEstimationOption, 'id')"
                v-for="fundsEstimationOption in fundsEstimations"
                :key="fundsEstimationOption.id"
              >
                {{ fundsEstimationOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectwbs.contractCostBudget')" for="projectwbs-contractCostBudget"></label>
            <select
              class="form-control"
              id="projectwbs-contractCostBudgets"
              data-cy="contractCostBudget"
              multiple
              name="contractCostBudget"
              v-if="projectwbs.contractCostBudgets !== undefined"
              v-model="projectwbs.contractCostBudgets"
            >
              <option
                v-bind:value="getSelected(projectwbs.contractCostBudgets, contractCostBudgetOption, 'id')"
                v-for="contractCostBudgetOption in contractCostBudgets"
                :key="contractCostBudgetOption.id"
              >
                {{ contractCostBudgetOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectwbs.costControlSystem')" for="projectwbs-costControlSystem"></label>
            <select
              class="form-control"
              id="projectwbs-costControlSystems"
              data-cy="costControlSystem"
              multiple
              name="costControlSystem"
              v-if="projectwbs.costControlSystems !== undefined"
              v-model="projectwbs.costControlSystems"
            >
              <option
                v-bind:value="getSelected(projectwbs.costControlSystems, costControlSystemOption, 'id')"
                v-for="costControlSystemOption in costControlSystems"
                :key="costControlSystemOption.id"
              >
                {{ costControlSystemOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectwbs.qualityObjectives')" for="projectwbs-qualityObjectives"></label>
            <select
              class="form-control"
              id="projectwbs-qualityObjectives"
              data-cy="qualityObjectives"
              multiple
              name="qualityObjectives"
              v-if="projectwbs.qualityObjectives !== undefined"
              v-model="projectwbs.qualityObjectives"
            >
              <option
                v-bind:value="getSelected(projectwbs.qualityObjectives, qualityObjectivesOption, 'id')"
                v-for="qualityObjectivesOption in qualityObjectives"
                :key="qualityObjectivesOption.id"
              >
                {{ qualityObjectivesOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectwbs.outsourcingContractual')" for="projectwbs-outsourcingContractual"></label>
            <select
              class="form-control"
              id="projectwbs-outsourcingContractuals"
              data-cy="outsourcingContractual"
              multiple
              name="outsourcingContractual"
              v-if="projectwbs.outsourcingContractuals !== undefined"
              v-model="projectwbs.outsourcingContractuals"
            >
              <option
                v-bind:value="getSelected(projectwbs.outsourcingContractuals, outsourcingContractualOption, 'id')"
                v-for="outsourcingContractualOption in outsourcingContractuals"
                :key="outsourcingContractualOption.id"
              >
                {{ outsourcingContractualOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectwbs.outsourcingPurchasePlan')" for="projectwbs-outsourcingPurchasePlan"></label>
            <select
              class="form-control"
              id="projectwbs-outsourcingPurchasePlans"
              data-cy="outsourcingPurchasePlan"
              multiple
              name="outsourcingPurchasePlan"
              v-if="projectwbs.outsourcingPurchasePlans !== undefined"
              v-model="projectwbs.outsourcingPurchasePlans"
            >
              <option
                v-bind:value="getSelected(projectwbs.outsourcingPurchasePlans, outsourcingPurchasePlanOption, 'id')"
                v-for="outsourcingPurchasePlanOption in outsourcingPurchasePlans"
                :key="outsourcingPurchasePlanOption.id"
              >
                {{ outsourcingPurchasePlanOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectwbs.technical')" for="projectwbs-technical"></label>
            <select
              class="form-control"
              id="projectwbs-technicals"
              data-cy="technical"
              multiple
              name="technical"
              v-if="projectwbs.technicals !== undefined"
              v-model="projectwbs.technicals"
            >
              <option
                v-bind:value="getSelected(projectwbs.technicals, technicalOption, 'id')"
                v-for="technicalOption in technicals"
                :key="technicalOption.id"
              >
                {{ technicalOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectwbs.technicalCondition')" for="projectwbs-technicalCondition"></label>
            <select
              class="form-control"
              id="projectwbs-technicalConditions"
              data-cy="technicalCondition"
              multiple
              name="technicalCondition"
              v-if="projectwbs.technicalConditions !== undefined"
              v-model="projectwbs.technicalConditions"
            >
              <option
                v-bind:value="getSelected(projectwbs.technicalConditions, technicalConditionOption, 'id')"
                v-for="technicalConditionOption in technicalConditions"
                :key="technicalConditionOption.id"
              >
                {{ technicalConditionOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectwbs.projectRisk')" for="projectwbs-projectRisk"></label>
            <select
              class="form-control"
              id="projectwbs-projectRisks"
              data-cy="projectRisk"
              multiple
              name="projectRisk"
              v-if="projectwbs.projectRisks !== undefined"
              v-model="projectwbs.projectRisks"
            >
              <option
                v-bind:value="getSelected(projectwbs.projectRisks, projectRiskOption, 'id')"
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
<script lang="ts" src="./projectwbs-update.component.ts"></script>
