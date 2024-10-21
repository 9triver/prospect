<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.fundSourceList.home.createOrEditLabel"
          data-cy="FundSourceListCreateUpdateHeading"
          v-text="t$('jy1App.fundSourceList.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="fundSourceList.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="fundSourceList.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.fundSourceList.paymentid')" for="fund-source-list-paymentid"></label>
            <el-input
              type="text"
              class="form-control"
              name="paymentid"
              id="fund-source-list-paymentid"
              data-cy="paymentid"
              :class="{ valid: !v$.paymentid.$invalid, invalid: v$.paymentid.$invalid }"
              v-model="v$.paymentid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.fundSourceList.contractcode')" for="fund-source-list-contractcode"></label>
            <el-input
              type="text"
              class="form-control"
              name="contractcode"
              id="fund-source-list-contractcode"
              data-cy="contractcode"
              :class="{ valid: !v$.contractcode.$invalid, invalid: v$.contractcode.$invalid }"
              v-model="v$.contractcode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.fundSourceList.contractname')" for="fund-source-list-contractname"></label>
            <el-input
              type="text"
              class="form-control"
              name="contractname"
              id="fund-source-list-contractname"
              data-cy="contractname"
              :class="{ valid: !v$.contractname.$invalid, invalid: v$.contractname.$invalid }"
              v-model="v$.contractname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.fundSourceList.amount')" for="fund-source-list-amount"></label>
            <el-input
              type="number"
              class="form-control"
              name="amount"
              id="fund-source-list-amount"
              data-cy="amount"
              :class="{ valid: !v$.amount.$invalid, invalid: v$.amount.$invalid }"
              v-model.number="v$.amount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.fundSourceList.transactionPayment')"
              for="fund-source-list-transactionPayment"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="fund-source-list-transactionPayment"
              data-cy="transactionPayment"
              name="transactionPayment"
              v-model="fundSourceList.transactionPayment"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  fundSourceList.transactionPayment && transactionPaymentOption.id === fundSourceList.transactionPayment.id
                    ? fundSourceList.transactionPayment
                    : transactionPaymentOption
                "
                v-for="transactionPaymentOption in transactionPayments"
                :key="transactionPaymentOption.id"
                :label="transactionPaymentOption.id"
                >{{ transactionPaymentOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.fundSourceList.sporadicPurchasePayment')"
              for="fund-source-list-sporadicPurchasePayment"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="fund-source-list-sporadicPurchasePayment"
              data-cy="sporadicPurchasePayment"
              name="sporadicPurchasePayment"
              v-model="fundSourceList.sporadicPurchasePayment"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  fundSourceList.sporadicPurchasePayment && sporadicPurchasePaymentOption.id === fundSourceList.sporadicPurchasePayment.id
                    ? fundSourceList.sporadicPurchasePayment
                    : sporadicPurchasePaymentOption
                "
                v-for="sporadicPurchasePaymentOption in sporadicPurchasePayments"
                :key="sporadicPurchasePaymentOption.id"
                :label="sporadicPurchasePaymentOption.id"
                >{{ sporadicPurchasePaymentOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.fundSourceList.sharePayment')" for="fund-source-list-sharePayment"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="fund-source-list-sharePayment"
              data-cy="sharePayment"
              name="sharePayment"
              v-model="fundSourceList.sharePayment"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  fundSourceList.sharePayment && sharePaymentOption.id === fundSourceList.sharePayment.id
                    ? fundSourceList.sharePayment
                    : sharePaymentOption
                "
                v-for="sharePaymentOption in sharePayments"
                :key="sharePaymentOption.id"
                :label="sharePaymentOption.id"
                >{{ sharePaymentOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.fundSourceList.contractPayment')"
              for="fund-source-list-contractPayment"
            ></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="fund-source-list-contractPayment"
              data-cy="contractPayment"
              name="contractPayment"
              v-model="fundSourceList.contractPayment"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  fundSourceList.contractPayment && contractPaymentOption.id === fundSourceList.contractPayment.id
                    ? fundSourceList.contractPayment
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
<script lang="ts" src="./fund-source-list-update.component.ts"></script>
