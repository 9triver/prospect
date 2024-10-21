<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.planReturns.home.createOrEditLabel"
          data-cy="PlanReturnsCreateUpdateHeading"
          v-text="t$('jy1App.planReturns.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="planReturns.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="planReturns.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.planReturns.planreturnsname')" for="plan-returns-planreturnsname"></label>
            <el-input
              type="text"
              class="form-control"
              name="planreturnsname"
              id="plan-returns-planreturnsname"
              data-cy="planreturnsname"
              :class="{ valid: !v$.planreturnsname.$invalid, invalid: v$.planreturnsname.$invalid }"
              v-model="v$.planreturnsname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.planReturns.plantype')" for="plan-returns-plantype"></label>
            <el-input
              type="number"
              class="form-control"
              name="plantype"
              id="plan-returns-plantype"
              data-cy="plantype"
              :class="{ valid: !v$.plantype.$invalid, invalid: v$.plantype.$invalid }"
              v-model.number="v$.plantype.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.planReturns.planlevel')" for="plan-returns-planlevel"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="planlevel"
              :class="{ valid: !v$.planlevel.$invalid, invalid: v$.planlevel.$invalid }"
              v-model="v$.planlevel.$model"
              id="plan-returns-planlevel"
              data-cy="planlevel"
            >
              <el-option
                v-for="planLevel in planLevelValues"
                :key="planLevel"
                v-bind:value="planLevel"
                v-bind:label="t$('jy1App.PlanLevel.' + planLevel)"
                >{{ planLevel }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.planReturns.description')" for="plan-returns-description"></label>
            <el-input
              type="text"
              class="form-control"
              name="description"
              id="plan-returns-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.planReturns.actualstarttime')" for="plan-returns-actualstarttime"></label>
            <el-date-picker v-model="v$.actualstarttime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.planReturns.actualendtime')" for="plan-returns-actualendtime"></label>
            <el-date-picker v-model="v$.actualendtime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.planReturns.deliverables')" for="plan-returns-deliverables"></label>
            <el-input
              type="text"
              class="form-control"
              name="deliverables"
              id="plan-returns-deliverables"
              data-cy="deliverables"
              :class="{ valid: !v$.deliverables.$invalid, invalid: v$.deliverables.$invalid }"
              v-model="v$.deliverables.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.planReturns.progress')" for="plan-returns-progress"></label>
            <el-input
              type="number"
              class="form-control"
              name="progress"
              id="plan-returns-progress"
              data-cy="progress"
              :class="{ valid: !v$.progress.$invalid, invalid: v$.progress.$invalid }"
              v-model.number="v$.progress.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.planReturns.status')" for="plan-returns-status"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
              id="plan-returns-status"
              data-cy="status"
            >
              <el-option
                v-for="planstatus in planstatusValues"
                :key="planstatus"
                v-bind:value="planstatus"
                v-bind:label="t$('jy1App.Planstatus.' + planstatus)"
                >{{ planstatus }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.planReturns.impactanalysis')" for="plan-returns-impactanalysis"></label>
            <el-input
              type="text"
              class="form-control"
              name="impactanalysis"
              id="plan-returns-impactanalysis"
              data-cy="impactanalysis"
              :class="{ valid: !v$.impactanalysis.$invalid, invalid: v$.impactanalysis.$invalid }"
              v-model="v$.impactanalysis.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.planReturns.returnstime')" for="plan-returns-returnstime"></label>
            <el-date-picker v-model="v$.returnstime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.planReturns.rejectionreason')" for="plan-returns-rejectionreason"></label>
            <el-input
              type="text"
              class="form-control"
              name="rejectionreason"
              id="plan-returns-rejectionreason"
              data-cy="rejectionreason"
              :class="{ valid: !v$.rejectionreason.$invalid, invalid: v$.rejectionreason.$invalid }"
              v-model="v$.rejectionreason.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.planReturns.returnsstatus')" for="plan-returns-returnsstatus"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="returnsstatus"
              :class="{ valid: !v$.returnsstatus.$invalid, invalid: v$.returnsstatus.$invalid }"
              v-model="v$.returnsstatus.$model"
              id="plan-returns-returnsstatus"
              data-cy="returnsstatus"
            >
              <el-option
                v-for="returnsStatus in returnsStatusValues"
                :key="returnsStatus"
                v-bind:value="returnsStatus"
                v-bind:label="t$('jy1App.ReturnsStatus.' + returnsStatus)"
                >{{ returnsStatus }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.planReturns.responsibleperson')"
              for="plan-returns-responsibleperson"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="plan-returns-responsibleperson"
              data-cy="responsibleperson"
              name="responsibleperson"
              v-model="planReturns.responsibleperson"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  planReturns.responsibleperson && hrManagementOption.id === planReturns.responsibleperson.id
                    ? planReturns.responsibleperson
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
            <label class="form-control-label" v-text="t$('jy1App.planReturns.auditorid')" for="plan-returns-auditorid"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="plan-returns-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="planReturns.auditorid"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  planReturns.auditorid && hrManagementOption.id === planReturns.auditorid.id ? planReturns.auditorid : hrManagementOption
                "
                v-for="hrManagementOption in hrManagements"
                :key="hrManagementOption.id"
                :label="hrManagementOption.id"
                >{{ hrManagementOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.planReturns.progressPlan')" for="plan-returns-progressPlan"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="plan-returns-progressPlan"
              data-cy="progressPlan"
              name="progressPlan"
              v-model="planReturns.progressPlan"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  planReturns.progressPlan && progressPlanOption.id === planReturns.progressPlan.id
                    ? planReturns.progressPlan
                    : progressPlanOption
                "
                v-for="progressPlanOption in progressPlans"
                :key="progressPlanOption.id"
                :label="progressPlanOption.id"
                >{{ progressPlanOption.id }}</el-option
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
<script lang="ts" src="./plan-returns-update.component.ts"></script>
