<template>
  <div>
    <h2 id="page-heading" data-cy="PbsmanageHeading">
      <span v-text="t$('jHipster0App.pbsmanage.home.title')" id="pbsmanage-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.pbsmanage.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'PbsmanageCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-pbsmanage"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.pbsmanage.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && pbsmanages && pbsmanages.length === 0">
      <span v-text="t$('jHipster0App.pbsmanage.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="pbsmanages && pbsmanages.length > 0">
      <table class="table table-striped" aria-describedby="pbsmanages">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.pbsmanage.pbsname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.pbsmanage.number')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.pbsmanage.type')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.pbsmanage.starttime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.pbsmanage.endtime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.pbsmanage.administratorid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.pbsmanage.administratorname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.pbsmanage.responsiblename')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.pbsmanage.department')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.pbsmanage.secretlevel')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.pbsmanage.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.pbsmanage.auditUserid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.pbsmanage.pbssubmanage')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.pbsmanage.responsibleid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.pbsmanage.auditorid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="pbsmanage in pbsmanages" :key="pbsmanage.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'PbsmanageView', params: { pbsmanageId: pbsmanage.id } }">{{ pbsmanage.id }}</router-link>
            </td>
            <td>{{ pbsmanage.pbsname }}</td>
            <td>{{ pbsmanage.number }}</td>
            <td>{{ pbsmanage.type }}</td>
            <td>{{ pbsmanage.starttime }}</td>
            <td>{{ pbsmanage.endtime }}</td>
            <td>{{ pbsmanage.administratorid }}</td>
            <td>{{ pbsmanage.administratorname }}</td>
            <td>{{ pbsmanage.responsiblename }}</td>
            <td>{{ pbsmanage.department }}</td>
            <td v-text="t$('jHipster0App.Secretlevel.' + pbsmanage.secretlevel)"></td>
            <td v-text="t$('jHipster0App.AuditStatus.' + pbsmanage.auditStatus)"></td>
            <td>{{ pbsmanage.auditUserid }}</td>
            <td>
              <div v-if="pbsmanage.pbssubmanage">
                <router-link :to="{ name: 'PbssubmanageView', params: { pbssubmanageId: pbsmanage.pbssubmanage.id } }">{{
                  pbsmanage.pbssubmanage.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="pbsmanage.responsibleid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: pbsmanage.responsibleid.id } }">{{
                  pbsmanage.responsibleid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="pbsmanage.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: pbsmanage.auditorid.id } }">{{
                  pbsmanage.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'PbsmanageView', params: { pbsmanageId: pbsmanage.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'PbsmanageEdit', params: { pbsmanageId: pbsmanage.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(pbsmanage)"
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
        <span id="jHipster0App.pbsmanage.delete.question" data-cy="pbsmanageDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-pbsmanage-heading" v-text="t$('jHipster0App.pbsmanage.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-pbsmanage"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removePbsmanage()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./pbsmanage.component.ts"></script>
