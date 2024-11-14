<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.subjectCostBudget.home.createOrEditLabel"
          data-cy="SubjectCostBudgetCreateUpdateHeading"
          v-text="t$('jy1App.subjectCostBudget.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="subjectCostBudget.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="subjectCostBudget.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.subjectCostBudget.contractcode')"
              for="subject-cost-budget-contractcode"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="contractcode"
              id="subject-cost-budget-contractcode"
              data-cy="contractcode"
              :class="{ valid: !v$.contractcode.$invalid, invalid: v$.contractcode.$invalid }"
              v-model="v$.contractcode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.subjectCostBudget.subjectid')" for="subject-cost-budget-subjectid"></label>
            <el-input
              type="number"
              class="form-control"
              name="subjectid"
              id="subject-cost-budget-subjectid"
              data-cy="subjectid"
              :class="{ valid: !v$.subjectid.$invalid, invalid: v$.subjectid.$invalid }"
              v-model.number="v$.subjectid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.subjectCostBudget.subjectname')"
              for="subject-cost-budget-subjectname"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="subjectname"
              id="subject-cost-budget-subjectname"
              data-cy="subjectname"
              :class="{ valid: !v$.subjectname.$invalid, invalid: v$.subjectname.$invalid }"
              v-model="v$.subjectname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.subjectCostBudget.budgetamount')"
              for="subject-cost-budget-budgetamount"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="budgetamount"
              id="subject-cost-budget-budgetamount"
              data-cy="budgetamount"
              :class="{ valid: !v$.budgetamount.$invalid, invalid: v$.budgetamount.$invalid }"
              v-model.number="v$.budgetamount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.subjectCostBudget.estimatedamount')"
              for="subject-cost-budget-estimatedamount"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="estimatedamount"
              id="subject-cost-budget-estimatedamount"
              data-cy="estimatedamount"
              :class="{ valid: !v$.estimatedamount.$invalid, invalid: v$.estimatedamount.$invalid }"
              v-model.number="v$.estimatedamount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.subjectCostBudget.percentage')"
              for="subject-cost-budget-percentage"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="percentage"
              id="subject-cost-budget-percentage"
              data-cy="percentage"
              :class="{ valid: !v$.percentage.$invalid, invalid: v$.percentage.$invalid }"
              v-model.number="v$.percentage.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.subjectCostBudget.projectBudget')"
              for="subject-cost-budget-projectBudget"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="subject-cost-budget-projectBudget"
              data-cy="projectBudget"
              name="projectBudget"
              v-model="subjectCostBudget.projectBudget"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  subjectCostBudget.projectBudget && projectBudgetOption.id === subjectCostBudget.projectBudget.id
                    ? subjectCostBudget.projectBudget
                    : projectBudgetOption
                "
                v-for="projectBudgetOption in projectBudgets"
                :key="projectBudgetOption.id"
                :label="projectBudgetOption.id"
                >{{ projectBudgetOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.subjectCostBudget.responsibleperson')"
              for="subject-cost-budget-responsibleperson"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="subject-cost-budget-responsibleperson"
              data-cy="responsibleperson"
              name="responsibleperson"
              v-model="subjectCostBudget.responsibleperson"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  subjectCostBudget.responsibleperson && hrManagementOption.id === subjectCostBudget.responsibleperson.id
                    ? subjectCostBudget.responsibleperson
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
            <label class="form-control-label" v-text="t$('jy1App.subjectCostBudget.auditorid')" for="subject-cost-budget-auditorid"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="subject-cost-budget-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="subjectCostBudget.auditorid"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  subjectCostBudget.auditorid && hrManagementOption.id === subjectCostBudget.auditorid.id
                    ? subjectCostBudget.auditorid
                    : hrManagementOption
                "
                v-for="hrManagementOption in hrManagements"
                :key="hrManagementOption.id"
                :label="hrManagementOption.id"
                >{{ hrManagementOption.id }}</el-option
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
<script lang="ts" src="./subject-cost-budget-update.component.ts"></script>
