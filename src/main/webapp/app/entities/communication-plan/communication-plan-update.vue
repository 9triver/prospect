<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.communicationPlan.home.createOrEditLabel"
          data-cy="CommunicationPlanCreateUpdateHeading"
          v-text="t$('jy1App.communicationPlan.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="communicationPlan.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="communicationPlan.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.communicationPlan.wbsid')" for="communication-plan-wbsid"></label>
            <el-input
              type="text"
              class="form-control"
              name="wbsid"
              id="communication-plan-wbsid"
              data-cy="wbsid"
              :class="{ valid: !v$.wbsid.$invalid, invalid: v$.wbsid.$invalid }"
              v-model="v$.wbsid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.communicationPlan.communicationtopic')"
              for="communication-plan-communicationtopic"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="communicationtopic"
              id="communication-plan-communicationtopic"
              data-cy="communicationtopic"
              :class="{ valid: !v$.communicationtopic.$invalid, invalid: v$.communicationtopic.$invalid }"
              v-model="v$.communicationtopic.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.communicationPlan.communicationtime')"
              for="communication-plan-communicationtime"
            ></label>
            <el-date-picker v-model="v$.communicationtime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.communicationPlan.worktarget')"
              for="communication-plan-worktarget"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="worktarget"
              id="communication-plan-worktarget"
              data-cy="worktarget"
              :class="{ valid: !v$.worktarget.$invalid, invalid: v$.worktarget.$invalid }"
              v-model="v$.worktarget.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.communicationPlan.workcontent')"
              for="communication-plan-workcontent"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="workcontent"
              id="communication-plan-workcontent"
              data-cy="workcontent"
              :class="{ valid: !v$.workcontent.$invalid, invalid: v$.workcontent.$invalid }"
              v-model="v$.workcontent.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.communicationPlan.remarks')" for="communication-plan-remarks"></label>
            <el-input
              type="text"
              class="form-control"
              name="remarks"
              id="communication-plan-remarks"
              data-cy="remarks"
              :class="{ valid: !v$.remarks.$invalid, invalid: v$.remarks.$invalid }"
              v-model="v$.remarks.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.communicationPlan.auditStatus')"
              for="communication-plan-auditStatus"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="communication-plan-auditStatus"
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
            <label
              class="form-control-label"
              v-text="t$('jy1App.communicationPlan.projectwbs')"
              for="communication-plan-projectwbs"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="communication-plan-projectwbs"
              data-cy="projectwbs"
              name="projectwbs"
              v-model="communicationPlan.projectwbs"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  communicationPlan.projectwbs && projectwbsOption.id === communicationPlan.projectwbs.id
                    ? communicationPlan.projectwbs
                    : projectwbsOption
                "
                v-for="projectwbsOption in projectwbs"
                :key="projectwbsOption.id"
                :label="projectwbsOption.id"
                >{{ projectwbsOption.id }}</el-option
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
<script lang="ts" src="./communication-plan-update.component.ts"></script>
