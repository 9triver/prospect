<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.qualityReturns.home.createOrEditLabel"
          data-cy="QualityReturnsCreateUpdateHeading"
          v-text="t$('jy1App.qualityReturns.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="qualityReturns.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="qualityReturns.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityReturns.qualityplanid')"
              for="quality-returns-qualityplanid"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="qualityplanid"
              id="quality-returns-qualityplanid"
              data-cy="qualityplanid"
              :class="{ valid: !v$.qualityplanid.$invalid, invalid: v$.qualityplanid.$invalid }"
              v-model="v$.qualityplanid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityReturns.qualityobjectivesid')"
              for="quality-returns-qualityobjectivesid"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="qualityobjectivesid"
              id="quality-returns-qualityobjectivesid"
              data-cy="qualityobjectivesid"
              :class="{ valid: !v$.qualityobjectivesid.$invalid, invalid: v$.qualityobjectivesid.$invalid }"
              v-model="v$.qualityobjectivesid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityReturns.name')" for="quality-returns-name"></label>
            <el-input
              type="text"
              class="form-control"
              name="name"
              id="quality-returns-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityReturns.department')" for="quality-returns-department"></label>
            <el-input
              type="text"
              class="form-control"
              name="department"
              id="quality-returns-department"
              data-cy="department"
              :class="{ valid: !v$.department.$invalid, invalid: v$.department.$invalid }"
              v-model="v$.department.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityReturns.responsibleid')"
              for="quality-returns-responsibleid"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="responsibleid"
              id="quality-returns-responsibleid"
              data-cy="responsibleid"
              :class="{ valid: !v$.responsibleid.$invalid, invalid: v$.responsibleid.$invalid }"
              v-model="v$.responsibleid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityReturns.wbsid')" for="quality-returns-wbsid"></label>
            <el-input
              type="text"
              class="form-control"
              name="wbsid"
              id="quality-returns-wbsid"
              data-cy="wbsid"
              :class="{ valid: !v$.wbsid.$invalid, invalid: v$.wbsid.$invalid }"
              v-model="v$.wbsid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityReturns.workbagid')" for="quality-returns-workbagid"></label>
            <el-input
              type="text"
              class="form-control"
              name="workbagid"
              id="quality-returns-workbagid"
              data-cy="workbagid"
              :class="{ valid: !v$.workbagid.$invalid, invalid: v$.workbagid.$invalid }"
              v-model="v$.workbagid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityReturns.objectiveslevel')"
              for="quality-returns-objectiveslevel"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="objectiveslevel"
              id="quality-returns-objectiveslevel"
              data-cy="objectiveslevel"
              :class="{ valid: !v$.objectiveslevel.$invalid, invalid: v$.objectiveslevel.$invalid }"
              v-model="v$.objectiveslevel.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityReturns.objectives')" for="quality-returns-objectives"></label>
            <el-input
              type="text"
              class="form-control"
              name="objectives"
              id="quality-returns-objectives"
              data-cy="objectives"
              :class="{ valid: !v$.objectives.$invalid, invalid: v$.objectives.$invalid }"
              v-model="v$.objectives.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityReturns.objectivesvalue')"
              for="quality-returns-objectivesvalue"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="objectivesvalue"
              id="quality-returns-objectivesvalue"
              data-cy="objectivesvalue"
              :class="{ valid: !v$.objectivesvalue.$invalid, invalid: v$.objectivesvalue.$invalid }"
              v-model="v$.objectivesvalue.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityReturns.calculationmethod')"
              for="quality-returns-calculationmethod"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="calculationmethod"
              id="quality-returns-calculationmethod"
              data-cy="calculationmethod"
              :class="{ valid: !v$.calculationmethod.$invalid, invalid: v$.calculationmethod.$invalid }"
              v-model="v$.calculationmethod.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityReturns.frequency')" for="quality-returns-frequency"></label>
            <el-input
              type="text"
              class="form-control"
              name="frequency"
              id="quality-returns-frequency"
              data-cy="frequency"
              :class="{ valid: !v$.frequency.$invalid, invalid: v$.frequency.$invalid }"
              v-model="v$.frequency.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityReturns.isobjectivesvalue')"
              for="quality-returns-isobjectivesvalue"
            ></label>
            <el-input
              type="checkbox"
              class="form-check"
              name="isobjectivesvalue"
              id="quality-returns-isobjectivesvalue"
              data-cy="isobjectivesvalue"
              :class="{ valid: !v$.isobjectivesvalue.$invalid, invalid: v$.isobjectivesvalue.$invalid }"
              v-model="v$.isobjectivesvalue.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityReturns.percentage')" for="quality-returns-percentage"></label>
            <el-input
              type="number"
              class="form-control"
              name="percentage"
              id="quality-returns-percentage"
              data-cy="percentage"
              :class="{ valid: !v$.percentage.$invalid, invalid: v$.percentage.$invalid }"
              v-model.number="v$.percentage.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityReturns.objectivescompletion')"
              for="quality-returns-objectivescompletion"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="objectivescompletion"
              id="quality-returns-objectivescompletion"
              data-cy="objectivescompletion"
              :class="{ valid: !v$.objectivescompletion.$invalid, invalid: v$.objectivescompletion.$invalid }"
              v-model="v$.objectivescompletion.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityReturns.problem')" for="quality-returns-problem"></label>
            <el-input
              type="text"
              class="form-control"
              name="problem"
              id="quality-returns-problem"
              data-cy="problem"
              :class="{ valid: !v$.problem.$invalid, invalid: v$.problem.$invalid }"
              v-model="v$.problem.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityReturns.takeaction')" for="quality-returns-takeaction"></label>
            <el-input
              type="text"
              class="form-control"
              name="takeaction"
              id="quality-returns-takeaction"
              data-cy="takeaction"
              :class="{ valid: !v$.takeaction.$invalid, invalid: v$.takeaction.$invalid }"
              v-model="v$.takeaction.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityReturns.continuousimprovement')"
              for="quality-returns-continuousimprovement"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="continuousimprovement"
              id="quality-returns-continuousimprovement"
              data-cy="continuousimprovement"
              :class="{ valid: !v$.continuousimprovement.$invalid, invalid: v$.continuousimprovement.$invalid }"
              v-model="v$.continuousimprovement.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityReturns.workevidence')" for="quality-returns-workevidence"></label>
            <el-input
              type="text"
              class="form-control"
              name="workevidence"
              id="quality-returns-workevidence"
              data-cy="workevidence"
              :class="{ valid: !v$.workevidence.$invalid, invalid: v$.workevidence.$invalid }"
              v-model="v$.workevidence.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityReturns.returntime')" for="quality-returns-returntime"></label>
            <el-date-picker v-model="v$.returntime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityReturns.status')" for="quality-returns-status"></label>
            <el-input
              type="text"
              class="form-control"
              name="status"
              id="quality-returns-status"
              data-cy="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.qualityReturns.responsibleperson')"
              for="quality-returns-responsibleperson"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="quality-returns-responsibleperson"
              data-cy="responsibleperson"
              name="responsibleperson"
              v-model="qualityReturns.responsibleperson"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  qualityReturns.responsibleperson && hrManagementOption.id === qualityReturns.responsibleperson.id
                    ? qualityReturns.responsibleperson
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
            <label class="form-control-label" v-text="t$('jy1App.qualityReturns.auditorid')" for="quality-returns-auditorid"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="quality-returns-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="qualityReturns.auditorid"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  qualityReturns.auditorid && hrManagementOption.id === qualityReturns.auditorid.id
                    ? qualityReturns.auditorid
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
            <label v-text="t$('jy1App.qualityReturns.qualityObjectives')" for="quality-returns-qualityObjectives"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="quality-returns-qualityObjectives"
              data-cy="qualityObjectives"
              multiple
              name="qualityObjectives"
              v-if="qualityReturns.qualityObjectives !== undefined"
              v-model="qualityReturns.qualityObjectives"
            >
              <el-option
                v-bind:value="getSelected(qualityReturns.qualityObjectives, qualityObjectivesOption, 'id')"
                v-for="qualityObjectivesOption in qualityObjectives"
                :key="qualityObjectivesOption.id"
                :label="qualityObjectivesOption.id"
                >{{ qualityObjectivesOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.qualityReturns.qualityPlan')" for="quality-returns-qualityPlan"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="quality-returns-qualityPlan"
              data-cy="qualityPlan"
              name="qualityPlan"
              v-model="qualityReturns.qualityPlan"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  qualityReturns.qualityPlan && qualityPlanOption.id === qualityReturns.qualityPlan.id
                    ? qualityReturns.qualityPlan
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
<script lang="ts" src="./quality-returns-update.component.ts"></script>
