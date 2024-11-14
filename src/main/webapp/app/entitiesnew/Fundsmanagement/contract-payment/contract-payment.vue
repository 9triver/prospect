<template>
  <div>
    <h2 id="page-heading" data-cy="ContractPaymentHeading">
      <span v-text="t$('jy1App.contractPayment.home.title')" id="contract-payment-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.contractPayment.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'ContractPaymentCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-contract-payment"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.contractPayment.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && contractPayments && contractPayments.length === 0">
      <span v-text="t$('jy1App.contractPayment.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="contractPayments && contractPayments.length > 0">
      <el-table :data="contractPayments" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'ContractPaymentView', params: { contractPaymentId: scope.row.id } }">{{ scope.row.id }}</router-link>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="workbagid"
          :label="t$('jy1App.contractPayment.workbagid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.workbagid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="workbagname"
          :label="t$('jy1App.contractPayment.workbagname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.workbagname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="contractcode"
          :label="t$('jy1App.contractPayment.contractcode')"
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
          :label="t$('jy1App.contractPayment.contractname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.contractname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="planpaymentnode"
          :label="t$('jy1App.contractPayment.planpaymentnode')"
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
          :label="t$('jy1App.contractPayment.planpaymentamount')"
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
          :label="t$('jy1App.contractPayment.actualpaymentamount')"
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
          :label="t$('jy1App.contractPayment.paymenttype')"
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
          :label="t$('jy1App.contractPayment.financialvoucherid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.financialvoucherid }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="workbag.id" :label="t$('jy1App.contractPayment.workbag')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.workbag">
                <router-link :to="{ name: 'WorkbagView', params: { workbagId: scope.row.workbag.id } }">{{
                  scope.row.workbag.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="paymentApplication.id"
          :label="t$('jy1App.contractPayment.paymentApplication')"
        >
          <template #default="scope">
            <td>
              <div v-if="scope.row.paymentApplication">
                <router-link :to="{ name: 'PaymentApplicationView', params: { paymentApplicationId: scope.row.paymentApplication.id } }">{{
                  scope.row.paymentApplication.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="paymentCostList.id"
          :label="t$('jy1App.contractPayment.paymentCostList')"
        >
          <template #default="scope">
            <td>
              <span v-for="(paymentCostList, i) in scope.row.paymentCostLists" :key="paymentCostList.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{ name: 'PaymentCostListView', params: { paymentCostListId: paymentCostList.id } }"
                  >{{ paymentCostList.id }}</router-link
                >
              </span>
            </td>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="fundSourceList.id"
          :label="t$('jy1App.contractPayment.fundSourceList')"
        >
          <template #default="scope">
            <td>
              <span v-for="(fundSourceList, i) in scope.row.fundSourceLists" :key="fundSourceList.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{ name: 'FundSourceListView', params: { fundSourceListId: fundSourceList.id } }"
                  >{{ fundSourceList.id }}</router-link
                >
              </span>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'ContractPaymentView', params: { contractPaymentId: scope.row.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'ContractPaymentEdit', params: { contractPaymentId: scope.row.id } }"
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
      <!-- <table class="table table-striped" aria-describedby="contractPayments">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contractPayment.workbagid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contractPayment.workbagname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contractPayment.contractcode')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contractPayment.contractname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contractPayment.planpaymentnode')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contractPayment.planpaymentamount')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contractPayment.actualpaymentamount')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contractPayment.paymenttype')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contractPayment.financialvoucherid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contractPayment.workbag')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contractPayment.paymentApplication')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contractPayment.paymentCostList')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contractPayment.fundSourceList')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="contractPayment in contractPayments"
                    :key="contractPayment.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'ContractPaymentView', params: {contractPaymentId: contractPayment.id}}">{{contractPayment.id}}</router-link>
                    </td>
                    <td>{{contractPayment.workbagid}}</td>
                    <td>{{contractPayment.workbagname}}</td>
                    <td>{{contractPayment.contractcode}}</td>
                    <td>{{contractPayment.contractname}}</td>
                    <td>{{contractPayment.planpaymentnode}}</td>
                    <td>{{contractPayment.planpaymentamount}}</td>
                    <td>{{contractPayment.actualpaymentamount}}</td>
                    <td v-text="t$('jy1App.PaymentType.' + contractPayment.paymenttype)"></td>
                    <td>{{contractPayment.financialvoucherid}}</td>
                    <td>
                        <div v-if="contractPayment.workbag">
                            <router-link :to="{name: 'WorkbagView', params: {workbagId: contractPayment.workbag.id}}">{{contractPayment.workbag.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="contractPayment.paymentApplication">
                            <router-link :to="{name: 'PaymentApplicationView', params: {paymentApplicationId: contractPayment.paymentApplication.id}}">{{contractPayment.paymentApplication.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <span v-for="(paymentCostList, i) in contractPayment.paymentCostLists" :key="paymentCostList.id">{{i > 0 ? ', ' : ''}}
                            <router-link class="form-control-static" :to="{name: 'PaymentCostListView', params: {paymentCostListId: paymentCostList.id}}">{{paymentCostList.id}}</router-link>
                        </span>
                    </td>
                    <td>
                        <span v-for="(fundSourceList, i) in contractPayment.fundSourceLists" :key="fundSourceList.id">{{i > 0 ? ', ' : ''}}
                            <router-link class="form-control-static" :to="{name: 'FundSourceListView', params: {fundSourceListId: fundSourceList.id}}">{{fundSourceList.id}}</router-link>
                        </span>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'ContractPaymentView', params: {contractPaymentId: contractPayment.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'ContractPaymentEdit', params: {contractPaymentId: contractPayment.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(contractPayment)"
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
          id="jy1App.contractPayment.delete.question"
          data-cy="contractPaymentDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-contractPayment-heading" v-text="t$('jy1App.contractPayment.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-contractPayment"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeContractPayment()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./contract-payment.component.ts"></script>
