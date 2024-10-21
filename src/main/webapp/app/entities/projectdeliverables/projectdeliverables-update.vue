<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.projectdeliverables.home.createOrEditLabel"
          data-cy="ProjectdeliverablesCreateUpdateHeading"
          v-text="t$('jy1App.projectdeliverables.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="projectdeliverables.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="projectdeliverables.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.projectdeliverables.parentcode')"
              for="projectdeliverables-parentcode"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="parentcode"
              id="projectdeliverables-parentcode"
              data-cy="parentcode"
              :class="{ valid: !v$.parentcode.$invalid, invalid: v$.parentcode.$invalid }"
              v-model="v$.parentcode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectdeliverables.isSubmit')" for="projectdeliverables-isSubmit"></label>
            <el-input
              type="checkbox"
              class="form-check"
              name="isSubmit"
              id="projectdeliverables-isSubmit"
              data-cy="isSubmit"
              :class="{ valid: !v$.isSubmit.$invalid, invalid: v$.isSubmit.$invalid }"
              v-model="v$.isSubmit.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectdeliverables.status')" for="projectdeliverables-status"></label>
            <el-input
              type="text"
              class="form-control"
              name="status"
              id="projectdeliverables-status"
              data-cy="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectdeliverables.code')" for="projectdeliverables-code"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="projectdeliverables-code"
              data-cy="code"
              name="code"
              v-model="projectdeliverables.code"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  projectdeliverables.code && deliverablesOption.id === projectdeliverables.code.id
                    ? projectdeliverables.code
                    : deliverablesOption
                "
                v-for="deliverablesOption in deliverables"
                :key="deliverablesOption.id"
                :label="deliverablesOption.id"
                >{{ deliverablesOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectdeliverables.belongwbsid')" for="projectdeliverables-belongwbsid"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="projectdeliverables-belongwbsids"
              data-cy="belongwbsid"
              multiple
              name="belongwbsid"
              v-if="projectdeliverables.belongwbsids !== undefined"
              v-model="projectdeliverables.belongwbsids"
            >
              <el-option
                v-bind:value="getSelected(projectdeliverables.belongwbsids, projectwbsOption, 'id')"
                v-for="projectwbsOption in projectwbs"
                :key="projectwbsOption.id"
                :label="projectwbsOption.id"
                >{{ projectwbsOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.projectdeliverables.belongworkbagid')" for="projectdeliverables-belongworkbagid"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="projectdeliverables-belongworkbagids"
              data-cy="belongworkbagid"
              multiple
              name="belongworkbagid"
              v-if="projectdeliverables.belongworkbagids !== undefined"
              v-model="projectdeliverables.belongworkbagids"
            >
              <el-option
                v-bind:value="getSelected(projectdeliverables.belongworkbagids, workbagOption, 'id')"
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
<script lang="ts" src="./projectdeliverables-update.component.ts"></script>
