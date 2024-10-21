<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.paymentApplication.home.createOrEditLabel"
          data-cy="PaymentApplicationCreateUpdateHeading"
          v-text="t$('jy1App.paymentApplication.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="paymentApplication.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="paymentApplication.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.paymentApplication.workbagid')"
              for="payment-application-workbagid"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="workbagid"
              id="payment-application-workbagid"
              data-cy="workbagid"
              :class="{ valid: !v$.workbagid.$invalid, invalid: v$.workbagid.$invalid }"
              v-model="v$.workbagid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.paymentApplication.contractcode')"
              for="payment-application-contractcode"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="contractcode"
              id="payment-application-contractcode"
              data-cy="contractcode"
              :class="{ valid: !v$.contractcode.$invalid, invalid: v$.contractcode.$invalid }"
              v-model="v$.contractcode.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.paymentApplication.planpaymentnode')"
              for="payment-application-planpaymentnode"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="planpaymentnode"
              id="payment-application-planpaymentnode"
              data-cy="planpaymentnode"
              :class="{ valid: !v$.planpaymentnode.$invalid, invalid: v$.planpaymentnode.$invalid }"
              v-model="v$.planpaymentnode.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.paymentApplication.planpaymentamount')"
              for="payment-application-planpaymentamount"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="planpaymentamount"
              id="payment-application-planpaymentamount"
              data-cy="planpaymentamount"
              :class="{ valid: !v$.planpaymentamount.$invalid, invalid: v$.planpaymentamount.$invalid }"
              v-model.number="v$.planpaymentamount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.paymentApplication.outsourcingContract')"
              for="payment-application-outsourcingContract"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="payment-application-outsourcingContract"
              data-cy="outsourcingContract"
              name="outsourcingContract"
              v-model="paymentApplication.outsourcingContract"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  paymentApplication.outsourcingContract && outsourcingContractOption.id === paymentApplication.outsourcingContract.id
                    ? paymentApplication.outsourcingContract
                    : outsourcingContractOption
                "
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
<script lang="ts" src="./payment-application-update.component.ts"></script>
