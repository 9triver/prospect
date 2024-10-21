<template>
  <div>
    <h2 id="page-heading" data-cy="FundSourceListHeading">
      <span v-text="t$('jy1App.fundSourceList.home.title')" id="fund-source-list-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.fundSourceList.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'FundSourceListCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-fund-source-list"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.fundSourceList.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && fundSourceLists && fundSourceLists.length === 0">
      <span v-text="t$('jy1App.fundSourceList.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="fundSourceLists && fundSourceLists.length > 0">
      <el-table :data="fundSourceLists" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'FundSourceListView', params: { fundSourceListId: scope.row.id } }">{{ scope.row.id }}</router-link>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="paymentid"
          :label="t$('jy1App.fundSourceList.paymentid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.paymentid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="contractcode"
          :label="t$('jy1App.fundSourceList.contractcode')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.contractcode }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="contractname"
          :label="t$('jy1App.fundSourceList.contractname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.contractname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="amount"
          :label="t$('jy1App.fundSourceList.amount')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.amount }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="transactionPayment.id"
          :label="t$('jy1App.fundSourceList.transactionPayment')"
        >
          <template #default="scope">
            <td>
              <div v-if="scope.row.transactionPayment">
                <router-link :to="{ name: 'TransactionPaymentView', params: { transactionPaymentId: scope.row.transactionPayment.id } }">{{
                  scope.row.transactionPayment.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="sporadicPurchasePayment.id"
          :label="t$('jy1App.fundSourceList.sporadicPurchasePayment')"
        >
          <template #default="scope">
            <td>
              <div v-if="scope.row.sporadicPurchasePayment">
                <router-link
                  :to="{ name: 'SporadicPurchasePaymentView', params: { sporadicPurchasePaymentId: scope.row.sporadicPurchasePayment.id } }"
                  >{{ scope.row.sporadicPurchasePayment.id }}</router-link
                >
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="sharePayment.id" :label="t$('jy1App.fundSourceList.sharePayment')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.sharePayment">
                <router-link :to="{ name: 'SharePaymentView', params: { sharePaymentId: scope.row.sharePayment.id } }">{{
                  scope.row.sharePayment.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="contractPayment.id"
          :label="t$('jy1App.fundSourceList.contractPayment')"
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
                <router-link :to="{ name: 'FundSourceListView', params: { fundSourceListId: scope.row.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'FundSourceListEdit', params: { fundSourceListId: scope.row.id } }" custom v-slot="{ navigate }">
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
      <!-- <table class="table table-striped" aria-describedby="fundSourceLists">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.fundSourceList.paymentid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.fundSourceList.contractcode')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.fundSourceList.contractname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.fundSourceList.amount')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.fundSourceList.transactionPayment')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.fundSourceList.sporadicPurchasePayment')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.fundSourceList.sharePayment')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.fundSourceList.contractPayment')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="fundSourceList in fundSourceLists"
                    :key="fundSourceList.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'FundSourceListView', params: {fundSourceListId: fundSourceList.id}}">{{fundSourceList.id}}</router-link>
                    </td>
                    <td>{{fundSourceList.paymentid}}</td>
                    <td>{{fundSourceList.contractcode}}</td>
                    <td>{{fundSourceList.contractname}}</td>
                    <td>{{fundSourceList.amount}}</td>
                    <td>
                        <div v-if="fundSourceList.transactionPayment">
                            <router-link :to="{name: 'TransactionPaymentView', params: {transactionPaymentId: fundSourceList.transactionPayment.id}}">{{fundSourceList.transactionPayment.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="fundSourceList.sporadicPurchasePayment">
                            <router-link :to="{name: 'SporadicPurchasePaymentView', params: {sporadicPurchasePaymentId: fundSourceList.sporadicPurchasePayment.id}}">{{fundSourceList.sporadicPurchasePayment.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="fundSourceList.sharePayment">
                            <router-link :to="{name: 'SharePaymentView', params: {sharePaymentId: fundSourceList.sharePayment.id}}">{{fundSourceList.sharePayment.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="fundSourceList.contractPayment">
                            <router-link :to="{name: 'ContractPaymentView', params: {contractPaymentId: fundSourceList.contractPayment.id}}">{{fundSourceList.contractPayment.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'FundSourceListView', params: {fundSourceListId: fundSourceList.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'FundSourceListEdit', params: {fundSourceListId: fundSourceList.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(fundSourceList)"
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
          id="jy1App.fundSourceList.delete.question"
          data-cy="fundSourceListDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-fundSourceList-heading" v-text="t$('jy1App.fundSourceList.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-fundSourceList"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeFundSourceList()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./fund-source-list.component.ts"></script>
