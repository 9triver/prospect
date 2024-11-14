<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.otherPayment.home.createOrEditLabel"
          data-cy="OtherPaymentCreateUpdateHeading"
          v-text="t$('jy1App.otherPayment.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="otherPayment.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="otherPayment.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.otherPayment.name')" for="other-payment-name"></label>
            <el-input
              type="text"
              class="form-control"
              name="name"
              id="other-payment-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.otherPayment.type')" for="other-payment-type"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="type"
              :class="{ valid: !v$.type.$invalid, invalid: v$.type.$invalid }"
              v-model="v$.type.$model"
              id="other-payment-type"
              data-cy="type"
            >
              <el-option
                v-for="otherPaymenttype in otherPaymenttypeValues"
                :key="otherPaymenttype"
                v-bind:value="otherPaymenttype"
                v-bind:label="t$('jy1App.OtherPaymenttype.' + otherPaymenttype)"
                >{{ otherPaymenttype }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.otherPayment.registertime')" for="other-payment-registertime"></label>
            <el-date-picker v-model="v$.registertime.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.otherPayment.subjectid')" for="other-payment-subjectid"></label>
            <el-input
              type="number"
              class="form-control"
              name="subjectid"
              id="other-payment-subjectid"
              data-cy="subjectid"
              :class="{ valid: !v$.subjectid.$invalid, invalid: v$.subjectid.$invalid }"
              v-model.number="v$.subjectid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.otherPayment.subjectname')" for="other-payment-subjectname"></label>
            <el-input
              type="text"
              class="form-control"
              name="subjectname"
              id="other-payment-subjectname"
              data-cy="subjectname"
              :class="{ valid: !v$.subjectname.$invalid, invalid: v$.subjectname.$invalid }"
              v-model="v$.subjectname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.otherPayment.paymentamount')" for="other-payment-paymentamount"></label>
            <el-input
              type="number"
              class="form-control"
              name="paymentamount"
              id="other-payment-paymentamount"
              data-cy="paymentamount"
              :class="{ valid: !v$.paymentamount.$invalid, invalid: v$.paymentamount.$invalid }"
              v-model.number="v$.paymentamount.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.otherPayment.contractcode')" for="other-payment-contractcode"></label>
            <el-input
              type="text"
              class="form-control"
              name="contractcode"
              id="other-payment-contractcode"
              data-cy="contractcode"
              :class="{ valid: !v$.contractcode.$invalid, invalid: v$.contractcode.$invalid }"
              v-model="v$.contractcode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.otherPayment.contractname')" for="other-payment-contractname"></label>
            <el-input
              type="text"
              class="form-control"
              name="contractname"
              id="other-payment-contractname"
              data-cy="contractname"
              :class="{ valid: !v$.contractname.$invalid, invalid: v$.contractname.$invalid }"
              v-model="v$.contractname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.otherPayment.wbsid')" for="other-payment-wbsid"></label>
            <el-input
              type="text"
              class="form-control"
              name="wbsid"
              id="other-payment-wbsid"
              data-cy="wbsid"
              :class="{ valid: !v$.wbsid.$invalid, invalid: v$.wbsid.$invalid }"
              v-model="v$.wbsid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.otherPayment.wbsname')" for="other-payment-wbsname"></label>
            <el-input
              type="text"
              class="form-control"
              name="wbsname"
              id="other-payment-wbsname"
              data-cy="wbsname"
              :class="{ valid: !v$.wbsname.$invalid, invalid: v$.wbsname.$invalid }"
              v-model="v$.wbsname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.otherPayment.projectwbs')" for="other-payment-projectwbs"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="other-payment-projectwbs"
              data-cy="projectwbs"
              name="projectwbs"
              v-model="otherPayment.projectwbs"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  otherPayment.projectwbs && projectwbsOption.id === otherPayment.projectwbs.id ? otherPayment.projectwbs : projectwbsOption
                "
                v-for="projectwbsOption in projectwbs"
                :key="projectwbsOption.id"
                :label="projectwbsOption.id"
                >{{ projectwbsOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.otherPayment.contract')" for="other-payment-contract"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="other-payment-contract"
              data-cy="contract"
              name="contract"
              v-model="otherPayment.contract"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  otherPayment.contract && contractOption.id === otherPayment.contract.id ? otherPayment.contract : contractOption
                "
                v-for="contractOption in contracts"
                :key="contractOption.id"
                :label="contractOption.id"
                >{{ contractOption.id }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.otherPayment.subject')" for="other-payment-subject"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="other-payment-subject"
              data-cy="subject"
              name="subject"
              v-model="otherPayment.subject"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="otherPayment.subject && subjectOption.id === otherPayment.subject.id ? otherPayment.subject : subjectOption"
                v-for="subjectOption in subjects"
                :key="subjectOption.id"
                :label="subjectOption.id"
                >{{ subjectOption.id }}</el-option
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
<script lang="ts" src="./other-payment-update.component.ts"></script>
