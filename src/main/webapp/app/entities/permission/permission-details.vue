<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="permission">
        <h2 class="jh-entity-heading" data-cy="permissionDetailsHeading">
          <span v-text="t$('jy1App.permission.detail.title')"></span> {{ permission.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jy1App.permission.permissionname')"></span>
          </dt>
          <dd>
            <span>{{ permission.permissionname }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.permission.description')"></span>
          </dt>
          <dd>
            <span>{{ permission.description }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.permission.role')"></span>
          </dt>
          <dd>
            <span v-for="(role, i) in permission.roles" :key="role.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'RoleView', params: { roleId: role.id } }">{{ role.id }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="permission.id"
          :to="{ name: 'PermissionEdit', params: { permissionId: permission.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./permission-details.component.ts"></script>
