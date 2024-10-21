<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.riskReport.home.createOrEditLabel"
          data-cy="RiskReportCreateUpdateHeading"
          v-text="t$('jy1App.riskReport.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="riskReport.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="riskReport.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.riskReport.type')" for="risk-report-type"></label>
            <el-input
              type="text"
              class="form-control"
              name="type"
              id="risk-report-type"
              data-cy="type"
              :class="{ valid: !v$.type.$invalid, invalid: v$.type.$invalid }"
              v-model="v$.type.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.riskReport.year')" for="risk-report-year"></label>
            <el-input
              type="number"
              class="form-control"
              name="year"
              id="risk-report-year"
              data-cy="year"
              :class="{ valid: !v$.year.$invalid, invalid: v$.year.$invalid }"
              v-model.number="v$.year.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.riskReport.riskreportname')" for="risk-report-riskreportname"></label>
            <el-input
              type="text"
              class="form-control"
              name="riskreportname"
              id="risk-report-riskreportname"
              data-cy="riskreportname"
              :class="{ valid: !v$.riskreportname.$invalid, invalid: v$.riskreportname.$invalid }"
              v-model="v$.riskreportname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.riskReport.reporttime')" for="risk-report-reporttime"></label>
            <el-date-picker v-model="v$.reporttime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.riskReport.auditStatus')" for="risk-report-auditStatus"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="risk-report-auditStatus"
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
            <label class="form-control-label" v-text="t$('jy1App.riskReport.creatorid')" for="risk-report-creatorid"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="risk-report-creatorid"
              data-cy="creatorid"
              name="creatorid"
              v-model="riskReport.creatorid"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  riskReport.creatorid && hrManagementOption.id === riskReport.creatorid.id ? riskReport.creatorid : hrManagementOption
                "
                v-for="hrManagementOption in hrManagements"
                :key="hrManagementOption.id"
                :label="hrManagementOption.id"
                >{{ hrManagementOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.riskReport.wbsid')" for="risk-report-wbsid"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="risk-report-wbsid"
              data-cy="wbsid"
              name="wbsid"
              v-model="riskReport.wbsid"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="riskReport.wbsid && projectwbsOption.id === riskReport.wbsid.id ? riskReport.wbsid : projectwbsOption"
                v-for="projectwbsOption in projectwbs"
                :key="projectwbsOption.id"
                :label="projectwbsOption.id"
                >{{ projectwbsOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.riskReport.workbag')" for="risk-report-workbag"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="risk-report-workbag"
              data-cy="workbag"
              name="workbag"
              v-model="riskReport.workbag"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="riskReport.workbag && workbagOption.id === riskReport.workbag.id ? riskReport.workbag : workbagOption"
                v-for="workbagOption in workbags"
                :key="workbagOption.id"
                :label="workbagOption.id"
                >{{ workbagOption.id }}</el-option
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
<script lang="ts" src="./risk-report-update.component.ts"></script>
