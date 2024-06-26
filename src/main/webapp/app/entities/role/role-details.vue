<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="role">
        <h2 class="jh-entity-heading" data-cy="roleDetailsHeading">
          <span v-text="t$('jHipster0App.role.detail.title')"></span> {{ role.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('jHipster0App.role.rolename')"></span>
          </dt>
          <dd>
            <span>{{ role.rolename }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.role.description')"></span>
          </dt>
          <dd>
            <span>{{ role.description }}</span>
          </dd>
          <dt>
            <span v-text="t$('jHipster0App.role.permission')"></span>
          </dt>
          <dd>
            <div v-if="role.permission">
              <router-link :to="{ name: 'PermissionView', params: { permissionId: role.permission.id } }">{{
                role.permission.id
              }}</router-link>
            </div>
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
