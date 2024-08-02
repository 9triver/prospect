<template>
  <div>
    <h2 id="page-heading" data-cy="QualityReturnsHeading">
      <span v-text="t$('jy1App.qualityReturns.home.title')" id="quality-returns-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.qualityReturns.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'QualityReturnsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-quality-returns"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.qualityReturns.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && qualityReturns && qualityReturns.length === 0">
      <span v-text="t$('jy1App.qualityReturns.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="qualityReturns && qualityReturns.length > 0">
      <table class="table table-striped" aria-describedby="qualityReturns">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityReturns.name')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityReturns.objectives')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityReturns.qualitytype')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityReturns.secretlevel')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityReturns.target')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityReturns.statisticalmethod')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityReturns.statisticalfrequency')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityReturns.istarget')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityReturns.progress')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityReturns.description')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityReturns.problems')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityReturns.improvementmeasures')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityReturns.returntime')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityReturns.createtime')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityReturns.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityReturns.responsibleperson')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityReturns.auditorid')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityReturns.creatorid')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.qualityReturns.qualityObjectives')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="qualityReturns in qualityReturns" :key="qualityReturns.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'QualityReturnsView', params: { qualityReturnsId: qualityReturns.id } }">{{
                qualityReturns.id
              }}</router-link>
            </td>
            <td>{{ qualityReturns.name }}</td>
            <td>{{ qualityReturns.objectives }}</td>
            <td v-text="t$('jy1App.QualityType.' + qualityReturns.qualitytype)"></td>
            <td v-text="t$('jy1App.Secretlevel.' + qualityReturns.secretlevel)"></td>
            <td>{{ qualityReturns.target }}</td>
            <td>{{ qualityReturns.statisticalmethod }}</td>
            <td>{{ qualityReturns.statisticalfrequency }}</td>
            <td>{{ qualityReturns.istarget }}</td>
            <td>{{ qualityReturns.progress }}</td>
            <td>{{ qualityReturns.description }}</td>
            <td>{{ qualityReturns.problems }}</td>
            <td>{{ qualityReturns.improvementmeasures }}</td>
            <td>{{ qualityReturns.returntime }}</td>
            <td>{{ qualityReturns.createtime }}</td>
            <td v-text="t$('jy1App.AuditStatus.' + qualityReturns.auditStatus)"></td>
            <td>
              <div v-if="qualityReturns.responsibleperson">
                <router-link :to="{ name: 'OfficersView', params: { officersId: qualityReturns.responsibleperson.id } }">{{
                  qualityReturns.responsibleperson.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="qualityReturns.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: qualityReturns.auditorid.id } }">{{
                  qualityReturns.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="qualityReturns.creatorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: qualityReturns.creatorid.id } }">{{
                  qualityReturns.creatorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <span v-for="(qualityObjectives, i) in qualityReturns.qualityObjectives" :key="qualityObjectives.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link
                  class="form-control-static"
                  :to="{ name: 'QualityObjectivesView', params: { qualityObjectivesId: qualityObjectives.id } }"
                  >{{ qualityObjectives.id }}</router-link
                >
              </span>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'QualityReturnsView', params: { qualityReturnsId: qualityReturns.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'QualityReturnsEdit', params: { qualityReturnsId: qualityReturns.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(qualityReturns)"
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
