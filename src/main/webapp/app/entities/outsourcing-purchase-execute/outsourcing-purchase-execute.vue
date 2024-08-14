<template>
  <div>
    <h2 id="page-heading" data-cy="OutsourcingPurchaseExecuteHeading">
      <span v-text="t$('jy1App.outsourcingPurchaseExecute.home.title')" id="outsourcing-purchase-execute-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.outsourcingPurchaseExecute.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'OutsourcingPurchaseExecuteCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-outsourcing-purchase-execute"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.outsourcingPurchaseExecute.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && outsourcingPurchaseExecutes && outsourcingPurchaseExecutes.length === 0">
      <span v-text="t$('jy1App.outsourcingPurchaseExecute.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="outsourcingPurchaseExecutes && outsourcingPurchaseExecutes.length > 0">
      <el-table :data="outsourcingPurchaseExecutes" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'OutsourcingPurchaseExecuteView', params: { outsourcingPurchaseExecuteId: scope.row.id } }">{{
              scope.row.id
            }}</router-link>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="matarialname"
          :label="t$('jy1App.outsourcingPurchaseExecute.matarialname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.matarialname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="purchasingmethod"
          :label="t$('jy1App.outsourcingPurchaseExecute.purchasingmethod')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.purchasingmethod }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="budgit"
          :label="t$('jy1App.outsourcingPurchaseExecute.budgit')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.budgit }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="needtime"
          :label="t$('jy1App.outsourcingPurchaseExecute.needtime')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.needtime }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="planusetime"
          :label="t$('jy1App.outsourcingPurchaseExecute.planusetime')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.planusetime }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="supplierid"
          :label="t$('jy1App.outsourcingPurchaseExecute.supplierid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.supplierid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="price"
          :label="t$('jy1App.outsourcingPurchaseExecute.price')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.price }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="secretlevel"
          :label="t$('jy1App.outsourcingPurchaseExecute.secretlevel')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.Secretlevel.' + scope.row.secretlevel)"></span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="responsibleperson.id"
          :label="t$('jy1App.outsourcingPurchaseExecute.responsibleperson')"
        >
          <template #default="scope">
            <td>
              <div v-if="scope.row.responsibleperson">
                <router-link :to="{ name: 'OfficersView', params: { officersId: scope.row.responsibleperson.id } }">{{
                  scope.row.responsibleperson.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="outsourcingplanid.id"
          :label="t$('jy1App.outsourcingPurchaseExecute.outsourcingplanid')"
        >
          <template #default="scope">
            <td>
              <div v-if="scope.row.outsourcingplanid">
                <router-link
                  :to="{ name: 'OutsourcingPurchasePlanView', params: { outsourcingPurchasePlanId: scope.row.outsourcingplanid.id } }"
                  >{{ scope.row.outsourcingplanid.id }}</router-link
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
                  :to="{ name: 'OutsourcingPurchaseExecuteView', params: { outsourcingPurchaseExecuteId: scope.row.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'OutsourcingPurchaseExecuteEdit', params: { outsourcingPurchaseExecuteId: scope.row.id } }"
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
      <!-- <table class="table table-striped" aria-describedby="outsourcingPurchaseExecutes">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingPurchaseExecute.matarialname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingPurchaseExecute.purchasingmethod')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingPurchaseExecute.budgit')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingPurchaseExecute.needtime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingPurchaseExecute.planusetime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingPurchaseExecute.supplierid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingPurchaseExecute.price')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingPurchaseExecute.secretlevel')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingPurchaseExecute.responsibleperson')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingPurchaseExecute.outsourcingplanid')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="outsourcingPurchaseExecute in outsourcingPurchaseExecutes"
                    :key="outsourcingPurchaseExecute.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'OutsourcingPurchaseExecuteView', params: {outsourcingPurchaseExecuteId: outsourcingPurchaseExecute.id}}">{{outsourcingPurchaseExecute.id}}</router-link>
                    </td>
                    <td>{{outsourcingPurchaseExecute.matarialname}}</td>
                    <td>{{outsourcingPurchaseExecute.purchasingmethod}}</td>
                    <td>{{outsourcingPurchaseExecute.budgit}}</td>
                    <td>{{outsourcingPurchaseExecute.needtime}}</td>
                    <td>{{outsourcingPurchaseExecute.planusetime}}</td>
                    <td>{{outsourcingPurchaseExecute.supplierid}}</td>
                    <td>{{outsourcingPurchaseExecute.price}}</td>
                    <td v-text="t$('jy1App.Secretlevel.' + outsourcingPurchaseExecute.secretlevel)"></td>
                    <td>
                        <div v-if="outsourcingPurchaseExecute.responsibleperson">
                            <router-link :to="{name: 'OfficersView', params: {officersId: outsourcingPurchaseExecute.responsibleperson.id}}">{{outsourcingPurchaseExecute.responsibleperson.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="outsourcingPurchaseExecute.outsourcingplanid">
                            <router-link :to="{name: 'OutsourcingPurchasePlanView', params: {outsourcingPurchasePlanId: outsourcingPurchaseExecute.outsourcingplanid.id}}">{{outsourcingPurchaseExecute.outsourcingplanid.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'OutsourcingPurchaseExecuteView', params: {outsourcingPurchaseExecuteId: outsourcingPurchaseExecute.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'OutsourcingPurchaseExecuteEdit', params: {outsourcingPurchaseExecuteId: outsourcingPurchaseExecute.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(outsourcingPurchaseExecute)"
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
          id="jy1App.outsourcingPurchaseExecute.delete.question"
          data-cy="outsourcingPurchaseExecuteDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-outsourcingPurchaseExecute-heading"
          v-text="t$('jy1App.outsourcingPurchaseExecute.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-outsourcingPurchaseExecute"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeOutsourcingPurchaseExecute()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./outsourcing-purchase-execute.component.ts"></script>
