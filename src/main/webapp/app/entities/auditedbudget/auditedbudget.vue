<template>
  <div>
    <h2 id="page-heading" data-cy="AuditedbudgetHeading">
      <span v-text="t$('jHipster0App.auditedbudget.home.title')" id="auditedbudget-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.auditedbudget.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'AuditedbudgetCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-auditedbudget"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.auditedbudget.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && auditedbudgets && auditedbudgets.length === 0">
      <span v-text="t$('jHipster0App.auditedbudget.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="auditedbudgets && auditedbudgets.length > 0">
      <table class="table table-striped" aria-describedby="auditedbudgets">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.auditedbudget.createtime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.auditedbudget.creatorname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.auditedbudget.secretlevel')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.auditedbudget.year')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.auditedbudget.budgit')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.auditedbudget.dapartmentid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.auditedbudget.draftapproval')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.auditedbudget.totalbudgetid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.auditedbudget.unitbudgetid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.auditedbudget.documentid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.auditedbudget.maintainerid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.auditedbudget.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.auditedbudget.totalbudget')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.auditedbudget.unitbudget')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.auditedbudget.document')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.auditedbudget.creatorid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.auditedbudget.auditorid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="auditedbudget in auditedbudgets" :key="auditedbudget.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'AuditedbudgetView', params: { auditedbudgetId: auditedbudget.id } }">{{
                auditedbudget.id
              }}</router-link>
            </td>
            <td>{{ auditedbudget.createtime }}</td>
            <td>{{ auditedbudget.creatorname }}</td>
            <td v-text="t$('jHipster0App.Secretlevel.' + auditedbudget.secretlevel)"></td>
            <td>{{ auditedbudget.year }}</td>
            <td>{{ auditedbudget.budgit }}</td>
            <td>{{ auditedbudget.dapartmentid }}</td>
            <td>{{ auditedbudget.draftapproval }}</td>
            <td>{{ auditedbudget.totalbudgetid }}</td>
            <td>{{ auditedbudget.unitbudgetid }}</td>
            <td>{{ auditedbudget.documentid }}</td>
            <td>{{ auditedbudget.maintainerid }}</td>
            <td v-text="t$('jHipster0App.AuditStatus.' + auditedbudget.auditStatus)"></td>
            <td>
              <div v-if="auditedbudget.totalbudget">
                <router-link :to="{ name: 'TotalbudgetView', params: { totalbudgetId: auditedbudget.totalbudget.id } }">{{
                  auditedbudget.totalbudget.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="auditedbudget.unitbudget">
                <router-link :to="{ name: 'UnitbudgetView', params: { unitbudgetId: auditedbudget.unitbudget.id } }">{{
                  auditedbudget.unitbudget.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="auditedbudget.document">
                <router-link :to="{ name: 'DocumentView', params: { documentId: auditedbudget.document.id } }">{{
                  auditedbudget.document.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="auditedbudget.creatorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: auditedbudget.creatorid.id } }">{{
                  auditedbudget.creatorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="auditedbudget.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: auditedbudget.auditorid.id } }">{{
                  auditedbudget.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'AuditedbudgetView', params: { auditedbudgetId: auditedbudget.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'AuditedbudgetEdit', params: { auditedbudgetId: auditedbudget.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(auditedbudget)"
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
          id="jHipster0App.auditedbudget.delete.question"
          data-cy="auditedbudgetDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-auditedbudget-heading" v-text="t$('jHipster0App.auditedbudget.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-auditedbudget"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeAuditedbudget()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./auditedbudget.component.ts"></script>
