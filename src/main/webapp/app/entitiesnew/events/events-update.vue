<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.events.home.createOrEditLabel"
          data-cy="EventsCreateUpdateHeading"
          v-text="t$('jy1App.events.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="events.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="events.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.events.title')" for="events-title"></label>
            <el-input
              type="text"
              class="form-control"
              name="title"
              id="events-title"
              data-cy="title"
              :class="{ valid: !v$.title.$invalid, invalid: v$.title.$invalid }"
              v-model="v$.title.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.events.content')" for="events-content"></label>
            <el-input
              type="text"
              class="form-control"
              name="content"
              id="events-content"
              data-cy="content"
              :class="{ valid: !v$.content.$invalid, invalid: v$.content.$invalid }"
              v-model="v$.content.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.events.time')" for="events-time"></label>
            <el-date-picker v-model="v$.time.$model" type="date" placeholder="" />
            <div></div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.events.place')" for="events-place"></label>
            <el-input
              type="text"
              class="form-control"
              name="place"
              id="events-place"
              data-cy="place"
              :class="{ valid: !v$.place.$invalid, invalid: v$.place.$invalid }"
              v-model="v$.place.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.events.participants')" for="events-participants"></label>
            <el-input
              type="text"
              class="form-control"
              name="participants"
              id="events-participants"
              data-cy="participants"
              :class="{ valid: !v$.participants.$invalid, invalid: v$.participants.$invalid }"
              v-model="v$.participants.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.events.picture')" for="events-picture"></label>
            <el-input
              type="text"
              class="form-control"
              name="picture"
              id="events-picture"
              data-cy="picture"
              :class="{ valid: !v$.picture.$invalid, invalid: v$.picture.$invalid }"
              v-model="v$.picture.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.events.description')" for="events-description"></label>
            <el-input
              type="text"
              class="form-control"
              name="description"
              id="events-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.events.secretlevel')" for="events-secretlevel"></label>
            <el-select
              collapse-tags
              value-key="id"
              class="form-control"
              name="secretlevel"
              :class="{ valid: !v$.secretlevel.$invalid, invalid: v$.secretlevel.$invalid }"
              v-model="v$.secretlevel.$model"
              id="events-secretlevel"
              data-cy="secretlevel"
            >
              <el-option
                v-for="secretlevel in secretlevelValues"
                :key="secretlevel"
                v-bind:value="secretlevel"
                v-bind:label="t$('jy1App.Secretlevel.' + secretlevel)"
                >{{ secretlevel }}</el-option
              >
            </el-select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.events.attachment')" for="events-attachment"></label>
            <el-input
              type="text"
              class="form-control"
              name="attachment"
              id="events-attachment"
              data-cy="attachment"
              :class="{ valid: !v$.attachment.$invalid, invalid: v$.attachment.$invalid }"
              v-model="v$.attachment.$model"
            />
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.cancel')"></span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="v$.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.save')"></span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./events-update.component.ts"></script>
