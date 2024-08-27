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
          <!-- <div class="form-group" v-if="contract.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="contract.id" readonly />
          </div> -->
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
            <label class="form-control-label" v-text="t$('jy1App.contract.contractbudgetcost')" for="contract-contractbudgetcost"></label>
            <el-input
              type="number"
              class="form-control"
              name="contractbudgetcost"
              id="contract-contractbudgetcost"
              data-cy="contractbudgetcost"
              :class="{ valid: !v$.contractbudgetcost.$invalid, invalid: v$.contractbudgetcost.$invalid }"
              v-model.number="v$.contractbudgetcost.$model"
            />
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
                >{{ t$('jy1App.Secretlevel.' + secretlevel) }}</el-option
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
                >{{ t$('jy1App.ContractStatus.' + contractStatus) }}</el-option
              >
            </el-select>
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
                :label="costControlSystemOption.type"
                >{{ costControlSystemOption.type }}</el-option
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
