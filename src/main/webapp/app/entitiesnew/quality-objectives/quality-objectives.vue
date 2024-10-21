<template>
  <div>
    <h2 id="page-heading" data-cy="QualityObjectivesHeading">
      <span v-text="t$('jy1App.qualityObjectives.home.title')" id="quality-objectives-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.qualityObjectives.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'QualityObjectivesCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-quality-objectives"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.qualityObjectives.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && qualityObjectives && qualityObjectives.length === 0">
      <span v-text="t$('jy1App.qualityObjectives.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="qualityObjectives && qualityObjectives.length > 0">
      <el-table :data="qualityObjectives" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'QualityObjectivesView', params: { qualityObjectivesId: scope.row.id } }">{{
              scope.row.id
            }}</router-link>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="name" :label="t$('jy1App.qualityObjectives.name')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="objectiveslevel"
          :label="t$('jy1App.qualityObjectives.objectiveslevel')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.objectiveslevel }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="objectives"
          :label="t$('jy1App.qualityObjectives.objectives')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.objectives }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="objectivesvalue"
          :label="t$('jy1App.qualityObjectives.objectivesvalue')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.objectivesvalue }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="calculationmethod"
          :label="t$('jy1App.qualityObjectives.calculationmethod')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.calculationmethod }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="frequency"
          :label="t$('jy1App.qualityObjectives.frequency')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.frequency }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="takeaction"
          :label="t$('jy1App.qualityObjectives.takeaction')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.takeaction }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="needresource"
          :label="t$('jy1App.qualityObjectives.needresource')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.needresource }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="status"
          :label="t$('jy1App.qualityObjectives.status')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.status }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="responsibleperson.id"
          :label="t$('jy1App.qualityObjectives.responsibleperson')"
        >
          <template #default="scope">
            <td>
              <div v-if="scope.row.responsibleperson">
                <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: scope.row.responsibleperson.id } }">{{
                  scope.row.responsibleperson.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="qualityReturns.id"
          :label="t$('jy1App.qualityObjectives.qualityReturns')"
        >
          <template #default="scope">
            <td>
              <span v-for="(qualityReturns, i) in scope.row.qualityReturns" :key="qualityReturns.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{ name: 'QualityReturnsView', params: { qualityReturnsId: qualityReturns.id } }"
                  >{{ qualityReturns.id }}</router-link
                >
              </span>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="qualityPlan.id" :label="t$('jy1App.qualityObjectives.qualityPlan')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.qualityPlan">
                <router-link :to="{ name: 'QualityPlanView', params: { qualityPlanId: scope.row.qualityPlan.id } }">{{
                  scope.row.qualityPlan.id
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
                  :to="{ name: 'QualityObjectivesView', params: { qualityObjectivesId: scope.row.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'QualityObjectivesEdit', params: { qualityObjectivesId: scope.row.id } }"
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
      <!-- <table class="table table-striped" aria-describedby="qualityObjectives">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityObjectives.name')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityObjectives.objectiveslevel')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityObjectives.objectives')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityObjectives.objectivesvalue')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityObjectives.calculationmethod')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityObjectives.frequency')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityObjectives.takeaction')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityObjectives.needresource')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityObjectives.status')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityObjectives.responsibleperson')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityObjectives.qualityReturns')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityObjectives.qualityPlan')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="qualityObjectives in qualityObjectives"
                    :key="qualityObjectives.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'QualityObjectivesView', params: {qualityObjectivesId: qualityObjectives.id}}">{{qualityObjectives.id}}</router-link>
                    </td>
                    <td>{{qualityObjectives.name}}</td>
                    <td>{{qualityObjectives.objectiveslevel}}</td>
                    <td>{{qualityObjectives.objectives}}</td>
                    <td>{{qualityObjectives.objectivesvalue}}</td>
                    <td>{{qualityObjectives.calculationmethod}}</td>
                    <td>{{qualityObjectives.frequency}}</td>
                    <td>{{qualityObjectives.takeaction}}</td>
                    <td>{{qualityObjectives.needresource}}</td>
                    <td>{{qualityObjectives.status}}</td>
                    <td>
                        <div v-if="qualityObjectives.responsibleperson">
                            <router-link :to="{name: 'HrManagementView', params: {hrManagementId: qualityObjectives.responsibleperson.id}}">{{qualityObjectives.responsibleperson.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <span v-for="(qualityReturns, i) in qualityObjectives.qualityReturns" :key="qualityReturns.id">{{i > 0 ? ', ' : ''}}
                            <router-link class="form-control-static" :to="{name: 'QualityReturnsView', params: {qualityReturnsId: qualityReturns.id}}">{{qualityReturns.id}}</router-link>
                        </span>
                    </td>
                    <td>
                        <div v-if="qualityObjectives.qualityPlan">
                            <router-link :to="{name: 'QualityPlanView', params: {qualityPlanId: qualityObjectives.qualityPlan.id}}">{{qualityObjectives.qualityPlan.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'QualityObjectivesView', params: {qualityObjectivesId: qualityObjectives.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'QualityObjectivesEdit', params: {qualityObjectivesId: qualityObjectives.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(qualityObjectives)"
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
          id="jy1App.qualityObjectives.delete.question"
          data-cy="qualityObjectivesDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-qualityObjectives-heading" v-text="t$('jy1App.qualityObjectives.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-qualityObjectives"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeQualityObjectives()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./quality-objectives.component.ts"></script>
