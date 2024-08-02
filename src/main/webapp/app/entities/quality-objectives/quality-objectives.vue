<template>
  <div>
    <h2 id="page-heading" data-cy="QualityObjectivesHeading">
      <span v-text="t$('jy1App.qualityObjectives.home.title')" id="quality-objectives-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.qualityObjectives.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'QualityObjectivesCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-quality-objectives"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.qualityObjectives.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && qualityObjectives && qualityObjectives.length === 0">
      <span v-text="t$('jy1App.qualityObjectives.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="qualityObjectives && qualityObjectives.length > 0">
      <table class="table table-striped" aria-describedby="qualityObjectives">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityObjectives.name')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityObjectives.objectives')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityObjectives.qualitytype')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityObjectives.secretlevel')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityObjectives.target')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityObjectives.statisticalmethod')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityObjectives.statisticalfrequency')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityObjectives.istarget')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityObjectives.progress')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityObjectives.description')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityObjectives.problems')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityObjectives.improvementmeasures')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityObjectives.returntime')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityObjectives.createtime')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityObjectives.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityObjectives.responsibleperson')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityObjectives.auditorid')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityObjectives.projectwbs')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityObjectives.qualityReturns')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="qualityObjectives in qualityObjectives" :key="qualityObjectives.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'QualityObjectivesView', params: { qualityObjectivesId: qualityObjectives.id } }">{{
                qualityObjectives.id
              }}</router-link>
            </td>
            <td>{{ qualityObjectives.name }}</td>
            <td>{{ qualityObjectives.objectives }}</td>
            <td v-text="t$('jy1App.QualityType.' + qualityObjectives.qualitytype)"></td>
            <td v-text="t$('jy1App.Secretlevel.' + qualityObjectives.secretlevel)"></td>
            <td>{{ qualityObjectives.target }}</td>
            <td>{{ qualityObjectives.statisticalmethod }}</td>
            <td>{{ qualityObjectives.statisticalfrequency }}</td>
            <td>{{ qualityObjectives.istarget }}</td>
            <td>{{ qualityObjectives.progress }}</td>
            <td>{{ qualityObjectives.description }}</td>
            <td>{{ qualityObjectives.problems }}</td>
            <td>{{ qualityObjectives.improvementmeasures }}</td>
            <td>{{ qualityObjectives.returntime }}</td>
            <td>{{ qualityObjectives.createtime }}</td>
            <td v-text="t$('jy1App.AuditStatus.' + qualityObjectives.auditStatus)"></td>
            <td>
              <div v-if="qualityObjectives.responsibleperson">
                <router-link :to="{ name: 'OfficersView', params: { officersId: qualityObjectives.responsibleperson.id } }">{{
                  qualityObjectives.responsibleperson.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="qualityObjectives.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: qualityObjectives.auditorid.id } }">{{
                  qualityObjectives.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <span v-for="(projectwbs, i) in qualityObjectives.projectwbs" :key="projectwbs.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'ProjectwbsView', params: { projectwbsId: projectwbs.id } }">{{
                  projectwbs.id
                }}</router-link>
              </span>
            </td>
            <td>
              <span v-for="(qualityReturns, i) in qualityObjectives.qualityReturns" :key="qualityReturns.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{ name: 'QualityReturnsView', params: { qualityReturnsId: qualityReturns.id } }"
                  >{{ qualityReturns.id }}</router-link
                >
              </span>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'QualityObjectivesView', params: { qualityObjectivesId: qualityObjectives.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'QualityObjectivesEdit', params: { qualityObjectivesId: qualityObjectives.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(qualityObjectives)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="t$('entity.action.delete')"></span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
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
