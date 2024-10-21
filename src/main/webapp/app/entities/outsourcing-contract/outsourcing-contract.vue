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
