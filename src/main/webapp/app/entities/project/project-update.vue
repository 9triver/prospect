<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster3App.project.home.createOrEditLabel"
          data-cy="ProjectCreateUpdateHeading"
          v-text="t$('jHipster3App.project.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="project.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="project.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.project.projectid')" for="project-projectid"></label>
            <input
              type="number"
              class="form-control"
              name="projectid"
              id="project-projectid"
              data-cy="projectid"
              :class="{ valid: !v$.projectid.$invalid, invalid: v$.projectid.$invalid }"
              v-model.number="v$.projectid.$model"
            />
            <div v-if="v$.projectid.$anyDirty && v$.projectid.$invalid">
              <small class="form-text text-danger" v-for="error of v$.projectid.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.project.projectname')" for="project-projectname"></label>
            <input
              type="text"
              class="form-control"
              name="projectname"
              id="project-projectname"
              data-cy="projectname"
              :class="{ valid: !v$.projectname.$invalid, invalid: v$.projectname.$invalid }"
              v-model="v$.projectname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.project.description')" for="project-description"></label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="project-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.project.number')" for="project-number"></label>
            <input
              type="number"
              class="form-control"
              name="number"
              id="project-number"
              data-cy="number"
              :class="{ valid: !v$.number.$invalid, invalid: v$.number.$invalid }"
              v-model.number="v$.number.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.project.projecttype')" for="project-projecttype"></label>
            <input
              type="number"
              class="form-control"
              name="projecttype"
              id="project-projecttype"
              data-cy="projecttype"
              :class="{ valid: !v$.projecttype.$invalid, invalid: v$.projecttype.$invalid }"
              v-model.number="v$.projecttype.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.project.responsiblename')" for="project-responsiblename"></label>
            <input
              type="text"
              class="form-control"
              name="responsiblename"
              id="project-responsiblename"
              data-cy="responsiblename"
              :class="{ valid: !v$.responsiblename.$invalid, invalid: v$.responsiblename.$invalid }"
              v-model="v$.responsiblename.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.project.duedate')" for="project-duedate"></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="project-duedate"
                  v-model="v$.duedate.$model"
                  name="duedate"
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
                id="project-duedate"
                data-cy="duedate"
                type="text"
                class="form-control"
                name="duedate"
                :class="{ valid: !v$.duedate.$invalid, invalid: v$.duedate.$invalid }"
                v-model="v$.duedate.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.project.priorty')" for="project-priorty"></label>
            <input
              type="number"
              class="form-control"
              name="priorty"
              id="project-priorty"
              data-cy="priorty"
              :class="{ valid: !v$.priorty.$invalid, invalid: v$.priorty.$invalid }"
              v-model.number="v$.priorty.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.project.progressid')" for="project-progressid"></label>
            <input
              type="number"
              class="form-control"
              name="progressid"
              id="project-progressid"
              data-cy="progressid"
              :class="{ valid: !v$.progressid.$invalid, invalid: v$.progressid.$invalid }"
              v-model.number="v$.progressid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.project.returnsid')" for="project-returnsid"></label>
            <input
              type="number"
              class="form-control"
              name="returnsid"
              id="project-returnsid"
              data-cy="returnsid"
              :class="{ valid: !v$.returnsid.$invalid, invalid: v$.returnsid.$invalid }"
              v-model.number="v$.returnsid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.project.qualityid')" for="project-qualityid"></label>
            <input
              type="number"
              class="form-control"
              name="qualityid"
              id="project-qualityid"
              data-cy="qualityid"
              :class="{ valid: !v$.qualityid.$invalid, invalid: v$.qualityid.$invalid }"
              v-model.number="v$.qualityid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.project.fundsid')" for="project-fundsid"></label>
            <input
              type="number"
              class="form-control"
              name="fundsid"
              id="project-fundsid"
              data-cy="fundsid"
              :class="{ valid: !v$.fundsid.$invalid, invalid: v$.fundsid.$invalid }"
              v-model.number="v$.fundsid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.project.status')" for="project-status"></label>
            <select
              class="form-control"
              name="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
              id="project-status"
              data-cy="status"
            >
              <option
                v-for="projectStatus in projectStatusValues"
                :key="projectStatus"
                v-bind:value="projectStatus"
                v-bind:label="t$('jHipster3App.ProjectStatus.' + projectStatus)"
              >
                {{ projectStatus }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.project.auditStatus')" for="project-auditStatus"></label>
            <select
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="project-auditStatus"
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
            <label class="form-control-label" v-text="t$('jHipster3App.project.cycleplan')" for="project-cycleplan"></label>
            <select class="form-control" id="project-cycleplan" data-cy="cycleplan" name="cycleplan" v-model="project.cycleplan">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="project.cycleplan && cycleplanOption.id === project.cycleplan.id ? project.cycleplan : cycleplanOption"
                v-for="cycleplanOption in cycleplans"
                :key="cycleplanOption.id"
              >
                {{ cycleplanOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.project.progressmanagement')"
              for="project-progressmanagement"
            ></label>
            <select
              class="form-control"
              id="project-progressmanagement"
              data-cy="progressmanagement"
              name="progressmanagement"
              v-model="project.progressmanagement"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  project.progressmanagement && progressmanagementOption.id === project.progressmanagement.id
                    ? project.progressmanagement
                    : progressmanagementOption
                "
                v-for="progressmanagementOption in progressmanagements"
                :key="progressmanagementOption.id"
              >
                {{ progressmanagementOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.project.qualitymanagement')" for="project-qualitymanagement"></label>
            <select
              class="form-control"
              id="project-qualitymanagement"
              data-cy="qualitymanagement"
              name="qualitymanagement"
              v-model="project.qualitymanagement"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  project.qualitymanagement && qualitymanagementOption.id === project.qualitymanagement.id
                    ? project.qualitymanagement
                    : qualitymanagementOption
                "
                v-for="qualitymanagementOption in qualitymanagements"
                :key="qualitymanagementOption.id"
              >
                {{ qualitymanagementOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.project.fundsmanagement')" for="project-fundsmanagement"></label>
            <select
              class="form-control"
              id="project-fundsmanagement"
              data-cy="fundsmanagement"
              name="fundsmanagement"
              v-model="project.fundsmanagement"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  project.fundsmanagement && fundsmanagementOption.id === project.fundsmanagement.id
                    ? project.fundsmanagement
                    : fundsmanagementOption
                "
                v-for="fundsmanagementOption in fundsmanagements"
                :key="fundsmanagementOption.id"
              >
                {{ fundsmanagementOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.project.technicalCondition')"
              for="project-technicalCondition"
            ></label>
            <select
              class="form-control"
              id="project-technicalCondition"
              data-cy="technicalCondition"
              name="technicalCondition"
              v-model="project.technicalCondition"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  project.technicalCondition && technicalConditionOption.id === project.technicalCondition.id
                    ? project.technicalCondition
                    : technicalConditionOption
                "
                v-for="technicalConditionOption in technicalConditions"
                :key="technicalConditionOption.id"
              >
                {{ technicalConditionOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.project.contractualfunds')" for="project-contractualfunds"></label>
            <select
              class="form-control"
              id="project-contractualfunds"
              data-cy="contractualfunds"
              name="contractualfunds"
              v-model="project.contractualfunds"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  project.contractualfunds && contractualfundsOption.id === project.contractualfunds.id
                    ? project.contractualfunds
                    : contractualfundsOption
                "
                v-for="contractualfundsOption in contractualfunds"
                :key="contractualfundsOption.id"
              >
                {{ contractualfundsOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.project.outsourcingmPurchaseExecute')"
              for="project-outsourcingmPurchaseExecute"
            ></label>
            <select
              class="form-control"
              id="project-outsourcingmPurchaseExecute"
              data-cy="outsourcingmPurchaseExecute"
              name="outsourcingmPurchaseExecute"
              v-model="project.outsourcingmPurchaseExecute"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  project.outsourcingmPurchaseExecute && outsourcingmPurchaseExecuteOption.id === project.outsourcingmPurchaseExecute.id
                    ? project.outsourcingmPurchaseExecute
                    : outsourcingmPurchaseExecuteOption
                "
                v-for="outsourcingmPurchaseExecuteOption in outsourcingmPurchaseExecutes"
                :key="outsourcingmPurchaseExecuteOption.id"
              >
                {{ outsourcingmPurchaseExecuteOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster3App.project.resourcemanagement')"
              for="project-resourcemanagement"
            ></label>
            <select
              class="form-control"
              id="project-resourcemanagement"
              data-cy="resourcemanagement"
              name="resourcemanagement"
              v-model="project.resourcemanagement"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  project.resourcemanagement && resourcemanagementOption.id === project.resourcemanagement.id
                    ? project.resourcemanagement
                    : resourcemanagementOption
                "
                v-for="resourcemanagementOption in resourcemanagements"
                :key="resourcemanagementOption.id"
              >
                {{ resourcemanagementOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.project.riskmanagement')" for="project-riskmanagement"></label>
            <select
              class="form-control"
              id="project-riskmanagement"
              data-cy="riskmanagement"
              name="riskmanagement"
              v-model="project.riskmanagement"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  project.riskmanagement && riskmanagementOption.id === project.riskmanagement.id
                    ? project.riskmanagement
                    : riskmanagementOption
                "
                v-for="riskmanagementOption in riskmanagements"
                :key="riskmanagementOption.id"
              >
                {{ riskmanagementOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.project.document')" for="project-document"></label>
            <select class="form-control" id="project-document" data-cy="document" name="document" v-model="project.document">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="project.document && documentOption.id === project.document.id ? project.document : documentOption"
                v-for="documentOption in documents"
                :key="documentOption.id"
              >
                {{ documentOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.project.safetycheck')" for="project-safetycheck"></label>
            <select class="form-control" id="project-safetycheck" data-cy="safetycheck" name="safetycheck" v-model="project.safetycheck">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  project.safetycheck && safetycheckOption.id === project.safetycheck.id ? project.safetycheck : safetycheckOption
                "
                v-for="safetycheckOption in safetychecks"
                :key="safetycheckOption.id"
              >
                {{ safetycheckOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.project.department')" for="project-department"></label>
            <select class="form-control" id="project-department" data-cy="department" name="department" v-model="project.department">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="project.department && departmentOption.id === project.department.id ? project.department : departmentOption"
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
              v-text="t$('jHipster3App.project.evaluationCriteria')"
              for="project-evaluationCriteria"
            ></label>
            <select
              class="form-control"
              id="project-evaluationCriteria"
              data-cy="evaluationCriteria"
              name="evaluationCriteria"
              v-model="project.evaluationCriteria"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  project.evaluationCriteria && evaluationCriteriaOption.id === project.evaluationCriteria.id
                    ? project.evaluationCriteria
                    : evaluationCriteriaOption
                "
                v-for="evaluationCriteriaOption in evaluationCriteria"
                :key="evaluationCriteriaOption.id"
              >
                {{ evaluationCriteriaOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.project.responsibleid')" for="project-responsibleid"></label>
            <select
              class="form-control"
              id="project-responsibleid"
              data-cy="responsibleid"
              name="responsibleid"
              v-model="project.responsibleid"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  project.responsibleid && officersOption.id === project.responsibleid.id ? project.responsibleid : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.project.auditorid')" for="project-auditorid"></label>
            <select class="form-control" id="project-auditorid" data-cy="auditorid" name="auditorid" v-model="project.auditorid">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="project.auditorid && officersOption.id === project.auditorid.id ? project.auditorid : officersOption"
                v-for="officersOption in officers"
                :key="officersOption.id"
              >
                {{ officersOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster3App.project.projectSecrecy')" for="project-projectSecrecy"></label>
            <select
              class="form-control"
              id="project-projectSecrecy"
              data-cy="projectSecrecy"
              name="projectSecrecy"
              v-model="project.projectSecrecy"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  project.projectSecrecy && projectSecrecyOption.id === project.projectSecrecy.id
                    ? project.projectSecrecy
                    : projectSecrecyOption
                "
                v-for="projectSecrecyOption in projectSecrecies"
                :key="projectSecrecyOption.id"
              >
                {{ projectSecrecyOption.id }}
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
<script lang="ts" src="./project-update.component.ts"></script>
