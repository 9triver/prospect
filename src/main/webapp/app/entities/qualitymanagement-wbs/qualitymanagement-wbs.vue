<template>
  <div>
    <h2 id="page-heading" data-cy="QualitymanagementWbsHeading">
      <span v-text="t$('jHipster0App.qualitymanagementWbs.home.title')" id="qualitymanagement-wbs-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.qualitymanagementWbs.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'QualitymanagementWbsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-qualitymanagement-wbs"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.qualitymanagementWbs.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && qualitymanagementWbs && qualitymanagementWbs.length === 0">
      <span v-text="t$('jHipster0App.qualitymanagementWbs.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="qualitymanagementWbs && qualitymanagementWbs.length > 0">
      <table class="table table-striped" aria-describedby="qualitymanagementWbs">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.qualitymanagementWbs.wbsspare1')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.qualitymanagementWbs.wbsspare2')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.qualitymanagementWbs.wbsspare3')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.qualitymanagementWbs.wbsspare4')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.qualitymanagementWbs.wbsspare5')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.qualitymanagementWbs.qualityobjectives')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.qualitymanagementWbs.qualityreturns')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.qualitymanagementWbs.unQualityAudit')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="qualitymanagementWbs in qualitymanagementWbs" :key="qualitymanagementWbs.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'QualitymanagementWbsView', params: { qualitymanagementWbsId: qualitymanagementWbs.id } }">{{
                qualitymanagementWbs.id
              }}</router-link>
            </td>
            <td>{{ qualitymanagementWbs.wbsspare1 }}</td>
            <td>{{ qualitymanagementWbs.wbsspare2 }}</td>
            <td>{{ qualitymanagementWbs.wbsspare3 }}</td>
            <td>{{ qualitymanagementWbs.wbsspare4 }}</td>
            <td>{{ qualitymanagementWbs.wbsspare5 }}</td>
            <td>
              <div v-if="qualitymanagementWbs.qualityobjectives">
                <router-link
                  :to="{ name: 'QualityobjectivesView', params: { qualityobjectivesId: qualitymanagementWbs.qualityobjectives.id } }"
                  >{{ qualitymanagementWbs.qualityobjectives.id }}</router-link
                >
              </div>
            </td>
            <td>
              <div v-if="qualitymanagementWbs.qualityreturns">
                <router-link :to="{ name: 'QualityreturnsView', params: { qualityreturnsId: qualitymanagementWbs.qualityreturns.id } }">{{
                  qualitymanagementWbs.qualityreturns.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="qualitymanagementWbs.unQualityAudit">
                <router-link :to="{ name: 'UnQualityAuditView', params: { unQualityAuditId: qualitymanagementWbs.unQualityAudit.id } }">{{
                  qualitymanagementWbs.unQualityAudit.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'QualitymanagementWbsView', params: { qualitymanagementWbsId: qualitymanagementWbs.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'QualitymanagementWbsEdit', params: { qualitymanagementWbsId: qualitymanagementWbs.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(qualitymanagementWbs)"
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
          id="jHipster0App.qualitymanagementWbs.delete.question"
          data-cy="qualitymanagementWbsDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-qualitymanagementWbs-heading"
          v-text="t$('jHipster0App.qualitymanagementWbs.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-qualitymanagementWbs"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeQualitymanagementWbs()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./qualitymanagement-wbs.component.ts"></script>
