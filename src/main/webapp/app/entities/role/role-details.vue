<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="role">
        <h2 class="jh-entity-heading" data-cy="roleDetailsHeading"><span v-text="t$('jy1App.role.detail.title')"></span> {{ role.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jy1App.role.rolename')"></span>
          </dt>
          <dd>
            <span>{{ role.rolename }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.role.description')"></span>
          </dt>
          <dd>
            <span>{{ role.description }}</span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.role.permission')"></span>
          </dt>
          <dd>
            <span v-for="(permission, i) in role.permissions" :key="permission.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'PermissionView', params: { permissionId: permission.id } }">{{ permission.id }}</router-link>
            </span>
          </dd>
          <dt>
            <span v-text="t$('jy1App.role.officers')"></span>
          </dt>
          <dd>
            <span v-for="(officers, i) in role.officers" :key="officers.id"
              >{{ i > 0 ? ', ' : '' }}
              <router-link :to="{ name: 'OfficersView', params: { officersId: officers.id } }">{{ officers.id }}</router-link>
            </span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="role.id" :to="{ name: 'RoleEdit', params: { roleId: role.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./role-details.component.ts"></script>
