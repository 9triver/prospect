<template>
  <div>
    <h2 id="page-heading" data-cy="DeliverablesHeading">
      <span v-text="t$('jy1App.deliverables.home.title')" id="deliverables-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.deliverables.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'DeliverablesCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-deliverables"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.deliverables.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && deliverables && deliverables.length === 0">
      <span v-text="t$('jy1App.deliverables.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="deliverables && deliverables.length > 0">
      <el-table :data="deliverables" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'DeliverablesView', params: { deliverablesId: scope.row.id } }">{{ scope.row.id }}</router-link>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="code" :label="t$('jy1App.deliverables.code')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.code }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="name" :label="t$('jy1App.deliverables.name')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="parentcode"
          :label="t$('jy1App.deliverables.parentcode')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.parentcode }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="level" :label="t$('jy1App.deliverables.level')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.level }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="status" :label="t$('jy1App.deliverables.status')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.status }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="description"
          :label="t$('jy1App.deliverables.description')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.description }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'DeliverablesView', params: { deliverablesId: scope.row.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'DeliverablesEdit', params: { deliverablesId: scope.row.id } }" custom v-slot="{ navigate }">
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
      <!-- <table class="table table-striped" aria-describedby="deliverables">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliverables.code')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliverables.name')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliverables.parentcode')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliverables.level')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliverables.status')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.deliverables.description')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="deliverables in deliverables"
                    :key="deliverables.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'DeliverablesView', params: {deliverablesId: deliverables.id}}">{{deliverables.id}}</router-link>
                    </td>
                    <td>{{deliverables.code}}</td>
                    <td>{{deliverables.name}}</td>
                    <td>{{deliverables.parentcode}}</td>
                    <td>{{deliverables.level}}</td>
                    <td>{{deliverables.status}}</td>
                    <td>{{deliverables.description}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'DeliverablesView', params: {deliverablesId: deliverables.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'DeliverablesEdit', params: {deliverablesId: deliverables.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(deliverables)"
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
        <span id="jy1App.deliverables.delete.question" data-cy="deliverablesDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-deliverables-heading" v-text="t$('jy1App.deliverables.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-deliverables"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeDeliverables()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./deliverables.component.ts"></script>
