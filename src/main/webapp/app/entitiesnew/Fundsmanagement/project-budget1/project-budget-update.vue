<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.projectBudget.home.createOrEditLabel"
          data-cy="ProjectBudgetCreateUpdateHeading"
          v-text="t$('jy1App.projectBudget.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="projectBudget.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="projectBudget.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectBudget.wbsid')" for="project-budget-wbsid"></label>
            <el-input
              type="text"
              class="form-control"
              name="wbsid"
              id="project-budget-wbsid"
              data-cy="wbsid"
              :class="{ valid: !v$.wbsid.$invalid, invalid: v$.wbsid.$invalid }"
              v-model="v$.wbsid.$model"
              required
            />
            <div v-if="v$.wbsid.$anyDirty && v$.wbsid.$invalid">
              <small class="form-text text-danger" v-for="error of v$.wbsid.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectBudget.wbsname')" for="project-budget-wbsname"></label>
            <el-input
              type="text"
              class="form-control"
              name="wbsname"
              id="project-budget-wbsname"
              data-cy="wbsname"
              :class="{ valid: !v$.wbsname.$invalid, invalid: v$.wbsname.$invalid }"
              v-model="v$.wbsname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectBudget.parentwbsid')" for="project-budget-parentwbsid"></label>
            <el-input
              type="text"
              class="form-control"
              name="parentwbsid"
              id="project-budget-parentwbsid"
              data-cy="parentwbsid"
              :class="{ valid: !v$.parentwbsid.$invalid, invalid: v$.parentwbsid.$invalid }"
              v-model="v$.parentwbsid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectBudget.subjectid')" for="project-budget-subjectid"></label>
            <el-input
              type="number"
              class="form-control"
              name="subjectid"
              id="project-budget-subjectid"
              data-cy="subjectid"
              :class="{ valid: !v$.subjectid.$invalid, invalid: v$.subjectid.$invalid }"
              v-model.number="v$.subjectid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectBudget.subjectname')" for="project-budget-subjectname"></label>
            <el-input
              type="text"
              class="form-control"
              name="subjectname"
              id="project-budget-subjectname"
              data-cy="subjectname"
              :class="{ valid: !v$.subjectname.$invalid, invalid: v$.subjectname.$invalid }"
              v-model="v$.subjectname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectBudget.contractid')" for="project-budget-contractid"></label>
            <el-input
              type="text"
              class="form-control"
              name="contractid"
              id="project-budget-contractid"
              data-cy="contractid"
              :class="{ valid: !v$.contractid.$invalid, invalid: v$.contractid.$invalid }"
              v-model="v$.contractid.$model"
              required
            />
            <div v-if="v$.contractid.$anyDirty && v$.contractid.$invalid">
              <small class="form-text text-danger" v-for="error of v$.contractid.$errors" :key="error.$uid">{{ error.$message }}</small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectBudget.contractname')" for="project-budget-contractname"></label>
            <el-input
              type="text"
              class="form-control"
              name="contractname"
              id="project-budget-contractname"
              data-cy="contractname"
              :class="{ valid: !v$.contractname.$invalid, invalid: v$.contractname.$invalid }"
              v-model="v$.contractname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectBudget.year')" for="project-budget-year"></label>
            <el-input
              type="number"
              class="form-control"
              name="year"
              id="project-budget-year"
              data-cy="year"
              :class="{ valid: !v$.year.$invalid, invalid: v$.year.$invalid }"
              v-model.number="v$.year.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectBudget.auxiliaryitem')" for="project-budget-auxiliaryitem"></label>
            <el-input
              type="text"
              class="form-control"
              name="auxiliaryitem"
              id="project-budget-auxiliaryitem"
              data-cy="auxiliaryitem"
              :class="{ valid: !v$.auxiliaryitem.$invalid, invalid: v$.auxiliaryitem.$invalid }"
              v-model="v$.auxiliaryitem.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectBudget.unit')" for="project-budget-unit"></label>
            <el-input
              type="text"
              class="form-control"
              name="unit"
              id="project-budget-unit"
              data-cy="unit"
              :class="{ valid: !v$.unit.$invalid, invalid: v$.unit.$invalid }"
              v-model="v$.unit.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectBudget.number')" for="project-budget-number"></label>
            <el-input
              type="text"
              class="form-control"
              name="number"
              id="project-budget-number"
              data-cy="number"
              :class="{ valid: !v$.number.$invalid, invalid: v$.number.$invalid }"
              v-model="v$.number.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectBudget.unitprice')" for="project-budget-unitprice"></label>
            <el-input
              type="number"
              class="form-control"
              name="unitprice"
              id="project-budget-unitprice"
              data-cy="unitprice"
              :class="{ valid: !v$.unitprice.$invalid, invalid: v$.unitprice.$invalid }"
              v-model.number="v$.unitprice.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectBudget.budgetamount')" for="project-budget-budgetamount"></label>
            <el-input
              type="number"
              class="form-control"
              name="budgetamount"
              id="project-budget-budgetamount"
              data-cy="budgetamount"
              :class="{ valid: !v$.budgetamount.$invalid, invalid: v$.budgetamount.$invalid }"
              v-model.number="v$.budgetamount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.projectBudget.estimatedamount')"
              for="project-budget-estimatedamount"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="estimatedamount"
              id="project-budget-estimatedamount"
              data-cy="estimatedamount"
              :class="{ valid: !v$.estimatedamount.$invalid, invalid: v$.estimatedamount.$invalid }"
              v-model.number="v$.estimatedamount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.projectBudget.implementedamount')"
              for="project-budget-implementedamount"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="implementedamount"
              id="project-budget-implementedamount"
              data-cy="implementedamount"
              :class="{ valid: !v$.implementedamount.$invalid, invalid: v$.implementedamount.$invalid }"
              v-model.number="v$.implementedamount.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectBudget.difference')" for="project-budget-difference"></label>
            <el-input
              type="number"
              class="form-control"
              name="difference"
              id="project-budget-difference"
              data-cy="difference"
              :class="{ valid: !v$.difference.$invalid, invalid: v$.difference.$invalid }"
              v-model.number="v$.difference.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.projectBudget.remark')" for="project-budget-remark"></label>
            <el-input
              type="text"
              class="form-control"
              name="remark"
              id="project-budget-remark"
              data-cy="remark"
              :class="{ valid: !v$.remark.$invalid, invalid: v$.remark.$invalid }"
              v-model="v$.remark.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.projectBudget.responsibleperson')"
              for="project-budget-responsibleperson"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="project-budget-responsibleperson"
              data-cy="responsibleperson"
              name="responsibleperson"
              v-model="projectBudget.responsibleperson"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  projectBudget.responsibleperson && hrManagementOption.id === projectBudget.responsibleperson.id
                    ? projectBudget.responsibleperson
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
            <label class="form-control-label" v-text="t$('jy1App.projectBudget.auditorid')" for="project-budget-auditorid"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="project-budget-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="projectBudget.auditorid"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  projectBudget.auditorid && hrManagementOption.id === projectBudget.auditorid.id
                    ? projectBudget.auditorid
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
            <label v-text="t$('jy1App.projectBudget.projectwbs')" for="project-budget-projectwbs"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="project-budget-projectwbs"
              data-cy="projectwbs"
              multiple
              name="projectwbs"
              v-if="projectBudget.projectwbs !== undefined"
              v-model="projectBudget.projectwbs"
            >
              <el-option
                v-bind:value="getSelected(projectBudget.projectwbs, projectwbsOption, 'id')"
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
<script lang="ts" src="./project-budget-update.component.ts"></script>
