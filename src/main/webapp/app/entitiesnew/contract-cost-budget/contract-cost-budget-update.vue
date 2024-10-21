<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.contractCostBudget.home.createOrEditLabel"
          data-cy="ContractCostBudgetCreateUpdateHeading"
          v-text="t$('jy1App.contractCostBudget.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="contractCostBudget.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="contractCostBudget.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.contractCostBudget.subject')" for="contract-cost-budget-subject"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="subject"
              :class="{ valid: !v$.subject.$invalid, invalid: v$.subject.$invalid }"
              v-model="v$.subject.$model"
              id="contract-cost-budget-subject"
              data-cy="subject"
            >
              <el-option
                v-for="contractSubject in contractSubjectValues"
                :key="contractSubject"
                v-bind:value="contractSubject"
                v-bind:label="t$('jy1App.ContractSubject.' + contractSubject)"
                >{{ contractSubject }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.contractCostBudget.auxiliaryitem')"
              for="contract-cost-budget-auxiliaryitem"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="auxiliaryitem"
              id="contract-cost-budget-auxiliaryitem"
              data-cy="auxiliaryitem"
              :class="{ valid: !v$.auxiliaryitem.$invalid, invalid: v$.auxiliaryitem.$invalid }"
              v-model="v$.auxiliaryitem.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.contractCostBudget.unit')" for="contract-cost-budget-unit"></label>
            <el-input
              type="text"
              class="form-control"
              name="unit"
              id="contract-cost-budget-unit"
              data-cy="unit"
              :class="{ valid: !v$.unit.$invalid, invalid: v$.unit.$invalid }"
              v-model="v$.unit.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.contractCostBudget.number')" for="contract-cost-budget-number"></label>
            <el-input
              type="text"
              class="form-control"
              name="number"
              id="contract-cost-budget-number"
              data-cy="number"
              :class="{ valid: !v$.number.$invalid, invalid: v$.number.$invalid }"
              v-model="v$.number.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.contractCostBudget.unitprice')"
              for="contract-cost-budget-unitprice"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="unitprice"
              id="contract-cost-budget-unitprice"
              data-cy="unitprice"
              :class="{ valid: !v$.unitprice.$invalid, invalid: v$.unitprice.$invalid }"
              v-model.number="v$.unitprice.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.contractCostBudget.totalprice')"
              for="contract-cost-budget-totalprice"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="totalprice"
              id="contract-cost-budget-totalprice"
              data-cy="totalprice"
              :class="{ valid: !v$.totalprice.$invalid, invalid: v$.totalprice.$invalid }"
              v-model.number="v$.totalprice.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.contractCostBudget.responsibleperson')"
              for="contract-cost-budget-responsibleperson"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="contract-cost-budget-responsibleperson"
              data-cy="responsibleperson"
              name="responsibleperson"
              v-model="contractCostBudget.responsibleperson"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  contractCostBudget.responsibleperson && officersOption.id === contractCostBudget.responsibleperson.id
                    ? contractCostBudget.responsibleperson
                    : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
                :label="officersOption.id"
                >{{ officersOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.contractCostBudget.auditorid')"
              for="contract-cost-budget-auditorid"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="contract-cost-budget-auditorid"
              data-cy="auditorid"
              name="auditorid"
              v-model="contractCostBudget.auditorid"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  contractCostBudget.auditorid && officersOption.id === contractCostBudget.auditorid.id
                    ? contractCostBudget.auditorid
                    : officersOption
                "
                v-for="officersOption in officers"
                :key="officersOption.id"
                :label="officersOption.id"
                >{{ officersOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.contractCostBudget.projectwbs')" for="contract-cost-budget-projectwbs"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="contract-cost-budget-projectwbs"
              data-cy="projectwbs"
              multiple
              name="projectwbs"
              v-if="contractCostBudget.projectwbs !== undefined"
              v-model="contractCostBudget.projectwbs"
            >
              <el-option
                v-bind:value="getSelected(contractCostBudget.projectwbs, projectwbsOption, 'id')"
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
<script lang="ts" src="./contract-cost-budget-update.component.ts"></script>
