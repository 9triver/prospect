<template>
  <div>
    <h2 id="page-heading" data-cy="RiskPossibilityHeading">
      <span v-text="t$('jy1App.riskPossibility.home.title')" id="risk-possibility-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.riskPossibility.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'RiskPossibilityCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-risk-possibility"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.riskPossibility.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && riskPossibilities && riskPossibilities.length === 0">
      <span v-text="t$('jy1App.riskPossibility.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="riskPossibilities && riskPossibilities.length > 0">
      <el-table :data="riskPossibilities" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'RiskPossibilityView', params: { riskPossibilityId: scope.row.id } }">{{ scope.row.id }}</router-link>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="name" :label="t$('jy1App.riskPossibility.name')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'RiskPossibilityView', params: { riskPossibilityId: scope.row.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'RiskPossibilityEdit', params: { riskPossibilityId: scope.row.id } }"
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
      <!-- <table class="table table-striped" aria-describedby="riskPossibilities">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.riskPossibility.name')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="riskPossibility in riskPossibilities"
                    :key="riskPossibility.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'RiskPossibilityView', params: {riskPossibilityId: riskPossibility.id}}">{{riskPossibility.id}}</router-link>
                    </td>
                    <td>{{riskPossibility.name}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'RiskPossibilityView', params: {riskPossibilityId: riskPossibility.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'RiskPossibilityEdit', params: {riskPossibilityId: riskPossibility.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(riskPossibility)"
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
          id="jy1App.riskPossibility.delete.question"
          data-cy="riskPossibilityDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-riskPossibility-heading" v-text="t$('jy1App.riskPossibility.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-riskPossibility"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeRiskPossibility()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./risk-possibility.component.ts"></script>
