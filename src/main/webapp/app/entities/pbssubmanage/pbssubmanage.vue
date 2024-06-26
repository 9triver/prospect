<template>
  <div>
    <h2 id="page-heading" data-cy="PbssubmanageHeading">
      <span v-text="t$('jHipster0App.pbssubmanage.home.title')" id="pbssubmanage-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.pbssubmanage.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'PbssubmanageCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-pbssubmanage"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.pbssubmanage.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && pbssubmanages && pbssubmanages.length === 0">
      <span v-text="t$('jHipster0App.pbssubmanage.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="pbssubmanages && pbssubmanages.length > 0">
      <table class="table table-striped" aria-describedby="pbssubmanages">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.pbssubmanage.pbssubname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.pbssubmanage.responsiblename')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.pbssubmanage.responsibledepartment')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.pbssubmanage.relevantdepartment')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.pbssubmanage.type')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.pbssubmanage.starttime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.pbssubmanage.endtime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.pbssubmanage.secretlevel')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.pbssubmanage.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.pbssubmanage.responsibleid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.pbssubmanage.auditorid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="pbssubmanage in pbssubmanages" :key="pbssubmanage.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'PbssubmanageView', params: { pbssubmanageId: pbssubmanage.id } }">{{
                pbssubmanage.id
              }}</router-link>
            </td>
            <td>{{ pbssubmanage.pbssubname }}</td>
            <td>{{ pbssubmanage.responsiblename }}</td>
            <td>{{ pbssubmanage.responsibledepartment }}</td>
            <td>{{ pbssubmanage.relevantdepartment }}</td>
            <td>{{ pbssubmanage.type }}</td>
            <td>{{ pbssubmanage.starttime }}</td>
            <td>{{ pbssubmanage.endtime }}</td>
            <td v-text="t$('jHipster0App.Secretlevel.' + pbssubmanage.secretlevel)"></td>
            <td v-text="t$('jHipster0App.AuditStatus.' + pbssubmanage.auditStatus)"></td>
            <td>
              <div v-if="pbssubmanage.responsibleid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: pbssubmanage.responsibleid.id } }">{{
                  pbssubmanage.responsibleid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="pbssubmanage.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: pbssubmanage.auditorid.id } }">{{
                  pbssubmanage.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'PbssubmanageView', params: { pbssubmanageId: pbssubmanage.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'PbssubmanageEdit', params: { pbssubmanageId: pbssubmanage.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(pbssubmanage)"
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
          id="jHipster0App.pbssubmanage.delete.question"
          data-cy="pbssubmanageDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-pbssubmanage-heading" v-text="t$('jHipster0App.pbssubmanage.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-pbssubmanage"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removePbssubmanage()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./pbssubmanage.component.ts"></script>
