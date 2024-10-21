<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.leaveApplicationInfo.home.createOrEditLabel"
          data-cy="LeaveApplicationInfoCreateUpdateHeading"
          v-text="t$('jy1App.leaveApplicationInfo.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="leaveApplicationInfo.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="leaveApplicationInfo.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.leaveApplicationInfo.startDate')"
              for="leave-application-info-startDate"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="startDate"
              id="leave-application-info-startDate"
              data-cy="startDate"
              :class="{ valid: !v$.startDate.$invalid, invalid: v$.startDate.$invalid }"
              v-model="v$.startDate.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.leaveApplicationInfo.endDate')"
              for="leave-application-info-endDate"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="endDate"
              id="leave-application-info-endDate"
              data-cy="endDate"
              :class="{ valid: !v$.endDate.$invalid, invalid: v$.endDate.$invalid }"
              v-model="v$.endDate.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.leaveApplicationInfo.leaveType')"
              for="leave-application-info-leaveType"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="leaveType"
              id="leave-application-info-leaveType"
              data-cy="leaveType"
              :class="{ valid: !v$.leaveType.$invalid, invalid: v$.leaveType.$invalid }"
              v-model="v$.leaveType.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.leaveApplicationInfo.reason')" for="leave-application-info-reason"></label>
            <el-input
              type="text"
              class="form-control"
              name="reason"
              id="leave-application-info-reason"
              data-cy="reason"
              :class="{ valid: !v$.reason.$invalid, invalid: v$.reason.$invalid }"
              v-model="v$.reason.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.leaveApplicationInfo.status')" for="leave-application-info-status"></label>
            <el-input
              type="text"
              class="form-control"
              name="status"
              id="leave-application-info-status"
              data-cy="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
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
<script lang="ts" src="./leave-application-info-update.component.ts"></script>
