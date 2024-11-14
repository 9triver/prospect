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
              v-text="t$('jy1App.paymentApplication.workbagname')"
              for="payment-application-workbagname"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="workbagname"
              id="payment-application-workbagname"
              data-cy="workbagname"
              :class="{ valid: !v$.workbagname.$invalid, invalid: v$.workbagname.$invalid }"
              v-model="v$.workbagname.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.paymentApplication.outsourcingcontractid')"
              for="payment-application-outsourcingcontractid"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="outsourcingcontractid"
              id="payment-application-outsourcingcontractid"
              data-cy="outsourcingcontractid"
              :class="{ valid: !v$.outsourcingcontractid.$invalid, invalid: v$.outsourcingcontractid.$invalid }"
              v-model="v$.outsourcingcontractid.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('jy1App.paymentApplication.outsourcingcontractname')"
              for="payment-application-outsourcingcontractname"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="outsourcingcontractname"
              id="payment-application-outsourcingcontractname"
              data-cy="outsourcingcontractname"
              :class="{ valid: !v$.outsourcingcontractname.$invalid, invalid: v$.outsourcingcontractname.$invalid }"
              v-model="v$.outsourcingcontractname.$model"
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
              v-text="t$('jy1App.paymentApplication.planpaymentname')"
              for="payment-application-planpaymentname"
            ></label>
            <el-input
              type="text"
              class="form-control"
              name="planpaymentname"
              id="payment-application-planpaymentname"
              data-cy="planpaymentname"
              :class="{ valid: !v$.planpaymentname.$invalid, invalid: v$.planpaymentname.$invalid }"
              v-model="v$.planpaymentname.$model"
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
              v-text="t$('jy1App.paymentApplication.contractpaymentid')"
              for="payment-application-contractpaymentid"
            ></label>
            <el-input
              type="number"
              class="form-control"
              name="contractpaymentid"
              id="payment-application-contractpaymentid"
              data-cy="contractpaymentid"
              :class="{ valid: !v$.contractpaymentid.$invalid, invalid: v$.contractpaymentid.$invalid }"
              v-model.number="v$.contractpaymentid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.paymentApplication.status')" for="payment-application-status"></label>
            <el-input
              type="text"
              class="form-control"
              name="status"
              id="payment-application-status"
              data-cy="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.paymentApplication.workbag')" for="payment-application-workbag"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="payment-application-workbag"
              data-cy="workbag"
              name="workbag"
              v-model="paymentApplication.workbag"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  paymentApplication.workbag && workbagOption.id === paymentApplication.workbag.id
                    ? paymentApplication.workbag
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
<script lang="ts" src="./payment-application-update.component.ts"></script>
