<template>
  <div>
    <h2 id="page-heading" data-cy="EventsHeading">
      <span v-text="t$('jy1App.events.home.title')" id="events-heading"></span>
      <div class="d-flex justify-content-end">
        <el-button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('jy1App.events.home.refreshListLabel')"></span>
        </el-button>
        <router-link :to="{ name: 'EventsCreate' }" custom v-slot="{ navigate }">
          <el-button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-events"
            type="primary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('jy1App.events.home.createLabel')"></span>
          </el-button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && events && events.length === 0">
      <span v-text="t$('jy1App.events.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="events && events.length > 0">
      <el-table :data="events" style="width: 100%" border stripe fit v-loading="isFetching">
        <el-table-column min-width="150px" show-overflow-tooltip prop="id" :label="t$('global.field.id')">
          <template #default="scope">
            <router-link :to="{ name: 'EventsView', params: { eventsId: scope.row.id } }">{{ scope.row.id }}</router-link>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="title" :label="t$('jy1App.events.title')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.title }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="content" :label="t$('jy1App.events.content')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.content }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="time" :label="t$('jy1App.events.time')" :sortable="true">
          <template #default="scope">
            <span class="field-default">{{ scope.row.time }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="place" :label="t$('jy1App.events.place')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.place }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="participants"
          :label="t$('jy1App.events.participants')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.participants }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip prop="picture" :label="t$('jy1App.events.picture')" :sortable="false">
          <template #default="scope">
            <span class="field-default">{{ scope.row.picture }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="description"
          :label="t$('jy1App.events.description')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.description }}</span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="secretlevel"
          :label="t$('jy1App.events.secretlevel')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-fieldIsEnum" v-text="t$('jy1App.Secretlevel.' + scope.row.secretlevel)"></span>
          </template>
        </el-table-column>
        <el-table-column
          min-width="150px"
          show-overflow-tooltip
          prop="attachment"
          :label="t$('jy1App.events.attachment')"
          :sortable="false"
        >
          <template #default="scope">
            <span class="field-default">{{ scope.row.attachment }}</span>
          </template>
        </el-table-column>
        <el-table-column min-width="150px" show-overflow-tooltip label="操作">
          <template #default="scope">
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'EventsView', params: { eventsId: scope.row.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'EventsEdit', params: { eventsId: scope.row.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(scope.row)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="trash"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="t$('entity.action.delete')"></span>
                </b-button>
              </div>
            </td>
          </template>
        </el-table-column>
      </el-table>
      <!-- <table class="table table-striped" aria-describedby="events">
                <thead>
                <tr>
                    <th scope="row"><span v-text="t$('global.field.id')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.events.title')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.events.content')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.events.time')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.events.place')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.events.participants')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.events.picture')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.events.description')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.events.secretlevel')"></span></th>
                    <th scope="row"><span v-text="t$('jy1App.events.attachment')"></span></th>
                    <th scope="row"></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="events in events"
                    :key="events.id" data-cy="entityTable">
                    <td>
                        <router-link :to="{name: 'EventsView', params: {eventsId: events.id}}">{{events.id}}</router-link>
                    </td>
                    <td>{{events.title}}</td>
                    <td>{{events.content}}</td>
                    <td>{{events.time}}</td>
                    <td>{{events.place}}</td>
                    <td>{{events.participants}}</td>
                    <td>{{events.picture}}</td>
                    <td>{{events.description}}</td>
                    <td v-text="t$('jy1App.Secretlevel.' + events.secretlevel)"></td>
                    <td>{{events.attachment}}</td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'EventsView', params: {eventsId: events.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                                    <font-awesome-icon icon="eye"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                                </button>
                            </router-link>
                            <router-link :to="{name: 'EventsEdit', params: {eventsId: events.id}}" custom v-slot="{ navigate }">
                                <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                                </button>
                            </router-link>
                            <b-button v-on:click="prepareRemove(events)"
                                   variant="danger"
                                   class="btn btn-sm"
                                   data-cy="entityDeleteButton"
                                   v-b-modal.removeEntity>
                                <font-awesome-icon icon="times"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="t$('entity.action.delete')"></span>
                            </b-button>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>-->
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <template #modal-title>
        <span id="jy1App.events.delete.question" data-cy="eventsDeleteDialogHeading" v-text="t$('entity.delete.title')"></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-events-heading" v-text="t$('jy1App.events.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-events"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeEvents()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./events.component.ts"></script>
