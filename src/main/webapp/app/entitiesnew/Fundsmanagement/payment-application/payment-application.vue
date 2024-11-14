<template>
  <div>

    <el-form :model="form" class="demo-form-inline" label-width="auto" ref="queryFormRef">
        <el-row :gutter="10">
          <el-col :span="8">
            <el-form-item label="合同编号" prop="outsourcingcontractid">
              <el-input v-model="form.outsourcingcontractid" ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="任务包" prop="workbag">
              <el-input v-model="form.workbag" ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    
      <div class="d-flex justify-content-center">
        <el-form-item>
            <el-button type="primary" plain @click="onSubmit">查询</el-button>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" plain @click="handleSyncList(queryFormRef)">重置</el-button>
        </el-form-item>
        <el-form-item>
          <router-link :to="{ name: 'PaymentApplicationCreate' }" custom v-slot="{ navigate }">
            <el-button type="primary" @click="navigate"
              id="jh-create-entity"
              data-cy="entityCreateButton"
              class="btn btn-primary jh-create-entity create-other-payment">创建</el-button>
          </router-link>
        </el-form-item>
      </div>  

    <br />
    <div class="alert alert-warning" v-if="!isFetching && paymentApplications && paymentApplications.length === 0">
      <span>未查询到符合条件的数据</span>
    </div>
    <div class="table-responsive" v-if="paymentApplications && paymentApplications.length > 0">
      <el-table :data="paymentApplications" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="60px" show-overflow-tooltip prop="id" label="序号">
          <template #default="scope">
            <router-link :to="{ name: 'PaymentApplicationView', params: { paymentApplicationId: scope.row.id } }">{{
              scope.row.id
            }}</router-link>
          </template>
        </el-table-column>

        <el-table-column min-width="150px" show-overflow-tooltip prop="outsourcingcontractid" label="外协合同编号">
          <template #default="scope">
            <td>
              <div v-if="scope.row.outsourcingcontractid">
                <router-link :to="{ name: 'OutsourcingContractView', params: { outsourcingContractId: scope.row.outsourcingcontractid } }">{{
                  scope.row.outsourcingcontractname
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>

        <el-table-column min-width="150px" show-overflow-tooltip prop="planpaymentnode" label="计划付款节点">
          <template #default="scope">
            <td>
              <div v-if="scope.row.planpaymentnode">
                <router-link :to="{ name: 'MilestoneNodeView', params: { milestoneNodeId: scope.row.planpaymentnode } }">{{
                  scope.row.planpaymentname
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="planpaymentamount"
          label="计划付款金额"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.planpaymentamount }}</span>
          </template>
        </el-table-column>

        <el-table-column min-width="150px" show-overflow-tooltip prop="workbag.id" label="任务包">
          <template #default="scope">
            <td>
              <div v-if="scope.row.workbag">
                <router-link :to="{ name: 'WorkbagView', params: { workbagId: scope.row.workbag.id } }">{{
                  scope.row.workbagname
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>

        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'PaymentApplicationView', params: { paymentApplicationId: scope.row.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'PaymentApplicationEdit', params: { paymentApplicationId: scope.row.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(scope.row)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="trash"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="t$('entity.action.delete')"></span>
                </b-button>
              </div>
            </td>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <template #modal-title>
        <span
          id="jy1App.paymentApplication.delete.question"
          data-cy="paymentApplicationDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-paymentApplication-heading" v-text="t$('jy1App.paymentApplication.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-paymentApplication"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removePaymentApplication()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./payment-application.component.ts"></script>
