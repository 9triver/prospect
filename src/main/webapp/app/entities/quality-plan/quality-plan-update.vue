<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.qualityPlan.home.createOrEditLabel"
          data-cy="QualityPlanCreateUpdateHeading"
          v-text="t$('jy1App.qualityPlan.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="qualityPlan.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="qualityPlan.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityPlan.name')" for="quality-plan-name"></label>
            <el-input
              type="text"
              class="form-control"
              name="name"
              id="quality-plan-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityPlan.qualitytype')" for="quality-plan-qualitytype"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="qualitytype"
              :class="{ valid: !v$.qualitytype.$invalid, invalid: v$.qualitytype.$invalid }"
              v-model="v$.qualitytype.$model"
              id="quality-plan-qualitytype"
              data-cy="qualitytype"
            >
              <el-option
                v-for="qualityType in qualityTypeValues"
                :key="qualityType"
                v-bind:value="qualityType"
                v-bind:label="t$('jy1App.QualityType.' + qualityType)"
                >{{ qualityType }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityPlan.secretlevel')" for="quality-plan-secretlevel"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="quality-plan-secretlevel"
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
            <label class="form-control-label" v-text="t$('jy1App.qualityPlan.wbsid')" for="quality-plan-wbsid"></label>
            <el-input
              type="text"
              class="form-control"
              name="wbsid"
              id="quality-plan-wbsid"
              data-cy="wbsid"
              :class="{ valid: !v$.wbsid.$invalid, invalid: v$.wbsid.$invalid }"
              v-model="v$.wbsid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityPlan.workbagid')" for="quality-plan-workbagid"></label>
            <el-input
              type="text"
              class="form-control"
              name="workbagid"
              id="quality-plan-workbagid"
              data-cy="workbagid"
              :class="{ valid: !v$.workbagid.$invalid, invalid: v$.workbagid.$invalid }"
              v-model="v$.workbagid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityPlan.fileversion')" for="quality-plan-fileversion"></label>
            <el-input
              type="text"
              class="form-control"
              name="fileversion"
              id="quality-plan-fileversion"
              data-cy="fileversion"
              :class="{ valid: !v$.fileversion.$invalid, invalid: v$.fileversion.$invalid }"
              v-model="v$.fileversion.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityPlan.author')" for="quality-plan-author"></label>
            <el-input
              type="text"
              class="form-control"
              name="author"
              id="quality-plan-author"
              data-cy="author"
              :class="{ valid: !v$.author.$invalid, invalid: v$.author.$invalid }"
              v-model="v$.author.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityPlan.attachment')" for="quality-plan-attachment"></label>
            <el-input
              type="text"
              class="form-control"
              name="attachment"
              id="quality-plan-attachment"
              data-cy="attachment"
              :class="{ valid: !v$.attachment.$invalid, invalid: v$.attachment.$invalid }"
              v-model="v$.attachment.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityPlan.projectwbs')" for="quality-plan-projectwbs"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="quality-plan-projectwbs"
              data-cy="projectwbs"
              name="projectwbs"
              v-model="qualityPlan.projectwbs"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  qualityPlan.projectwbs && projectwbsOption.id === qualityPlan.projectwbs.id ? qualityPlan.projectwbs : projectwbsOption
                "
                v-for="projectwbsOption in projectwbs"
                :key="projectwbsOption.id"
                :label="projectwbsOption.id"
                >{{ projectwbsOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityPlan.workbag')" for="quality-plan-workbag"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="quality-plan-workbag"
              data-cy="workbag"
              name="workbag"
              v-model="qualityPlan.workbag"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="qualityPlan.workbag && workbagOption.id === qualityPlan.workbag.id ? qualityPlan.workbag : workbagOption"
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
<script lang="ts" src="./quality-plan-update.component.ts"></script>
