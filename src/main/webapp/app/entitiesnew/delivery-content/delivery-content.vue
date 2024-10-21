<template>
  <div>
    <h2 id="page-heading" data-cy="DeliveryContentHeading">
      <span v-text="t$('jy1App.deliveryContent.home.title')" id="delivery-content-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.deliveryContent.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'DeliveryContentCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-delivery-content"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.deliveryContent.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && deliveryContents && deliveryContents.length === 0">
      <span v-text="t$('jy1App.deliveryContent.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="deliveryContents && deliveryContents.length > 0">
      <el-table :data="deliveryContents" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'DeliveryContentView', params: { deliveryContentId: scope.row.id } }">{{ scope.row.id }}</router-link>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="warrantyrequirement"
          :label="t$('jy1App.deliveryContent.warrantyrequirement')"
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
          :label="t$('jy1App.deliveryContent.purchaseplanno')"
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
          :label="t$('jy1App.deliveryContent.purchaseplandate')"
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
          :label="t$('jy1App.deliveryContent.purchaseplanamount')"
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
          :label="t$('jy1App.deliveryContent.purchasemethod')"
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
          :label="t$('jy1App.deliveryContent.purchasesecretlevel')"
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
          :label="t$('jy1App.deliveryContent.reviewmethod')"
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
          :label="t$('jy1App.deliveryContent.requirementdepartment')"
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
          :label="t$('jy1App.deliveryContent.requirementperson')"
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
          :label="t$('jy1App.deliveryContent.undertaker')"
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
          :label="t$('jy1App.deliveryContent.undertakingdepartment')"
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
          :label="t$('jy1App.deliveryContent.workbagid')"
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
          :label="t$('jy1App.deliveryContent.projectmanager')"
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
          :label="t$('jy1App.deliveryContent.fundsource')"
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
          :label="t$('jy1App.deliveryContent.thesisname')"
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
          :label="t$('jy1App.deliveryContent.contractauxiliaryno')"
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
          :label="t$('jy1App.deliveryContent.reasonfornosuppliers')"
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
          :label="t$('jy1App.deliveryContent.reasonforchange')"
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
          :label="t$('jy1App.deliveryContent.negotiationfiletime')"
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
          :label="t$('jy1App.deliveryContent.bidopeningtime')"
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
          :label="t$('jy1App.deliveryContent.judges')"
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
          :label="t$('jy1App.deliveryContent.responsevendorname')"
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
          :label="t$('jy1App.deliveryContent.finalquoteandscore')"
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
          :label="t$('jy1App.deliveryContent.noticeofcompletiontime')"
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
          :label="t$('jy1App.deliveryContent.signingdate')"
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
          :label="t$('jy1App.deliveryContent.contractenddate')"
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
          :label="t$('jy1App.deliveryContent.actualcompletiontime')"
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
          :label="t$('jy1App.deliveryContent.issubmitsecrecyagreement')"
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
          :label="t$('jy1App.deliveryContent.issubmitsecurityagreement')"
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
          :label="t$('jy1App.deliveryContent.remark')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.remark }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="outsourcingContract.id"
          :label="t$('jy1App.deliveryContent.outsourcingContract')"
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
                  :to="{ name: 'DeliveryContentView', params: { deliveryContentId: scope.row.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'DeliveryContentEdit', params: { deliveryContentId: scope.row.id } }"
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
      <!-- <table class="table table-striped" aria-describedby="deliveryContents">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliveryContent.warrantyrequirement')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliveryContent.purchaseplanno')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliveryContent.purchaseplandate')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliveryContent.purchaseplanamount')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliveryContent.purchasemethod')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliveryContent.purchasesecretlevel')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliveryContent.reviewmethod')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliveryContent.requirementdepartment')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliveryContent.requirementperson')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliveryContent.undertaker')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliveryContent.undertakingdepartment')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliveryContent.workbagid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliveryContent.projectmanager')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliveryContent.fundsource')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliveryContent.thesisname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliveryContent.contractauxiliaryno')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliveryContent.reasonfornosuppliers')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliveryContent.reasonforchange')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliveryContent.negotiationfiletime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliveryContent.bidopeningtime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliveryContent.judges')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliveryContent.responsevendorname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliveryContent.finalquoteandscore')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliveryContent.noticeofcompletiontime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliveryContent.signingdate')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliveryContent.contractenddate')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliveryContent.actualcompletiontime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliveryContent.issubmitsecrecyagreement')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliveryContent.issubmitsecurityagreement')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliveryContent.remark')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliveryContent.outsourcingContract')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="deliveryContent in deliveryContents"
                    :key="deliveryContent.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'DeliveryContentView', params: {deliveryContentId: deliveryContent.id}}">{{deliveryContent.id}}</router-link>
                    </td>
                    <td>{{deliveryContent.warrantyrequirement}}</td>
                    <td>{{deliveryContent.purchaseplanno}}</td>
                    <td>{{deliveryContent.purchaseplandate}}</td>
                    <td>{{deliveryContent.purchaseplanamount}}</td>
                    <td>{{deliveryContent.purchasemethod}}</td>
                    <td>{{deliveryContent.purchasesecretlevel}}</td>
                    <td>{{deliveryContent.reviewmethod}}</td>
                    <td>{{deliveryContent.requirementdepartment}}</td>
                    <td>{{deliveryContent.requirementperson}}</td>
                    <td>{{deliveryContent.undertaker}}</td>
                    <td>{{deliveryContent.undertakingdepartment}}</td>
                    <td>{{deliveryContent.workbagid}}</td>
                    <td>{{deliveryContent.projectmanager}}</td>
                    <td>{{deliveryContent.fundsource}}</td>
                    <td>{{deliveryContent.thesisname}}</td>
                    <td>{{deliveryContent.contractauxiliaryno}}</td>
                    <td>{{deliveryContent.reasonfornosuppliers}}</td>
                    <td>{{deliveryContent.reasonforchange}}</td>
                    <td>{{deliveryContent.negotiationfiletime}}</td>
                    <td>{{deliveryContent.bidopeningtime}}</td>
                    <td>{{deliveryContent.judges}}</td>
                    <td>{{deliveryContent.responsevendorname}}</td>
                    <td>{{deliveryContent.finalquoteandscore}}</td>
                    <td>{{deliveryContent.noticeofcompletiontime}}</td>
                    <td>{{deliveryContent.signingdate}}</td>
                    <td>{{deliveryContent.contractenddate}}</td>
                    <td>{{deliveryContent.actualcompletiontime}}</td>
                    <td>{{deliveryContent.issubmitsecrecyagreement}}</td>
                    <td>{{deliveryContent.issubmitsecurityagreement}}</td>
                    <td>{{deliveryContent.remark}}</td>
                    <td>
                        <div v-if="deliveryContent.outsourcingContract">
                            <router-link :to="{name: 'OutsourcingContractView', params: {outsourcingContractId: deliveryContent.outsourcingContract.id}}">{{deliveryContent.outsourcingContract.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'DeliveryContentView', params: {deliveryContentId: deliveryContent.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'DeliveryContentEdit', params: {deliveryContentId: deliveryContent.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(deliveryContent)"
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
          id="jy1App.deliveryContent.delete.question"
          data-cy="deliveryContentDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-deliveryContent-heading" v-text="t$('jy1App.deliveryContent.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-deliveryContent"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeDeliveryContent()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./delivery-content.component.ts"></script>
