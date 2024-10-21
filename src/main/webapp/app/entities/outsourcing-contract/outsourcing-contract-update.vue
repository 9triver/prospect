<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.outsourcingContract.home.createOrEditLabel"
          data-cy="OutsourcingContractCreateUpdateHeading"
          v-text="t$('jy1App.outsourcingContract.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="outsourcingContract.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="outsourcingContract.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.contractid')"
              for="outsourcing-contract-contractid"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="contractid"
              id="outsourcing-contract-contractid"
              data-cy="contractid"
              :class="{ valid: !v$.contractid.$invalid, invalid: v$.contractid.$invalid }"
              v-model="v$.contractid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.contractcode')"
              for="outsourcing-contract-contractcode"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="contractcode"
              id="outsourcing-contract-contractcode"
              data-cy="contractcode"
              :class="{ valid: !v$.contractcode.$invalid, invalid: v$.contractcode.$invalid }"
              v-model="v$.contractcode.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.contractname')"
              for="outsourcing-contract-contractname"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="contractname"
              id="outsourcing-contract-contractname"
              data-cy="contractname"
              :class="{ valid: !v$.contractname.$invalid, invalid: v$.contractname.$invalid }"
              v-model="v$.contractname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.contractqualityid')"
              for="outsourcing-contract-contractqualityid"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="contractqualityid"
              id="outsourcing-contract-contractqualityid"
              data-cy="contractqualityid"
              :class="{ valid: !v$.contractqualityid.$invalid, invalid: v$.contractqualityid.$invalid }"
              v-model="v$.contractqualityid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.contractcostid')"
              for="outsourcing-contract-contractcostid"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="contractcostid"
              id="outsourcing-contract-contractcostid"
              data-cy="contractcostid"
              :class="{ valid: !v$.contractcostid.$invalid, invalid: v$.contractcostid.$invalid }"
              v-model="v$.contractcostid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.contractfinanceid')"
              for="outsourcing-contract-contractfinanceid"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="contractfinanceid"
              id="outsourcing-contract-contractfinanceid"
              data-cy="contractfinanceid"
              :class="{ valid: !v$.contractfinanceid.$invalid, invalid: v$.contractfinanceid.$invalid }"
              v-model="v$.contractfinanceid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.projectid')"
              for="outsourcing-contract-projectid"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="projectid"
              id="outsourcing-contract-projectid"
              data-cy="projectid"
              :class="{ valid: !v$.projectid.$invalid, invalid: v$.projectid.$invalid }"
              v-model="v$.projectid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.projectsecretlevel')"
              for="outsourcing-contract-projectsecretlevel"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="projectsecretlevel"
              id="outsourcing-contract-projectsecretlevel"
              data-cy="projectsecretlevel"
              :class="{ valid: !v$.projectsecretlevel.$invalid, invalid: v$.projectsecretlevel.$invalid }"
              v-model="v$.projectsecretlevel.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.counterpartyunit')"
              for="outsourcing-contract-counterpartyunit"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="counterpartyunit"
              id="outsourcing-contract-counterpartyunit"
              data-cy="counterpartyunit"
              :class="{ valid: !v$.counterpartyunit.$invalid, invalid: v$.counterpartyunit.$invalid }"
              v-model="v$.counterpartyunit.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.negotiationdate')"
              for="outsourcing-contract-negotiationdate"
            ></label>
            <el-date-picker v-model="v$.negotiationdate.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.negotiationlocation')"
              for="outsourcing-contract-negotiationlocation"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="negotiationlocation"
              id="outsourcing-contract-negotiationlocation"
              data-cy="negotiationlocation"
              :class="{ valid: !v$.negotiationlocation.$invalid, invalid: v$.negotiationlocation.$invalid }"
              v-model="v$.negotiationlocation.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.negotiator')"
              for="outsourcing-contract-negotiator"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="negotiator"
              id="outsourcing-contract-negotiator"
              data-cy="negotiator"
              :class="{ valid: !v$.negotiator.$invalid, invalid: v$.negotiator.$invalid }"
              v-model="v$.negotiator.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.budgetamount')"
              for="outsourcing-contract-budgetamount"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="budgetamount"
              id="outsourcing-contract-budgetamount"
              data-cy="budgetamount"
              :class="{ valid: !v$.budgetamount.$invalid, invalid: v$.budgetamount.$invalid }"
              v-model.number="v$.budgetamount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.contractamount')"
              for="outsourcing-contract-contractamount"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="contractamount"
              id="outsourcing-contract-contractamount"
              data-cy="contractamount"
              :class="{ valid: !v$.contractamount.$invalid, invalid: v$.contractamount.$invalid }"
              v-model.number="v$.contractamount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.approver')"
              for="outsourcing-contract-approver"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="approver"
              id="outsourcing-contract-approver"
              data-cy="approver"
              :class="{ valid: !v$.approver.$invalid, invalid: v$.approver.$invalid }"
              v-model="v$.approver.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.approvaldate')"
              for="outsourcing-contract-approvaldate"
            ></label>
            <el-date-picker v-model="v$.approvaldate.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.outsourcingContract.contractsecretlevel')"
              for="outsourcing-contract-contractsecretlevel"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="contractsecretlevel"
              id="outsourcing-contract-contractsecretlevel"
              data-cy="contractsecretlevel"
              :class="{ valid: !v$.contractsecretlevel.$invalid, invalid: v$.contractsecretlevel.$invalid }"
              v-model="v$.contractsecretlevel.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.outsourcingContract.workbag')" for="outsourcing-contract-workbag"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="outsourcing-contract-workbag"
              data-cy="workbag"
              name="workbag"
              v-model="outsourcingContract.workbag"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  outsourcingContract.workbag && workbagOption.id === outsourcingContract.workbag.id
                    ? outsourcingContract.workbag
                    : workbagOption
                "
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
<script lang="ts" src="./outsourcing-contract-update.component.ts"></script>
