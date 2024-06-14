<template>
  <div>
    <h2 id="page-heading" data-cy="TechnicalConditionHeading">
      <span v-text="t$('jHipster3App.technicalCondition.home.title')" id="technical-condition-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster3App.technicalCondition.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'TechnicalConditionCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-technical-condition"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster3App.technicalCondition.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && technicalConditions && technicalConditions.length === 0">
      <span v-text="t$('jHipster3App.technicalCondition.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="technicalConditions && technicalConditions.length > 0">
      <table class="table table-striped" aria-describedby="technicalConditions">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.technicalCondition.caption')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.technicalCondition.projectname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.technicalCondition.decumentid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.technicalCondition.claimant')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.technicalCondition.applicant')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.technicalCondition.applicanttime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.technicalCondition.validrange')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.technicalCondition.createtime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.technicalCondition.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.technicalCondition.creatorid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.technicalCondition.auditorid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="technicalCondition in technicalConditions" :key="technicalCondition.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'TechnicalConditionView', params: { technicalConditionId: technicalCondition.id } }">{{
                technicalCondition.id
              }}</router-link>
            </td>
            <td>{{ technicalCondition.caption }}</td>
            <td>{{ technicalCondition.projectname }}</td>
            <td>{{ technicalCondition.decumentid }}</td>
            <td>{{ technicalCondition.claimant }}</td>
            <td>{{ technicalCondition.applicant }}</td>
            <td>{{ technicalCondition.applicanttime }}</td>
            <td>{{ technicalCondition.validrange }}</td>
            <td>{{ technicalCondition.createtime }}</td>
            <td v-text="t$('jHipster3App.AuditStatus.' + technicalCondition.auditStatus)"></td>
            <td>
              <div v-if="technicalCondition.creatorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: technicalCondition.creatorid.id } }">{{
                  technicalCondition.creatorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="technicalCondition.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: technicalCondition.auditorid.id } }">{{
                  technicalCondition.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'TechnicalConditionView', params: { technicalConditionId: technicalCondition.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'TechnicalConditionEdit', params: { technicalConditionId: technicalCondition.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(technicalCondition)"
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
          id="jHipster3App.technicalCondition.delete.question"
          data-cy="technicalConditionDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-technicalCondition-heading" v-text="t$('jHipster3App.technicalCondition.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-technicalCondition"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeTechnicalCondition()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./technical-condition.component.ts"></script>
