<template>
  <div>
    <h2 id="page-heading" data-cy="KeyNodeInspectionHeading">
      <span v-text="t$('jy1App.keyNodeInspection.home.title')" id="key-node-inspection-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.keyNodeInspection.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'KeyNodeInspectionCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-key-node-inspection"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.keyNodeInspection.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && keyNodeInspections && keyNodeInspections.length === 0">
      <span v-text="t$('jy1App.keyNodeInspection.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="keyNodeInspections && keyNodeInspections.length > 0">
      <el-table :data="keyNodeInspections" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'KeyNodeInspectionView', params: { keyNodeInspectionId: scope.row.id } }">{{
              scope.row.id
            }}</router-link>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="name" :label="t$('jy1App.keyNodeInspection.name')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="workbagid"
          :label="t$('jy1App.keyNodeInspection.workbagid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.workbagid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="workbagname"
          :label="t$('jy1App.keyNodeInspection.workbagname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.workbagname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="belongwbsid"
          :label="t$('jy1App.keyNodeInspection.belongwbsid')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.belongwbsid }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="projectlevel"
          :label="t$('jy1App.keyNodeInspection.projectlevel')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.projectlevel }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="iskey"
          :label="t$('jy1App.keyNodeInspection.iskey')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.iskey }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="isimplementationplan"
          :label="t$('jy1App.keyNodeInspection.isimplementationplan')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.isimplementationplan }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="isqualityplan"
          :label="t$('jy1App.keyNodeInspection.isqualityplan')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.isqualityplan }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="istechniqueplan"
          :label="t$('jy1App.keyNodeInspection.istechniqueplan')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.istechniqueplan }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="implementationplanstatus"
          :label="t$('jy1App.keyNodeInspection.implementationplanstatus')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.implementationplanstatus }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="isimplementationplanmaterial"
          :label="t$('jy1App.keyNodeInspection.isimplementationplanmaterial')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.isimplementationplanmaterial }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="technologyplanstatus"
          :label="t$('jy1App.keyNodeInspection.technologyplanstatus')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.technologyplanstatus }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="istechnologymaterial"
          :label="t$('jy1App.keyNodeInspection.istechnologymaterial')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.istechnologymaterial }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="firstcheckstatus"
          :label="t$('jy1App.keyNodeInspection.firstcheckstatus')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.firstcheckstatus }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="isfirstcheckmaterial"
          :label="t$('jy1App.keyNodeInspection.isfirstcheckmaterial')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.isfirstcheckmaterial }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="productioncheckstatus"
          :label="t$('jy1App.keyNodeInspection.productioncheckstatus')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.productioncheckstatus }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="isproductioncheckmaterial"
          :label="t$('jy1App.keyNodeInspection.isproductioncheckmaterial')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.isproductioncheckmaterial }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="status"
          :label="t$('jy1App.keyNodeInspection.status')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.status }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'KeyNodeInspectionView', params: { keyNodeInspectionId: scope.row.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'KeyNodeInspectionEdit', params: { keyNodeInspectionId: scope.row.id } }"
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
      <!-- <table class="table table-striped" aria-describedby="keyNodeInspections">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.keyNodeInspection.name')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.keyNodeInspection.workbagid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.keyNodeInspection.workbagname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.keyNodeInspection.belongwbsid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.keyNodeInspection.projectlevel')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.keyNodeInspection.iskey')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.keyNodeInspection.isimplementationplan')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.keyNodeInspection.isqualityplan')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.keyNodeInspection.istechniqueplan')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.keyNodeInspection.implementationplanstatus')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.keyNodeInspection.isimplementationplanmaterial')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.keyNodeInspection.technologyplanstatus')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.keyNodeInspection.istechnologymaterial')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.keyNodeInspection.firstcheckstatus')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.keyNodeInspection.isfirstcheckmaterial')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.keyNodeInspection.productioncheckstatus')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.keyNodeInspection.isproductioncheckmaterial')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.keyNodeInspection.status')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="keyNodeInspection in keyNodeInspections"
                    :key="keyNodeInspection.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'KeyNodeInspectionView', params: {keyNodeInspectionId: keyNodeInspection.id}}">{{keyNodeInspection.id}}</router-link>
                    </td>
                    <td>{{keyNodeInspection.name}}</td>
                    <td>{{keyNodeInspection.workbagid}}</td>
                    <td>{{keyNodeInspection.workbagname}}</td>
                    <td>{{keyNodeInspection.belongwbsid}}</td>
                    <td>{{keyNodeInspection.projectlevel}}</td>
                    <td>{{keyNodeInspection.iskey}}</td>
                    <td>{{keyNodeInspection.isimplementationplan}}</td>
                    <td>{{keyNodeInspection.isqualityplan}}</td>
                    <td>{{keyNodeInspection.istechniqueplan}}</td>
                    <td>{{keyNodeInspection.implementationplanstatus}}</td>
                    <td>{{keyNodeInspection.isimplementationplanmaterial}}</td>
                    <td>{{keyNodeInspection.technologyplanstatus}}</td>
                    <td>{{keyNodeInspection.istechnologymaterial}}</td>
                    <td>{{keyNodeInspection.firstcheckstatus}}</td>
                    <td>{{keyNodeInspection.isfirstcheckmaterial}}</td>
                    <td>{{keyNodeInspection.productioncheckstatus}}</td>
                    <td>{{keyNodeInspection.isproductioncheckmaterial}}</td>
                    <td>{{keyNodeInspection.status}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'KeyNodeInspectionView', params: {keyNodeInspectionId: keyNodeInspection.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'KeyNodeInspectionEdit', params: {keyNodeInspectionId: keyNodeInspection.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(keyNodeInspection)"
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
          id="jy1App.keyNodeInspection.delete.question"
          data-cy="keyNodeInspectionDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-keyNodeInspection-heading" v-text="t$('jy1App.keyNodeInspection.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-keyNodeInspection"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeKeyNodeInspection()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./key-node-inspection.component.ts"></script>
