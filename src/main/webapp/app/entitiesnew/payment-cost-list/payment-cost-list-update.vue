<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.paymentCostList.home.createOrEditLabel"
          data-cy="PaymentCostListCreateUpdateHeading"
          v-text="t$('jy1App.paymentCostList.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="paymentCostList.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="paymentCostList.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.paymentCostList.wbsid')" for="payment-cost-list-wbsid"></label>
            <el-input
              type="text"
              class="form-control"
              name="wbsid"
              id="payment-cost-list-wbsid"
              data-cy="wbsid"
              :class="{ valid: !v$.wbsid.$invalid, invalid: v$.wbsid.$invalid }"
              v-model="v$.wbsid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.paymentCostList.wbsname')" for="payment-cost-list-wbsname"></label>
            <el-input
              type="text"
              class="form-control"
              name="wbsname"
              id="payment-cost-list-wbsname"
              data-cy="wbsname"
              :class="{ valid: !v$.wbsname.$invalid, invalid: v$.wbsname.$invalid }"
              v-model="v$.wbsname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.paymentCostList.parentwbsid')" for="payment-cost-list-parentwbsid"></label>
            <el-input
              type="text"
              class="form-control"
              name="parentwbsid"
              id="payment-cost-list-parentwbsid"
              data-cy="parentwbsid"
              :class="{ valid: !v$.parentwbsid.$invalid, invalid: v$.parentwbsid.$invalid }"
              v-model="v$.parentwbsid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.paymentCostList.unit')" for="payment-cost-list-unit"></label>
            <el-input
              type="text"
              class="form-control"
              name="unit"
              id="payment-cost-list-unit"
              data-cy="unit"
              :class="{ valid: !v$.unit.$invalid, invalid: v$.unit.$invalid }"
              v-model="v$.unit.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.paymentCostList.unitprice')" for="payment-cost-list-unitprice"></label>
            <el-input
              type="number"
              class="form-control"
              name="unitprice"
              id="payment-cost-list-unitprice"
              data-cy="unitprice"
              :class="{ valid: !v$.unitprice.$invalid, invalid: v$.unitprice.$invalid }"
              v-model.number="v$.unitprice.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.paymentCostList.number')" for="payment-cost-list-number"></label>
            <el-input
              type="number"
              class="form-control"
              name="number"
              id="payment-cost-list-number"
              data-cy="number"
              :class="{ valid: !v$.number.$invalid, invalid: v$.number.$invalid }"
              v-model.number="v$.number.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.paymentCostList.invoicepaymentamount')"
              for="payment-cost-list-invoicepaymentamount"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="invoicepaymentamount"
              id="payment-cost-list-invoicepaymentamount"
              data-cy="invoicepaymentamount"
              :class="{ valid: !v$.invoicepaymentamount.$invalid, invalid: v$.invoicepaymentamount.$invalid }"
              v-model.number="v$.invoicepaymentamount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.paymentCostList.borrowingpaymentamount')"
              for="payment-cost-list-borrowingpaymentamount"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="borrowingpaymentamount"
              id="payment-cost-list-borrowingpaymentamount"
              data-cy="borrowingpaymentamount"
              :class="{ valid: !v$.borrowingpaymentamount.$invalid, invalid: v$.borrowingpaymentamount.$invalid }"
              v-model.number="v$.borrowingpaymentamount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.paymentCostList.accountingamount')"
              for="payment-cost-list-accountingamount"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="accountingamount"
              id="payment-cost-list-accountingamount"
              data-cy="accountingamount"
              :class="{ valid: !v$.accountingamount.$invalid, invalid: v$.accountingamount.$invalid }"
              v-model.number="v$.accountingamount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.paymentCostList.contractPayment')"
              for="payment-cost-list-contractPayment"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="payment-cost-list-contractPayment"
              data-cy="contractPayment"
              name="contractPayment"
              v-model="paymentCostList.contractPayment"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  paymentCostList.contractPayment && contractPaymentOption.id === paymentCostList.contractPayment.id
                    ? paymentCostList.contractPayment
                    : contractPaymentOption
                "
                v-for="contractPaymentOption in contractPayments"
                :key="contractPaymentOption.id"
                :label="contractPaymentOption.id"
                >{{ contractPaymentOption.id }}</el-option
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
<script lang="ts" src="./payment-cost-list-update.component.ts"></script>
