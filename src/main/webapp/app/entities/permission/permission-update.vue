<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" novalidate v-on:submit.prevent="save()">
        <h2
          id="jy1App.permission.home.createOrEditLabel"
          data-cy="PermissionCreateUpdateHeading"
          v-text="t$('jy1App.permission.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="permission.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="permission.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.permission.permissionname')" for="permission-permissionname"></label>
            <input
              type="text"
              class="form-control"
              name="permissionname"
              id="permission-permissionname"
              data-cy="permissionname"
              :class="{ valid: !v$.permissionname.$invalid, invalid: v$.permissionname.$invalid }"
              v-model="v$.permissionname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('jy1App.permission.description')" for="permission-description"></label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="permission-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
            />
          </div>
          <div class="form-group">
            <label v-text="t$('jy1App.permission.role')" for="permission-role"></label>
            <select
              class="form-control"
              id="permission-roles"
              data-cy="role"
              multiple
              name="role"
              v-if="permission.roles !== undefined"
              v-model="permission.roles"
            >
              <option v-bind:value="getSelected(permission.roles, roleOption, 'id')" v-for="roleOption in roles" :key="roleOption.id">
                {{ roleOption.id }}
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
<script lang="ts" src="./permission-update.component.ts"></script>
