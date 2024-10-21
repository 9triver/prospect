<template>
  <div>
    <h2 id="page-heading" data-cy="MilestoneNodeHeading">
      <span v-text="t$('jy1App.milestoneNode.home.title')" id="milestone-node-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.milestoneNode.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'MilestoneNodeCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-milestone-node"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.milestoneNode.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && milestoneNodes && milestoneNodes.length === 0">
      <span v-text="t$('jy1App.milestoneNode.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="milestoneNodes && milestoneNodes.length > 0">
      <el-table :data="milestoneNodes" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'MilestoneNodeView', params: { milestoneNodeId: scope.row.id } }">{{ scope.row.id }}</router-link>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="name" :label="t$('jy1App.milestoneNode.name')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="planpaymenttime"
          :label="t$('jy1App.milestoneNode.planpaymenttime')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.planpaymenttime }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="planpaymentamount"
          :label="t$('jy1App.milestoneNode.planpaymentamount')"
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
          :label="t$('jy1App.milestoneNode.outsourcingContract')"
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
                <router-link :to="{ name: 'MilestoneNodeView', params: { milestoneNodeId: scope.row.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'MilestoneNodeEdit', params: { milestoneNodeId: scope.row.id } }" custom v-slot="{ navigate }">
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
      <!-- <table class="table table-striped" aria-describedby="milestoneNodes">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.milestoneNode.name')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.milestoneNode.planpaymenttime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.milestoneNode.planpaymentamount')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.milestoneNode.outsourcingContract')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="milestoneNode in milestoneNodes"
                    :key="milestoneNode.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'MilestoneNodeView', params: {milestoneNodeId: milestoneNode.id}}">{{milestoneNode.id}}</router-link>
                    </td>
                    <td>{{milestoneNode.name}}</td>
                    <td>{{milestoneNode.planpaymenttime}}</td>
                    <td>{{milestoneNode.planpaymentamount}}</td>
                    <td>
                        <div v-if="milestoneNode.outsourcingContract">
                            <router-link :to="{name: 'OutsourcingContractView', params: {outsourcingContractId: milestoneNode.outsourcingContract.id}}">{{milestoneNode.outsourcingContract.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'MilestoneNodeView', params: {milestoneNodeId: milestoneNode.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'MilestoneNodeEdit', params: {milestoneNodeId: milestoneNode.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(milestoneNode)"
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
          id="jy1App.milestoneNode.delete.question"
          data-cy="milestoneNodeDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-milestoneNode-heading" v-text="t$('jy1App.milestoneNode.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-milestoneNode"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeMilestoneNode()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./milestone-node.component.ts"></script>
