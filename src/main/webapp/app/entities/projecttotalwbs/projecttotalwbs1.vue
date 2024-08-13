<template>
  <div>
    <h2 id="page-heading" data-cy="ProjectTotalwbsHeading">
      <span v-text="t$('jy1App.projectTotalwbs.home.title')" id="projecttotalwbs-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.projectTotalwbs.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ProjectTotalwbsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-projecttotalwbs"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.projectTotalwbs.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && projectTotalwbs && projectTotalwbs.length === 0">
      <span v-text="t$('jy1App.projectTotalwbs.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="projectTotalwbs && projectTotalwbs.length > 0">
      <table class="table table-striped" aria-describedby="projectTotalwbs">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectTotalwbs.wbsname')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectTotalwbs.parentwbsid')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectTotalwbs.pbsid')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectTotalwbs.description')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectTotalwbs.belongfront')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectTotalwbs.starttime')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectTotalwbs.endtime')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectTotalwbs.progress')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectTotalwbs.type')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectTotalwbs.priorty')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectTotalwbs.secretlevel')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectTotalwbs.deliverables')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectTotalwbs.status')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectTotalwbs.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectTotalwbs.workbag')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectTotalwbs.projectwbs')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectTotalwbs.responsibleperson')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectTotalwbs.technicaldirector')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectTotalwbs.administrativedirector')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectTotalwbs.knowingpeople')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectTotalwbs.auditorid')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectTotalwbs.responsibledepartment')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectTotalwbs.relevantdepartment')"></span></th>
            <th scope="row"><span v-text="t$('jy1App.projectTotalwbs.department')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="projectTotalwbs in projectTotalwbs" :key="projectTotalwbs.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ProjectTotalwbsView', params: { projectTotalwbsId: projectTotalwbs.id } }">{{
                projectTotalwbs.id
              }}</router-link>
            </td>
            <td>{{ projectTotalwbs.wbsname }}</td>
            <td>{{ projectTotalwbs.parentwbsid }}</td>
            <td>{{ projectTotalwbs.pbsid }}</td>
            <td>{{ projectTotalwbs.description }}</td>
            <td>{{ projectTotalwbs.belongfront }}</td>
            <td>{{ projectTotalwbs.starttime }}</td>
            <td>{{ projectTotalwbs.endtime }}</td>
            <td>{{ projectTotalwbs.progress }}</td>
            <td>{{ projectTotalwbs.type }}</td>
            <td>{{ projectTotalwbs.priorty }}</td>
            <td v-text="t$('jy1App.Secretlevel.' + projectTotalwbs.secretlevel)"></td>
            <td>{{ projectTotalwbs.deliverables }}</td>
            <td v-text="t$('jy1App.ProjectStatus.' + projectTotalwbs.status)"></td>
            <td v-text="t$('jy1App.AuditStatus.' + projectTotalwbs.auditStatus)"></td>
            <td>{{ projectTotalwbs.workbag }}</td>
            <td>
              <div v-if="projectTotalwbs.projectwbs">
                <router-link :to="{ name: 'ProjectwbsView', params: { projectwbsId: projectTotalwbs.projectwbs.id } }">{{
                  projectTotalwbs.projectwbs.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="projectTotalwbs.responsibleperson">
                <router-link :to="{ name: 'OfficersView', params: { officersId: projectTotalwbs.responsibleperson.id } }">{{
                  projectTotalwbs.responsibleperson.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="projectTotalwbs.technicaldirector">
                <router-link :to="{ name: 'OfficersView', params: { officersId: projectTotalwbs.technicaldirector.id } }">{{
                  projectTotalwbs.technicaldirector.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="projectTotalwbs.administrativedirector">
                <router-link :to="{ name: 'OfficersView', params: { officersId: projectTotalwbs.administrativedirector.id } }">{{
                  projectTotalwbs.administrativedirector.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="projectTotalwbs.knowingpeople">
                <router-link :to="{ name: 'OfficersView', params: { officersId: projectTotalwbs.knowingpeople.id } }">{{
                  projectTotalwbs.knowingpeople.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="projectTotalwbs.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: projectTotalwbs.auditorid.id } }">{{
                  projectTotalwbs.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="projectTotalwbs.responsibledepartment">
                <router-link :to="{ name: 'DepartmentView', params: { departmentId: projectTotalwbs.responsibledepartment.id } }">{{
                  projectTotalwbs.responsibledepartment.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="projectTotalwbs.relevantdepartment">
                <router-link :to="{ name: 'DepartmentView', params: { departmentId: projectTotalwbs.relevantdepartment.id } }">{{
                  projectTotalwbs.relevantdepartment.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="projectTotalwbs.department">
                <router-link :to="{ name: 'DepartmentView', params: { departmentId: projectTotalwbs.department.id } }">{{
                  projectTotalwbs.department.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'ProjectTotalwbsView', params: { projectTotalwbsId: projectTotalwbs.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'ProjectTotalwbsEdit', params: { projectTotalwbsId: projectTotalwbs.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(projectTotalwbs)"
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
          id="jy1App.projectTotalwbs.delete.question"
          data-cy="projectTotalwbsDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-projectTotalwbs-heading" v-text="t$('jy1App.projectTotalwbs.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-projectTotalwbs"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeProjectTotalwbs()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./projecttotalwbs.component.ts"></script>
