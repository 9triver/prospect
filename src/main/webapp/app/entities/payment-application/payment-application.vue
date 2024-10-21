<template>
  <div>
    <h2 id="page-heading" data-cy="PaymentApplicationHeading">
      <span v-text="t$('jy1App.paymentApplication.home.title')" id="payment-application-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.paymentApplication.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'PaymentApplicationCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-payment-application"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.paymentApplication.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && paymentApplications && paymentApplications.length === 0">
      <span v-text="t$('jy1App.paymentApplication.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="paymentApplications && paymentApplications.length > 0">
      <el-table :data="paymentApplications" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'PaymentApplicationView', params: { paymentApplicationId: scope.row.id } }">{{
              scope.row.id
            }}</router-link>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="workbagid"
          :label="t$('jy1App.paymentApplication.workbagid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.workbagid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="contractcode"
          :label="t$('jy1App.paymentApplication.contractcode')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.contractcode }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="planpaymentnode"
          :label="t$('jy1App.paymentApplication.planpaymentnode')"
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
          :label="t$('jy1App.paymentApplication.planpaymentamount')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.planpaymentamount }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="outsourcingContract.id"
          :label="t$('jy1App.paymentApplication.outsourcingContract')"
        >
          <template #default="scope">
            <td>
              <div v-if="scope.row.outsourcingContract">
                <router-link
                  :to="{ name: 'OutsourcingContractView', params: { outsourcingContractId: scope.row.outsourcingContract.id } }"
                  >{{ scope.row.outsourcingContract.id }}</router-link
                >
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
      <!-- <table class="table table-striped" aria-describedby="paymentApplications">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.paymentApplication.workbagid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.paymentApplication.contractcode')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.paymentApplication.planpaymentnode')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.paymentApplication.planpaymentamount')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.paymentApplication.outsourcingContract')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="paymentApplication in paymentApplications"
                    :key="paymentApplication.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'PaymentApplicationView', params: {paymentApplicationId: paymentApplication.id}}">{{paymentApplication.id}}</router-link>
                    </td>
                    <td>{{paymentApplication.workbagid}}</td>
                    <td>{{paymentApplication.contractcode}}</td>
                    <td>{{paymentApplication.planpaymentnode}}</td>
                    <td>{{paymentApplication.planpaymentamount}}</td>
                    <td>
                        <div v-if="paymentApplication.outsourcingContract">
                            <router-link :to="{name: 'OutsourcingContractView', params: {outsourcingContractId: paymentApplication.outsourcingContract.id}}">{{paymentApplication.outsourcingContract.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'PaymentApplicationView', params: {paymentApplicationId: paymentApplication.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'PaymentApplicationEdit', params: {paymentApplicationId: paymentApplication.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(paymentApplication)"
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
