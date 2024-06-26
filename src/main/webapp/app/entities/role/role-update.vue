<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jHipster0App.role.home.createOrEditLabel"
          data-cy="RoleCreateUpdateHeading"
          v-text="t$('jHipster0App.role.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="role.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="role.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.role.rolename')" for="role-rolename"></label>
            <input
              type="text"
              class="form-control"
              name="rolename"
              id="role-rolename"
              data-cy="rolename"
              :class="{ valid: !v$.rolename.$invalid, invalid: v$.rolename.$invalid }"
              v-model="v$.rolename.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.role.description')" for="role-description"></label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="role-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jHipster0App.role.permission')" for="role-permission"></label>
            <select class="form-control" id="role-permission" data-cy="permission" name="permission" v-model="role.permission">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="role.permission && permissionOption.id === role.permission.id ? role.permission : permissionOption"
                v-for="permissionOption in permissions"
                :key="permissionOption.id"
              >
                {{ permissionOption.id }}
              </option>
            </select>
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
<script lang="ts" src="./role-update.component.ts"></script>
