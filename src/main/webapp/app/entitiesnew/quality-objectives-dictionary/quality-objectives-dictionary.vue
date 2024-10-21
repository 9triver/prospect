<template>
  <div>
    <h2 id="page-heading" data-cy="QualityObjectivesDictionaryHeading">
      <span v-text="t$('jy1App.qualityObjectivesDictionary.home.title')" id="quality-objectives-dictionary-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.qualityObjectivesDictionary.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'QualityObjectivesDictionaryCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-quality-objectives-dictionary"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.qualityObjectivesDictionary.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && qualityObjectivesDictionaries && qualityObjectivesDictionaries.length === 0">
      <span v-text="t$('jy1App.qualityObjectivesDictionary.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="qualityObjectivesDictionaries && qualityObjectivesDictionaries.length > 0">
      <el-table :data="qualityObjectivesDictionaries" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'QualityObjectivesDictionaryView', params: { qualityObjectivesDictionaryId: scope.row.id } }">{{
              scope.row.id
            }}</router-link>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="objectiveslevel"
          :label="t$('jy1App.qualityObjectivesDictionary.objectiveslevel')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.objectiveslevel }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="objectivestype"
          :label="t$('jy1App.qualityObjectivesDictionary.objectivestype')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.objectivestype }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="objectivesname"
          :label="t$('jy1App.qualityObjectivesDictionary.objectivesname')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.objectivesname }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="objectivescontent"
          :label="t$('jy1App.qualityObjectivesDictionary.objectivescontent')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.objectivescontent }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="calculationmethod"
          :label="t$('jy1App.qualityObjectivesDictionary.calculationmethod')"
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
          :label="t$('jy1App.qualityObjectivesDictionary.frequency')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.frequency }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="evaluationcriteria"
          :label="t$('jy1App.qualityObjectivesDictionary.evaluationcriteria')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.evaluationcriteria }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'QualityObjectivesDictionaryView', params: { qualityObjectivesDictionaryId: scope.row.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'QualityObjectivesDictionaryEdit', params: { qualityObjectivesDictionaryId: scope.row.id } }"
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
      <!-- <table class="table table-striped" aria-describedby="qualityObjectivesDictionaries">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityObjectivesDictionary.objectiveslevel')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityObjectivesDictionary.objectivestype')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityObjectivesDictionary.objectivesname')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityObjectivesDictionary.objectivescontent')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityObjectivesDictionary.calculationmethod')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityObjectivesDictionary.frequency')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.qualityObjectivesDictionary.evaluationcriteria')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="qualityObjectivesDictionary in qualityObjectivesDictionaries"
                    :key="qualityObjectivesDictionary.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'QualityObjectivesDictionaryView', params: {qualityObjectivesDictionaryId: qualityObjectivesDictionary.id}}">{{qualityObjectivesDictionary.id}}</router-link>
                    </td>
                    <td>{{qualityObjectivesDictionary.objectiveslevel}}</td>
                    <td>{{qualityObjectivesDictionary.objectivestype}}</td>
                    <td>{{qualityObjectivesDictionary.objectivesname}}</td>
                    <td>{{qualityObjectivesDictionary.objectivescontent}}</td>
                    <td>{{qualityObjectivesDictionary.calculationmethod}}</td>
                    <td>{{qualityObjectivesDictionary.frequency}}</td>
                    <td>{{qualityObjectivesDictionary.evaluationcriteria}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'QualityObjectivesDictionaryView', params: {qualityObjectivesDictionaryId: qualityObjectivesDictionary.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'QualityObjectivesDictionaryEdit', params: {qualityObjectivesDictionaryId: qualityObjectivesDictionary.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(qualityObjectivesDictionary)"
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
          id="jy1App.qualityObjectivesDictionary.delete.question"
          data-cy="qualityObjectivesDictionaryDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-qualityObjectivesDictionary-heading"
          v-text="t$('jy1App.qualityObjectivesDictionary.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-qualityObjectivesDictionary"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeQualityObjectivesDictionary()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./quality-objectives-dictionary.component.ts"></script>
