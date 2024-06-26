<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster2App.projectwbs.home.createOrEditLabel"
          data-cy="ProjectwbsCreateUpdateHeading"
          v-text="t$('jHipster2App.projectwbs.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="projectwbs.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="projectwbs.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster2App.projectwbs.wbsid')" for="projectwbs-wbsid"></label>
            <input
              type="text"
              class="form-control"
              name="wbsid"
              id="projectwbs-wbsid"
              data-cy="wbsid"
              :class="{ valid: !v$.wbsid.$invalid, invalid: v$.wbsid.$invalid }"
              v-model="v$.wbsid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster2App.projectwbs.cycleplan')" for="projectwbs-cycleplan"></label>
            <el-row>
              <el-col>
                <span>已创建：</span>
                <select class="form-control" id="projectwbs-cycleplan" data-cy="cycleplan" name="cycleplan" v-model="projectwbs.cycleplan">
                  <option v-bind:value="null"></option>
                  <option
                    v-bind:value="
                      projectwbs.cycleplan && cycleplanOption.id === projectwbs.cycleplan.id ? projectwbs.cycleplan : cycleplanOption
                    "
                    v-for="cycleplanOption in cycleplans"
                    :key="cycleplanOption.id"
                  >
                    {{ cycleplanOption.id }}
                  </option>
                </select>
              </el-col>
            </el-row>
            <el-row>
              <el-col>
                <span>未创建：</span>
                <router-link :to="{ name: 'CycleplanCreate' }" custom v-slot="{ navigate }">
                  <button
                    @click="navigate"
                    id="jh-create-entity"
                    data-cy="entityCreateButton"
                    class="btn btn-primary jh-create-entity create-cycleplan"
                  >
                    <font-awesome-icon icon="plus"></font-awesome-icon>
                    <span v-text="t$('jHipster2App.cycleplan.home.createLabel')"></span>
                  </button>
                </router-link>
              </el-col>
            </el-row>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster2App.projectwbs.progressmanagement')"
              for="projectwbs-progressmanagement"
            ></label>
            <el-row>
              <el-col>
                <span>已创建：</span>
                <select
                  class="form-control"
                  id="projectwbs-progressmanagement"
                  data-cy="progressmanagement"
                  name="progressmanagement"
                  v-model="projectwbs.progressmanagement"
                >
                  <option v-bind:value="null"></option>
                  <option
                    v-bind:value="
                      projectwbs.progressmanagement && progressmanagementOption.id === projectwbs.progressmanagement.id
                        ? projectwbs.progressmanagement
                        : progressmanagementOption
                    "
                    v-for="progressmanagementOption in progressmanagements"
                    :key="progressmanagementOption.id"
                  >
                    {{ progressmanagementOption.id }}
                  </option>
                </select>
              </el-col>
            </el-row>
            <el-row>
              <el-col>
                <span>未创建：</span>
                <router-link :to="{ name: 'ProgressmanagementCreate' }" custom v-slot="{ navigate }">
                  <button
                    @click="navigate"
                    id="jh-create-entity"
                    data-cy="entityCreateButton"
                    class="btn btn-primary jh-create-entity create-progressmanagement"
                  >
                    <font-awesome-icon icon="plus"></font-awesome-icon>
                    <span v-text="t$('jHipster2App.progressmanagement.home.createLabel')"></span>
                  </button>
                </router-link>
              </el-col>
            </el-row>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster2App.projectwbs.qualitymanagement')"
              for="projectwbs-qualitymanagement"
            ></label>
            <select
              class="form-control"
              id="projectwbs-qualitymanagement"
              data-cy="qualitymanagement"
              name="qualitymanagement"
              v-model="projectwbs.qualitymanagement"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectwbs.qualitymanagement && qualitymanagementOption.id === projectwbs.qualitymanagement.id
                    ? projectwbs.qualitymanagement
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
            <label
              class="form-control-label"
              v-text="t$('jHipster2App.projectwbs.fundsmanagement')"
              for="projectwbs-fundsmanagement"
            ></label>
            <select
              class="form-control"
              id="projectwbs-fundsmanagement"
              data-cy="fundsmanagement"
              name="fundsmanagement"
              v-model="projectwbs.fundsmanagement"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectwbs.fundsmanagement && fundsmanagementOption.id === projectwbs.fundsmanagement.id
                    ? projectwbs.fundsmanagement
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
              v-text="t$('jHipster2App.projectwbs.technicalmanagement')"
              for="projectwbs-technicalmanagement"
            ></label>
            <select
              class="form-control"
              id="projectwbs-technicalmanagement"
              data-cy="technicalmanagement"
              name="technicalmanagement"
              v-model="projectwbs.technicalmanagement"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectwbs.technicalmanagement && technicalmanagementOption.id === projectwbs.technicalmanagement.id
                    ? projectwbs.technicalmanagement
                    : technicalmanagementOption
                "
                v-for="technicalmanagementOption in technicalmanagements"
                :key="technicalmanagementOption.id"
              >
                {{ technicalmanagementOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster2App.projectwbs.contractualfunds')"
              for="projectwbs-contractualfunds"
            ></label>
            <select
              class="form-control"
              id="projectwbs-contractualfunds"
              data-cy="contractualfunds"
              name="contractualfunds"
              v-model="projectwbs.contractualfunds"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectwbs.contractualfunds && contractualfundsOption.id === projectwbs.contractualfunds.id
                    ? projectwbs.contractualfunds
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
              v-text="t$('jHipster2App.projectwbs.outsourcingmanagement')"
              for="projectwbs-outsourcingmanagement"
            ></label>
            <select
              class="form-control"
              id="projectwbs-outsourcingmanagement"
              data-cy="outsourcingmanagement"
              name="outsourcingmanagement"
              v-model="projectwbs.outsourcingmanagement"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectwbs.outsourcingmanagement && outsourcingmanagementOption.id === projectwbs.outsourcingmanagement.id
                    ? projectwbs.outsourcingmanagement
                    : outsourcingmanagementOption
                "
                v-for="outsourcingmanagementOption in outsourcingmanagements"
                :key="outsourcingmanagementOption.id"
              >
                {{ outsourcingmanagementOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jHipster2App.projectwbs.resourcemanagement')"
              for="projectwbs-resourcemanagement"
            ></label>
            <select
              class="form-control"
              id="projectwbs-resourcemanagement"
              data-cy="resourcemanagement"
              name="resourcemanagement"
              v-model="projectwbs.resourcemanagement"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectwbs.resourcemanagement && resourcemanagementOption.id === projectwbs.resourcemanagement.id
                    ? projectwbs.resourcemanagement
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
            <label class="form-control-label" v-text="t$('jHipster2App.projectwbs.riskmanagement')" for="projectwbs-riskmanagement"></label>
            <select
              class="form-control"
              id="projectwbs-riskmanagement"
              data-cy="riskmanagement"
              name="riskmanagement"
              v-model="projectwbs.riskmanagement"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectwbs.riskmanagement && riskmanagementOption.id === projectwbs.riskmanagement.id
                    ? projectwbs.riskmanagement
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
            <label
              class="form-control-label"
              v-text="t$('jHipster2App.projectwbs.securitymanagement')"
              for="projectwbs-securitymanagement"
            ></label>
            <select
              class="form-control"
              id="projectwbs-securitymanagement"
              data-cy="securitymanagement"
              name="securitymanagement"
              v-model="projectwbs.securitymanagement"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectwbs.securitymanagement && securitymanagementOption.id === projectwbs.securitymanagement.id
                    ? projectwbs.securitymanagement
                    : securitymanagementOption
                "
                v-for="securitymanagementOption in securitymanagements"
                :key="securitymanagementOption.id"
              >
                {{ securitymanagementOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster2App.projectwbs.document')" for="projectwbs-document"></label>
            <select class="form-control" id="projectwbs-document" data-cy="document" name="document" v-model="projectwbs.document">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="projectwbs.document && documentOption.id === projectwbs.document.id ? projectwbs.document : documentOption"
                v-for="documentOption in documents"
                :key="documentOption.id"
              >
                {{ documentOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster2App.projectwbs.safetycheck')" for="projectwbs-safetycheck"></label>
            <select
              class="form-control"
              id="projectwbs-safetycheck"
              data-cy="safetycheck"
              name="safetycheck"
              v-model="projectwbs.safetycheck"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectwbs.safetycheck && safetycheckOption.id === projectwbs.safetycheck.id ? projectwbs.safetycheck : safetycheckOption
                "
                v-for="safetycheckOption in safetychecks"
                :key="safetycheckOption.id"
              >
                {{ safetycheckOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster2App.projectwbs.department')" for="projectwbs-department"></label>
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
            <label
              class="form-control-label"
              v-text="t$('jHipster2App.projectwbs.evaluationCriteria')"
              for="projectwbs-evaluationCriteria"
            ></label>
            <select
              class="form-control"
              id="projectwbs-evaluationCriteria"
              data-cy="evaluationCriteria"
              name="evaluationCriteria"
              v-model="projectwbs.evaluationCriteria"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  projectwbs.evaluationCriteria && evaluationCriteriaOption.id === projectwbs.evaluationCriteria.id
                    ? projectwbs.evaluationCriteria
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
            <label class="form-control-label" v-text="t$('jHipster2App.projectwbs.wbsspare1')" for="projectwbs-wbsspare1"></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare1"
              id="projectwbs-wbsspare1"
              data-cy="wbsspare1"
              :class="{ valid: !v$.wbsspare1.$invalid, invalid: v$.wbsspare1.$invalid }"
              v-model="v$.wbsspare1.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster2App.projectwbs.wbsspare2')" for="projectwbs-wbsspare2"></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare2"
              id="projectwbs-wbsspare2"
              data-cy="wbsspare2"
              :class="{ valid: !v$.wbsspare2.$invalid, invalid: v$.wbsspare2.$invalid }"
              v-model="v$.wbsspare2.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster2App.projectwbs.wbsspare3')" for="projectwbs-wbsspare3"></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare3"
              id="projectwbs-wbsspare3"
              data-cy="wbsspare3"
              :class="{ valid: !v$.wbsspare3.$invalid, invalid: v$.wbsspare3.$invalid }"
              v-model="v$.wbsspare3.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster2App.projectwbs.wbsspare4')" for="projectwbs-wbsspare4"></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare4"
              id="projectwbs-wbsspare4"
              data-cy="wbsspare4"
              :class="{ valid: !v$.wbsspare4.$invalid, invalid: v$.wbsspare4.$invalid }"
              v-model="v$.wbsspare4.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster2App.projectwbs.wbsspare5')" for="projectwbs-wbsspare5"></label>
            <input
              type="text"
              class="form-control"
              name="wbsspare5"
              id="projectwbs-wbsspare5"
              data-cy="wbsspare5"
              :class="{ valid: !v$.wbsspare5.$invalid, invalid: v$.wbsspare5.$invalid }"
              v-model="v$.wbsspare5.$model"
            />
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
