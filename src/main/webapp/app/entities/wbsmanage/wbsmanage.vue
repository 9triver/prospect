<template>
  <div>
    <h2 id="page-heading" data-cy="WbsmanageHeading">
      <span v-text="t$('jHipster3App.wbsmanage.home.title')" id="wbsmanage-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster3App.wbsmanage.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'WbsmanageCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-wbsmanage"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster3App.wbsmanage.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && wbsmanages && wbsmanages.length === 0">
      <span v-text="t$('jHipster3App.wbsmanage.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="wbsmanages && wbsmanages.length > 0">
      <table class="table table-striped" aria-describedby="wbsmanages">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.wbsmanage.wbsid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.wbsmanage.wbsname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.wbsmanage.description')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.wbsmanage.result')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.wbsmanage.administratorname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.wbsmanage.responsiblename')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.wbsmanage.department')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.wbsmanage.secretlevel')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.wbsmanage.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.wbsmanage.wbssubmanage')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.wbsmanage.pbssubmanage')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.wbsmanage.project')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.wbsmanage.administratorid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.wbsmanage.auditorid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.wbsmanage.responsibleid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="wbsmanage in wbsmanages" :key="wbsmanage.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'WbsmanageView', params: { wbsmanageId: wbsmanage.id } }">{{ wbsmanage.id }}</router-link>
            </td>
            <td>{{ wbsmanage.wbsid }}</td>
            <td>{{ wbsmanage.wbsname }}</td>
            <td>{{ wbsmanage.description }}</td>
            <td>{{ wbsmanage.result }}</td>
            <td>{{ wbsmanage.administratorname }}</td>
            <td>{{ wbsmanage.responsiblename }}</td>
            <td>{{ wbsmanage.department }}</td>
            <td v-text="t$('jHipster3App.Secretlevel.' + wbsmanage.secretlevel)"></td>
            <td v-text="t$('jHipster3App.AuditStatus.' + wbsmanage.auditStatus)"></td>
            <td>
              <div v-if="wbsmanage.wbssubmanage">
                <router-link :to="{ name: 'WbssubmanageView', params: { wbssubmanageId: wbsmanage.wbssubmanage.id } }">{{
                  wbsmanage.wbssubmanage.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="wbsmanage.pbssubmanage">
                <router-link :to="{ name: 'PbssubmanageView', params: { pbssubmanageId: wbsmanage.pbssubmanage.id } }">{{
                  wbsmanage.pbssubmanage.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="wbsmanage.project">
                <router-link :to="{ name: 'ProjectView', params: { projectId: wbsmanage.project.id } }">{{
                  wbsmanage.project.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="wbsmanage.administratorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: wbsmanage.administratorid.id } }">{{
                  wbsmanage.administratorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="wbsmanage.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: wbsmanage.auditorid.id } }">{{
                  wbsmanage.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="wbsmanage.responsibleid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: wbsmanage.responsibleid.id } }">{{
                  wbsmanage.responsibleid.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'WbsmanageView', params: { wbsmanageId: wbsmanage.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'WbsmanageEdit', params: { wbsmanageId: wbsmanage.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(wbsmanage)"
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
        <span id="jHipster3App.wbsmanage.delete.question" data-cy="wbsmanageDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-wbsmanage-heading" v-text="t$('jHipster3App.wbsmanage.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-wbsmanage"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeWbsmanage()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./wbsmanage.component.ts"></script>
