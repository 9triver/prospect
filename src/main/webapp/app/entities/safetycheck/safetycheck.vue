<template>
  <div>
    <h2 id="page-heading" data-cy="SafetycheckHeading">
      <span v-text="t$('jHipster3App.safetycheck.home.title')" id="safetycheck-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster3App.safetycheck.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'SafetycheckCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-safetycheck"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster3App.safetycheck.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && safetychecks && safetychecks.length === 0">
      <span v-text="t$('jHipster3App.safetycheck.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="safetychecks && safetychecks.length > 0">
      <table class="table table-striped" aria-describedby="safetychecks">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.safetycheck.safetycheckid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.safetycheck.safetycheckname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.safetycheck.checksource')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.safetycheck.checktime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.safetycheck.effectivetime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.safetycheck.operatinglocation')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.safetycheck.deprotment')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.safetycheck.phonenumber')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.safetycheck.risklevel')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.safetycheck.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.safetycheck.auditorid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster3App.safetycheck.responsibleid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="safetycheck in safetychecks" :key="safetycheck.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'SafetycheckView', params: { safetycheckId: safetycheck.id } }">{{ safetycheck.id }}</router-link>
            </td>
            <td>{{ safetycheck.safetycheckid }}</td>
            <td>{{ safetycheck.safetycheckname }}</td>
            <td>{{ safetycheck.checksource }}</td>
            <td>{{ safetycheck.checktime }}</td>
            <td>{{ safetycheck.effectivetime }}</td>
            <td>{{ safetycheck.operatinglocation }}</td>
            <td>{{ safetycheck.deprotment }}</td>
            <td>{{ safetycheck.phonenumber }}</td>
            <td v-text="t$('jHipster3App.Risklevel.' + safetycheck.risklevel)"></td>
            <td v-text="t$('jHipster3App.AuditStatus.' + safetycheck.auditStatus)"></td>
            <td>
              <div v-if="safetycheck.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: safetycheck.auditorid.id } }">{{
                  safetycheck.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="safetycheck.responsibleid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: safetycheck.responsibleid.id } }">{{
                  safetycheck.responsibleid.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'SafetycheckView', params: { safetycheckId: safetycheck.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'SafetycheckEdit', params: { safetycheckId: safetycheck.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(safetycheck)"
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
          id="jHipster3App.safetycheck.delete.question"
          data-cy="safetycheckDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-safetycheck-heading" v-text="t$('jHipster3App.safetycheck.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-safetycheck"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeSafetycheck()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./safetycheck.component.ts"></script>
