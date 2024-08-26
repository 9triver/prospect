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
            <label class="form-control-label" v-text="t$('jy1App.riskReport.releasetime')" for="risk-report-releasetime"></label>
            <el-date-picker v-model="v$.releasetime.$model" type="date" placeholder="" />
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
                >{{ t$('jy1App.AuditStatus.' + auditStatus) }}</el-option
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
                v-bind:value="riskReport.creatorid && officersOption.id === riskReport.creatorid.id ? riskReport.creatorid : officersOption"
                v-for="officersOption in officers"
                :key="officersOption.id"
                :label="officersOption.name"
                >{{ officersOption.name }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.riskReport.auditorid')" for="risk-report-auditorid"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="risk-report-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="riskReport.auditorid"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="riskReport.auditorid && officersOption.id === riskReport.auditorid.id ? riskReport.auditorid : officersOption"
                v-for="officersOption in officers"
                :key="officersOption.id"
                :label="officersOption.name"
                >{{ officersOption.name }}</el-option
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
