<template>
  <div>
    <h2 id="page-heading" data-cy="RiskidentificationHeading">
      <span v-text="t$('jHipster0App.riskidentification.home.title')" id="riskidentification-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.riskidentification.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'RiskidentificationCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-riskidentification"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.riskidentification.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && riskidentifications && riskidentifications.length === 0">
      <span v-text="t$('jHipster0App.riskidentification.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="riskidentifications && riskidentifications.length > 0">
      <table class="table table-striped" aria-describedby="riskidentifications">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.riskidentification.projectname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.riskidentification.year')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.riskidentification.nodename')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.riskidentification.risktype')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.riskidentification.decumentid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.riskidentification.version')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.riskidentification.usetime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.riskidentification.systemlevel')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.riskidentification.risklevel')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.riskidentification.limitationtime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.riskidentification.closetype')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.riskidentification.creatorid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.riskidentification.responsibleid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.riskidentification.auditorid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="riskidentification in riskidentifications" :key="riskidentification.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'RiskidentificationView', params: { riskidentificationId: riskidentification.id } }">{{
                riskidentification.id
              }}</router-link>
            </td>
            <td>{{ riskidentification.projectname }}</td>
            <td>{{ riskidentification.year }}</td>
            <td>{{ riskidentification.nodename }}</td>
            <td>{{ riskidentification.risktype }}</td>
            <td>{{ riskidentification.decumentid }}</td>
            <td>{{ riskidentification.version }}</td>
            <td>{{ riskidentification.usetime }}</td>
            <td>{{ riskidentification.systemlevel }}</td>
            <td v-text="t$('jHipster0App.Risklevel.' + riskidentification.risklevel)"></td>
            <td>{{ riskidentification.limitationtime }}</td>
            <td>{{ riskidentification.closetype }}</td>
            <td>
              <div v-if="riskidentification.creatorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: riskidentification.creatorid.id } }">{{
                  riskidentification.creatorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="riskidentification.responsibleid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: riskidentification.responsibleid.id } }">{{
                  riskidentification.responsibleid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="riskidentification.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: riskidentification.auditorid.id } }">{{
                  riskidentification.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'RiskidentificationView', params: { riskidentificationId: riskidentification.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'RiskidentificationEdit', params: { riskidentificationId: riskidentification.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(riskidentification)"
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
          id="jHipster0App.riskidentification.delete.question"
          data-cy="riskidentificationDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-riskidentification-heading" v-text="t$('jHipster0App.riskidentification.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-riskidentification"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeRiskidentification()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./riskidentification.component.ts"></script>
