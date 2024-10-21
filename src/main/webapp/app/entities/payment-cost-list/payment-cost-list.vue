<template>
  <div>
    <h2 id="page-heading" data-cy="PaymentCostListHeading">
      <span v-text="t$('jy1App.paymentCostList.home.title')" id="payment-cost-list-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.paymentCostList.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'PaymentCostListCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-payment-cost-list"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.paymentCostList.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && paymentCostLists && paymentCostLists.length === 0">
      <span v-text="t$('jy1App.paymentCostList.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="paymentCostLists && paymentCostLists.length > 0">
      <el-table :data="paymentCostLists" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'PaymentCostListView', params: { paymentCostListId: scope.row.id } }">{{ scope.row.id }}</router-link>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="wbsid" :label="t$('jy1App.paymentCostList.wbsid')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.wbsid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="wbsname"
          :label="t$('jy1App.paymentCostList.wbsname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.wbsname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="parentwbsid"
          :label="t$('jy1App.paymentCostList.parentwbsid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.parentwbsid }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="unit" :label="t$('jy1App.paymentCostList.unit')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.unit }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="unitprice"
          :label="t$('jy1App.paymentCostList.unitprice')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.unitprice }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="number"
          :label="t$('jy1App.paymentCostList.number')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.number }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="invoicepaymentamount"
          :label="t$('jy1App.paymentCostList.invoicepaymentamount')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.invoicepaymentamount }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="borrowingpaymentamount"
          :label="t$('jy1App.paymentCostList.borrowingpaymentamount')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.borrowingpaymentamount }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="accountingamount"
          :label="t$('jy1App.paymentCostList.accountingamount')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.accountingamount }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="contractPayment.id"
          :label="t$('jy1App.paymentCostList.contractPayment')"
        >
          <template #default="scope">
            <td>
              <div v-if="scope.row.contractPayment">
                <router-link :to="{ name: 'ContractPaymentView', params: { contractPaymentId: scope.row.contractPayment.id } }">{{
                  scope.row.contractPayment.id
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
                  :to="{ name: 'PaymentCostListView', params: { paymentCostListId: scope.row.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'PaymentCostListEdit', params: { paymentCostListId: scope.row.id } }"
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
      <!-- <table class="table table-striped" aria-describedby="paymentCostLists">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.paymentCostList.wbsid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.paymentCostList.wbsname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.paymentCostList.parentwbsid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.paymentCostList.unit')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.paymentCostList.unitprice')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.paymentCostList.number')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.paymentCostList.invoicepaymentamount')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.paymentCostList.borrowingpaymentamount')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.paymentCostList.accountingamount')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.paymentCostList.contractPayment')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="paymentCostList in paymentCostLists"
                    :key="paymentCostList.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'PaymentCostListView', params: {paymentCostListId: paymentCostList.id}}">{{paymentCostList.id}}</router-link>
                    </td>
                    <td>{{paymentCostList.wbsid}}</td>
                    <td>{{paymentCostList.wbsname}}</td>
                    <td>{{paymentCostList.parentwbsid}}</td>
                    <td>{{paymentCostList.unit}}</td>
                    <td>{{paymentCostList.unitprice}}</td>
                    <td>{{paymentCostList.number}}</td>
                    <td>{{paymentCostList.invoicepaymentamount}}</td>
                    <td>{{paymentCostList.borrowingpaymentamount}}</td>
                    <td>{{paymentCostList.accountingamount}}</td>
                    <td>
                        <div v-if="paymentCostList.contractPayment">
                            <router-link :to="{name: 'ContractPaymentView', params: {contractPaymentId: paymentCostList.contractPayment.id}}">{{paymentCostList.contractPayment.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'PaymentCostListView', params: {paymentCostListId: paymentCostList.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'PaymentCostListEdit', params: {paymentCostListId: paymentCostList.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(paymentCostList)"
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
          id="jy1App.paymentCostList.delete.question"
          data-cy="paymentCostListDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-paymentCostList-heading" v-text="t$('jy1App.paymentCostList.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-paymentCostList"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removePaymentCostList()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./payment-cost-list.component.ts"></script>
