<template>
  <div>
    <h2 id="page-heading" data-cy="TransactionPaymentHeading">
      <span v-text="t$('jy1App.transactionPayment.home.title')" id="transaction-payment-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.transactionPayment.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'TransactionPaymentCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-transaction-payment"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.transactionPayment.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && transactionPayments && transactionPayments.length === 0">
      <span v-text="t$('jy1App.transactionPayment.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="transactionPayments && transactionPayments.length > 0">
      <el-table :data="transactionPayments" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'TransactionPaymentView', params: { transactionPaymentId: scope.row.id } }">{{
              scope.row.id
            }}</router-link>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="planpaymentnode"
          :label="t$('jy1App.transactionPayment.planpaymentnode')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.planpaymentnode }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="planpaymentamount"
          :label="t$('jy1App.transactionPayment.planpaymentamount')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.planpaymentamount }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="actualpaymentamount"
          :label="t$('jy1App.transactionPayment.actualpaymentamount')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.actualpaymentamount }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="paymenttype"
          :label="t$('jy1App.transactionPayment.paymenttype')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.PaymentType.' + scope.row.paymenttype)"></span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="financialvoucherid"
          :label="t$('jy1App.transactionPayment.financialvoucherid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.financialvoucherid }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'TransactionPaymentView', params: { transactionPaymentId: scope.row.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'TransactionPaymentEdit', params: { transactionPaymentId: scope.row.id } }"
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
      <!-- <table class="table table-striped" aria-describedby="transactionPayments">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.transactionPayment.planpaymentnode')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.transactionPayment.planpaymentamount')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.transactionPayment.actualpaymentamount')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.transactionPayment.paymenttype')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.transactionPayment.financialvoucherid')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="transactionPayment in transactionPayments"
                    :key="transactionPayment.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'TransactionPaymentView', params: {transactionPaymentId: transactionPayment.id}}">{{transactionPayment.id}}</router-link>
                    </td>
                    <td>{{transactionPayment.planpaymentnode}}</td>
                    <td>{{transactionPayment.planpaymentamount}}</td>
                    <td>{{transactionPayment.actualpaymentamount}}</td>
                    <td v-text="t$('jy1App.PaymentType.' + transactionPayment.paymenttype)"></td>
                    <td>{{transactionPayment.financialvoucherid}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'TransactionPaymentView', params: {transactionPaymentId: transactionPayment.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'TransactionPaymentEdit', params: {transactionPaymentId: transactionPayment.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(transactionPayment)"
                                   variant="danger"
                                   class="btn btn-sm"
                                   data-cy="entityDeleteButton"
                                   v-b-modal.removeEntity>
                                <font-awesome-icon icon="times"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="t$('entity.action.delete')"></span>
                            </b-button>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>-->
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <template #modal-title>
        <span
          id="jy1App.transactionPayment.delete.question"
          data-cy="transactionPaymentDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-transactionPayment-heading" v-text="t$('jy1App.transactionPayment.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-transactionPayment"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeTransactionPayment()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./transaction-payment.component.ts"></script>
