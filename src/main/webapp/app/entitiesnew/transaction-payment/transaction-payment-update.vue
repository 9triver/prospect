<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.transactionPayment.home.createOrEditLabel"
          data-cy="TransactionPaymentCreateUpdateHeading"
          v-text="t$('jy1App.transactionPayment.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="transactionPayment.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="transactionPayment.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.transactionPayment.planpaymentnode')"
              for="transaction-payment-planpaymentnode"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="planpaymentnode"
              id="transaction-payment-planpaymentnode"
              data-cy="planpaymentnode"
              :class="{ valid: !v$.planpaymentnode.$invalid, invalid: v$.planpaymentnode.$invalid }"
              v-model="v$.planpaymentnode.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.transactionPayment.planpaymentamount')"
              for="transaction-payment-planpaymentamount"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="planpaymentamount"
              id="transaction-payment-planpaymentamount"
              data-cy="planpaymentamount"
              :class="{ valid: !v$.planpaymentamount.$invalid, invalid: v$.planpaymentamount.$invalid }"
              v-model.number="v$.planpaymentamount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.transactionPayment.actualpaymentamount')"
              for="transaction-payment-actualpaymentamount"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="actualpaymentamount"
              id="transaction-payment-actualpaymentamount"
              data-cy="actualpaymentamount"
              :class="{ valid: !v$.actualpaymentamount.$invalid, invalid: v$.actualpaymentamount.$invalid }"
              v-model.number="v$.actualpaymentamount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.transactionPayment.paymenttype')"
              for="transaction-payment-paymenttype"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="paymenttype"
              :class="{ valid: !v$.paymenttype.$invalid, invalid: v$.paymenttype.$invalid }"
              v-model="v$.paymenttype.$model"
              id="transaction-payment-paymenttype"
              data-cy="paymenttype"
            >
              <el-option
                v-for="paymentType in paymentTypeValues"
                :key="paymentType"
                v-bind:value="paymentType"
                v-bind:label="t$('jy1App.PaymentType.' + paymentType)"
                >{{ paymentType }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.transactionPayment.financialvoucherid')"
              for="transaction-payment-financialvoucherid"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="financialvoucherid"
              id="transaction-payment-financialvoucherid"
              data-cy="financialvoucherid"
              :class="{ valid: !v$.financialvoucherid.$invalid, invalid: v$.financialvoucherid.$invalid }"
              v-model="v$.financialvoucherid.$model"
            />
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
<script lang="ts" src="./transaction-payment-update.component.ts"></script>
