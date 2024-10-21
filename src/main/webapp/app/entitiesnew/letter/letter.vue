<template>
  <div>
    <h2 id="page-heading" data-cy="LetterHeading">
      <span v-text="t$('jy1App.letter.home.title')" id="letter-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.letter.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'LetterCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-letter"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.letter.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && letters && letters.length === 0">
      <span v-text="t$('jy1App.letter.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="letters && letters.length > 0">
      <el-table :data="letters" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'LetterView', params: { letterId: scope.row.id } }">{{ scope.row.id }}</router-link>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="lettername"
          :label="t$('jy1App.letter.lettername')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.lettername }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="letternumber"
          :label="t$('jy1App.letter.letternumber')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.letternumber }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="lettertype"
          :label="t$('jy1App.letter.lettertype')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.lettertype }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="secretlevel"
          :label="t$('jy1App.letter.secretlevel')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.Secretlevel.' + scope.row.secretlevel)"></span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="lettercontent"
          :label="t$('jy1App.letter.lettercontent')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.lettercontent }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="letterstatus"
          :label="t$('jy1App.letter.letterstatus')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.letterstatus }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="lettertime" :label="t$('jy1App.letter.lettertime')" :sortable="true">
          <template #default="scope">
            <span class="field-default">{{ scope.row.lettertime }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="previousfile"
          :label="t$('jy1App.letter.previousfile')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.previousfile }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="datarecordstatus"
          :label="t$('jy1App.letter.datarecordstatus')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.datarecordstatus }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="wbsid.id" :label="t$('jy1App.letter.wbsid')">
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
        <el-table-column min-width="150px" show-overflow-tooltip prop="workbagid.id" :label="t$('jy1App.letter.workbagid')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.workbagid">
                <router-link :to="{ name: 'WorkbagView', params: { workbagId: scope.row.workbagid.id } }">{{
                  scope.row.workbagid.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="frontlineid.id" :label="t$('jy1App.letter.frontlineid')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.frontlineid">
                <router-link :to="{ name: 'FrontlineView', params: { frontlineId: scope.row.frontlineid.id } }">{{
                  scope.row.frontlineid.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="receivingunit.id" :label="t$('jy1App.letter.receivingunit')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.receivingunit">
                <router-link :to="{ name: 'DepartmentView', params: { departmentId: scope.row.receivingunit.id } }">{{
                  scope.row.receivingunit.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="sendingunit.id" :label="t$('jy1App.letter.sendingunit')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.sendingunit">
                <router-link :to="{ name: 'DepartmentView', params: { departmentId: scope.row.sendingunit.id } }">{{
                  scope.row.sendingunit.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="lettermaker.id" :label="t$('jy1App.letter.lettermaker')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.lettermaker">
                <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: scope.row.lettermaker.id } }">{{
                  scope.row.lettermaker.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="letterreceiver.id" :label="t$('jy1App.letter.letterreceiver')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.letterreceiver">
                <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: scope.row.letterreceiver.id } }">{{
                  scope.row.letterreceiver.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="letterhandler.id" :label="t$('jy1App.letter.letterhandler')">
          <template #default="scope">
            <td>
              <div v-if="scope.row.letterhandler">
                <router-link :to="{ name: 'HrManagementView', params: { hrManagementId: scope.row.letterhandler.id } }">{{
                  scope.row.letterhandler.id
                }}</router-link>
              </div>
            </td>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'LetterView', params: { letterId: scope.row.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'LetterEdit', params: { letterId: scope.row.id } }" custom v-slot="{ navigate }">
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
      <!-- <table class="table table-striped" aria-describedby="letters">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.letter.lettername')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.letter.letternumber')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.letter.lettertype')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.letter.secretlevel')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.letter.lettercontent')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.letter.letterstatus')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.letter.lettertime')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.letter.previousfile')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.letter.datarecordstatus')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.letter.wbsid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.letter.workbagid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.letter.frontlineid')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.letter.receivingunit')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.letter.sendingunit')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.letter.lettermaker')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.letter.letterreceiver')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.letter.letterhandler')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="letter in letters"
                    :key="letter.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'LetterView', params: {letterId: letter.id}}">{{letter.id}}</router-link>
                    </td>
                    <td>{{letter.lettername}}</td>
                    <td>{{letter.letternumber}}</td>
                    <td>{{letter.lettertype}}</td>
                    <td v-text="t$('jy1App.Secretlevel.' + letter.secretlevel)"></td>
                    <td>{{letter.lettercontent}}</td>
                    <td>{{letter.letterstatus}}</td>
                    <td>{{letter.lettertime}}</td>
                    <td>{{letter.previousfile}}</td>
                    <td>{{letter.datarecordstatus}}</td>
                    <td>
                        <div v-if="letter.wbsid">
                            <router-link :to="{name: 'ProjectwbsView', params: {projectwbsId: letter.wbsid.id}}">{{letter.wbsid.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="letter.workbagid">
                            <router-link :to="{name: 'WorkbagView', params: {workbagId: letter.workbagid.id}}">{{letter.workbagid.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="letter.frontlineid">
                            <router-link :to="{name: 'FrontlineView', params: {frontlineId: letter.frontlineid.id}}">{{letter.frontlineid.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="letter.receivingunit">
                            <router-link :to="{name: 'DepartmentView', params: {departmentId: letter.receivingunit.id}}">{{letter.receivingunit.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="letter.sendingunit">
                            <router-link :to="{name: 'DepartmentView', params: {departmentId: letter.sendingunit.id}}">{{letter.sendingunit.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="letter.lettermaker">
                            <router-link :to="{name: 'HrManagementView', params: {hrManagementId: letter.lettermaker.id}}">{{letter.lettermaker.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="letter.letterreceiver">
                            <router-link :to="{name: 'HrManagementView', params: {hrManagementId: letter.letterreceiver.id}}">{{letter.letterreceiver.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="letter.letterhandler">
                            <router-link :to="{name: 'HrManagementView', params: {hrManagementId: letter.letterhandler.id}}">{{letter.letterhandler.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'LetterView', params: {letterId: letter.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'LetterEdit', params: {letterId: letter.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(letter)"
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
        <span id="jy1App.letter.delete.question" data-cy="letterDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-letter-heading" v-text="t$('jy1App.letter.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-letter"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeLetter()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./letter.component.ts"></script>
