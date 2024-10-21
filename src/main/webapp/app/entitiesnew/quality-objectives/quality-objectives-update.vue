<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.qualityObjectives.home.createOrEditLabel"
          data-cy="QualityObjectivesCreateUpdateHeading"
          v-text="t$('jy1App.qualityObjectives.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="qualityObjectives.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="qualityObjectives.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityObjectives.name')" for="quality-objectives-name"></label>
            <el-input
              type="text"
              class="form-control"
              name="name"
              id="quality-objectives-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityObjectives.objectiveslevel')"
              for="quality-objectives-objectiveslevel"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="objectiveslevel"
              id="quality-objectives-objectiveslevel"
              data-cy="objectiveslevel"
              :class="{ valid: !v$.objectiveslevel.$invalid, invalid: v$.objectiveslevel.$invalid }"
              v-model="v$.objectiveslevel.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityObjectives.objectives')"
              for="quality-objectives-objectives"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="objectives"
              id="quality-objectives-objectives"
              data-cy="objectives"
              :class="{ valid: !v$.objectives.$invalid, invalid: v$.objectives.$invalid }"
              v-model="v$.objectives.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityObjectives.objectivesvalue')"
              for="quality-objectives-objectivesvalue"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="objectivesvalue"
              id="quality-objectives-objectivesvalue"
              data-cy="objectivesvalue"
              :class="{ valid: !v$.objectivesvalue.$invalid, invalid: v$.objectivesvalue.$invalid }"
              v-model="v$.objectivesvalue.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityObjectives.calculationmethod')"
              for="quality-objectives-calculationmethod"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="calculationmethod"
              id="quality-objectives-calculationmethod"
              data-cy="calculationmethod"
              :class="{ valid: !v$.calculationmethod.$invalid, invalid: v$.calculationmethod.$invalid }"
              v-model="v$.calculationmethod.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityObjectives.frequency')" for="quality-objectives-frequency"></label>
            <el-input
              type="text"
              class="form-control"
              name="frequency"
              id="quality-objectives-frequency"
              data-cy="frequency"
              :class="{ valid: !v$.frequency.$invalid, invalid: v$.frequency.$invalid }"
              v-model="v$.frequency.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityObjectives.takeaction')"
              for="quality-objectives-takeaction"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="takeaction"
              id="quality-objectives-takeaction"
              data-cy="takeaction"
              :class="{ valid: !v$.takeaction.$invalid, invalid: v$.takeaction.$invalid }"
              v-model="v$.takeaction.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityObjectives.needresource')"
              for="quality-objectives-needresource"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="needresource"
              id="quality-objectives-needresource"
              data-cy="needresource"
              :class="{ valid: !v$.needresource.$invalid, invalid: v$.needresource.$invalid }"
              v-model="v$.needresource.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityObjectives.status')" for="quality-objectives-status"></label>
            <el-input
              type="text"
              class="form-control"
              name="status"
              id="quality-objectives-status"
              data-cy="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityObjectives.responsibleperson')"
              for="quality-objectives-responsibleperson"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="quality-objectives-responsibleperson"
              data-cy="responsibleperson"
              name="responsibleperson"
              v-model="qualityObjectives.responsibleperson"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  qualityObjectives.responsibleperson && hrManagementOption.id === qualityObjectives.responsibleperson.id
                    ? qualityObjectives.responsibleperson
                    : hrManagementOption
                "
                v-for="hrManagementOption in hrManagements"
                :key="hrManagementOption.id"
                :label="hrManagementOption.id"
                >{{ hrManagementOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.qualityObjectives.qualityReturns')" for="quality-objectives-qualityReturns"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="quality-objectives-qualityReturns"
              data-cy="qualityReturns"
              multiple
              name="qualityReturns"
              v-if="qualityObjectives.qualityReturns !== undefined"
              v-model="qualityObjectives.qualityReturns"
            >
              <el-option
                v-bind:value="getSelected(qualityObjectives.qualityReturns, qualityReturnsOption, 'id')"
                v-for="qualityReturnsOption in qualityReturns"
                :key="qualityReturnsOption.id"
                :label="qualityReturnsOption.id"
                >{{ qualityReturnsOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityObjectives.qualityPlan')"
              for="quality-objectives-qualityPlan"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="quality-objectives-qualityPlan"
              data-cy="qualityPlan"
              name="qualityPlan"
              v-model="qualityObjectives.qualityPlan"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  qualityObjectives.qualityPlan && qualityPlanOption.id === qualityObjectives.qualityPlan.id
                    ? qualityObjectives.qualityPlan
                    : qualityPlanOption
                "
                v-for="qualityPlanOption in qualityPlans"
                :key="qualityPlanOption.id"
                :label="qualityPlanOption.id"
                >{{ qualityPlanOption.id }}</el-option
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
<script lang="ts" src="./quality-objectives-update.component.ts"></script>
