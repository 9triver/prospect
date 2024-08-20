<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.projectremit.home.createOrEditLabel"
          data-cy="ProjectremitCreateUpdateHeading"
          v-text="t$('jy1App.projectremit.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="projectremit.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="projectremit.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectremit.remit')" for="projectremit-remit"></label>
            <el-input
              type="text"
              class="form-control"
              name="remit"
              id="projectremit-remit"
              data-cy="remit"
              :class="{ valid: !v$.remit.$invalid, invalid: v$.remit.$invalid }"
              v-model="v$.remit.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectremit.outdeportment')" for="projectremit-outdeportment"></label>
            <el-input
              type="text"
              class="form-control"
              name="outdeportment"
              id="projectremit-outdeportment"
              data-cy="outdeportment"
              :class="{ valid: !v$.outdeportment.$invalid, invalid: v$.outdeportment.$invalid }"
              v-model="v$.outdeportment.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectremit.indeportment')" for="projectremit-indeportment"></label>
            <el-input
              type="text"
              class="form-control"
              name="indeportment"
              id="projectremit-indeportment"
              data-cy="indeportment"
              :class="{ valid: !v$.indeportment.$invalid, invalid: v$.indeportment.$invalid }"
              v-model="v$.indeportment.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectremit.projectname')" for="projectremit-projectname"></label>
            <el-input
              type="text"
              class="form-control"
              name="projectname"
              id="projectremit-projectname"
              data-cy="projectname"
              :class="{ valid: !v$.projectname.$invalid, invalid: v$.projectname.$invalid }"
              v-model="v$.projectname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectremit.deportment')" for="projectremit-deportment"></label>
            <el-input
              type="text"
              class="form-control"
              name="deportment"
              id="projectremit-deportment"
              data-cy="deportment"
              :class="{ valid: !v$.deportment.$invalid, invalid: v$.deportment.$invalid }"
              v-model="v$.deportment.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectremit.projectleader')" for="projectremit-projectleader"></label>
            <el-input
              type="text"
              class="form-control"
              name="projectleader"
              id="projectremit-projectleader"
              data-cy="projectleader"
              :class="{ valid: !v$.projectleader.$invalid, invalid: v$.projectleader.$invalid }"
              v-model="v$.projectleader.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectremit.secretlevel')" for="projectremit-secretlevel"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="projectremit-secretlevel"
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
            <label class="form-control-label" v-text="t$('jy1App.projectremit.auditStatus')" for="projectremit-auditStatus"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="auditStatus"
              :class="{ valid: !v$.auditStatus.$invalid, invalid: v$.auditStatus.$invalid }"
              v-model="v$.auditStatus.$model"
              id="projectremit-auditStatus"
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
<script lang="ts" src="./projectremit-update.component.ts"></script>
