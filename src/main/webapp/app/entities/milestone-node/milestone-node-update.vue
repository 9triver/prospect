<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.milestoneNode.home.createOrEditLabel"
          data-cy="MilestoneNodeCreateUpdateHeading"
          v-text="t$('jy1App.milestoneNode.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="milestoneNode.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="milestoneNode.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.milestoneNode.outsourcingcontractid')"
              for="milestone-node-outsourcingcontractid"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="outsourcingcontractid"
              id="milestone-node-outsourcingcontractid"
              data-cy="outsourcingcontractid"
              :class="{ valid: !v$.outsourcingcontractid.$invalid, invalid: v$.outsourcingcontractid.$invalid }"
              v-model="v$.outsourcingcontractid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.milestoneNode.outsourcingcontractname')"
              for="milestone-node-outsourcingcontractname"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="outsourcingcontractname"
              id="milestone-node-outsourcingcontractname"
              data-cy="outsourcingcontractname"
              :class="{ valid: !v$.outsourcingcontractname.$invalid, invalid: v$.outsourcingcontractname.$invalid }"
              v-model="v$.outsourcingcontractname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.milestoneNode.name')" for="milestone-node-name"></label>
            <el-input
              type="text"
              class="form-control"
              name="name"
              id="milestone-node-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.milestoneNode.planpaymenttime')"
              for="milestone-node-planpaymenttime"
            ></label>
            <el-date-picker v-model="v$.planpaymenttime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.milestoneNode.planpaymentamount')"
              for="milestone-node-planpaymentamount"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="planpaymentamount"
              id="milestone-node-planpaymentamount"
              data-cy="planpaymentamount"
              :class="{ valid: !v$.planpaymentamount.$invalid, invalid: v$.planpaymentamount.$invalid }"
              v-model.number="v$.planpaymentamount.$model"
            />
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.milestoneNode.outsourcingContract')" for="milestone-node-outsourcingContract"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="milestone-node-outsourcingContracts"
              data-cy="outsourcingContract"
              multiple
              name="outsourcingContract"
              v-if="milestoneNode.outsourcingContracts !== undefined"
              v-model="milestoneNode.outsourcingContracts"
            >
              <el-option
                v-bind:value="getSelected(milestoneNode.outsourcingContracts, outsourcingContractOption, 'id')"
                v-for="outsourcingContractOption in outsourcingContracts"
                :key="outsourcingContractOption.id"
                :label="outsourcingContractOption.id"
                >{{ outsourcingContractOption.id }}</el-option
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
<script lang="ts" src="./milestone-node-update.component.ts"></script>
