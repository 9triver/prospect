<template>
  <div>
    <h2 id="page-heading" data-cy="OtherPaymentHeading">
      <span v-text="t$('jy1App.otherPayment.home.title')" id="other-payment-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.otherPayment.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'OtherPaymentCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-other-payment"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.otherPayment.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && otherPayments && otherPayments.length === 0">
      <span v-text="t$('jy1App.otherPayment.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="otherPayments && otherPayments.length > 0">
      <el-table :data="otherPayments" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'OtherPaymentView', params: { otherPaymentId: scope.row.id } }">{{ scope.row.id }}</router-link>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="name" :label="t$('jy1App.otherPayment.name')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="type" :label="t$('jy1App.otherPayment.type')" :sortable="false">
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.OtherPaymenttype.' + scope.row.type)"></span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="registertime"
          :label="t$('jy1App.otherPayment.registertime')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.registertime }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="subjectid"
          :label="t$('jy1App.otherPayment.subjectid')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.subjectid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="subjectname"
          :label="t$('jy1App.otherPayment.subjectname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.subjectname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="paymentamount"
          :label="t$('jy1App.otherPayment.paymentamount')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.paymentamount }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="contractcode"
          :label="t$('jy1App.otherPayment.contractcode')"
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
          :label="t$('jy1App.otherPayment.contractname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.contractname }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="wbsid" :label="t$('jy1App.otherPayment.wbsid')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.wbsid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="wbsname"
          :label="t$('jy1App.otherPayment.wbsname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.wbsname }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="projectwbs.id" :label="t$('jy1App.otherPayment.projectwbs')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.projectwbs">
                <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: scope.row.projectwbs.id } }">{{
                  scope.row.projectwbs.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="contract.id" :label="t$('jy1App.otherPayment.contract')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.contract">
                <router-link :to="{ name: 'ContractView', params: { contractId: scope.row.contract.id } }">{{
                  scope.row.contract.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="subject.id" :label="t$('jy1App.otherPayment.subject')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.subject">
                <router-link :to="{ name: 'SubjectView', params: { subjectId: scope.row.subject.id } }">{{
                  scope.row.subject.id
                }}</router-link>
              </div>
            </td>
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
      <!-- <table class="table table-striped" aria-describedby="otherPayments">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.otherPayment.name')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.otherPayment.type')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.otherPayment.registertime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.otherPayment.subjectid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.otherPayment.subjectname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.otherPayment.paymentamount')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.otherPayment.contractcode')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.otherPayment.contractname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.otherPayment.wbsid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.otherPayment.wbsname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.otherPayment.projectwbs')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.otherPayment.contract')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.otherPayment.subject')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="otherPayment in otherPayments"
                    :key="otherPayment.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'OtherPaymentView', params: {otherPaymentId: otherPayment.id}}">{{otherPayment.id}}</router-link>
                    </td>
                    <td>{{otherPayment.name}}</td>
                    <td v-text="t$('jy1App.OtherPaymenttype.' + otherPayment.type)"></td>
                    <td>{{otherPayment.registertime}}</td>
                    <td>{{otherPayment.subjectid}}</td>
                    <td>{{otherPayment.subjectname}}</td>
                    <td>{{otherPayment.paymentamount}}</td>
                    <td>{{otherPayment.contractcode}}</td>
                    <td>{{otherPayment.contractname}}</td>
                    <td>{{otherPayment.wbsid}}</td>
                    <td>{{otherPayment.wbsname}}</td>
                    <td>
                        <div v-if="otherPayment.projectwbs">
                            <router-link :to="{name: 'ProjectwbsView', params: {projectwbsId: otherPayment.projectwbs.id}}">{{otherPayment.projectwbs.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="otherPayment.contract">
                            <router-link :to="{name: 'ContractView', params: {contractId: otherPayment.contract.id}}">{{otherPayment.contract.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="otherPayment.subject">
                            <router-link :to="{name: 'SubjectView', params: {subjectId: otherPayment.subject.id}}">{{otherPayment.subject.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'OtherPaymentView', params: {otherPaymentId: otherPayment.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'OtherPaymentEdit', params: {otherPaymentId: otherPayment.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(otherPayment)"
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
