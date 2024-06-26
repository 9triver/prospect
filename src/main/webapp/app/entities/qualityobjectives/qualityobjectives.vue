<template>
  <div>
    <h2 id="page-heading" data-cy="QualityobjectivesHeading">
      <span v-text="t$('jHipster0App.qualityobjectives.home.title')" id="qualityobjectives-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jHipster0App.qualityobjectives.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'QualityobjectivesCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-qualityobjectives"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jHipster0App.qualityobjectives.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && qualityobjectives && qualityobjectives.length === 0">
      <span v-text="t$('jHipster0App.qualityobjectives.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="qualityobjectives && qualityobjectives.length > 0">
      <table class="table table-striped" aria-describedby="qualityobjectives">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.qualityobjectives.qualityobjectivesname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.qualityobjectives.year')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.qualityobjectives.createtime')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.qualityobjectives.creatorname')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.qualityobjectives.secretlevel')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.qualityobjectives.auditStatus')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.qualityobjectives.qualityreturns')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.qualityobjectives.creatorid')"></span></th>
            <th scope="row"><span v-text="t$('jHipster0App.qualityobjectives.auditorid')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="qualityobjectives in qualityobjectives" :key="qualityobjectives.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'QualityobjectivesView', params: { qualityobjectivesId: qualityobjectives.id } }">{{
                qualityobjectives.id
              }}</router-link>
            </td>
            <td>{{ qualityobjectives.qualityobjectivesname }}</td>
            <td>{{ qualityobjectives.year }}</td>
            <td>{{ qualityobjectives.createtime }}</td>
            <td>{{ qualityobjectives.creatorname }}</td>
            <td v-text="t$('jHipster0App.Secretlevel.' + qualityobjectives.secretlevel)"></td>
            <td v-text="t$('jHipster0App.AuditStatus.' + qualityobjectives.auditStatus)"></td>
            <td>
              <div v-if="qualityobjectives.qualityreturns">
                <router-link :to="{ name: 'QualityreturnsView', params: { qualityreturnsId: qualityobjectives.qualityreturns.id } }">{{
                  qualityobjectives.qualityreturns.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="qualityobjectives.creatorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: qualityobjectives.creatorid.id } }">{{
                  qualityobjectives.creatorid.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="qualityobjectives.auditorid">
                <router-link :to="{ name: 'OfficersView', params: { officersId: qualityobjectives.auditorid.id } }">{{
                  qualityobjectives.auditorid.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'QualityobjectivesView', params: { qualityobjectivesId: qualityobjectives.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'QualityobjectivesEdit', params: { qualityobjectivesId: qualityobjectives.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(qualityobjectives)"
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
          id="jHipster0App.qualityobjectives.delete.question"
          data-cy="qualityobjectivesDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-qualityobjectives-heading" v-text="t$('jHipster0App.qualityobjectives.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-qualityobjectives"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeQualityobjectives()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./qualityobjectives.component.ts"></script>
