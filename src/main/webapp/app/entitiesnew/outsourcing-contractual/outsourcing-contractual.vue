<template>
  <div>
    <h2 id="page-heading" data-cy="OutsourcingContractualHeading">
      <span v-text="t$('jy1App.outsourcingContractual.home.title')" id="outsourcing-contractual-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.outsourcingContractual.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'OutsourcingContractualCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-outsourcing-contractual"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.outsourcingContractual.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && outsourcingContractuals && outsourcingContractuals.length === 0">
      <span v-text="t$('jy1App.outsourcingContractual.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="outsourcingContractuals && outsourcingContractuals.length > 0">
      <el-table :data="outsourcingContractuals" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'OutsourcingContractualView', params: { outsourcingContractualId: scope.row.id } }">{{
              scope.row.id
            }}</router-link>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="department"
          :label="t$('jy1App.outsourcingContractual.department')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.department }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="year"
          :label="t$('jy1App.outsourcingContractual.year')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.year }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="starttime"
          :label="t$('jy1App.outsourcingContractual.starttime')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.starttime }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="endtime"
          :label="t$('jy1App.outsourcingContractual.endtime')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.endtime }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="status"
          :label="t$('jy1App.outsourcingContractual.status')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.status }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="secretlevel"
          :label="t$('jy1App.outsourcingContractual.secretlevel')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.Secretlevel.' + scope.row.secretlevel)"></span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="foreigncurrency"
          :label="t$('jy1App.outsourcingContractual.foreigncurrency')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.foreigncurrency }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="totalbudget"
          :label="t$('jy1App.outsourcingContractual.totalbudget')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.totalbudget }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="fundsinplace"
          :label="t$('jy1App.outsourcingContractual.fundsinplace')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.fundsinplace }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="responsibleunitname"
          :label="t$('jy1App.outsourcingContractual.responsibleunitname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.responsibleunitname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="audittime"
          :label="t$('jy1App.outsourcingContractual.audittime')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.audittime }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="accountbank"
          :label="t$('jy1App.outsourcingContractual.accountbank')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.accountbank }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="creatorid.id" :label="t$('jy1App.outsourcingContractual.creatorid')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.creatorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: scope.row.creatorid.id } }">{{
                  scope.row.creatorid.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="auditorid.id" :label="t$('jy1App.outsourcingContractual.auditorid')">
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
          :label="t$('jy1App.outsourcingContractual.projectwbs')"
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
                  :to="{ name: 'OutsourcingContractualView', params: { outsourcingContractualId: scope.row.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'OutsourcingContractualEdit', params: { outsourcingContractualId: scope.row.id } }"
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
      <!-- <table class="table table-striped" aria-describedby="outsourcingContractuals">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContractual.department')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContractual.year')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContractual.starttime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContractual.endtime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContractual.status')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContractual.secretlevel')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContractual.foreigncurrency')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContractual.totalbudget')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContractual.fundsinplace')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContractual.responsibleunitname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContractual.audittime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContractual.accountbank')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContractual.creatorid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContractual.auditorid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.outsourcingContractual.projectwbs')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="outsourcingContractual in outsourcingContractuals"
                    :key="outsourcingContractual.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'OutsourcingContractualView', params: {outsourcingContractualId: outsourcingContractual.id}}">{{outsourcingContractual.id}}</router-link>
                    </td>
                    <td>{{outsourcingContractual.department}}</td>
                    <td>{{outsourcingContractual.year}}</td>
                    <td>{{outsourcingContractual.starttime}}</td>
                    <td>{{outsourcingContractual.endtime}}</td>
                    <td>{{outsourcingContractual.status}}</td>
                    <td v-text="t$('jy1App.Secretlevel.' + outsourcingContractual.secretlevel)"></td>
                    <td>{{outsourcingContractual.foreigncurrency}}</td>
                    <td>{{outsourcingContractual.totalbudget}}</td>
                    <td>{{outsourcingContractual.fundsinplace}}</td>
                    <td>{{outsourcingContractual.responsibleunitname}}</td>
                    <td>{{outsourcingContractual.audittime}}</td>
                    <td>{{outsourcingContractual.accountbank}}</td>
                    <td>
                        <div v-if="outsourcingContractual.creatorid">
                            <router-link :to="{name: 'OfficersView', params: {officersId: outsourcingContractual.creatorid.id}}">{{outsourcingContractual.creatorid.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="outsourcingContractual.auditorid">
                            <router-link :to="{name: 'OfficersView', params: {officersId: outsourcingContractual.auditorid.id}}">{{outsourcingContractual.auditorid.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <span v-for="(projectwbs, i) in outsourcingContractual.projectwbs" :key="projectwbs.id">{{i > 0 ? ', ' : ''}}
                            <router-link class="form-control-static" :to="{name: 'ProjectwbsView', params: {projectwbsId: projectwbs.id}}">{{projectwbs.id}}</router-link>
                        </span>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'OutsourcingContractualView', params: {outsourcingContractualId: outsourcingContractual.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'OutsourcingContractualEdit', params: {outsourcingContractualId: outsourcingContractual.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(outsourcingContractual)"
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
          id="jy1App.outsourcingContractual.delete.question"
          data-cy="outsourcingContractualDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-outsourcingContractual-heading"
          v-text="t$('jy1App.outsourcingContractual.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-outsourcingContractual"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeOutsourcingContractual()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./outsourcing-contractual.component.ts"></script>
s