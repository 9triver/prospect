<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.contract.home.createOrEditLabel"
          data-cy="ContractCreateUpdateHeading"
          v-text="t$('jy1App.contract.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="contract.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="contract.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.contract.contractcode')" for="contract-contractcode"></label>
            <el-input
              type="text"
              class="form-control"
              name="contractcode"
              id="contract-contractcode"
              data-cy="contractcode"
              :class="{ valid: !v$.contractcode.$invalid, invalid: v$.contractcode.$invalid }"
              v-model="v$.contractcode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.contract.contractname')" for="contract-contractname"></label>
            <el-input
              type="text"
              class="form-control"
              name="contractname"
              id="contract-contractname"
              data-cy="contractname"
              :class="{ valid: !v$.contractname.$invalid, invalid: v$.contractname.$invalid }"
              v-model="v$.contractname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.contract.projectid')" for="contract-projectid"></label>
            <el-input
              type="text"
              class="form-control"
              name="projectid"
              id="contract-projectid"
              data-cy="projectid"
              :class="{ valid: !v$.projectid.$invalid, invalid: v$.projectid.$invalid }"
              v-model="v$.projectid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.contract.projectname')" for="contract-projectname"></label>
            <el-input
              type="text"
              class="form-control"
              name="projectname"
              id="contract-projectname"
              data-cy="projectname"
              :class="{ valid: !v$.projectname.$invalid, invalid: v$.projectname.$invalid }"
              v-model="v$.projectname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.contract.contracttype')" for="contract-contracttype"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="contracttype"
              :class="{ valid: !v$.contracttype.$invalid, invalid: v$.contracttype.$invalid }"
              v-model="v$.contracttype.$model"
              id="contract-contracttype"
              data-cy="contracttype"
            >
              <el-option
                v-for="contractType in contractTypeValues"
                :key="contractType"
                v-bind:value="contractType"
                v-bind:label="t$('jy1App.ContractType.' + contractType)"
                >{{ contractType }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.contract.year')" for="contract-year"></label>
            <el-input
              type="number"
              class="form-control"
              name="year"
              id="contract-year"
              data-cy="year"
              :class="{ valid: !v$.year.$invalid, invalid: v$.year.$invalid }"
              v-model.number="v$.year.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.contract.amount')" for="contract-amount"></label>
            <el-input
              type="number"
              class="form-control"
              name="amount"
              id="contract-amount"
              data-cy="amount"
              :class="{ valid: !v$.amount.$invalid, invalid: v$.amount.$invalid }"
              v-model.number="v$.amount.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.contract.starttime')" for="contract-starttime"></label>
            <el-date-picker v-model="v$.starttime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.contract.endtime')" for="contract-endtime"></label>
            <el-date-picker v-model="v$.endtime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.contract.secretlevel')" for="contract-secretlevel"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="contract-secretlevel"
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
            <label class="form-control-label" v-text="t$('jy1App.contract.status')" for="contract-status"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
              id="contract-status"
              data-cy="status"
            >
              <el-option
                v-for="contractStatus in contractStatusValues"
                :key="contractStatus"
                v-bind:value="contractStatus"
                v-bind:label="t$('jy1App.ContractStatus.' + contractStatus)"
                >{{ contractStatus }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.contract.budgetamount')" for="contract-budgetamount"></label>
            <el-input
              type="number"
              class="form-control"
              name="budgetamount"
              id="contract-budgetamount"
              data-cy="budgetamount"
              :class="{ valid: !v$.budgetamount.$invalid, invalid: v$.budgetamount.$invalid }"
              v-model.number="v$.budgetamount.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.contract.estimatedamount')" for="contract-estimatedamount"></label>
            <el-input
              type="number"
              class="form-control"
              name="estimatedamount"
              id="contract-estimatedamount"
              data-cy="estimatedamount"
              :class="{ valid: !v$.estimatedamount.$invalid, invalid: v$.estimatedamount.$invalid }"
              v-model.number="v$.estimatedamount.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.contract.implementedamount')" for="contract-implementedamount"></label>
            <el-input
              type="number"
              class="form-control"
              name="implementedamount"
              id="contract-implementedamount"
              data-cy="implementedamount"
              :class="{ valid: !v$.implementedamount.$invalid, invalid: v$.implementedamount.$invalid }"
              v-model.number="v$.implementedamount.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.contract.difference')" for="contract-difference"></label>
            <el-input
              type="number"
              class="form-control"
              name="difference"
              id="contract-difference"
              data-cy="difference"
              :class="{ valid: !v$.difference.$invalid, invalid: v$.difference.$invalid }"
              v-model.number="v$.difference.$model"
            />
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.contract.costControlSystem')" for="contract-costControlSystem"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="contract-costControlSystems"
              data-cy="costControlSystem"
              multiple
              name="costControlSystem"
              v-if="contract.costControlSystems !== undefined"
              v-model="contract.costControlSystems"
            >
              <el-option
                v-bind:value="getSelected(contract.costControlSystems, costControlSystemOption, 'id')"
                v-for="costControlSystemOption in costControlSystems"
                :key="costControlSystemOption.id"
                :label="costControlSystemOption.id"
                >{{ costControlSystemOption.id }}</el-option
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
<script lang="ts" src="./contract-update.component.ts"></script>
