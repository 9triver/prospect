<template>
  <div>
    <h2 id="page-heading" data-cy="ContractHeading">
      <span v-text="t$('jy1App.contract.home.title')" id="contract-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.contract.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'ContractCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-contract"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.contract.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && contracts && contracts.length === 0">
      <span v-text="t$('jy1App.contract.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="contracts && contracts.length > 0">
      <el-table :data="contracts" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'ContractView', params: { contractId: scope.row.id } }">{{ scope.row.id }}</router-link>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="contractcode"
          :label="t$('jy1App.contract.contractcode')"
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
          :label="t$('jy1App.contract.contractname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.contractname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="projectid"
          :label="t$('jy1App.contract.projectid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.projectid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="projectname"
          :label="t$('jy1App.contract.projectname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.projectname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="contracttype"
          :label="t$('jy1App.contract.contracttype')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.ContractType.' + scope.row.contracttype)"></span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="year" :label="t$('jy1App.contract.year')" :sortable="true">
          <template #default="scope">
            <span class="field-default">{{ scope.row.year }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="amount" :label="t$('jy1App.contract.amount')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.amount }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="starttime" :label="t$('jy1App.contract.starttime')" :sortable="true">
          <template #default="scope">
            <span class="field-default">{{ scope.row.starttime }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="endtime" :label="t$('jy1App.contract.endtime')" :sortable="true">
          <template #default="scope">
            <span class="field-default">{{ scope.row.endtime }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="secretlevel"
          :label="t$('jy1App.contract.secretlevel')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.Secretlevel.' + scope.row.secretlevel)"></span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="status" :label="t$('jy1App.contract.status')" :sortable="false">
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.ContractStatus.' + scope.row.status)"></span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="budgetamount"
          :label="t$('jy1App.contract.budgetamount')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.budgetamount }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="estimatedamount"
          :label="t$('jy1App.contract.estimatedamount')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.estimatedamount }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="implementedamount"
          :label="t$('jy1App.contract.implementedamount')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.implementedamount }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="difference"
          :label="t$('jy1App.contract.difference')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.difference }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="costControlSystem.id"
          :label="t$('jy1App.contract.costControlSystem')"
        >
          <template #default="scope">
            <td>
              <span v-for="(costControlSystem, i) in scope.row.costControlSystems" :key="costControlSystem.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{ name: 'CostControlSystemView', params: { costControlSystemId: costControlSystem.id } }"
                  >{{ costControlSystem.id }}</router-link
                >
              </span>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ContractView', params: { contractId: scope.row.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ContractEdit', params: { contractId: scope.row.id } }" custom v-slot="{ navigate }">
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
      <!-- <table class="table table-striped" aria-describedby="contracts">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contract.contractcode')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contract.contractname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contract.projectid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contract.projectname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contract.contracttype')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contract.year')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contract.amount')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contract.starttime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contract.endtime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contract.secretlevel')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contract.status')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contract.budgetamount')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contract.estimatedamount')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contract.implementedamount')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contract.difference')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.contract.costControlSystem')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="contract in contracts"
                    :key="contract.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'ContractView', params: {contractId: contract.id}}">{{contract.id}}</router-link>
                    </td>
                    <td>{{contract.contractcode}}</td>
                    <td>{{contract.contractname}}</td>
                    <td>{{contract.projectid}}</td>
                    <td>{{contract.projectname}}</td>
                    <td v-text="t$('jy1App.ContractType.' + contract.contracttype)"></td>
                    <td>{{contract.year}}</td>
                    <td>{{contract.amount}}</td>
                    <td>{{contract.starttime}}</td>
                    <td>{{contract.endtime}}</td>
                    <td v-text="t$('jy1App.Secretlevel.' + contract.secretlevel)"></td>
                    <td v-text="t$('jy1App.ContractStatus.' + contract.status)"></td>
                    <td>{{contract.budgetamount}}</td>
                    <td>{{contract.estimatedamount}}</td>
                    <td>{{contract.implementedamount}}</td>
                    <td>{{contract.difference}}</td>
                    <td>
                        <span v-for="(costControlSystem, i) in contract.costControlSystems" :key="costControlSystem.id">{{i > 0 ? ', ' : ''}}
                            <router-link class="form-control-static" :to="{name: 'CostControlSystemView', params: {costControlSystemId: costControlSystem.id}}">{{costControlSystem.id}}</router-link>
                        </span>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'ContractView', params: {contractId: contract.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'ContractEdit', params: {contractId: contract.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(contract)"
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
        <span id="jy1App.contract.delete.question" data-cy="contractDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-contract-heading" v-text="t$('jy1App.contract.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-contract"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeContract()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./contract.component.ts"></script>
