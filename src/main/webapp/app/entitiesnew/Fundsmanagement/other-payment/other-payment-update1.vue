<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <div>
          
          <div class="form-group">
            <label class="form-control-label">付款类型</label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="type"
              :class="{ valid: !v$.type.$invalid, invalid: v$.type.$invalid }"
              v-model="v$.type.$model"
              id="other-payment-type"
              data-cy="type"
              placeholder="请选择"
            >
              <el-option
                v-for="otherPaymenttype in otherPaymenttypeValues"
                :key="otherPaymenttype"
                v-bind:value="otherPaymenttype"
                :label="getPaymentTypeLabel(otherPaymenttype)"
                >{{ getPaymentTypeLabel(otherPaymenttype) }}</el-option
              >
            </el-select>
          </div>

          <div class="form-group">
            <label class="form-control-label" for="other-payment-registertime">登记时间</label>
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
            <label class="form-control-label">科目名称</label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="subjectname"
              :class="{ valid: !v$.subjectname.$invalid, invalid: v$.subjectname.$invalid }"
              v-model="v$.subjectname.$model"
              id="other-payment-subjectname"
              data-cy="subjectname"
              placeholder="请选择"
              @change="onSubjectChange"
            >
              <el-option 
                v-for="subject in subjects" 
                :key="subject.name" 
                :label="subject.name" 
                :value="subject.name"
              />
            </el-select>
          </div>
          <!-- <div class="form-group">
            <label class="form-control-label" for="other-payment-subjectid">科目编号</label>
            <el-input
              type="text"
              class="form-control"
              name="subjectid"
              id="other-payment-subjectid"
              data-cy="subjectid"
              :class="{ valid: !v$.subjectid.$invalid, invalid: v$.subjectid.$invalid }"
              v-model="subjectId" 
            />
          </div> -->
          <input  name="subjectid" v-model="subjectId" />

          <div class="form-group">
            <label class="form-control-label" for="other-payment-paymentamount">付款金额</label>
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
            <label class="form-control-label" for="other-payment-contract">承沿合同</label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="other-payment-contract"
              data-cy="contract"
              name="contract"
              v-model="otherPayment.contract"
              placeholder="请选择"
              @change="onContractChange"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  otherPayment.contract && contractOption.id === otherPayment.contract.id ? otherPayment.contract : contractOption
                "
                v-for="contractOption in contracts"
                :key="contractOption.id"
                :label="contractOption.contractname"
                >{{ contractOption.contractname }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group"  v-show="false">
            <label class="form-control-label" for="other-payment-contractname">承沿合同名称</label>
            <el-input
              type="text"
              class="form-control"
              name="contractname"
              id="other-payment-contractname"
              data-cy="contractname"
              :class="{ valid: !v$.contractname.$invalid, invalid: v$.contractname.$invalid }"
              v-model="contractName"
            />
          </div>


          <div class="form-group">
            <label class="form-control-label" for="other-payment-projectwbs">重大项目</label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="other-payment-projectwbs"
              data-cy="projectwbs"
              name="projectwbs"
              v-model="otherPayment.projectwbs"
              placeholder="请选择"
              @change="onWbsChange"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  otherPayment.projectwbs && projectwbsOption.id === otherPayment.projectwbs.id ? otherPayment.projectwbs : projectwbsOption
                "
                v-for="projectwbsOption in projectwbs"
                :key="projectwbsOption.id"
                :label="projectwbsOption.wbsname"
                >{{ projectwbsOption.wbsname }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group" v-show="false">
            <label class="form-control-label" for="other-payment-wbsname">重大项目名称</label>
            <el-input
              type="text"
              class="form-control"
              name="wbsname"
              id="other-payment-wbsname"
              data-cy="wbsname"
              :class="{ valid: !v$.wbsname.$invalid, invalid: v$.wbsname.$invalid }"
              v-model="wbsName"
            />
          </div>

        </div>

        <div class="my-4"></div>
        
        <div class="d-flex justify-content-between">
          <el-button 
            type="button"  
            id="cancel-save" 
            data-cy="entityCreateCancelButton" 
            class="btn btn-secondary" 
            @click="previousState()"
          >
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>取消</span>
          </el-button>
          <el-button 
            type="primary" 
            id="save-entity" 
            data-cy="entityCreateSaveButton" 
            :disabled="v$.$invalid || isSaving" 
            class="btn btn-primary"
            @click="save"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>保存</span>
          </el-button>
        </div>
      </form>
    </div>
  </div>
</template>


<script lang="ts" src="./other-payment-update.component.ts"></script>


