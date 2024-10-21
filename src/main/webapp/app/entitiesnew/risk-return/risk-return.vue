<template>
  <div>
    <h2 id="page-heading" data-cy="RiskReturnHeading">
      <span v-text="t$('jy1App.riskReturn.home.title')" id="risk-return-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.riskReturn.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'RiskReturnCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-risk-return"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.riskReturn.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && riskReturns && riskReturns.length === 0">
      <span v-text="t$('jy1App.riskReturn.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="riskReturns && riskReturns.length > 0">
      <el-table :data="riskReturns" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'RiskReturnView', params: { riskReturnId: scope.row.id } }">{{ scope.row.id }}</router-link>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="belongriskid"
          :label="t$('jy1App.riskReturn.belongriskid')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.belongriskid }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="status" :label="t$('jy1App.riskReturn.status')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.status }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="closestatus"
          :label="t$('jy1App.riskReturn.closestatus')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.closestatus }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="evidencefile"
          :label="t$('jy1App.riskReturn.evidencefile')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.evidencefile }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="riskid.id" :label="t$('jy1App.riskReturn.riskid')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.riskid">
                <router-link :to="{ name: 'ProjectRiskView', params: { projectRiskId: scope.row.riskid.id } }">{{
                  scope.row.riskid.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="creatorid.id" :label="t$('jy1App.riskReturn.creatorid')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.creatorid">
                <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: scope.row.creatorid.id } }">{{
                  scope.row.creatorid.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'RiskReturnView', params: { riskReturnId: scope.row.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'RiskReturnEdit', params: { riskReturnId: scope.row.id } }" custom v-slot="{ navigate }">
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
      <!-- <table class="table table-striped" aria-describedby="riskReturns">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.riskReturn.belongriskid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.riskReturn.status')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.riskReturn.closestatus')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.riskReturn.evidencefile')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.riskReturn.riskid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.riskReturn.creatorid')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="riskReturn in riskReturns"
                    :key="riskReturn.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'RiskReturnView', params: {riskReturnId: riskReturn.id}}">{{riskReturn.id}}</router-link>
                    </td>
                    <td>{{riskReturn.belongriskid}}</td>
                    <td>{{riskReturn.status}}</td>
                    <td>{{riskReturn.closestatus}}</td>
                    <td>{{riskReturn.evidencefile}}</td>
                    <td>
                        <div v-if="riskReturn.riskid">
                            <router-link :to="{name: 'ProjectRiskView', params: {projectRiskId: riskReturn.riskid.id}}">{{riskReturn.riskid.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="riskReturn.creatorid">
                            <router-link :to="{name: 'HrManagementView', params: {hrManagementId: riskReturn.creatorid.id}}">{{riskReturn.creatorid.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'RiskReturnView', params: {riskReturnId: riskReturn.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'RiskReturnEdit', params: {riskReturnId: riskReturn.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(riskReturn)"
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
        <span id="jy1App.riskReturn.delete.question" data-cy="riskReturnDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-riskReturn-heading" v-text="t$('jy1App.riskReturn.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-riskReturn"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeRiskReturn()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./risk-return.component.ts"></script>
