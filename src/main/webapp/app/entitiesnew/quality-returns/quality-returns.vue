<template>
  <div>
    <h2 id="page-heading" data-cy="QualityReturnsHeading">
      <span v-text="t$('jy1App.qualityReturns.home.title')" id="quality-returns-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.qualityReturns.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'QualityReturnsCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-quality-returns"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.qualityReturns.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && qualityReturns && qualityReturns.length === 0">
      <span v-text="t$('jy1App.qualityReturns.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="qualityReturns && qualityReturns.length > 0">
      <el-table :data="qualityReturns" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'QualityReturnsView', params: { qualityReturnsId: scope.row.id } }">{{ scope.row.id }}</router-link>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="qualityplanid"
          :label="t$('jy1App.qualityReturns.qualityplanid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.qualityplanid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="qualityobjectivesid"
          :label="t$('jy1App.qualityReturns.qualityobjectivesid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.qualityobjectivesid }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="name" :label="t$('jy1App.qualityReturns.name')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="department"
          :label="t$('jy1App.qualityReturns.department')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.department }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="responsibleid"
          :label="t$('jy1App.qualityReturns.responsibleid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.responsibleid }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="wbsid" :label="t$('jy1App.qualityReturns.wbsid')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.wbsid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="workbagid"
          :label="t$('jy1App.qualityReturns.workbagid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.workbagid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="objectiveslevel"
          :label="t$('jy1App.qualityReturns.objectiveslevel')"
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
          :label="t$('jy1App.qualityReturns.objectives')"
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
          :label="t$('jy1App.qualityReturns.objectivesvalue')"
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
          :label="t$('jy1App.qualityReturns.calculationmethod')"
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
          :label="t$('jy1App.qualityReturns.frequency')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.frequency }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="isobjectivesvalue"
          :label="t$('jy1App.qualityReturns.isobjectivesvalue')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.isobjectivesvalue }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="percentage"
          :label="t$('jy1App.qualityReturns.percentage')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.percentage }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="objectivescompletion"
          :label="t$('jy1App.qualityReturns.objectivescompletion')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.objectivescompletion }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="problem"
          :label="t$('jy1App.qualityReturns.problem')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.problem }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="takeaction"
          :label="t$('jy1App.qualityReturns.takeaction')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.takeaction }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="continuousimprovement"
          :label="t$('jy1App.qualityReturns.continuousimprovement')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.continuousimprovement }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="workevidence"
          :label="t$('jy1App.qualityReturns.workevidence')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.workevidence }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="returntime"
          :label="t$('jy1App.qualityReturns.returntime')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.returntime }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="status"
          :label="t$('jy1App.qualityReturns.status')"
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
          :label="t$('jy1App.qualityReturns.responsibleperson')"
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
        <el-table-column min-width="150px" show-overflow-tooltip prop="auditorid.id" :label="t$('jy1App.qualityReturns.auditorid')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.auditorid">
                <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: scope.row.auditorid.id } }">{{
                  scope.row.auditorid.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="qualityObjectives.id"
          :label="t$('jy1App.qualityReturns.qualityObjectives')"
        >
          <template #default="scope">
            <td>
              <span v-for="(qualityObjectives, i) in scope.row.qualityObjectives" :key="qualityObjectives.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{ name: 'QualityObjectivesView', params: { qualityObjectivesId: qualityObjectives.id } }"
                  >{{ qualityObjectives.id }}</router-link
                >
              </span>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="qualityPlan.id" :label="t$('jy1App.qualityReturns.qualityPlan')">
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
                <router-link :to="{ name: 'QualityReturnsView', params: { qualityReturnsId: scope.row.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'QualityReturnsEdit', params: { qualityReturnsId: scope.row.id } }" custom v-slot="{ navigate }">
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
      <!-- <table class="table table-striped" aria-describedby="qualityReturns">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityReturns.qualityplanid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityReturns.qualityobjectivesid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityReturns.name')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityReturns.department')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityReturns.responsibleid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityReturns.wbsid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityReturns.workbagid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityReturns.objectiveslevel')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityReturns.objectives')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityReturns.objectivesvalue')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityReturns.calculationmethod')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityReturns.frequency')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityReturns.isobjectivesvalue')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityReturns.percentage')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityReturns.objectivescompletion')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityReturns.problem')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityReturns.takeaction')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityReturns.continuousimprovement')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityReturns.workevidence')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityReturns.returntime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityReturns.status')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityReturns.responsibleperson')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityReturns.auditorid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityReturns.qualityObjectives')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityReturns.qualityPlan')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="qualityReturns in qualityReturns"
                    :key="qualityReturns.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'QualityReturnsView', params: {qualityReturnsId: qualityReturns.id}}">{{qualityReturns.id}}</router-link>
                    </td>
                    <td>{{qualityReturns.qualityplanid}}</td>
                    <td>{{qualityReturns.qualityobjectivesid}}</td>
                    <td>{{qualityReturns.name}}</td>
                    <td>{{qualityReturns.department}}</td>
                    <td>{{qualityReturns.responsibleid}}</td>
                    <td>{{qualityReturns.wbsid}}</td>
                    <td>{{qualityReturns.workbagid}}</td>
                    <td>{{qualityReturns.objectiveslevel}}</td>
                    <td>{{qualityReturns.objectives}}</td>
                    <td>{{qualityReturns.objectivesvalue}}</td>
                    <td>{{qualityReturns.calculationmethod}}</td>
                    <td>{{qualityReturns.frequency}}</td>
                    <td>{{qualityReturns.isobjectivesvalue}}</td>
                    <td>{{qualityReturns.percentage}}</td>
                    <td>{{qualityReturns.objectivescompletion}}</td>
                    <td>{{qualityReturns.problem}}</td>
                    <td>{{qualityReturns.takeaction}}</td>
                    <td>{{qualityReturns.continuousimprovement}}</td>
                    <td>{{qualityReturns.workevidence}}</td>
                    <td>{{qualityReturns.returntime}}</td>
                    <td>{{qualityReturns.status}}</td>
                    <td>
                        <div v-if="qualityReturns.responsibleperson">
                            <router-link :to="{name: 'HrManagementView', params: {hrManagementId: qualityReturns.responsibleperson.id}}">{{qualityReturns.responsibleperson.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="qualityReturns.auditorid">
                            <router-link :to="{name: 'HrManagementView', params: {hrManagementId: qualityReturns.auditorid.id}}">{{qualityReturns.auditorid.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <span v-for="(qualityObjectives, i) in qualityReturns.qualityObjectives" :key="qualityObjectives.id">{{i > 0 ? ', ' : ''}}
                            <router-link class="form-control-static" :to="{name: 'QualityObjectivesView', params: {qualityObjectivesId: qualityObjectives.id}}">{{qualityObjectives.id}}</router-link>
                        </span>
                    </td>
                    <td>
                        <div v-if="qualityReturns.qualityPlan">
                            <router-link :to="{name: 'QualityPlanView', params: {qualityPlanId: qualityReturns.qualityPlan.id}}">{{qualityReturns.qualityPlan.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'QualityReturnsView', params: {qualityReturnsId: qualityReturns.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'QualityReturnsEdit', params: {qualityReturnsId: qualityReturns.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(qualityReturns)"
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
          id="jy1App.qualityReturns.delete.question"
          data-cy="qualityReturnsDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-qualityReturns-heading" v-text="t$('jy1App.qualityReturns.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-qualityReturns"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeQualityReturns()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./quality-returns.component.ts"></script>
