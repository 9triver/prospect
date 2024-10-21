<template>
  <div>
    <h2 id="page-heading" data-cy="QualitytozeroHeading">
      <span v-text="t$('jy1App.qualitytozero.home.title')" id="qualitytozero-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.qualitytozero.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'QualitytozeroCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-qualitytozero"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.qualitytozero.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && qualitytozeros && qualitytozeros.length === 0">
      <span v-text="t$('jy1App.qualitytozero.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="qualitytozeros && qualitytozeros.length > 0">
      <el-table :data="qualitytozeros" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'QualitytozeroView', params: { qualitytozeroId: scope.row.id } }">{{ scope.row.id }}</router-link>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="workbagid"
          :label="t$('jy1App.qualitytozero.workbagid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.workbagid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="belongwbsid"
          :label="t$('jy1App.qualitytozero.belongwbsid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.belongwbsid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="outsourcingcontractid"
          :label="t$('jy1App.qualitytozero.outsourcingcontractid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.outsourcingcontractid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="qualityproblemid"
          :label="t$('jy1App.qualitytozero.qualityproblemid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.qualityproblemid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="qualityproblemname"
          :label="t$('jy1App.qualitytozero.qualityproblemname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.qualityproblemname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="problemhappentime"
          :label="t$('jy1App.qualitytozero.problemhappentime')"
          :sortable="true"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.problemhappentime }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="problemresponsibleperson"
          :label="t$('jy1App.qualitytozero.problemresponsibleperson')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.problemresponsibleperson }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="problemresponsibleunit"
          :label="t$('jy1App.qualitytozero.problemresponsibleunit')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.problemresponsibleunit }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="producttype"
          :label="t$('jy1App.qualitytozero.producttype')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.producttype }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="productname"
          :label="t$('jy1App.qualitytozero.productname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.productname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="problemphenomenon"
          :label="t$('jy1App.qualitytozero.problemphenomenon')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.problemphenomenon }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="problemtype"
          :label="t$('jy1App.qualitytozero.problemtype')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.problemtype }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="qualitylevel"
          :label="t$('jy1App.qualitytozero.qualitylevel')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.qualitylevel }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="zerotype"
          :label="t$('jy1App.qualitytozero.zerotype')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.zerotype }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="problemreasonanalysis"
          :label="t$('jy1App.qualitytozero.problemreasonanalysis')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.problemreasonanalysis }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="problemreasoncategory"
          :label="t$('jy1App.qualitytozero.problemreasoncategory')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.problemreasoncategory }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="takemeasures"
          :label="t$('jy1App.qualitytozero.takemeasures')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.takemeasures }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="onebyonecategory"
          :label="t$('jy1App.qualitytozero.onebyonecategory')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.onebyonecategory }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="verificationeffect"
          :label="t$('jy1App.qualitytozero.verificationeffect')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.verificationeffect }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="qualityprojectreport"
          :label="t$('jy1App.qualitytozero.qualityprojectreport')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.qualityprojectreport }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="qualitytozeroreport"
          :label="t$('jy1App.qualitytozero.qualitytozeroreport')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.qualitytozeroreport }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="reviewopinion"
          :label="t$('jy1App.qualitytozero.reviewopinion')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.reviewopinion }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="implementationverificationtable"
          :label="t$('jy1App.qualitytozero.implementationverificationtable')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.implementationverificationtable }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="auditStatus"
          :label="t$('jy1App.qualitytozero.auditStatus')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.AuditStatus.' + scope.row.auditStatus)"></span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="workbag.id" :label="t$('jy1App.qualitytozero.workbag')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.workbag">
                <router-link :to="{ name: 'WorkbagView', params: { workbagId: scope.row.workbag.id } }">{{
                  scope.row.workbag.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'QualitytozeroView', params: { qualitytozeroId: scope.row.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'QualitytozeroEdit', params: { qualitytozeroId: scope.row.id } }" custom v-slot="{ navigate }">
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
      <!-- <table class="table table-striped" aria-describedby="qualitytozeros">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualitytozero.workbagid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualitytozero.belongwbsid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualitytozero.outsourcingcontractid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualitytozero.qualityproblemid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualitytozero.qualityproblemname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualitytozero.problemhappentime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualitytozero.problemresponsibleperson')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualitytozero.problemresponsibleunit')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualitytozero.producttype')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualitytozero.productname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualitytozero.problemphenomenon')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualitytozero.problemtype')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualitytozero.qualitylevel')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualitytozero.zerotype')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualitytozero.problemreasonanalysis')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualitytozero.problemreasoncategory')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualitytozero.takemeasures')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualitytozero.onebyonecategory')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualitytozero.verificationeffect')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualitytozero.qualityprojectreport')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualitytozero.qualitytozeroreport')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualitytozero.reviewopinion')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualitytozero.implementationverificationtable')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualitytozero.auditStatus')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualitytozero.workbag')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="qualitytozero in qualitytozeros"
                    :key="qualitytozero.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'QualitytozeroView', params: {qualitytozeroId: qualitytozero.id}}">{{qualitytozero.id}}</router-link>
                    </td>
                    <td>{{qualitytozero.workbagid}}</td>
                    <td>{{qualitytozero.belongwbsid}}</td>
                    <td>{{qualitytozero.outsourcingcontractid}}</td>
                    <td>{{qualitytozero.qualityproblemid}}</td>
                    <td>{{qualitytozero.qualityproblemname}}</td>
                    <td>{{qualitytozero.problemhappentime}}</td>
                    <td>{{qualitytozero.problemresponsibleperson}}</td>
                    <td>{{qualitytozero.problemresponsibleunit}}</td>
                    <td>{{qualitytozero.producttype}}</td>
                    <td>{{qualitytozero.productname}}</td>
                    <td>{{qualitytozero.problemphenomenon}}</td>
                    <td>{{qualitytozero.problemtype}}</td>
                    <td>{{qualitytozero.qualitylevel}}</td>
                    <td>{{qualitytozero.zerotype}}</td>
                    <td>{{qualitytozero.problemreasonanalysis}}</td>
                    <td>{{qualitytozero.problemreasoncategory}}</td>
                    <td>{{qualitytozero.takemeasures}}</td>
                    <td>{{qualitytozero.onebyonecategory}}</td>
                    <td>{{qualitytozero.verificationeffect}}</td>
                    <td>{{qualitytozero.qualityprojectreport}}</td>
                    <td>{{qualitytozero.qualitytozeroreport}}</td>
                    <td>{{qualitytozero.reviewopinion}}</td>
                    <td>{{qualitytozero.implementationverificationtable}}</td>
                    <td v-text="t$('jy1App.AuditStatus.' + qualitytozero.auditStatus)"></td>
                    <td>
                        <div v-if="qualitytozero.workbag">
                            <router-link :to="{name: 'WorkbagView', params: {workbagId: qualitytozero.workbag.id}}">{{qualitytozero.workbag.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'QualitytozeroView', params: {qualitytozeroId: qualitytozero.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'QualitytozeroEdit', params: {qualitytozeroId: qualitytozero.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(qualitytozero)"
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
          id="jy1App.qualitytozero.delete.question"
          data-cy="qualitytozeroDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-qualitytozero-heading" v-text="t$('jy1App.qualitytozero.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-qualitytozero"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeQualitytozero()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./qualitytozero.component.ts"></script>
