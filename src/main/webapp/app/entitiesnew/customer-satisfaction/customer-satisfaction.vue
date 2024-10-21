<template>
  <div>
    <h2 id="page-heading" data-cy="CustomerSatisfactionHeading">
      <span v-text="t$('jy1App.customerSatisfaction.home.title')" id="customer-satisfaction-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.customerSatisfaction.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'CustomerSatisfactionCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-customer-satisfaction"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.customerSatisfaction.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && customerSatisfactions && customerSatisfactions.length === 0">
      <span v-text="t$('jy1App.customerSatisfaction.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="customerSatisfactions && customerSatisfactions.length > 0">
      <el-table :data="customerSatisfactions" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'CustomerSatisfactionView', params: { customerSatisfactionId: scope.row.id } }">{{
              scope.row.id
            }}</router-link>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="year"
          :label="t$('jy1App.customerSatisfaction.year')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.year }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="satisfactionitem"
          :label="t$('jy1App.customerSatisfaction.satisfactionitem')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.satisfactionitem }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="score"
          :label="t$('jy1App.customerSatisfaction.score')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.score }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="opinion"
          :label="t$('jy1App.customerSatisfaction.opinion')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.opinion }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="totalscore"
          :label="t$('jy1App.customerSatisfaction.totalscore')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.totalscore }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="surveytime"
          :label="t$('jy1App.customerSatisfaction.surveytime')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.surveytime }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="customer"
          :label="t$('jy1App.customerSatisfaction.customer')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.customer }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="plonenumber"
          :label="t$('jy1App.customerSatisfaction.plonenumber')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.plonenumber }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="filename"
          :label="t$('jy1App.customerSatisfaction.filename')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.filename }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="wbsid.id" :label="t$('jy1App.customerSatisfaction.wbsid')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.wbsid">
                <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: scope.row.wbsid.id } }">{{
                  scope.row.wbsid.id
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
                  :to="{ name: 'CustomerSatisfactionView', params: { customerSatisfactionId: scope.row.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'CustomerSatisfactionEdit', params: { customerSatisfactionId: scope.row.id } }"
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
      <!-- <table class="table table-striped" aria-describedby="customerSatisfactions">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.customerSatisfaction.year')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.customerSatisfaction.satisfactionitem')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.customerSatisfaction.score')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.customerSatisfaction.opinion')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.customerSatisfaction.totalscore')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.customerSatisfaction.surveytime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.customerSatisfaction.customer')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.customerSatisfaction.plonenumber')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.customerSatisfaction.filename')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.customerSatisfaction.wbsid')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="customerSatisfaction in customerSatisfactions"
                    :key="customerSatisfaction.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'CustomerSatisfactionView', params: {customerSatisfactionId: customerSatisfaction.id}}">{{customerSatisfaction.id}}</router-link>
                    </td>
                    <td>{{customerSatisfaction.year}}</td>
                    <td>{{customerSatisfaction.satisfactionitem}}</td>
                    <td>{{customerSatisfaction.score}}</td>
                    <td>{{customerSatisfaction.opinion}}</td>
                    <td>{{customerSatisfaction.totalscore}}</td>
                    <td>{{customerSatisfaction.surveytime}}</td>
                    <td>{{customerSatisfaction.customer}}</td>
                    <td>{{customerSatisfaction.plonenumber}}</td>
                    <td>{{customerSatisfaction.filename}}</td>
                    <td>
                        <div v-if="customerSatisfaction.wbsid">
                            <router-link :to="{name: 'ProjectwbsView', params: {projectwbsId: customerSatisfaction.wbsid.id}}">{{customerSatisfaction.wbsid.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'CustomerSatisfactionView', params: {customerSatisfactionId: customerSatisfaction.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'CustomerSatisfactionEdit', params: {customerSatisfactionId: customerSatisfaction.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(customerSatisfaction)"
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
          id="jy1App.customerSatisfaction.delete.question"
          data-cy="customerSatisfactionDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-customerSatisfaction-heading" v-text="t$('jy1App.customerSatisfaction.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-customerSatisfaction"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeCustomerSatisfaction()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./customer-satisfaction.component.ts"></script>
