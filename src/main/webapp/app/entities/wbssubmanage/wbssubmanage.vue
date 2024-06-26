<template>
  <div>
    <h2 id="page-heading" data-cy="WbssubmanageHeading">
      <span v-text="t$('jHipster0App.wbssubmanage.home.title')" id="wbssubmanage-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.wbssubmanage.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'WbssubmanageCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-wbssubmanage"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.wbssubmanage.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && wbssubmanages && wbssubmanages.length === 0">
      <span v-text="t$('jHipster0App.wbssubmanage.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="wbssubmanages && wbssubmanages.length > 0">
      <table class="table table-striped" aria-describedby="wbssubmanages">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.wbssubmanage.pbssubname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.wbssubmanage.responsiblename')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.wbssubmanage.responsibledepartment')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.wbssubmanage.relevantdepartment')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.wbssubmanage.type')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.wbssubmanage.starttime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.wbssubmanage.endtime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.wbssubmanage.secretlevel')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.wbssubmanage.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.wbssubmanage.responsibleid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.wbssubmanage.auditorid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="wbssubmanage in wbssubmanages" :key="wbssubmanage.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'WbssubmanageView', params: { wbssubmanageId: wbssubmanage.id } }">{{
                wbssubmanage.id
              }}</router-link>
            </td>
            <td>{{ wbssubmanage.pbssubname }}</td>
            <td>{{ wbssubmanage.responsiblename }}</td>
            <td>{{ wbssubmanage.responsibledepartment }}</td>
            <td>{{ wbssubmanage.relevantdepartment }}</td>
            <td>{{ wbssubmanage.type }}</td>
            <td>{{ wbssubmanage.starttime }}</td>
            <td>{{ wbssubmanage.endtime }}</td>
            <td v-text="t$('jHipster0App.Secretlevel.' + wbssubmanage.secretlevel)"></td>
            <td v-text="t$('jHipster0App.AuditStatus.' + wbssubmanage.auditStatus)"></td>
            <td>
              <div v-if="wbssubmanage.responsibleid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: wbssubmanage.responsibleid.id } }">{{
                  wbssubmanage.responsibleid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="wbssubmanage.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: wbssubmanage.auditorid.id } }">{{
                  wbssubmanage.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'WbssubmanageView', params: { wbssubmanageId: wbssubmanage.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'WbssubmanageEdit', params: { wbssubmanageId: wbssubmanage.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(wbssubmanage)"
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
          id="jHipster0App.wbssubmanage.delete.question"
          data-cy="wbssubmanageDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-wbssubmanage-heading" v-text="t$('jHipster0App.wbssubmanage.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-wbssubmanage"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeWbssubmanage()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./wbssubmanage.component.ts"></script>
