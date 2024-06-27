<template>
  <div>
    <h2 id="page-heading" data-cy="OfficersHeading">
      <span v-text="t$('jHipster0App.officers.home.title')" id="officers-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.officers.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'OfficersCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-officers"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.officers.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && officers && officers.length === 0">
      <span v-text="t$('jHipster0App.officers.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="officers && officers.length > 0">
      <table class="table table-striped" aria-describedby="officers">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.officers.officersname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.officers.password')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.officers.email')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.officers.phone')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.officers.role')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.officers.department')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="officers in officers" :key="officers.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'OfficersView', params: { officersId: officers.id } }">{{ officers.id }}</router-link>
            </td>
            <td>{{ officers.officersname }}</td>
            <td>{{ officers.password }}</td>
            <td>{{ officers.email }}</td>
            <td>{{ officers.phone }}</td>
            <td>
              <div v-if="officers.role">
                <router-link :to="{ name: 'RoleView', params: { roleId: officers.role.id } }">{{ officers.role.id }}</router-link>
              </div>
            </td>
            <td>
              <span v-for="(department, i) in officers.departments" :key="department.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'DepartmentView', params: { departmentId: department.id } }">{{
                  department.departmentname
                }}</router-link>
              </span>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'OfficersView', params: { officersId: officers.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'OfficersEdit', params: { officersId: officers.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(officers)"
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
        <span id="jHipster0App.officers.delete.question" data-cy="officersDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-officers-heading" v-text="t$('jHipster0App.officers.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-officers"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeOfficers()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./officers.component.ts"></script>
