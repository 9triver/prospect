<template>
  <div>
    <h2 id="page-heading" data-cy="OutsourcingContractHeading">
      <span v-text="t$('jy1App.outsourcingContract.home.title')" id="outsourcing-contract-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.outsourcingContract.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'OutsourcingContractCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-outsourcing-contract"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.outsourcingContract.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && outsourcingContracts && outsourcingContracts.length === 0">
      <span v-text="t$('jy1App.outsourcingContract.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="outsourcingContracts && outsourcingContracts.length > 0">
      <el-table :data="outsourcingContracts" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'OutsourcingContractView', params: { outsourcingContractId: scope.row.id } }">{{
              scope.row.id
            }}</router-link>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="contractid"
          :label="t$('jy1App.outsourcingContract.contractid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.contractid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="contractcode"
          :label="t$('jy1App.outsourcingContract.contractcode')"
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
          :label="t$('jy1App.outsourcingContract.contractname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.contractname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="contractqualityid"
          :label="t$('jy1App.outsourcingContract.contractqualityid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.contractqualityid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="contractcostid"
          :label="t$('jy1App.outsourcingContract.contractcostid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.contractcostid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="contractfinanceid"
          :label="t$('jy1App.outsourcingContract.contractfinanceid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.contractfinanceid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="projectid"
          :label="t$('jy1App.outsourcingContract.projectid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.projectid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="projectsecretlevel"
          :label="t$('jy1App.outsourcingContract.projectsecretlevel')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.projectsecretlevel }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="counterpartyunit"
          :label="t$('jy1App.outsourcingContract.counterpartyunit')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.counterpartyunit }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="negotiationdate"
          :label="t$('jy1App.outsourcingContract.negotiationdate')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.negotiationdate }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="negotiationlocation"
          :label="t$('jy1App.outsourcingContract.negotiationlocation')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.negotiationlocation }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="negotiator"
          :label="t$('jy1App.outsourcingContract.negotiator')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.negotiator }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="budgetamount"
          :label="t$('jy1App.outsourcingContract.budgetamount')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.budgetamount }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="contractamount"
          :label="t$('jy1App.outsourcingContract.contractamount')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.contractamount }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="approver"
          :label="t$('jy1App.outsourcingContract.approver')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.approver }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="approvaldate"
          :label="t$('jy1App.outsourcingContract.approvaldate')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.approvaldate }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="contractsecretlevel"
          :label="t$('jy1App.outsourcingContract.contractsecretlevel')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.contractsecretlevel }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="deliverycontent"
          :label="t$('jy1App.outsourcingContract.deliverycontent')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.deliverycontent }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="warrantyrequirement"
          :label="t$('jy1App.outsourcingContract.warrantyrequirement')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.warrantyrequirement }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="purchaseplanno"
          :label="t$('jy1App.outsourcingContract.purchaseplanno')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.purchaseplanno }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="purchaseplandate"
          :label="t$('jy1App.outsourcingContract.purchaseplandate')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.purchaseplandate }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="purchaseplanamount"
          :label="t$('jy1App.outsourcingContract.purchaseplanamount')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.purchaseplanamount }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="purchasemethod"
          :label="t$('jy1App.outsourcingContract.purchasemethod')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.purchasemethod }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="purchasesecretlevel"
          :label="t$('jy1App.outsourcingContract.purchasesecretlevel')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.purchasesecretlevel }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="reviewmethod"
          :label="t$('jy1App.outsourcingContract.reviewmethod')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.reviewmethod }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="requirementdepartment"
          :label="t$('jy1App.outsourcingContract.requirementdepartment')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.requirementdepartment }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="requirementperson"
          :label="t$('jy1App.outsourcingContract.requirementperson')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.requirementperson }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="undertaker"
          :label="t$('jy1App.outsourcingContract.undertaker')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.undertaker }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="undertakingdepartment"
          :label="t$('jy1App.outsourcingContract.undertakingdepartment')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.undertakingdepartment }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="workbagid"
          :label="t$('jy1App.outsourcingContract.workbagid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.workbagid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="projectmanager"
          :label="t$('jy1App.outsourcingContract.projectmanager')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.projectmanager }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="fundsource"
          :label="t$('jy1App.outsourcingContract.fundsource')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.fundsource }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="thesisname"
          :label="t$('jy1App.outsourcingContract.thesisname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.thesisname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="contractauxiliaryno"
          :label="t$('jy1App.outsourcingContract.contractauxiliaryno')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.contractauxiliaryno }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="reasonfornosuppliers"
          :label="t$('jy1App.outsourcingContract.reasonfornosuppliers')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.reasonfornosuppliers }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="reasonforchange"
          :label="t$('jy1App.outsourcingContract.reasonforchange')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.reasonforchange }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="negotiationfiletime"
          :label="t$('jy1App.outsourcingContract.negotiationfiletime')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.negotiationfiletime }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="bidopeningtime"
          :label="t$('jy1App.outsourcingContract.bidopeningtime')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.bidopeningtime }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="judges"
          :label="t$('jy1App.outsourcingContract.judges')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.judges }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="responsevendorname"
          :label="t$('jy1App.outsourcingContract.responsevendorname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.responsevendorname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="finalquoteandscore"
          :label="t$('jy1App.outsourcingContract.finalquoteandscore')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.finalquoteandscore }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="noticeofcompletiontime"
          :label="t$('jy1App.outsourcingContract.noticeofcompletiontime')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.noticeofcompletiontime }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="signingdate"
          :label="t$('jy1App.outsourcingContract.signingdate')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.signingdate }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="contractenddate"
          :label="t$('jy1App.outsourcingContract.contractenddate')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.contractenddate }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="actualcompletiontime"
          :label="t$('jy1App.outsourcingContract.actualcompletiontime')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.actualcompletiontime }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="issubmitsecrecyagreement"
          :label="t$('jy1App.outsourcingContract.issubmitsecrecyagreement')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.issubmitsecrecyagreement }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="issubmitsecurityagreement"
          :label="t$('jy1App.outsourcingContract.issubmitsecurityagreement')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.issubmitsecurityagreement }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="remark"
          :label="t$('jy1App.outsourcingContract.remark')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.remark }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="workbag.id" :label="t$('jy1App.outsourcingContract.workbag')">
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
        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'OutsourcingContractView', params: { outsourcingContractId: scope.row.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'OutsourcingContractEdit', params: { outsourcingContractId: scope.row.id } }"
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
      <!-- <table class="table table-striped" aria-describedby="outsourcingContracts">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.contractid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.contractcode')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.contractname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.contractqualityid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.contractcostid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.contractfinanceid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.projectid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.projectsecretlevel')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.counterpartyunit')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.negotiationdate')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.negotiationlocation')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.negotiator')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.budgetamount')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.contractamount')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.approver')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.approvaldate')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.contractsecretlevel')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.deliverycontent')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.warrantyrequirement')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.purchaseplanno')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.purchaseplandate')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.purchaseplanamount')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.purchasemethod')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.purchasesecretlevel')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.reviewmethod')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.requirementdepartment')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.requirementperson')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.undertaker')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.undertakingdepartment')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.workbagid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.projectmanager')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.fundsource')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.thesisname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.contractauxiliaryno')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.reasonfornosuppliers')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.reasonforchange')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.negotiationfiletime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.bidopeningtime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.judges')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.responsevendorname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.finalquoteandscore')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.noticeofcompletiontime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.signingdate')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.contractenddate')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.actualcompletiontime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.issubmitsecrecyagreement')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.issubmitsecurityagreement')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.remark')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContract.workbag')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="outsourcingContract in outsourcingContracts"
                    :key="outsourcingContract.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'OutsourcingContractView', params: {outsourcingContractId: outsourcingContract.id}}">{{outsourcingContract.id}}</router-link>
                    </td>
                    <td>{{outsourcingContract.contractid}}</td>
                    <td>{{outsourcingContract.contractcode}}</td>
                    <td>{{outsourcingContract.contractname}}</td>
                    <td>{{outsourcingContract.contractqualityid}}</td>
                    <td>{{outsourcingContract.contractcostid}}</td>
                    <td>{{outsourcingContract.contractfinanceid}}</td>
                    <td>{{outsourcingContract.projectid}}</td>
                    <td>{{outsourcingContract.projectsecretlevel}}</td>
                    <td>{{outsourcingContract.counterpartyunit}}</td>
                    <td>{{outsourcingContract.negotiationdate}}</td>
                    <td>{{outsourcingContract.negotiationlocation}}</td>
                    <td>{{outsourcingContract.negotiator}}</td>
                    <td>{{outsourcingContract.budgetamount}}</td>
                    <td>{{outsourcingContract.contractamount}}</td>
                    <td>{{outsourcingContract.approver}}</td>
                    <td>{{outsourcingContract.approvaldate}}</td>
                    <td>{{outsourcingContract.contractsecretlevel}}</td>
                    <td>{{outsourcingContract.deliverycontent}}</td>
                    <td>{{outsourcingContract.warrantyrequirement}}</td>
                    <td>{{outsourcingContract.purchaseplanno}}</td>
                    <td>{{outsourcingContract.purchaseplandate}}</td>
                    <td>{{outsourcingContract.purchaseplanamount}}</td>
                    <td>{{outsourcingContract.purchasemethod}}</td>
                    <td>{{outsourcingContract.purchasesecretlevel}}</td>
                    <td>{{outsourcingContract.reviewmethod}}</td>
                    <td>{{outsourcingContract.requirementdepartment}}</td>
                    <td>{{outsourcingContract.requirementperson}}</td>
                    <td>{{outsourcingContract.undertaker}}</td>
                    <td>{{outsourcingContract.undertakingdepartment}}</td>
                    <td>{{outsourcingContract.workbagid}}</td>
                    <td>{{outsourcingContract.projectmanager}}</td>
                    <td>{{outsourcingContract.fundsource}}</td>
                    <td>{{outsourcingContract.thesisname}}</td>
                    <td>{{outsourcingContract.contractauxiliaryno}}</td>
                    <td>{{outsourcingContract.reasonfornosuppliers}}</td>
                    <td>{{outsourcingContract.reasonforchange}}</td>
                    <td>{{outsourcingContract.negotiationfiletime}}</td>
                    <td>{{outsourcingContract.bidopeningtime}}</td>
                    <td>{{outsourcingContract.judges}}</td>
                    <td>{{outsourcingContract.responsevendorname}}</td>
                    <td>{{outsourcingContract.finalquoteandscore}}</td>
                    <td>{{outsourcingContract.noticeofcompletiontime}}</td>
                    <td>{{outsourcingContract.signingdate}}</td>
                    <td>{{outsourcingContract.contractenddate}}</td>
                    <td>{{outsourcingContract.actualcompletiontime}}</td>
                    <td>{{outsourcingContract.issubmitsecrecyagreement}}</td>
                    <td>{{outsourcingContract.issubmitsecurityagreement}}</td>
                    <td>{{outsourcingContract.remark}}</td>
                    <td>
                        <div v-if="outsourcingContract.workbag">
                            <router-link :to="{name: 'WorkbagView', params: {workbagId: outsourcingContract.workbag.id}}">{{outsourcingContract.workbag.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'OutsourcingContractView', params: {outsourcingContractId: outsourcingContract.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'OutsourcingContractEdit', params: {outsourcingContractId: outsourcingContract.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(outsourcingContract)"
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
          id="jy1App.outsourcingContract.delete.question"
          data-cy="outsourcingContractDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-outsourcingContract-heading" v-text="t$('jy1App.outsourcingContract.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-outsourcingContract"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeOutsourcingContract()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./outsourcing-contract.component.ts"></script>
