<template>
  <div>
    <h2 id="page-heading" data-cy="OutsourcingPurchasePlanHeading">
      <span v-text="t$('jy1App.outsourcingPurchasePlan.home.title')" id="outsourcing-purchase-plan-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.outsourcingPurchasePlan.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'OutsourcingPurchasePlanCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-outsourcing-purchase-plan"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.outsourcingPurchasePlan.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && outsourcingPurchasePlans && outsourcingPurchasePlans.length === 0">
      <span v-text="t$('jy1App.outsourcingPurchasePlan.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="outsourcingPurchasePlans && outsourcingPurchasePlans.length > 0">
      <el-table :data="outsourcingPurchasePlans" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'OutsourcingPurchasePlanView', params: { outsourcingPurchasePlanId: scope.row.id } }">{{
              scope.row.id
            }}</router-link>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="matarialname"
          :label="t$('jy1App.outsourcingPurchasePlan.matarialname')"
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
          :label="t$('jy1App.outsourcingPurchasePlan.purchasingmethod')"
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
          :label="t$('jy1App.outsourcingPurchasePlan.budgit')"
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
          :label="t$('jy1App.outsourcingPurchasePlan.needtime')"
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
          :label="t$('jy1App.outsourcingPurchasePlan.planusetime')"
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
          :label="t$('jy1App.outsourcingPurchasePlan.supplierid')"
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
          :label="t$('jy1App.outsourcingPurchasePlan.price')"
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
          :label="t$('jy1App.outsourcingPurchasePlan.secretlevel')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.Secretlevel.' + scope.row.secretlevel)"></span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="auditStatus"
          :label="t$('jy1App.outsourcingPurchasePlan.auditStatus')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.AuditStatus.' + scope.row.auditStatus)"></span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="responsibleperson.id"
          :label="t$('jy1App.outsourcingPurchasePlan.responsibleperson')"
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
          prop="auditorid.id"
          :label="t$('jy1App.outsourcingPurchasePlan.auditorid')"
        >
          <template #default="scope">
            <td>
              <div v-if="scope.row.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: scope.row.auditorid.id } }">{{
                  scope.row.auditorid.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="projectwbs.id"
          :label="t$('jy1App.outsourcingPurchasePlan.projectwbs')"
        >
          <template #default="scope">
            <td>
              <span v-for="(projectwbs, i) in scope.row.projectwbs" :key="projectwbs.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'ProjectwbsView', params: { projectwbsId: projectwbs.id } }">{{
                  projectwbs.id
                }}</router-link>
              </span>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'OutsourcingPurchasePlanView', params: { outsourcingPurchasePlanId: scope.row.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'OutsourcingPurchasePlanEdit', params: { outsourcingPurchasePlanId: scope.row.id } }"
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
      <!-- <table class="table table-striped" aria-describedby="outsourcingPurchasePlans">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingPurchasePlan.matarialname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingPurchasePlan.purchasingmethod')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingPurchasePlan.budgit')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingPurchasePlan.needtime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingPurchasePlan.planusetime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingPurchasePlan.supplierid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingPurchasePlan.price')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingPurchasePlan.secretlevel')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingPurchasePlan.auditStatus')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingPurchasePlan.responsibleperson')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingPurchasePlan.auditorid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingPurchasePlan.projectwbs')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="outsourcingPurchasePlan in outsourcingPurchasePlans"
                    :key="outsourcingPurchasePlan.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'OutsourcingPurchasePlanView', params: {outsourcingPurchasePlanId: outsourcingPurchasePlan.id}}">{{outsourcingPurchasePlan.id}}</router-link>
                    </td>
                    <td>{{outsourcingPurchasePlan.matarialname}}</td>
                    <td>{{outsourcingPurchasePlan.purchasingmethod}}</td>
                    <td>{{outsourcingPurchasePlan.budgit}}</td>
                    <td>{{outsourcingPurchasePlan.needtime}}</td>
                    <td>{{outsourcingPurchasePlan.planusetime}}</td>
                    <td>{{outsourcingPurchasePlan.supplierid}}</td>
                    <td>{{outsourcingPurchasePlan.price}}</td>
                    <td v-text="t$('jy1App.Secretlevel.' + outsourcingPurchasePlan.secretlevel)"></td>
                    <td v-text="t$('jy1App.AuditStatus.' + outsourcingPurchasePlan.auditStatus)"></td>
                    <td>
                        <div v-if="outsourcingPurchasePlan.responsibleperson">
                            <router-link :to="{name: 'OfficersView', params: {officersId: outsourcingPurchasePlan.responsibleperson.id}}">{{outsourcingPurchasePlan.responsibleperson.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="outsourcingPurchasePlan.auditorid">
                            <router-link :to="{name: 'OfficersView', params: {officersId: outsourcingPurchasePlan.auditorid.id}}">{{outsourcingPurchasePlan.auditorid.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <span v-for="(projectwbs, i) in outsourcingPurchasePlan.projectwbs" :key="projectwbs.id">{{i > 0 ? ', ' : ''}}
                            <router-link class="form-control-static" :to="{name: 'ProjectwbsView', params: {projectwbsId: projectwbs.id}}">{{projectwbs.id}}</router-link>
                        </span>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'OutsourcingPurchasePlanView', params: {outsourcingPurchasePlanId: outsourcingPurchasePlan.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'OutsourcingPurchasePlanEdit', params: {outsourcingPurchasePlanId: outsourcingPurchasePlan.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(outsourcingPurchasePlan)"
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
          id="jy1App.outsourcingPurchasePlan.delete.question"
          data-cy="outsourcingPurchasePlanDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-outsourcingPurchasePlan-heading"
          v-text="t$('jy1App.outsourcingPurchasePlan.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-outsourcingPurchasePlan"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeOutsourcingPurchasePlan()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./outsourcing-purchase-plan.component.ts"></script>
