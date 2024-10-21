<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.deliverables.home.createOrEditLabel"
          data-cy="DeliverablesCreateUpdateHeading"
          v-text="t$('jy1App.deliverables.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="deliverables.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="deliverables.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.deliverables.code')" for="deliverables-code"></label>
            <el-input
              type="text"
              class="form-control"
              name="code"
              id="deliverables-code"
              data-cy="code"
              :class="{ valid: !v$.code.$invalid, invalid: v$.code.$invalid }"
              v-model="v$.code.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.deliverables.name')" for="deliverables-name"></label>
            <el-input
              type="text"
              class="form-control"
              name="name"
              id="deliverables-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.deliverables.parentcode')" for="deliverables-parentcode"></label>
            <el-input
              type="text"
              class="form-control"
              name="parentcode"
              id="deliverables-parentcode"
              data-cy="parentcode"
              :class="{ valid: !v$.parentcode.$invalid, invalid: v$.parentcode.$invalid }"
              v-model="v$.parentcode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.deliverables.level')" for="deliverables-level"></label>
            <el-input
              type="text"
              class="form-control"
              name="level"
              id="deliverables-level"
              data-cy="level"
              :class="{ valid: !v$.level.$invalid, invalid: v$.level.$invalid }"
              v-model="v$.level.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.deliverables.status')" for="deliverables-status"></label>
            <el-input
              type="text"
              class="form-control"
              name="status"
              id="deliverables-status"
              data-cy="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.deliverables.description')" for="deliverables-description"></label>
            <el-input
              type="text"
              class="form-control"
              name="description"
              id="deliverables-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
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
<script lang="ts" src="./deliverables-update.component.ts"></script>
