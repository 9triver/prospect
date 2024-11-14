<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <div>
          
          <div class="form-group">
            <label class="form-control-label" for="payment-application-workbag">任务包</label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="payment-application-workbag"
              data-cy="workbag"
              name="workbag"
              v-model="paymentApplication.workbag"
              placeholder="请选择"
              @change="onWorkbagChange"
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
                :label="workbagOption.name"
                >{{ workbagOption.name }}</el-option
              >
            </el-select>
          </div>

          <div class="form-group" >
            <label class="form-control-label" for="payment-application-outsourcingcontractid">外协合同编号</label>
            <el-input
              type="text"
              class="form-control"
              name="outsourcingcontractid"
              id="payment-application-outsourcingcontractid"
              data-cy="outsourcingcontractid"
              :class="{ valid: !v$.outsourcingcontractid.$invalid, invalid: v$.outsourcingcontractid.$invalid }"
              v-model="outsourcingcontractId"
            />
          </div>

          <div class="form-group">
            <label class="form-control-label" for="payment-application-planpaymentnode">计划付款节点</label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              id="payment-application-planpaymentnode"
              data-cy="planpaymentnode"
              name="planpaymentnode"
              v-model="paymentApplication.planpaymentnode"
              placeholder="请选择"
              @change="onMilestoneNodeChange"
            >
              <el-option v-bind:value="null"></el-option>
              <el-option
                v-bind:value="
                  milestoneNodes && milestonenodeOption.id === paymentApplication.planpaymentnode ? milestoneNodes : milestonenodeOption
                "
                v-for="milestonenodeOption in milestoneNodes"
                :key="milestonenodeOption.id"
                :label="milestonenodeOption.name"
                >{{ milestonenodeOption.name }}</el-option
              >
            </el-select>
          </div>

          <!-- <div class="form-group">
            <label class="form-control-label" for="payment-application-planpaymentnode">计划付款节点</label>
            <el-input
              type="text"
              class="form-control"
              name="planpaymentnode"
              id="payment-application-planpaymentnode"
              data-cy="planpaymentnode"
              :class="{ valid: !v$.planpaymentnode.$invalid, invalid: v$.planpaymentnode.$invalid }"
              v-model="v$.planpaymentnode.$model"
            />
          </div> -->
          <div class="form-group">
            <label class="form-control-label" for="payment-application-planpaymentamount">计划付款金额</label>
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
<script lang="ts" src="./payment-application-update.component.ts"></script>
