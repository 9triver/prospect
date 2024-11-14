<template>
  <div>
      <el-form :model="form" class="demo-form-inline" label-width="auto" ref="queryFormRef">
        <el-row :gutter="10">
          <el-col :span="8">
            <el-form-item label="付款类型" prop="type">
              <el-select v-model="form.type" placeholder="请选择" size="moddle">
                <el-option label="零星采购" value="SPORADICPURCHASE" />
                <el-option label="分摊费用" value="SHARE" />
                <el-option label="事物费用" value="TRANSACTION" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="承沿合同" prop="contractname">
              <el-input v-model="form.contractname" ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="科目类型" prop="subjectname">
              <el-input v-model="form.subjectname" ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="WBS名称" prop="wbsname">
              <el-input v-model="form.wbsname" ></el-input>
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
          <router-link :to="{ name: 'OtherPaymentCreate' }" custom v-slot="{ navigate }">
            <el-button type="primary" @click="navigate"
              id="jh-create-entity"
              data-cy="entityCreateButton"
              class="btn btn-primary jh-create-entity create-other-payment">创建</el-button>
          </router-link>
        </el-form-item>
      </div>     
    <br />
    <div class="alert alert-warning" v-if="!isFetching && otherPayments && otherPayments.length === 0">
      <span>未查询到符合条件的数据</span>
    </div>
    
    <div class="table-responsive" v-if="otherPayments && otherPayments.length > 0">
      <el-table :data="otherPayments" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="60px" show-overflow-tooltip prop="id" label="序号">
          <template #default="scope">
            <router-link :to="{ name: 'OtherPaymentView', params: { otherPaymentId: scope.row.id } }">{{ scope.row.id }}</router-link>
          </template>
        </el-table-column>
        <el-table-column min-width="100px" show-overflow-tooltip prop="type" label="类型" :sortable="false">
          <template #default="scope">
              <span>
                <template v-if="scope.row.type === 'SPORADICPURCHASE'">零星采购</template>
                <template v-if="scope.row.type === 'SHARE'">分摊费用</template>
                <template v-if="scope.row.type === 'TRANSACTION'">事物费用</template>
              </span>
          </template>
        </el-table-column>
        
        <el-table-column min-width="150px" show-overflow-tooltip prop="registertime" label="登记时间" :sortable="true">
          <template #default="scope">
            <span class="field-default">{{ scope.row.registertime }}</span>
          </template>
        </el-table-column>

        <el-table-column min-width="150px" show-overflow-tooltip prop="subjectname" label="科目" :sortable="true">
          <template #default="scope">
            <span class="field-default">{{ scope.row.subjectname }}</span>
          </template>
        </el-table-column>

        <el-table-column min-width="150px" show-overflow-tooltip prop="paymentamount" label="付款金额" :sortable="true">
          <template #default="scope">
            <span class="field-default">{{ scope.row.paymentamount }}</span>
          </template>
        </el-table-column>

        <el-table-column min-width="150px" show-overflow-tooltip prop="contractname" label="承沿合同" :sortable="true">
          <template #default="scope">
            <span class="field-default">{{ scope.row.contractname }}</span>
          </template>
        </el-table-column>

        <el-table-column min-width="150px" show-overflow-tooltip prop="wbsname" label="重大项目" :sortable="true">
          <template #default="scope">
            <span class="field-default">{{ scope.row.wbsname }}</span>
          </template>
        </el-table-column>

        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'OtherPaymentView', params: { otherPaymentId: scope.row.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'OtherPaymentEdit', params: { otherPaymentId: scope.row.id } }" custom v-slot="{ navigate }">
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
        <span id="jy1App.otherPayment.delete.question" data-cy="otherPaymentDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-otherPayment-heading" v-text="t$('jy1App.otherPayment.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-otherPayment"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeOtherPayment()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./other-payment.component.ts"></script>
