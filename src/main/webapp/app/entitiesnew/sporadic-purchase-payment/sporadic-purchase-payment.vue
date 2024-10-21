<template>
  <div>
    <h2 id="page-heading" data-cy="SporadicPurchasePaymentHeading">
      <span v-text="t$('jy1App.sporadicPurchasePayment.home.title')" id="sporadic-purchase-payment-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.sporadicPurchasePayment.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'SporadicPurchasePaymentCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-sporadic-purchase-payment"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.sporadicPurchasePayment.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && sporadicPurchasePayments && sporadicPurchasePayments.length === 0">
      <span v-text="t$('jy1App.sporadicPurchasePayment.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="sporadicPurchasePayments && sporadicPurchasePayments.length > 0">
      <el-table :data="sporadicPurchasePayments" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'SporadicPurchasePaymentView', params: { sporadicPurchasePaymentId: scope.row.id } }">{{
              scope.row.id
            }}</router-link>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="planpaymentnode"
          :label="t$('jy1App.sporadicPurchasePayment.planpaymentnode')"
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
          :label="t$('jy1App.sporadicPurchasePayment.planpaymentamount')"
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
          :label="t$('jy1App.sporadicPurchasePayment.actualpaymentamount')"
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
          :label="t$('jy1App.sporadicPurchasePayment.paymenttype')"
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
          :label="t$('jy1App.sporadicPurchasePayment.financialvoucherid')"
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
                  :to="{ name: 'SporadicPurchasePaymentView', params: { sporadicPurchasePaymentId: scope.row.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'SporadicPurchasePaymentEdit', params: { sporadicPurchasePaymentId: scope.row.id } }"
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
      <!-- <table class="table table-striped" aria-describedby="sporadicPurchasePayments">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.sporadicPurchasePayment.planpaymentnode')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.sporadicPurchasePayment.planpaymentamount')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.sporadicPurchasePayment.actualpaymentamount')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.sporadicPurchasePayment.paymenttype')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.sporadicPurchasePayment.financialvoucherid')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="sporadicPurchasePayment in sporadicPurchasePayments"
                    :key="sporadicPurchasePayment.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'SporadicPurchasePaymentView', params: {sporadicPurchasePaymentId: sporadicPurchasePayment.id}}">{{sporadicPurchasePayment.id}}</router-link>
                    </td>
                    <td>{{sporadicPurchasePayment.planpaymentnode}}</td>
                    <td>{{sporadicPurchasePayment.planpaymentamount}}</td>
                    <td>{{sporadicPurchasePayment.actualpaymentamount}}</td>
                    <td v-text="t$('jy1App.PaymentType.' + sporadicPurchasePayment.paymenttype)"></td>
                    <td>{{sporadicPurchasePayment.financialvoucherid}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'SporadicPurchasePaymentView', params: {sporadicPurchasePaymentId: sporadicPurchasePayment.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'SporadicPurchasePaymentEdit', params: {sporadicPurchasePaymentId: sporadicPurchasePayment.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(sporadicPurchasePayment)"
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
          id="jy1App.sporadicPurchasePayment.delete.question"
          data-cy="sporadicPurchasePaymentDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-sporadicPurchasePayment-heading"
          v-text="t$('jy1App.sporadicPurchasePayment.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-sporadicPurchasePayment"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeSporadicPurchasePayment()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./sporadic-purchase-payment.component.ts"></script>
