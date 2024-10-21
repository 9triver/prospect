<template>
  <div>
    <h2 id="page-heading" data-cy="SharePaymentHeading">
      <span v-text="t$('jy1App.sharePayment.home.title')" id="share-payment-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.sharePayment.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'SharePaymentCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-share-payment"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.sharePayment.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && sharePayments && sharePayments.length === 0">
      <span v-text="t$('jy1App.sharePayment.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="sharePayments && sharePayments.length > 0">
      <el-table :data="sharePayments" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'SharePaymentView', params: { sharePaymentId: scope.row.id } }">{{ scope.row.id }}</router-link>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="planpaymentnode"
          :label="t$('jy1App.sharePayment.planpaymentnode')"
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
          :label="t$('jy1App.sharePayment.planpaymentamount')"
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
          :label="t$('jy1App.sharePayment.actualpaymentamount')"
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
          :label="t$('jy1App.sharePayment.paymenttype')"
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
          :label="t$('jy1App.sharePayment.financialvoucherid')"
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
                <router-link :to="{ name: 'SharePaymentView', params: { sharePaymentId: scope.row.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'SharePaymentEdit', params: { sharePaymentId: scope.row.id } }" custom v-slot="{ navigate }">
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
      <!-- <table class="table table-striped" aria-describedby="sharePayments">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.sharePayment.planpaymentnode')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.sharePayment.planpaymentamount')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.sharePayment.actualpaymentamount')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.sharePayment.paymenttype')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.sharePayment.financialvoucherid')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="sharePayment in sharePayments"
                    :key="sharePayment.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'SharePaymentView', params: {sharePaymentId: sharePayment.id}}">{{sharePayment.id}}</router-link>
                    </td>
                    <td>{{sharePayment.planpaymentnode}}</td>
                    <td>{{sharePayment.planpaymentamount}}</td>
                    <td>{{sharePayment.actualpaymentamount}}</td>
                    <td v-text="t$('jy1App.PaymentType.' + sharePayment.paymenttype)"></td>
                    <td>{{sharePayment.financialvoucherid}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'SharePaymentView', params: {sharePaymentId: sharePayment.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'SharePaymentEdit', params: {sharePaymentId: sharePayment.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(sharePayment)"
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
        <span id="jy1App.sharePayment.delete.question" data-cy="sharePaymentDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-sharePayment-heading" v-text="t$('jy1App.sharePayment.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-sharePayment"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeSharePayment()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./share-payment.component.ts"></script>
