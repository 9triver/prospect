<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.contractPayment.home.createOrEditLabel"
          data-cy="ContractPaymentCreateUpdateHeading"
          v-text="t$('jy1App.contractPayment.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="contractPayment.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="contractPayment.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.contractPayment.workbagid')" for="contract-payment-workbagid"></label>
            <el-input
              type="text"
              class="form-control"
              name="workbagid"
              id="contract-payment-workbagid"
              data-cy="workbagid"
              :class="{ valid: !v$.workbagid.$invalid, invalid: v$.workbagid.$invalid }"
              v-model="v$.workbagid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.contractPayment.workbagname')" for="contract-payment-workbagname"></label>
            <el-input
              type="text"
              class="form-control"
              name="workbagname"
              id="contract-payment-workbagname"
              data-cy="workbagname"
              :class="{ valid: !v$.workbagname.$invalid, invalid: v$.workbagname.$invalid }"
              v-model="v$.workbagname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.contractPayment.contractcode')"
              for="contract-payment-contractcode"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="contractcode"
              id="contract-payment-contractcode"
              data-cy="contractcode"
              :class="{ valid: !v$.contractcode.$invalid, invalid: v$.contractcode.$invalid }"
              v-model="v$.contractcode.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.contractPayment.contractname')"
              for="contract-payment-contractname"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="contractname"
              id="contract-payment-contractname"
              data-cy="contractname"
              :class="{ valid: !v$.contractname.$invalid, invalid: v$.contractname.$invalid }"
              v-model="v$.contractname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.contractPayment.planpaymentnode')"
              for="contract-payment-planpaymentnode"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="planpaymentnode"
              id="contract-payment-planpaymentnode"
              data-cy="planpaymentnode"
              :class="{ valid: !v$.planpaymentnode.$invalid, invalid: v$.planpaymentnode.$invalid }"
              v-model="v$.planpaymentnode.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.contractPayment.planpaymentamount')"
              for="contract-payment-planpaymentamount"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="planpaymentamount"
              id="contract-payment-planpaymentamount"
              data-cy="planpaymentamount"
              :class="{ valid: !v$.planpaymentamount.$invalid, invalid: v$.planpaymentamount.$invalid }"
              v-model.number="v$.planpaymentamount.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.contractPayment.actualpaymentamount')"
              for="contract-payment-actualpaymentamount"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="actualpaymentamount"
              id="contract-payment-actualpaymentamount"
              data-cy="actualpaymentamount"
              :class="{ valid: !v$.actualpaymentamount.$invalid, invalid: v$.actualpaymentamount.$invalid }"
              v-model.number="v$.actualpaymentamount.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.contractPayment.paymenttype')" for="contract-payment-paymenttype"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="paymenttype"
              :class="{ valid: !v$.paymenttype.$invalid, invalid: v$.paymenttype.$invalid }"
              v-model="v$.paymenttype.$model"
              id="contract-payment-paymenttype"
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
              v-text="t$('jy1App.contractPayment.financialvoucherid')"
              for="contract-payment-financialvoucherid"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="financialvoucherid"
              id="contract-payment-financialvoucherid"
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
<script lang="ts" src="./contract-payment-update.component.ts"></script>
