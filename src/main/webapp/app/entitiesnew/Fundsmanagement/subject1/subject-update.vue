<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.subject.home.createOrEditLabel"
          data-cy="SubjectCreateUpdateHeading"
          v-text="t$('jy1App.subject.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="subject.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <el-input type="text" class="form-control" id="id" name="id" v-model="subject.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.subject.name')" for="subject-name"></label>
            <el-input
              type="text"
              class="form-control"
              name="name"
              id="subject-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.subject.type')" for="subject-type"></label>
            <el-input
              type="text"
              class="form-control"
              name="type"
              id="subject-type"
              data-cy="type"
              :class="{ valid: !v$.type.$invalid, invalid: v$.type.$invalid }"
              v-model="v$.type.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.subject.parentid')" for="subject-parentid"></label>
            <el-input
              type="text"
              class="form-control"
              name="parentid"
              id="subject-parentid"
              data-cy="parentid"
              :class="{ valid: !v$.parentid.$invalid, invalid: v$.parentid.$invalid }"
              v-model="v$.parentid.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.subject.remark')" for="subject-remark"></label>
            <el-input
              type="text"
              class="form-control"
              name="remark"
              id="subject-remark"
              data-cy="remark"
              :class="{ valid: !v$.remark.$invalid, invalid: v$.remark.$invalid }"
              v-model="v$.remark.$model"
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
<script lang="ts" src="./subject-update.component.ts"></script>
